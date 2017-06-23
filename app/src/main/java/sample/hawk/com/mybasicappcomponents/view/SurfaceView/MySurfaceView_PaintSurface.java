package sample.hawk.com.mybasicappcomponents.view.SurfaceView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;

import java.io.ByteArrayOutputStream;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

import static sample.hawk.com.mybasicappcomponents.utils.Util.isUiThread;

/**
 * SurfaceView V.S View
 * surfaceView can be updated on the background thread.
 * However, there are more you might care.
     1. surfaceView has dedicate surface buffer while all the view share one surface buffer
        that is allocated by ViewRoot. In another word, surfaceView cost more resources.
     2. surfaceView can not be hardware accelerated (as of JB4.2) while 95% operations on
        normal View are HW accelerated using openGL ES.
     3. More work should be done to create your customized surfaceView. You need to listener
        to the surfaceCreated/Destroy Event, create an render thread, more importantly,
        synchronized the render thread and main thread. However, to customize the View, all
        you need to do is override onDraw method.
     4. The timing to update is different. Normal view update mechanism is constraint or
        controlled by the framework:You call view.invalidate in the UI thread or view.postInvalid
        in other thread to indicate to the framework that the view should be updated. However,
        the view won't be updated immediately but wait until next VSYNC event arrived.
        The easy approach to understand VYSNC is to consider it is as a timer that fire up every 16ms
        for a 60fps screen. In Android, all the normal view update
        (and display actually but I won't talk it today), is synchronized with VSYNC to achieve
        better smoothness. Now,back to the surfaceView, you can render it anytime as you wish.
        However,I can hardly tell if it is an advantage, since the display is also synchronized
        with VSNC, as stated previously.
 *
 *    https://stackoverflow.com/questions/9818593/android-draw-a-line-on-touch-error-on-event
 *
 *    This TOUCH performance is better than OnTouchListener.
 * */
public class MySurfaceView_PaintSurface extends SurfaceView {
    private final String LOG_TAG = this.getClass().getSimpleName();

    //定義繪圖的基本參數：線的width, color；是否擷取狀態；簽名圖示bitmap
    private float mSignatureWidth = 8f;
    private int mSignatureColor = Color.YELLOW;
    private boolean mCapturing = true;
    private Bitmap mSignature = null;

    //定義防止線條有鋸齒的常數
    private static final boolean GESTURE_RENDERING_ANTIALIAS = true;
    private static final boolean DITHER_FLAG = true;

    private Paint mPaint = new Paint();
    private Path mPath = new Path();

    //矩形
    private final Rect mInvalidRect = new Rect();

    private float mX;
    private float mY;

    private float mCurveEndX;
    private float mCurveEndY;

    private int mInvalidateExtraBorder = 10;

    public MySurfaceView_PaintSurface(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public MySurfaceView_PaintSurface(Context context) {
        super(context);
        init();
    }

    public MySurfaceView_PaintSurface(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setWillNotDraw(false);

        mPaint.setAntiAlias(GESTURE_RENDERING_ANTIALIAS);
        mPaint.setColor(mSignatureColor);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(mSignatureWidth);
        mPaint.setDither(DITHER_FLAG);
        mPath.reset();
    }

    @Override
    protected void onDraw(Canvas canvas) { // draw in UiThread
        SMLog.i("tId="+Thread.currentThread()  + "  UiThread="+isUiThread());
        if (mSignature != null) {
            canvas.drawBitmap(mSignature, null, new Rect(0, 0, getWidth(), getHeight()), null);
        } else {
            canvas.drawPath(mPath, mPaint);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (mCapturing) {
            processEvent(event);
            return true;
        } else {
            return false;
        }
    }

    private boolean processEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchDown(event);
                invalidate();
                return true;

            case MotionEvent.ACTION_MOVE:
                Rect rect = touchMove(event);
                if (rect != null) {
                    invalidate(rect);
                }
                return true;

            case MotionEvent.ACTION_UP:
                touchUp(event, false);
                invalidate();
                return true;

            case MotionEvent.ACTION_CANCEL:
                touchUp(event, true);
                invalidate();
                return true;
        }
        return false;
    }

    private void touchUp(MotionEvent event, boolean b) {
        // TODO Auto-generated method stub
    }

    private Rect touchMove(MotionEvent event) {
        Rect areaToRefresh = null;

        final float x = event.getX();
        final float y = event.getY();

        final float previousX = mX;
        final float previousY = mY;

        areaToRefresh = mInvalidRect;

        // start with the curve end
        final int border = mInvalidateExtraBorder;
        areaToRefresh.set((int) mCurveEndX - border, (int) mCurveEndY - border,
                (int) mCurveEndX + border, (int) mCurveEndY + border);

        float cX = mCurveEndX = (x + previousX) / 2;
        float cY = mCurveEndY = (y + previousY) / 2;

        mPath.quadTo(previousX, previousY, cX, cY);

        // union with the control point of the new curve
        areaToRefresh.union((int) previousX - border, (int) previousY - border, (int) previousX + border, (int) previousY + border);

        // union with the end point of the new curve
        areaToRefresh.union((int) cX - border, (int) cY - border, (int) cX + border, (int) cY + border);

        mX = x;
        mY = y;

        return areaToRefresh;

    }

    private void touchDown(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        mX = x;
        mY = y;
        mPath.moveTo(x, y);

        final int border = mInvalidateExtraBorder;
        mInvalidRect.set((int) x - border, (int) y - border, (int) x + border, (int) y + border);

        mCurveEndX = x;
        mCurveEndY = y;
    }

    public void clear() {
        mSignature = null;
        mPath.rewind();
        // Repaints the entire view.
        invalidate();
    }

    public void setSignatureBitmap(Bitmap signature) {
        mSignature = signature;
        invalidate();
    }

    public Bitmap getSignatureBitmap() {
        if (mSignature != null) {
            return mSignature;
        } else if (mPath.isEmpty()) {
            return null;
        } else {
            Bitmap bmp = Bitmap.createBitmap(getWidth(), getHeight(),
                    Bitmap.Config.ARGB_8888);
            Canvas c = new Canvas(bmp);
            c.drawPath(mPath, mPaint);
            return bmp;
        }
    }

    public byte[] getSignaturePNG() {
        return getSignatureBytes(Bitmap.CompressFormat.PNG, 0);
    }

    public byte[] getSignatureJPEG(int quality) {
        return getSignatureBytes(Bitmap.CompressFormat.JPEG, quality);
    }

    private byte[] getSignatureBytes(Bitmap.CompressFormat format, int quality) {
        Log.d(LOG_TAG, "getSignatureBytes() path is empty: " + mPath.isEmpty());
        Bitmap bmp = getSignatureBitmap();
        if (bmp == null) {
            return null;
        } else {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            getSignatureBitmap().compress(format, quality, stream);
            return stream.toByteArray();
        }
    }
}
