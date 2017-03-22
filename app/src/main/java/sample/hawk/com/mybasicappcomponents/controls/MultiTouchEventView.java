package sample.hawk.com.mybasicappcomponents.controls;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;

import static sample.hawk.com.mybasicappcomponents.controls.TouchDevice.maxTouchPoints;

/**
 * Handling single and multi touch on Android - Tutorial
 * http://www.vogella.com/tutorials/AndroidTouch/article.html#singletouch
 *
 * TOUCH_DOWN: This example can only support two finger touch simultaneously,
 * TOUC_MOVE:  This example can support over nine  finger touch simultaneously,
 *
 */

public class MultiTouchEventView extends View {
    private Paint mPaint = new Paint();
    Context mContext;

    private SparseArray<PointPath> mActivePointers;
    private int[] colors = { Color.BLUE, Color.GREEN, Color.MAGENTA,
            Color.BLACK, Color.CYAN, Color.GRAY, Color.RED, Color.DKGRAY,
            Color.LTGRAY, Color.YELLOW };
    private Paint textPaint;

    public MultiTouchEventView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;

        mActivePointers = new SparseArray<PointPath>();

        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(6f);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextSize(40);

    }

    class PointPath {
        PointF point;
        Path path;
        PointPath(){
            point = new PointF();
            path = new Path();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        PointPath pp = new PointPath();
        int pointerIndex = event.getActionIndex(); // get pointer index from the event object
        int pointerId = event.getPointerId(pointerIndex); // get pointer ID
        int maskedAction = event.getActionMasked(); // get masked (not specific to a pointer) action

        switch (maskedAction) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                pp.point.x = event.getX(pointerIndex);
                pp.point.y = event.getY(pointerIndex);
                pp.path.moveTo(pp.point.x, pp.point.y);
                mActivePointers.put(pointerId, pp);
                break;

            case MotionEvent.ACTION_MOVE:
                for (int size = event.getPointerCount(), i = 0; i < size; i++) {
                    pp = mActivePointers.get(event.getPointerId(i));
                    if (pp != null) {
                        pp.point.x = event.getX(i);
                        pp.point.y = event.getY(i);
                        pp.path.lineTo(pp.point.x, pp.point.y);
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                mActivePointers.remove(pointerId);
                break;
        }
        invalidate(); // Schedules a repaint.
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // draw all pointers
        for (int size = mActivePointers.size(), i = 0; i < size; i++) {
            PointPath pp = mActivePointers.valueAt(i);
            if (pp != null)
                mPaint.setColor(colors[i % 9]);
            //canvas.drawCircle(pp.point.x, pp.point.y, SIZE, mPaint);
            canvas.drawPath(pp.path, mPaint);
        }
        canvas.drawText("Total pointers: " + mActivePointers.size() + " ,  maxPoint: " + maxTouchPoints(mContext), 10, 40 , textPaint);
    }


}