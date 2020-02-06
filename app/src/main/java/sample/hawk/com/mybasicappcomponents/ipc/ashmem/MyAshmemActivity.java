package sample.hawk.com.mybasicappcomponents.ipc.ashmem;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;


//
// EXAMPLE at  https://github.com/xiaoxinxin003/AshmDemo
public class MyAshmemActivity extends AppCompatActivity {
    private static final String TAG = "MyAshmemActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myashmem_activity);

        // Example of a call to a native method
        TextView tv = (TextView) findViewById(R.id.sample_text);
        // tv.setText(stringFromJNI());
        tv.setText("----------- TEST STRING -----------");

        bind();
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    // public native String stringFromJNI();

    // Used to load the 'native-lib' library on application startup.
    // static {
    //     System.loadLibrary("native-lib");
    // }

    private void bind() {
        Intent intent = new Intent(MyAshmemActivity.this, MemoryFetchService.class);
        boolean bRet= bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {

                byte[] data = new byte[10];
                IMemoryAidlInterface iMemoryAidlInterface = IMemoryAidlInterface.Stub.asInterface(service);
                try {
                    ParcelFileDescriptor parcelFileDescriptor = iMemoryAidlInterface.getParcelFileDescriptor();
                    FileDescriptor descriptor = parcelFileDescriptor.getFileDescriptor();
                    FileInputStream fileInputStream = new FileInputStream(descriptor);
                    int read_count = fileInputStream.read(data);
                    SMLog.d(TAG, "onServiceConnected: read --- read_count=" + read_count);
                    // You can watch data[] which is same with write(), so memory shared OK!
                } catch (Exception e) {
                    SMLog.d(TAG, "onServiceConnected: exception: " + e.toString());
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, Service.BIND_AUTO_CREATE);
    }
}
