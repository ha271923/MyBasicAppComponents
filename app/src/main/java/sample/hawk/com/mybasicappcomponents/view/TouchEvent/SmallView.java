package sample.hawk.com.mybasicappcomponents.view.TouchEvent;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by pc on 2017/4/1.
 */

public class SmallView extends FrameLayout {

    public SmallView(@NonNull Context context) {
        super(context);
    }

    public SmallView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SmallView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    // dispatchTouchEvent：分發TouchEvent。
    // Return: True if the event was handled by the view, false otherwise.
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                SMLog.i( "        SmallView Dispatch DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                SMLog.i( "        SmallView Dispatch MOVE");
                break;
            case MotionEvent.ACTION_UP:
                SMLog.i( "        SmallView Dispatch up");
                break;
        }
        View viewFrist = null;
        if (getChildCount() > 0) {
            viewFrist = getChildAt(0);
        }
        if(viewFrist != null && TouchSettings.MIDDLEDISPATCHFLAG){
            viewFrist.dispatchTouchEvent(ev); // dispatch to child at here!!
            SMLog.i("        SmallView Dispatch --> child view");
            return true;
        }
        return super.dispatchTouchEvent(ev);
    }

    // onInterceptTouchEvent：攔截TouchEvent。
    // Return: true to steal motion events from the children and have them dispatched to this ViewGroup through onTouchEvent().
    //         The current target will receive an ACTION_CANCEL event, and no further messages will be delivered here.
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                SMLog.i( "        SmallView Intercept DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                SMLog.i( "        SmallView Intercept MOVE");
                break;
            case MotionEvent.ACTION_UP:
                SMLog.i( "        SmallView Intercept up");
                break;
        }
        return TouchSettings.SMALLINTERFLAG;
    }

    // onTouchEvent：處理TouchEvent。
    // Return: True if the event was handled, false otherwise.
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                SMLog.i( "        SmallView Touch DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                SMLog.i( "        SmallView Touch MOVE");
                setMyPosition(event.getRawX() , event.getRawY());
                break;
            case MotionEvent.ACTION_UP:
                SMLog.i( "        SmallView Touch UP");
                break;
        }
        return TouchSettings.SMALLTOUFLAG;
    }

    private void setMyPosition(float x , float y){
        SMLog.i("        SmallView:setMyPosition: left "+ x +" y "+y);
        LayoutParams params =(LayoutParams)getLayoutParams();
        params.leftMargin = (int)x ;
        params.topMargin = (int)y;
        this.setLayoutParams(params);
    }

}
