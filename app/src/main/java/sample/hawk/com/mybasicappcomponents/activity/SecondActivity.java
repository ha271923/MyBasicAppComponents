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

        String ext1 = (String) argument.get("ExtraName1");
        int    ext2 = (Integer) argument.get("ExtraName2");
        Float  ext3 = (Float) argument.get("ExtraName3");

        TextView showText = new TextView(this);
        showText.setText(
                "   ----- Bundle -----" +
                "\n String = " + valueStr +
                "\n Integer= " + valueInt +
                "\n Float  = " + valueFloat +
                "\n ----- Extra -----" +
                "\n ExtString = "+ ext1 +
                "\n ExtInteger = "+ ext2 +
                "\n ExtFloat = "+ ext3
        );

        setContentView(showText);
    }
}
