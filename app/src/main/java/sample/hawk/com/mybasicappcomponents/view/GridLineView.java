package sample.hawk.com.mybasicappcomponents.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class GridLineView extends View {

    private boolean showGrid = true;
    private int LINE_INTERVAL = 100; // draw line every 100 pixel

    private final Paint paint = new Paint();
    public GridLineView(Context context) {
        super(context);
        init();
    }

    public GridLineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GridLineView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        paint.setColor(Color.BLACK);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (showGrid) {
            int width = getMeasuredWidth();
            int height = getMeasuredHeight();
            // Vertical lines
            for (int i = 1; i <= (width/LINE_INTERVAL); i++) {
                canvas.drawLine(i*LINE_INTERVAL, 0, i*LINE_INTERVAL, height, paint);
            }

            // Horizontal lines
            for (int i = 1; i <= (height/LINE_INTERVAL); i++) {
                canvas.drawLine(0, i*LINE_INTERVAL, width, i*LINE_INTERVAL, paint);
            }
        }
    }
}