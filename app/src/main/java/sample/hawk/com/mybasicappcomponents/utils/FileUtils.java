package sample.hawk.com.mybasicappcomponents.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.support.v4.os.EnvironmentCompat;
import android.text.TextUtils;

import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Created by ha271 on 2017/3/7.
 *
 *  Different Manufacturers or Android OS especially 4.3(API 11 HONEYCOMB,18 JellyBean) ver uses different Paths
 *
 *  Consider these conditions:
 *  SD insert/remove
 *  OTG plug/unplug
 *
 * (1) Verified on HTC 10 (Android 7.0 > HONEYCOMB) with SD card inserted
 *     A. USBMassStorage\Internal shared storage\ is at mnt/sdcard/ or storage/emulated/0/
 *     B. USBMassStorage\SanDisk SD card\ is at sdcard/ or mnt/media_rw/375A-17F5/ or storage/375A-17F5/ or storage/ext_sd/
 *      HTC10 test result:
 *        getExternalSdCardPath=/storage/emulated/0 or /mnt/sdcard <-- If path not in list, the path will error.
 *        getExternalMounts=[/mnt/media_rw/375A-17F5] <-- SD card
 *        getStorageDirectories=/sdcard <-- SD card
 *        getSdCardPaths[0]=/storage/emulated <-- Not SD card
 *        getSdCardPaths[1]=/storage/375A-17F5 <-- SD card
 *
 * (2) Verified on Samsung Note2 (Android 4.4.2 < HONEYCOMB) with SD card inserted
 *     A. USBMassStorage\Phone\ is at sdcard/ or storage/emulated/legacy/ or mnt/sdcard/
 *     B. USBMassStorage\Card\ is at storage/extSdCard/ or mnt/extSdCard/
 *      Note2 test result:
 *        getExternalSdCardPath=/storage/emulated/0 <-- Also path in list, the path is error still.
 *        getExternalMounts=[/mnt/media_rw/extSdCard] <-- permission ERROR
 *        getStorageDirectories[0]=/storage/emulated/0 <-- No such path
 *        getStorageDirectories[1]=/storage/extSdCard <-- SD card
 *        getSdCardPaths[0]=/storage/emulated/0 <-- Not SD card
 *        getSdCardPaths[1]=/storage/extSdCard <-- SD card
 *
 *  Conclusion:
 *      You MUST check HONEYCOMB condition first.
 */

public class FileUtils {

    public FileUtils(Context context){
        SMLog.i("+++++++++++++++++++++++++");
        SMLog.i("getExternalSdCardPath="+getExternalSdCardPath());
        SMLog.i("getExternalMounts="+getExternalMounts());
        String[] dirs = getStorageDirectories();
        for( String dir: dirs){
            SMLog.i("getStorageDirectories="+dir);
        }
        List<String> paths = getSdCardPaths(context, true);
        for( String path: paths){
            SMLog.i("getSdCardPaths="+path);
        }
        SMLog.i("-------------------------");
    }

