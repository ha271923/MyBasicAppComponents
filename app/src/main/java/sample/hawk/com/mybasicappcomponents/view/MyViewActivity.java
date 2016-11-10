package sample.hawk.com.mybasicappcomponents.view;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import sample.hawk.com.mybasicappcomponents.R;

/**
 * Created by ha271 on 2016/11/9.
 */

public class MyViewActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       // setContentView(R.layout.myview_space); // Hawk: the programmer draws the view, no XML layout.
        MyView myView = new MyView(this);
        myView.setBackgroundColor(Color.YELLOW);
        setContentView(myView);
    }
}
