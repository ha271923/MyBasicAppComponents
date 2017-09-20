package sample.hawk.com.mybasicappcomponents.graphic.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.ColorInt;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by ha271 on 2017/9/19.
 */

public class DrawableCropped extends BitmapDrawable {
    private Path p = new Path();

    public DrawableCropped(Bitmap b) {
        super(b);
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        p.rewind();
        p.addCircle(bounds.width() / 2, // x
                bounds.height() / 2, // y
                Math.min(bounds.width(), bounds.height()) / 2, // radius
                Path.Direction.CW); // direction
    }

    @Override
    public int getOpacity() {
        return PixelFormat.RGBA_8888;
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    @Override
    public void setColorFilter(@ColorInt int color, @NonNull PorterDuff.Mode mode) {
        super.setColorFilter(color, mode);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        canvas.clipPath(p); // clip at HERE
        super.draw(canvas);
    }

    @Override
    public void setAlpha(@IntRange(from = 0, to = 255) int alpha) {

    }
}
