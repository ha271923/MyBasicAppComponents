package sample.hawk.com.mybasicappcomponents.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by ha271 on 2017/1/6.
 */

public class SecondActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle argument = getIntent().getExtras(); //從 Intent 取出包裹
        String valueStr = argument.getString("KeyNameOne"); //透過 key 取出字串
        int valueInt = argument.getInt("KeyNameTwo"); //透過 key 取出整數
        float valueFloat = argument.getFloat("KeyNameThree"); //透過 key 取出浮點數

        TextView showText = new TextView(this);
        showText.setText(
                "\n String = " + valueStr +
                "\n Integer= " + valueInt +
                "\n Float  = " + valueFloat);
        setContentView(showText);
    }
}
