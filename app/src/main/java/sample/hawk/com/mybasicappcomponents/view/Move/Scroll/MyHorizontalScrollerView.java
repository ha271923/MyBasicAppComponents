package sample.hawk.com.mybasicappcomponents.view.Move.Scroll;

import android.content.Context;
import android.support.v4.view.ViewConfigurationCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/9/25.
 */

public class MyHorizontalScrollerView  extends ViewGroup {
    private static final String TAG = MyHorizontalScrollerView.class.getSimpleName();

    private Scroller mScroller;
    private int mTouchSlop;
    private int mMaxVelocity;
    private int mMinVelocity;

    private int mPointerId;

    private int mDistance_leftBorder;
    private int mDistance_rightBorder;
    private float mLastX;
    private float mLastInterceptX;


    public MyHorizontalScrollerView(Context context) {
        this(context, null);
    }

    public MyHorizontalScrollerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyHorizontalScrollerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mScroller = new Scroller(context);
        ViewConfiguration configuration = ViewConfiguration.get(context);
        mTouchSlop = ViewConfigurationCompat.getScaledPagingTouchSlop(configuration);
        mMaxVelocity = configuration.getScaledMaximumFlingVelocity();
        mMinVelocity = configuration.getScaledMinimumFlingVelocity();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childrenCount = getChildCount();
        for (int i = 0; i < childrenCount; i++) {
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed) {
            int childrenCount = getChildCount();
            for (int i = 0; i < childrenCount; i++) {
                View child = getChildAt(i);
                if (child.getVisibility() != GONE) {
                    child.layout(i * child.getMeasuredWidth(), 0, (i + 1) * child.getMeasuredWidth(), child.getMeasuredHeight());
                }
            }
            View child = getChildAt(0);
            if(child != null)
                mDistance_leftBorder = child.getLeft(); // current show item
            child = getChildAt(childrenCount - 1);
            if(child != null)
                mDistance_rightBorder = child.getRight(); // next show item
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean isIntercept = false;
        float mInterceptX = ev.getX();
        float mInterceptY = ev.getY();
        acquireVelocityTracker(ev);
        final VelocityTracker verTracker = mVelocityTracker;
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPointerId = ev.getPointerId(0);
                isIntercept = false;
                break;
            case MotionEvent.ACTION_MOVE:
                float deltaX = mInterceptX - mLastInterceptX;
                verTracker.computeCurrentVelocity(1000, mMaxVelocity);
                final float velocityX = verTracker.getXVelocity(mPointerId);
                isIntercept = Math.abs(deltaX) > mTouchSlop || Math.abs(velocityX) > mMinVelocity;
                SMLog.i(TAG, "deltaX = " + deltaX + " | scrollX = " + getScrollX());
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                releaseVelocityTracker();
                isIntercept = false;
                break;
            default:
                break;
        }
        mLastX = mInterceptX;
        mLastInterceptX = mInterceptX;
        return isIntercept;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        float mTouchX = ev.getX();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_MOVE:
                float deltaX = mTouchX - mLastX;
                float scrollByStart = deltaX;
                if (getScrollX() - deltaX < mDistance_leftBorder) {
                    scrollByStart = deltaX / 3;
                } else if (getScrollX() + getWidth() - deltaX > mDistance_rightBorder) {
                    scrollByStart = deltaX / 3;
                }
                scrollBy((int) -scrollByStart, 0);
                break;
            case MotionEvent.ACTION_UP:
                // 当手指抬起时，根据当前的滚动值来判定应该滚动到哪个子控件的界面
                int targetIndex = (getScrollX() + getWidth() / 2) / getWidth();
                if (targetIndex > getChildCount() - 1) {
                    targetIndex = getChildCount() - 1;
                } else if (targetIndex < 0) {
                    targetIndex = 0;
                }
                int dx = targetIndex * getWidth() - getScrollX();
                mScroller.startScroll(getScrollX(), 0, dx, 0, 250);
                invalidate();
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
            default:
                break;
        }
        mLastX = mTouchX;
        return super.onTouchEvent(ev);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), 0);
            postInvalidate();
        }
    }


    /**
     * 向VelocityTracker添加MotionEvent
     *
     * @param event
     */
    private VelocityTracker mVelocityTracker;

    private void acquireVelocityTracker(final MotionEvent event) {
        if (null == mVelocityTracker) {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(event);
    }

    /**
     * 释放VelocityTracker
     */
    private void releaseVelocityTracker() {
        if (null != mVelocityTracker) {
            mVelocityTracker.clear();
            mVelocityTracker.recycle();
            mVelocityTracker = null;
        }
    }

}