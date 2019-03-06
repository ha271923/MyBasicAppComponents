package sample.hawk.com.mybasicappcomponents.data_structure.ByteArray;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.security.DataUtils;

/**
 * Created by ha271 on 2019/3/5.
 */

public class ByteArrayActivity extends Activity {
    static final String TAG = "ByteArrayActivity";
    Context mAppContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bytearray_activity);
        mAppContext = this.getApplicationContext();

    }

    public void onClickExtendByteArray(View v) {
        String output = DataUtils.getPngHexString(mAppContext); // hawk.png is 7688 bytes.
        Log.i(TAG, "output byte length="+output.getBytes().length);
        ByteArrayHolder byteArrayHolder = new ByteArrayHolder();

        // byteArrayHolder.extendSize(tz_processed.length);
        if(byteArrayHolder.byteArray.length >= output.getBytes().length) {
            byteArrayHolder.byteArray = Arrays.copyOf(output.getBytes(), output.getBytes().length);
            byteArrayHolder.receivedLength = output.getBytes().length;
        } else {
            Log.w(TAG, "byteArrayHolder.byteArray.length = " + byteArrayHolder.byteArray.length +" < output.getBytes().length="+output.getBytes().length+" extend size!");
            byteArrayHolder.extendSize(output.getBytes().length);
            byteArrayHolder.byteArray = Arrays.copyOf(output.getBytes(), output.getBytes().length);
            byteArrayHolder.receivedLength = output.getBytes().length;
        }

    }

    static private final int FILE_EOF = -1;
    public void onClickByteArrayOutputStream(View v) {
        byte buffer[] = new byte[512];
        int length = 0;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            InputStream is = getApplicationContext().getAssets().open("hawk.png");;
            while( (length = is.read(buffer)) != FILE_EOF ){
                baos.write(buffer, 0, length);
            }
            Log.i("ByteArrayActivity", "baos.size()="+baos.size());
            // convert ByteArrayOutputStream to byte[]
            byte data [] = baos.toByteArray();
            is.close();
            baos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
