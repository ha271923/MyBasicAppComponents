package sample.hawk.com.mybasicappcomponents.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2016/12/28.
 */

//////////////////////////// Custom Layout ////////////////////////////////
public class MyLayout extends LinearLayout implements OnTouchFeedbackListener {

    public MyLayout(Context context){
        super(context);
        SMLog.i("MyLayout(context)");
    }

    public MyLayout(Context context, AttributeSet atts){ // If support XML, this is constructor.
        super(context, atts);
        SMLog.i("MyLayout(context, atts)");

    }

    //////////////////////////// Custom Interface ////////////////////////////////
    @Override
    public void onTouchDownFeedback(){
        SMLog.i("onTouchDownFeedback");
        invalidate();
    }

    @Override
    public void onTouchUpFeedback(){
        SMLog.i("onTouchUpFeedback");
        invalidate();
    }

    ////////////////////////////  View  ////////////////////////////////

    @Override
    public void setOnTouchListener(OnTouchListener l) {
        super.setOnTouchListener(l);
    }

    ////////////////////////////  Layout  ////////////////////////////////

    @Override
    protected void onFinishInflate() {
        SMLog.i("onFinishInflate");
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_MOVE:
                SMLog.i("ACTION_MOVE");
                break;
            case MotionEvent.ACTION_DOWN:
                SMLog.i("ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                SMLog.i("ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                SMLog.i("ACTION_CANCEL");
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }


    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        SMLog.i("dispatchDraw");
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
        SMLog.i("onMeasure");
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        SMLog.i("onLayout");
    }

    @Override
    public boolean onHoverEvent(MotionEvent event) {
        SMLog.i("onHoverEvent");
        return super.onInterceptHoverEvent(event);
    }



}
