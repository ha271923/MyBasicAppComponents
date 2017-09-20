package sample.hawk.com.mybasicappcomponents.view.CustomView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.ArcShape;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.RectShape;
import android.view.View;

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

    static int x;
    static int y;
    static int width;
    static int height;

    private static ShapeDrawable createShapeDrawable(MyShapes shape, int color){
        ShapeDrawable outDrawable = null;
        x += 100;
        y += 100;
        switch(shape){
            case Oval:
                width = 800;
                height = 1200;
                outDrawable = new ShapeDrawable(new OvalShape());
                outDrawable.getPaint().setColor(color);
                outDrawable.setBounds(x, y, x + width, y + height);
                break;
            case Arc:
                width = 700;
                height = 600;
                outDrawable = new ShapeDrawable(new ArcShape(30f, 120f));
                outDrawable.getPaint().setColor(color);
                outDrawable.setBounds(x, y, x + width, y + height);
                break;
            case Rect:
                width = 100;
                height = 1400;
                outDrawable = new ShapeDrawable(new RectShape());
                outDrawable.getPaint().setColor(color);
                outDrawable.setBounds(x, y, x + width, y + height);
                break;
            case RoundRect:

                break;
        }
        return outDrawable;
    }


}