package sample.hawk.com.mybasicappcomponents.view.CustomView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.ArcShape;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.RectShape;
import android.view.MotionEvent;
import android.view.View;

import sample.hawk.com.mybasicappcomponents.graphic.utils.DrawableUtils;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/9/20.
 */

public class MyShapeDrawableView extends View {
    static private ShapeDrawable[] mDrawable = new ShapeDrawable[3];

    private enum MyShapes {
        Oval,
        Arc,
        Rect,
        RoundRect,
    }

    public MyShapeDrawableView(Context context) {
        super(context);

        mDrawable[0] = createShapeDrawable(MyShapes.Oval, Color.YELLOW);
        mDrawable[1] = createShapeDrawable(MyShapes.Arc, Color.BLUE);
        mDrawable[2] = createShapeDrawable(MyShapes.Rect, Color.RED);
    }

    protected void onDraw(Canvas canvas) {
        mDrawable[0].draw(canvas);
        mDrawable[1].draw(canvas);
        mDrawable[2].draw(canvas);
    }


    private static ShapeDrawable createShapeDrawable(MyShapes shape, int color){
        int x=0;
        int y=0;
        int width=0;
        int height=0;
        ShapeDrawable outDrawable = null;
        switch(shape){
            case Oval:
                x += 100; y += 100;
                width = 800; height = 1200;
                outDrawable = new ShapeDrawable(new OvalShape());
                outDrawable.getPaint().setColor(color);
                outDrawable.setBounds(x, y, x + width, y + height);
                break;
            case Arc:
                x += 200; y += 200;
                width = 700; height = 600;
                outDrawable = new ShapeDrawable(new ArcShape(30f, 120f));
                outDrawable.getPaint().setColor(color);
                outDrawable.setBounds(x, y, x + width, y + height);
                break;
            case Rect:
                x += 300; y += 300;
                width = 100; height = 1400;
                outDrawable = new ShapeDrawable(new RectShape());
                outDrawable.getPaint().setColor(color);
                outDrawable.setBounds(x, y, x + width, y + height);
                break;
            case RoundRect:

                break;
        }
        return outDrawable;
    }

    static Drawable selectedDrawable = null;
    private PointF last = new PointF();

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean handled = false;
        PointF curr = new PointF(event.getX(), event.getY());

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // DrawableUtils.moveDrawable(mDrawable[2], (int)point.x, (int)point.y);
                selectedDrawable = getTouchedDrawable((int)curr.x, (int)curr.y);
                if(selectedDrawable != null) {
                    last.set(curr);
                    invalidate();
                    handled = true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if(selectedDrawable != null) {
                    float deltaX = curr.x - last.x;
                    float deltaY = curr.y - last.y;
                    SMLog.i("deltaX="+deltaX+"   deltaY="+deltaY);
                    DrawableUtils.moveDrawable(selectedDrawable, (int)deltaX, (int)deltaY);
                    last.set(curr);
                    invalidate();
                    handled = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                selectedDrawable = null;
                PointF reset = new PointF();
                last.set(reset);
                curr.set(reset);
                break;
            default:
                return false;
        }
        return super.onTouchEvent(event) || handled;
    }


    private Drawable getTouchedDrawable(final int xTouch, final int yTouch) {
        for (Drawable drawable : mDrawable) {
            if(drawable.getBounds().contains(xTouch, yTouch))
                return drawable;
        }
        return null;
    }




}