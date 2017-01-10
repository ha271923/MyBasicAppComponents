package sample.hawk.com.mybasicappcomponents.view;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.RelativeLayout;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/1/9.
 */

public class MyViewTreeObserverActivity extends Activity {
    ViewGroup rootlayout;
    Button button1;
    Button button2;
    private int _xDelta;
    private int _yDelta;
    private final Rect mClipRect = new Rect();
    final int minPercentageViewed = 70;

    ViewTreeObserver.OnTouchModeChangeListener mOnTouchModeChangeListener;
    ViewTreeObserver.OnScrollChangedListener mOnScrollChangedListener;
    ViewTreeObserver.OnGlobalFocusChangeListener mOnGlobalFocusChangeListener;
    ViewTreeObserver.OnGlobalLayoutListener mOnGlobalLayoutListener;
    ViewTreeObserver.OnPreDrawListener mOnPreDrawListener;
    ViewTreeObserver.OnWindowAttachListener mOnWindowAttachListener;


    @Override
    protected void onDestroy() {
        removeObserver(button1);
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myviewtreeobserveractivity);
        rootlayout = (ViewGroup) findViewById(R.id.rootLayout);


        button1 = (Button) findViewById(R.id.button1);
        button1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int X = (int) event.getRawX();
                final int Y = (int) event.getRawY();
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                        RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) v.getLayoutParams();
                        _xDelta = X - lParams.leftMargin;
                        _yDelta = Y - lParams.topMargin;
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                    case MotionEvent.ACTION_POINTER_DOWN:
                        break;
                    case MotionEvent.ACTION_POINTER_UP:
                        break;
                    case MotionEvent.ACTION_MOVE:
                        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) v.getLayoutParams();
                        layoutParams.leftMargin = X - _xDelta;
                        layoutParams.topMargin = Y - _yDelta;
                        v.setLayoutParams(layoutParams);
                        break;
                }
                rootlayout.invalidate();
                return true;
            }
        });


        button2 = (Button) findViewById(R.id.button2);
        button2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int X = (int) event.getRawX();
                final int Y = (int) event.getRawY();
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                        RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) v.getLayoutParams();
                        _xDelta = X - lParams.leftMargin;
                        _yDelta = Y - lParams.topMargin;
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                    case MotionEvent.ACTION_POINTER_DOWN:
                        break;
                    case MotionEvent.ACTION_POINTER_UP:
                        break;
                    case MotionEvent.ACTION_MOVE:
                        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) v.getLayoutParams();
                        layoutParams.leftMargin = X - _xDelta;
                        layoutParams.topMargin = Y - _yDelta;
                        v.setLayoutParams(layoutParams);
                        break;
                }
                rootlayout.invalidate();
                return true;
            }
        });
        AddObserver(button2);

    }

    void AddObserver(final View v){
        // addOnScrollChangedListener
        mOnTouchModeChangeListener = new ViewTreeObserver.OnTouchModeChangeListener() {
            @Override
            public void onTouchModeChanged(boolean isInTouchMode) {
                SMLog.i("onTouchModeChanged");
            }
        };
        v.getViewTreeObserver().addOnTouchModeChangeListener(mOnTouchModeChangeListener);
        // addOnScrollChangedListener
        mOnScrollChangedListener = new ViewTreeObserver.OnScrollChangedListener(){
            @Override
            public void onScrollChanged() {
                SMLog.i("onScrollChanged");
            }
        };
        v.getViewTreeObserver().addOnScrollChangedListener(mOnScrollChangedListener);
        // addOnGlobalFocusChangeListener
       mOnGlobalFocusChangeListener= new ViewTreeObserver.OnGlobalFocusChangeListener(){
            @Override
            public void onGlobalFocusChanged(View oldFocus, View newFocus) {
                SMLog.i("onGlobalFocusChanged");
            }
        };
        v.getViewTreeObserver().addOnGlobalFocusChangeListener(mOnGlobalFocusChangeListener);
        // addOnGlobalLayoutListener
        mOnGlobalLayoutListener= new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                SMLog.i("onGlobalLayout");
                boolean isVisible = isVisibleRateOverMin(rootlayout, v, minPercentageViewed);
                SMLog.i("isVisible="+isVisible);
            }
        };
        v.getViewTreeObserver().addOnGlobalLayoutListener(mOnGlobalLayoutListener);
        // addOnPreDrawListener
        mOnPreDrawListener =new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                SMLog.i("onPreDraw");
                return true;
            }
        };
        v.getViewTreeObserver().addOnPreDrawListener(mOnPreDrawListener);
        // addOnWindowAttachListener
        mOnWindowAttachListener = new ViewTreeObserver.OnWindowAttachListener() {
            @Override
            public void onWindowAttached() {
                SMLog.i("onWindowAttached");
            }
            @Override
            public void onWindowDetached() {
                SMLog.i("onWindowAttached");
            }
        };
        v.getViewTreeObserver().addOnWindowAttachListener(mOnWindowAttachListener);

    }

    void removeObserver(final View v){
        if(mOnTouchModeChangeListener!=null) {
            v.getViewTreeObserver().removeOnTouchModeChangeListener(mOnTouchModeChangeListener);
            mOnTouchModeChangeListener = null;
        }
        if(mOnScrollChangedListener!=null){
            v.getViewTreeObserver().removeOnScrollChangedListener(mOnScrollChangedListener);
            mOnScrollChangedListener = null;
        }
        if(mOnGlobalFocusChangeListener!=null){
            v.getViewTreeObserver().removeOnGlobalFocusChangeListener(mOnGlobalFocusChangeListener);
            mOnGlobalFocusChangeListener = null;
        }
        if(mOnGlobalLayoutListener!=null){
            v.getViewTreeObserver().removeOnGlobalLayoutListener(mOnGlobalLayoutListener);
            mOnGlobalLayoutListener = null;
        }
        if(mOnPreDrawListener!=null){
            v.getViewTreeObserver().removeOnPreDrawListener(mOnPreDrawListener);
            mOnPreDrawListener = null;
        }
        if(mOnWindowAttachListener!=null){
            v.getViewTreeObserver().removeOnWindowAttachListener(mOnWindowAttachListener);
            mOnWindowAttachListener = null;
        }
    }

    @Nullable
    @Override
    public CharSequence onCreateDescription() {

        return super.onCreateDescription();
    }

    boolean isVisibleRateOverMin(@Nullable final View rootView, @Nullable final View view, final int minPercentageViewed) {
        // ListView & GridView both call detachFromParent() for views that can be recycled for
        // new data. This is one of the rare instances where a view will have a null parent for
        // an extended period of time and will not be the main window.
        // view.getGlobalVisibleRect() doesn't check that case, so if the view has visibility
        // of View.VISIBLE but it's group has no parent it is likely in the recycle bin of a
        // ListView / GridView and not on screen.
        if (view == null || view.getVisibility() != View.VISIBLE || rootView.getParent() == null) {
            return false;
        }

        if (!view.getGlobalVisibleRect(mClipRect)) {
            // Not visible
            return false;
        }

        // % visible check - the cast is to avoid int overflow for large views.
        final long visibleViewArea = (long) mClipRect.height() * mClipRect.width();
        SMLog.i("visibleViewArea="+visibleViewArea+ "   width=" + mClipRect.width() + "  height=" + mClipRect.height());
        final long totalViewArea = (long) view.getHeight() * view.getWidth();
        SMLog.i("totalViewArea="+totalViewArea+ "   width=" + view.getWidth() + "  height=" + view.getHeight());

        if (totalViewArea <= 0) {
            return false;
        }

        return 100 * visibleViewArea >= minPercentageViewed * totalViewArea;
    }
}
