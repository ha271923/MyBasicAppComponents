package sample.hawk.com.mybasicappcomponents.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class StorageHelper {
    private String appfilesdir;

    public StorageHelper(String AppFilesDir){
        appfilesdir = AppFilesDir;
    }

    public void unzip(String zipFile, String location){
        try  {
            dirChecker(location);
            FileInputStream fin = new FileInputStream(zipFile);
            ZipInputStream zin = new ZipInputStream(fin);
            ZipEntry ze = null;
            while ((ze = zin.getNextEntry()) != null) {
                // SMLog.i("Unzipping " + ze.getName()); // remark for performance.
                if(ze.isDirectory()) {
                    dirChecker(location + ze.getName());
                } else {
                    FileOutputStream fout = new FileOutputStream(appfilesdir+ location + ze.getName());
                    for (int c = zin.read(); c != -1; c = zin.read()) {
                        fout.write(c);
                    }
                    zin.closeEntry();
                    fout.close();
                }
            }
            zin.close();
        } catch(Exception e) {
            SMLog.e("unzip error:"+e);
        }
    }

    private void dirChecker(String dir) {
        File f = new File(appfilesdir + dir);
        if(!f.isDirectory()) {
            f.mkdirs();
        }
    }

    public void FileChecker(String path){
        File f = new File(path);
        if(f.exists())
            SMLog.i(path+" is exist!");
        else
            SMLog.e(path+" is NOT exist!");
    }

    public File createFileFromInputStream(InputStream inputStream, String outputFilePath) {
        try{
            File f = new File(outputFilePath);
            OutputStream outputStream = new FileOutputStream(f);
            byte buffer[] = new byte[1024];
            int length = 0;
            while((length=inputStream.read(buffer)) > 0) {
                outputStream.write(buffer,0,length);
            }
            outputStream.close();
            inputStream.close();
            return f;
        }catch (IOException e) {
            SMLog.e("Error:"+e);
        }
        return null;
    }

}
