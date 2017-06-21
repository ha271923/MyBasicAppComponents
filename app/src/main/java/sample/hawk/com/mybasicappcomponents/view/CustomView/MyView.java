package sample.hawk.com.mybasicappcomponents.view.CustomView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2016/11/10.
 */

public class MyView extends View {

    Paint mPaint = new Paint();

    public MyView(Context context) { // STEP0: constructor without xml
        super(context);
        checkHwCability(this);
    }

    public MyView(Context context, AttributeSet attrs) { // STEP0: constructor with xml attr
        super(context, attrs);
        checkHwCability(this);
    }

    public MyView(Context context, AttributeSet attrs, int defStyle) { // STEP0: constructor with xml attr and style
        super(context, attrs, defStyle);
        checkHwCability(this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) { // STEP1:
        // TODO Auto-generated method stub
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec),
                MeasureSpec.getSize(heightMeasureSpec));
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) { // STEP2:
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) { // STEP3:
        // TODO Auto-generated method stub
        super.onDraw(canvas);
        checkHwCability(canvas);
        testCanvasSaveRestore(canvas);
    }

/**
 * onDraw() 和dispatchDraw（）的區別:
 *
 * View組件的繪製會調用draw(Canvas canvas)方法，draw過程中主要是先畫Drawable背景，對drawable調用setBounds()
 * 然後是draw(Canvas c)方法.有點注意的是背景drawable的實際大小會影響view組件的大小，drawable的實際大小通過
 * getIntrinsicWidth()和getIntrinsicHeight()獲取，當背景比較大時view組件大小等於背景drawable的大小畫完背景後，
 * draw過程會調用onDraw(Canvas canvas)方法，然後就是dispatchDraw (Canvas canvas)方法, dispatchDraw()主要是
 * 分發給子組件進行繪製，我們通常定制組件的時候重寫的是onDraw()方法。值得注意的是ViewGroup容器組件的繪製，當它沒有
 * 背景時直接調用的是dispatchDraw()方法,而繞過了draw()方法，當它有背景的時候就調用draw()方法，而draw()方法裡包含
 * 了dispatchDraw()方法的調用。因此要在ViewGroup上繪製東西的時候往往重寫的是dispatchDraw()方法而不是onDraw()方
 * 法，或者自定制一個Drawable，重寫它的draw(Canvas c)和getIntrinsicWidth(),getIntrinsicHeight()方法，然後設
 * 為背景。
 *
 * */
    @Override
    protected void dispatchDraw(Canvas canvas) { // STEP3-1:
        super.dispatchDraw(canvas);
        drawMyRect(canvas);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////
    //  returns true if the View is attached to a hardware accelerated window.
    private boolean checkHwCability(View view){
        boolean ret = false;
        ret = view.isHardwareAccelerated();
        SMLog.i("View.isHardwareAccelerated="+ret);
        return ret;
    }

    //  returns true if the Canvas is hardware accelerated
    private boolean checkHwCability(Canvas canvas){
        boolean ret = false;
        ret = canvas.isHardwareAccelerated();
        SMLog.i("Canvas.isHardwareAccelerated="+ret);
        return ret;
    }

    // if canvas rotated, the graphic will also rotated.
    private void testCanvasSaveRestore(Canvas canvas) {
        Paint p = new Paint();
        canvas.save(); // save the current canvas settings.
        canvas.rotate(20.86f); // rotate the entire canvas paper.
        drawMyBitmap(canvas);
        canvas.restore(); // restore the canvas settings.
        p.setColor(Color.BLUE);
        canvas.drawRect(new Rect(100,100,200,200), p);
    }
    private void drawMyBitmap(Canvas canvas){
        Paint p = new Paint();
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.android_robot);
        canvas.drawBitmap(bmp,100,200,p);
    }

    private void drawMyRect(Canvas canvas){
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(3);
        canvas.drawRect(30, 30, 80, 80, mPaint);
        mPaint.setStrokeWidth(0);
        mPaint.setColor(Color.CYAN);
        canvas.drawRect(33, 60, 77, 77, mPaint);
        mPaint.setColor(Color.YELLOW);
        canvas.drawRect(33, 33, 77, 60, mPaint);
    }
}
