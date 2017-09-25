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

public class MyVerticalScrollerView extends ViewGroup {
    private static final String TAG = MyVerticalScrollerView.class.getSimpleName();

    private Scroller mScroller;
    private int mTouchSlop;
    private int mMaxVelocity;
    private int mMinVelocity;

    private int mPointerId;

    private int mDistance_topBorder;
    private int mDistance_bottomBorder;
    private float mLastY;
    private float mLastInterceptY;


    public MyVerticalScrollerView(Context context) {
        this(context, null);
    }

    public MyVerticalScrollerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyVerticalScrollerView(Context context, AttributeSet attrs, int defStyleAttr) {
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
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed) {
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childView = getChildAt(i);
                if (childView.getVisibility() != GONE) {
                    childView.layout(0, i * childView.getMeasuredHeight(), childView.getMeasuredWidth(), (i + 1) * childView.getMeasuredHeight());
                }
            }
            View child = getChildAt(0);
            if(child != null)
                mDistance_topBorder = child.getTop(); // current show item
            child = getChildAt(childCount - 1);
            if(child != null)
                mDistance_bottomBorder = child.getBottom(); // next show item
        }
    }



    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean isIntercept = false;
        float interceptX = ev.getX();
        float interceptY = ev.getY();
        acquireVelocityTracker(ev);
        final VelocityTracker verTracker = mVelocityTracker;
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPointerId = ev.getPointerId(0);
                isIntercept = false;
                break;
            case MotionEvent.ACTION_MOVE:
                float deltaY = interceptY - mLastInterceptY;
                verTracker.computeCurrentVelocity(1000, mMaxVelocity);
                final float velocityY = verTracker.getYVelocity(mPointerId);
                isIntercept = Math.abs(deltaY) > mTouchSlop || Math.abs(velocityY) > mMinVelocity;
                SMLog.i(TAG, "deltaY = " + deltaY + " | scrollY = " + getScrollY());
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                releaseVelocityTracker();
                isIntercept = false;
                break;
            default:
                break;
        }
        mLastY = interceptY;
        mLastInterceptY = interceptY;
        return isIntercept;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        float touchY = ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_MOVE:
                float deltaY = touchY - mLastY;
                float scrollByStart = deltaY;
                if (getScrollY() - deltaY < mDistance_topBorder) {
                    scrollByStart = deltaY / 3;
                } else if (getScrollY() + getHeight() > mDistance_bottomBorder) {
                    scrollByStart = deltaY / 3;
                }
                scrollBy(0, (int) -scrollByStart);
                break;
            case MotionEvent.ACTION_UP:
                // 当手指抬起时，根据当前的滚动值来判定应该滚动到哪个子控件的界面
                int index = (getScrollY() + getHeight() / 2) / getHeight();
                if (index > getChildCount() - 1) {
                    index = getChildCount() - 1;
                } else if (index < 0) {
                    index = 0;
                }
                int dy = index * getHeight() - getScrollY();
                mScroller.startScroll(0, getScrollY(), 0, dy, 250);
                invalidate();
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
            default:
                break;
        }
        mLastY = touchY;
        return super.onTouchEvent(ev);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            scrollTo(0, mScroller.getCurrY());
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