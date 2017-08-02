package sample.hawk.com.mybasicappcomponents.jni;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by ha271 on 2017/7/28.
 */

public class MyCppJNIActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView root = new TextView(this);
        root.setText(MyCppJNI.helloString("MagicJNI")+"\n"
                +MyCppJNI.multiply(3,4)+"\n"
                +MyCppJNI.getTimeOfDay());
        setContentView(root);
    }
}
