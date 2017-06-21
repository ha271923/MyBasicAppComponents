package sample.hawk.com.mybasicappcomponents.view.CustomView;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;

import sample.hawk.com.mybasicappcomponents.R;

import static sample.hawk.com.mybasicappcomponents.R.id.myview;

/**
 * Created by ha271 on 2016/11/9.
 */

public class MyViewActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
        setContentView(R.layout.myviewactivity); // Hawk: the programmer draws the view, no XML layout.
        LinearLayout myviewactivitylayout = (LinearLayout) findViewById(R.id.myviewactivitylayout);
        // static view by layout xml
        MyView myView1 = (MyView) findViewById(myview);
        myView1.setBackgroundColor(Color.GREEN);
        myView1.setLayoutParams(new LinearLayout.LayoutParams(900, 900));
        // dynamic view by programmer
        MyView myView2 = new MyView(this);
        myView2.setBackgroundColor(Color.YELLOW);
        myView2.setLayoutParams(new LinearLayout.LayoutParams(600, 600));
        myviewactivitylayout.addView(myView2);
        // dynamic view by programmer
        MyView myView3 = new MyView(this);
        myView3.setBackgroundColor(Color.RED);
        myView3.setLayoutParams(new LinearLayout.LayoutParams(300, 300));
        // myviewactivitylayout.addView(myView3); // no addView, so no show

    }
}
