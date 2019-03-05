package sample.hawk.com.mybasicappcomponents.security;

import android.content.Context;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;

import static java.io.File.separator;

/**
 * Created by ha271 on 2019/3/5.
 */

public class DataUtils {

    static public byte[] readBytes(InputStream inputStream) throws IOException {
        // this dynamically extends to take the bytes you read
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();

        // this is storage overwritten on each iteration with bytes
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        // we need to know how may bytes were read to write them to the byteBuffer
        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }

        // and then we can return your byte array.
        return byteBuffer.toByteArray();
    }

    static public String byteArrayToHexStr(byte[] a) {
        StringBuilder sb = new StringBuilder(a.length * 2);
        for(byte b: a)
            sb.append(String.format("%02x", b));
        return sb.toString();
    }

    static public String getPngHexString(Context appContext)
    {
        String ret = "";
        try {
            InputStream iStream = appContext.getAssets().open("hawk.png");

            InputStream is;
            byte[] bytes = readBytes(iStream);

            ret = byteArrayToHexStr(bytes);

        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.i("DataUtils", "hex is : " + ret);

        String path = appContext.getFilesDir().getAbsolutePath();
        path = path + separator + "image.txt";

        writeStringToFile(ret, path);

        return ret;
    }

    static public void writeToFile(byte[] data, String fileName) {

        FileOutputStream out = null;
        try {
            out = new FileOutputStream(fileName);
            out.write(data);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static public void writeStringToFile(String src, String fileName)
    {
        FileOutputStream out = null;

        try {
            out = new FileOutputStream(fileName);
            OutputStreamWriter myOutWriter = new OutputStreamWriter(out);
            myOutWriter.append(src);
            myOutWriter.close();
            out.flush();
            out.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
