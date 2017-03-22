package sample.hawk.com.mybasicappcomponents.controls;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Handling single and multi touch on Android - Tutorial
 * http://www.vogella.com/tutorials/AndroidTouch/article.html#singletouch
 */

public class SingleTouchEventView extends View {
    private Paint mPaint = new Paint();
    private Path mPath = new Path();
    Context mContext;

    GestureDetector gestureDetector;

    public SingleTouchEventView(Context context, AttributeSet attrs) {
        super(context, attrs);
        gestureDetector = new GestureDetector(context, new GestureListener());
        this.mContext = context;

        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(6f);
        mPaint.setColor(Color.BLACK);

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
    }

    public void setColor(int r, int g, int b) {
        int rgb = Color.rgb(r, g, b);
        mPaint.setColor(rgb);
    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        // event when double tap occurs
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            float x = e.getX();
            float y = e.getY();

            // clean drawing area on double tap
            mPath.reset();
            Log.d("Double Tap", "Tapped at: (" + x + "," + y + ")");

            return true;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        PointF point = new PointF();
        point.x = event.getX();
        point.y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(point.x, point.y);
                return true;
            case MotionEvent.ACTION_MOVE:

                mPath.lineTo(point.x, point.y);
                break;
            case MotionEvent.ACTION_UP:

                break;
            default:
                return false;
        }
        gestureDetector.onTouchEvent(event);
        invalidate(); // Schedules a repaint.
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPath(mPath, mPaint);
    }

}