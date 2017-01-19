package sample.hawk.com.mybasicappcomponents.debugTest.strictmode;

import android.content.Context;

import java.io.FileOutputStream;

/**
 * Created by ha271 on 2017/1/19.
 */

public class StrictModeTests {

    static public void WriteFile(Context context, String filename, String string) {

        FileOutputStream outputStream;

        //Code to perform FILE I/O which will be caught by StrictMode
        try {
            outputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(string.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
