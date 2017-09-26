package sample.hawk.com.mybasicappcomponents.controls.TouchEvent;

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

public class MiddleView extends FrameLayout {

    public MiddleView(@NonNull Context context) {
        super(context);
    }

    public MiddleView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MiddleView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    // dispatchTouchEvent：分發TouchEvent。
    // Return: True if the event was handled by the view, false otherwise.
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                SMLog.i( "    MiddleView Dispatch DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                SMLog.i( "    MiddleView Dispatch MOVE");
                break;
            case MotionEvent.ACTION_UP:
                SMLog.i( "    MiddleView Dispatch UP");
                break;
        }
        View viewFrist = null;
        if (getChildCount() > 0) {
            viewFrist = getChildAt(0);
        }
        if(viewFrist != null && TouchSettings.MIDDLEDISPATCHFLAG){
            viewFrist.dispatchTouchEvent(ev); // dispatch to child at here!!
            SMLog.i("    MiddleView Dispatch --> child view");
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
                SMLog.i( "    MiddleView Intercept DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                SMLog.i( "    MiddleView Intercept MOVE");
                break;
            case MotionEvent.ACTION_UP:
                SMLog.i( "    MiddleView Intercept UP");
                break;
        }
        return TouchSettings.MIDDLEINTERFALG;
    }

    // onTouchEvent：處理TouchEvent。
    // Return: True if the event was handled, false otherwise.
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                SMLog.i( "    MiddleView Touch DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                SMLog.i( "    MiddleView Touch MOVE");
                setMyPosition(event.getRawX() , event.getRawY());
                break;
            case MotionEvent.ACTION_UP:
                SMLog.i( "    MiddleView Touch UP");
                break;
        }
        return TouchSettings.MIDDLETOUFLAG;
    }

    private void setMyPosition(float x , float y){
        SMLog.i("    MiddleView:setMyPosition: left "+ x +" y "+y);
        LayoutParams params = (LayoutParams)getLayoutParams();
        params.leftMargin = (int)x;
        params.topMargin = (int)y;
        this.setLayoutParams(params);
    }

}
