package sample.hawk.com.mybasicappcomponents.view;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * For test Touch Event and View Layer relationship
 */

public class MyViewLayerActivity extends Activity implements View.OnClickListener {

    FrameLayout myRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // get current device screen size
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int DevWidth = displayMetrics.widthPixels;
        int DevHeight = displayMetrics.heightPixels;

        Button button1 = CreateButton("Layer1", Color.RED,    DevWidth-100, DevHeight-100 );
        Button button2 = CreateButton("Layer2", Color.GRAY,   DevWidth-200, DevHeight-200 );
        Button button3 = CreateButton("Layer3", Color.CYAN,   DevWidth-300, DevHeight-300 );
        Button button4 = CreateButton("Layer4", Color.GREEN,  DevWidth-400, DevHeight-400 );
        Button button5 = CreateButton("Layer5", Color.YELLOW, DevWidth-500, DevHeight-500 );
        Button button6 = CreateButton("Layer6", Color.BLUE,   DevWidth-600, DevHeight-600 );

        myRoot = new FrameLayout(this);
        myRoot.addView(button1);
        myRoot.addView(button2);
        myRoot.addView(button3);
        myRoot.addView(button4);
        myRoot.addView(button5);
        myRoot.addView(button6);
        setContentView(myRoot);

    }

    Button CreateButton(String title, int color, int width, int height){
        Button button = new Button(this);
        FrameLayout.LayoutParams button_layout =
                new FrameLayout.LayoutParams(width, height, Gravity.BOTTOM );
        button.setLayoutParams(button_layout);
        button.setText(title);
        button.setBackgroundColor(color);
        button.setGravity(Gravity.TOP|Gravity.CENTER);
        button.setOnClickListener(this);
        return button;
    }


    @Override
    public void onClick(View v) {
        SMLog.i("mText="+((Button)v).getText());
    }
}
