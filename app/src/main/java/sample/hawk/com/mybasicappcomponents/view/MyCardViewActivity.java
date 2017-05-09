package sample.hawk.com.mybasicappcomponents.view;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import sample.hawk.com.mybasicappcomponents.R;

/**
 * Created by ha271 on 2017/5/9.
 */

public class MyCardViewActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mycardview_activity);
        ImageView imgView = (ImageView) findViewById(R.id.imgView);
        imgView.setImageResource(R.drawable.android_robot);
        TextView textView1 = (TextView) findViewById(R.id.textView1);
        textView1.setText("Hello Title");
        TextView textView2 = (TextView) findViewById(R.id.textView2);
        textView2.setText("Hello Sub-Title");

    }

}
