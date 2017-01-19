package sample.hawk.com.mybasicappcomponents.cache.basic;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;

import java.io.File;
import java.io.IOException;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/1/18.
 */

public class FileCache {
    private static final String TAG = "MemoryCache";

    private File cacheDir;	//the directory to save images

    /**
     * Constructor
     * @param context The context related to this cache.
     * */
    public FileCache(Context context) {
        // Find the directory to save cached images
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable())
            cacheDir = new File(context.getExternalCacheDir().getPath());
        else
            cacheDir = context.getCacheDir();
        if (!cacheDir.exists())
            cacheDir.mkdirs();

        SMLog.d(TAG, "cache dir: " + cacheDir.getAbsolutePath());
    }

    /**
     * Search the specific image file with a unique key.
     * @param key Should be unique.
     * @return Returns the image file corresponding to the key.
     * */
    public File getFile(String key) {
        File f = new File(cacheDir, key);
        if (f.exists()){
            SMLog.i(TAG, "the file you wanted exists " + f.getAbsolutePath());
            return f;
        }else{
            SMLog.w(TAG, "the file you wanted does not exists: " + f.getAbsolutePath());
        }

        return null;
    }

    public File createFile(String key) {
        SMLog.d("cache a file: " + key);
        File f = new File(cacheDir, key);
        if(!f.exists())
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        return f;
    }

    /**
     * Put a bitmap into cache with a unique key.
     * @param key Should be unique.
     * @param value A bitmap.
     * */
    public void put(String key, Bitmap value){
        File f = new File(cacheDir, key);
        if(!f.exists())
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        //Use the util's function to save the bitmap.
        if(BitmapHelper.saveBitmap(f, value))
            SMLog.d(TAG, "Save file to sdcard successfully!");
        else
            SMLog.w(TAG, "Save file to sdcard failed!");

    }

    /**
     * Clear the cache directory on sdcard.
     * */
    public void clear() {
        File[] files = cacheDir.listFiles();
        for (File f : files)
            f.delete();
    }
}
