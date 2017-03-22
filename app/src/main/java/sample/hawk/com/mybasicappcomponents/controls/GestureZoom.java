package sample.hawk.com.mybasicappcomponents.controls;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import sample.hawk.com.mybasicappcomponents.R;

/**
 * Created by ha271 on 2017/3/22.
 */

public class GestureZoom extends View {
    private Drawable mImage;
    private float scaleFactor = 1.0f;
    private ScaleGestureDetector scaleGestureDetector;

    public GestureZoom(Context context) {
        super(context);
        mImage = context.getResources().getDrawable(R.drawable.android_robot);
        setFocusable(true);
        mImage.setBounds(0, 0, mImage.getIntrinsicWidth(), mImage.getIntrinsicHeight());
        scaleGestureDetector = new ScaleGestureDetector(context, new ScaleListener());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // Set the mImage bounderies
        canvas.save();
        canvas.scale(scaleFactor, scaleFactor);
        mImage.draw(canvas);
        canvas.restore();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        scaleGestureDetector.onTouchEvent(event);
        invalidate();
        return true;
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            scaleFactor *= detector.getScaleFactor();
            // don't let the object get too small or too large.
            scaleFactor = Math.max(0.1f, Math.min(scaleFactor, 5.0f));
            invalidate();
            return true;
        }
    }
}