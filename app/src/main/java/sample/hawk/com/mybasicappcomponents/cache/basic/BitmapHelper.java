package sample.hawk.com.mybasicappcomponents.cache.basic;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by ha271 on 2017/1/18.
 */

public class BitmapHelper {

    public static Bitmap decodeFile(File file, BitmapFactory.Options options){
        return BitmapFactory.decodeFile(file.getAbsolutePath(), options);
    }

    public static boolean saveBitmap(File file, Bitmap bitmap){
        if(file == null || bitmap == null)
            return false;
        try {
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
            return bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
