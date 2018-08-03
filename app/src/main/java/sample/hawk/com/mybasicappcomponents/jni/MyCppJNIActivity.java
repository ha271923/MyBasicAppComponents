package sample.hawk.com.mybasicappcomponents.jni;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/7/28.
 */

public class MyCppJNIActivity extends Activity {
    private MyCppJNI mMyCppJNI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.strings_ouput);
        MyCppJNI.setActivity(this);
        mMyCppJNI = new MyCppJNI();
        ((TextView)findViewById(R.id.string1)).setText(MyCppJNI.JAVAtoJNI("paramFromJava"));
        ((TextView)findViewById(R.id.string2)).setText(""+MyCppJNI.multiply(3,4));
        ((TextView)findViewById(R.id.string3)).setText(""+MyCppJNI.getTimeOfDay());
        ((TextView)findViewById(R.id.string4)).setText(""+mMyCppJNI.JAVAtoJNIcallbackJAVA("paramFromJava"));
        new Thread () {
            @Override
            public void run() {
                super.run();
                SMLog.i("Worker Thread ++++");
                SMLog.i(mMyCppJNI.JAVAtoJNIcallbackJAVA("paramFromJava"));
                SMLog.i("Worker Thread ---");
            }
        }.start();
        // ((TextView)findViewById(R.id.string4)).setText(""+MyCppJNI.JAVAtoJNIcallbackJAVA("paramFromJava")); // static Class will cause Exception.
    }
}