    // Hawk: it can't get sdcard actually sometimes.
    public static String getExternalSdCardPath() {
        String path = null;
        File sdCardFile = null;
        //
        List<String> sdCardPossiblePath = Arrays.asList(
                "external_sd", "ext_sd", "external", "extSdCard", "sdcard2", "sdcard", "media_rw" ); // diff OEM, diff name
        for (String sdPath : sdCardPossiblePath) {
            File file = new File("/mnt/", sdPath);
            if (file.isDirectory() && file.canWrite()) {
                path = file.getAbsolutePath();
                String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date());
                File testWritable = new File(path, "test_" + timeStamp);
                if (testWritable.mkdirs())
                    testWritable.delete();
                else
                    path = null;
            }
        }
        if (path != null)
            sdCardFile = new File(path);
        else
            sdCardFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath());

        return sdCardFile.getAbsolutePath();
    }

    public static HashSet<String> getExternalMounts() {
        final HashSet<String> out = new HashSet<String>();
        String reg = "(?i).*vold.*(vfat|ntfs|exfat|fat32|ext3|ext4).*rw.*";
        String s = "";
        try {
            final Process process = new ProcessBuilder().command("mount")
                    .redirectErrorStream(true).start();
            process.waitFor();
            final InputStream is = process.getInputStream();
            final byte[] buffer = new byte[1024];
            while (is.read(buffer) != -1) {
                s = s + new String(buffer);
            }
            is.close();
        } catch (final Exception e) {
            e.printStackTrace();
        }

        // parse output
        final String[] lines = s.split("\n");
        for (String line : lines) {
            if (!line.toLowerCase(Locale.US).contains("asec")) {
                if (line.matches(reg)) {
                    String[] parts = line.split(" ");
                    for (String part : parts) {
                        if (part.startsWith("/"))
                            if (!part.toLowerCase(Locale.US).contains("vold"))
                                out.add(part);
                    }
                }
            }
        }
        return out;
    }

    private static final Pattern DIR_SEPORATOR = Pattern.compile("/");

    /**
     * Raturns all available SD-Cards in the system (include emulated)
     *
     * Warning: Hack! Based on Android source code of version 4.3 (API 18)
     * Because there is no standart way to get it.
     * TODO: Test on future Android versions 4.4+
     *
     * @return paths to all available SD-Cards in the system (include emulated)
     */
    public static String[] getStorageDirectories()
    {
        // Final set of paths
        final Set<String> rv = new HashSet<String>();
        // Primary physical SD-CARD (not emulated)
        final String rawExternalStorage = System.getenv("EXTERNAL_STORAGE");
        // All Secondary SD-CARDs (all exclude primary) separated by ":"
        final String rawSecondaryStoragesStr = System.getenv("SECONDARY_STORAGE");
        // Primary emulated SD-CARD
        final String rawEmulatedStorageTarget = System.getenv("EMULATED_STORAGE_TARGET");
        if(TextUtils.isEmpty(rawEmulatedStorageTarget))
        {
            // Device has physical external storage; use plain paths.
            if(TextUtils.isEmpty(rawExternalStorage))
            {
                // EXTERNAL_STORAGE undefined; falling back to default.
                rv.add("/storage/sdcard0");
            }
            else
            {
                rv.add(rawExternalStorage);
            }
        }
        else
        {
            // Device has emulated storage; external storage paths should have
            // userId burned into them.
            final String rawUserId;
            if(Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1)
            {
                rawUserId = "";
            }
            else
            {
                final String path = Environment.getExternalStorageDirectory().getAbsolutePath();
                final String[] folders = DIR_SEPORATOR.split(path);
                final String lastFolder = folders[folders.length - 1];
                boolean isDigit = false;
                try
                {
                    Integer.valueOf(lastFolder);
                    isDigit = true;
                }
                catch(NumberFormatException ignored)
                {
                }
                rawUserId = isDigit ? lastFolder : "";
            }
            // /storage/emulated/0[1,2,...]
            if(TextUtils.isEmpty(rawUserId))
            {
                rv.add(rawEmulatedStorageTarget);
            }
            else
            {
                rv.add(rawEmulatedStorageTarget + File.separator + rawUserId);
            }
        }
        // Add all secondary storages
        if(!TextUtils.isEmpty(rawSecondaryStoragesStr))
        {
            // All Secondary SD-CARDs splited into array
            final String[] rawSecondaryStorages = rawSecondaryStoragesStr.split(File.pathSeparator);
            Collections.addAll(rv, rawSecondaryStorages);
        }
        return rv.toArray(new String[rv.size()]);
    }


    /**
     * returns a list of all available sd cards paths, or null if not found.
     *
     * @param includePrimaryExternalStorage set to true if you wish to also include the path of the primary external storage
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static List<String> getSdCardPaths(final Context context, final boolean includePrimaryExternalStorage)
    {
        final File[] externalCacheDirs= ContextCompat.getExternalCacheDirs(context);
        if(externalCacheDirs==null||externalCacheDirs.length==0)
            return null;
        if(externalCacheDirs.length==1)
        {
            if(externalCacheDirs[0]==null)
                return null;
            final String storageState= EnvironmentCompat.getStorageState(externalCacheDirs[0]);
            if(!Environment.MEDIA_MOUNTED.equals(storageState))
                return null;
            if(!includePrimaryExternalStorage&& Build.VERSION.SDK_INT>= Build.VERSION_CODES.HONEYCOMB&&Environment.isExternalStorageEmulated())
                return null;
        }
        final List<String> result=new ArrayList<>();
        if(includePrimaryExternalStorage||externalCacheDirs.length==1)
            result.add(getRootOfInnerSdCardFolder(externalCacheDirs[0]));
        for(int i=1;i<externalCacheDirs.length;++i)
        {
            final File file=externalCacheDirs[i];
            if(file==null)
                continue;
            final String storageState=EnvironmentCompat.getStorageState(file);
            if(Environment.MEDIA_MOUNTED.equals(storageState))
                result.add(getRootOfInnerSdCardFolder(externalCacheDirs[i]));
        }
        if(result.isEmpty())
            return null;
        return result;
    }

    /** Given any file/folder inside an sd card, this will return the path of the sd card */
    private static String getRootOfInnerSdCardFolder(File file)
    {
        if(file==null)
            return null;
        final long totalSpace=file.getTotalSpace();
        while(true)
        {
            final File parentFile=file.getParentFile();
            if(parentFile==null||parentFile.getTotalSpace()!=totalSpace)
                return file.getAbsolutePath();
            file=parentFile;
        }
    }
}