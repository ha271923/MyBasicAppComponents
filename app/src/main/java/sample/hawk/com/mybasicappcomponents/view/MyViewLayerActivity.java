package sample.hawk.com.mybasicappcomponents.view;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

import static android.view.View.INVISIBLE;

/**
 *  For test Touch Event and View Layer relationship
 *  A View's Elevation value determine which view is TOPMOST.
 *
 */

public class MyViewLayerActivity extends Activity implements View.OnClickListener, View.OnLongClickListener {

    FrameLayout myRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // get current device screen size
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int DevWidth = displayMetrics.widthPixels;
        int DevHeight = displayMetrics.heightPixels;
        DevHeight = DevHeight - 150; // reserve some space on screen TOP.

        Button button1 = CreateButton("Layer1", Color.RED,    DevWidth-100, DevHeight-100 );
        Button button2 = CreateButton("Layer2", Color.GRAY,   DevWidth-200, DevHeight-200 );
        Button button3 = CreateButton("Layer3", Color.CYAN,   DevWidth-300, DevHeight-300 );
        Button button4 = CreateButton("Layer4", Color.GREEN,  DevWidth-400, DevHeight-400 );
        Button button5 = CreateButton("Layer5", Color.YELLOW, DevWidth-500, DevHeight-500 );
        Button button6 = CreateButton("Layer6", Color.BLUE,   DevWidth-600, DevHeight-600 );
        TextView title = CreateText("Click=Alpha-20%, LongPress=InVisible", 16,   DevWidth, 200);

        myRoot = new FrameLayout(this);
        myRoot.addView(button1);
        myRoot.addView(button2);
        myRoot.addView(button3);
        myRoot.addView(button4);
        myRoot.addView(button5); // Button View's default Elevation=8.0f
        myRoot.addView(button6); // Button View's default Elevation=8.0f, Top of buttons
        // TextView will not cover any button view, since
        // Button View in Lollipop+ has a default elevation to them which causes them to always draw on top.
        // You can change this by overriding the default StateListAnimator.
        myRoot.addView(title); // Not TOP
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
        button.setOnLongClickListener(this);
        return button;
    }

    TextView CreateText(String title, int textSize, int width, int height){
        TextView textView = new TextView(this);
        textView.setBackgroundColor(Color.BLACK);
        textView.setText(title);
        textView.setTextColor(Color.YELLOW);
        textView.setTextSize(textSize);
        // textView.setElevation(9.0f); // to cover Button View, textView's Elevation should large than 8.0f.
        FrameLayout.LayoutParams textview_layout =
                new FrameLayout.LayoutParams(width, height, Gravity.TOP|Gravity.CENTER );
        textView.setLayoutParams(textview_layout);
        return textView;
    }

    @Override
    public void onClick(View v) { // If View alpha is 0, the click event will still hook, whatever its transparent, or not.
        Button button = ((Button)v);
        float alpha = button.getAlpha();
        button.setAlpha(alpha>0.2?alpha-0.2f:0);
        SMLog.i("mText="+button.getText() +"    alpha="+alpha+"  Elevation="+button.getElevation());
    }

    @Override
    public boolean onLongClick(View v) { // If View is inVisible, the click event will NOT hook.
        Button button = ((Button)v);
        button.setVisibility(INVISIBLE);
        SMLog.i("mText="+button.getText() +"  Visibility="+button.getVisibility());
        return true;
    }
}
