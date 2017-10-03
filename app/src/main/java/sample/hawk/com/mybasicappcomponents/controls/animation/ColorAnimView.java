package sample.hawk.com.mybasicappcomponents.controls.animation;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.view.View;

import static android.graphics.Color.BLUE;
import static android.graphics.Color.RED;

/**
 * Created by ha271 on 2016/11/8.
 */

public class ColorAnimView extends View {

    public ColorAnimView(Context context) {
        super(context);
        // Animate background color
        // Note that setting the background color will automatically invalidate the
        // view, so that the animated color, and the bouncing balls, get redisplayed on
        // every frame of the animation.
        ValueAnimator colorAnim = ObjectAnimator.ofInt(this, "backgroundColor", RED, BLUE);
        colorAnim.setDuration(3000);
        colorAnim.setEvaluator(new ArgbEvaluator());
        colorAnim.setRepeatCount(ValueAnimator.INFINITE);
        colorAnim.setRepeatMode(ValueAnimator.REVERSE);
        colorAnim.start();
    }
}
