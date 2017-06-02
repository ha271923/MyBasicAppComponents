package sample.hawk.com.mybasicappcomponents.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import sample.hawk.com.mybasicappcomponents.R;

public class MyTimeView extends TextView {
    private String timeText;
    private boolean timeColor;

    public MyTimeView(Context context) {
        super(context);
        setTimeView();
    }

    public MyTimeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // retrieved values correspond to the positions of the attributes
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyTimeView);
        int count = typedArray.getIndexCount();
        try{
            for (int i = 0; i < count; ++i) {
                int attr = typedArray.getIndex(i);
                // the attr corresponds to the title attribute
                if(attr == R.styleable.MyTimeView_timeText) { // set the text from the layout
                    timeText = typedArray.getString(attr);
                    setTimeView();
                } else if(attr == R.styleable.MyTimeView_timeColor) { // set the timeColor of the attr "setColor"
                    timeColor = typedArray.getBoolean(attr, false);
                    decorateText();
                }
            }
        }
        finally { // the recycle() will be executed obligatorily
            typedArray.recycle(); // for reuse
        }
    }

    public MyTimeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setTimeView();
    }

    private void setTimeView() {
        // has the format hour.minutes am/pm
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm aa");
        String time = dateFormat.format(Calendar.getInstance().getTime());

        if(this.timeText != null )
            setText(this.timeText +" "+time);
        else
            setText(time);
    }

    private void decorateText() {
        // when we set setColor attribute to true in the XML layout
        if(this.timeColor == true){
            // set the characteristics and the timeColor of the shadow
            setShadowLayer(4, 2, 2, Color.rgb(250, 00, 250));
            setBackgroundColor(Color.CYAN);
        } else {
            setBackgroundColor(Color.RED);
        }
    }
}
