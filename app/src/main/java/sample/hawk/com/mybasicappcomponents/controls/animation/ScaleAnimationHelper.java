package sample.hawk.com.mybasicappcomponents.controls.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;

import java.util.Random;


/**
 * Created by ha271 on 2016/11/15.
 */

class ScaleAnimationHelper extends AnimatorListenerAdapter
        implements ValueAnimator.AnimatorUpdateListener {
    private static class ScaleAnimObject {
        private final View mView;
        private float mToScale;

        public ScaleAnimObject(View view) {
            mView = view;
        }

        public void scaleY(float scale) {
            mView.setScaleY(scale);
        }

        public void setScale(float scale) {
            mToScale = scale;
        }

        public float getScale() {
            return mToScale;
        }
    }

    private ValueAnimator mRepeatAnimator = ValueAnimator.ofInt(0, 1);
    private Interpolator mInterpolator = new DecelerateInterpolator();

    private static final Random s_Random = new Random();
    private ScaleAnimObject[] mAnimObjects;

    private float m_fMinScale = 0.428f;

    public ScaleAnimationHelper(long interval, float minScale, View... views) {
        mRepeatAnimator.setCurrentPlayTime(0);
        mRepeatAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mRepeatAnimator.setRepeatMode(ValueAnimator.RESTART);
        mRepeatAnimator.setDuration(interval);

        if (views != null && views.length > 0) {
            mAnimObjects = new ScaleAnimObject[views.length];
            for (int i = 0; i < views.length; i++) {
                mAnimObjects[i] = new ScaleAnimObject(views[i]);
            }
        }

        m_fMinScale = minScale;
    }

    public void setStart() {
        mRepeatAnimator.addUpdateListener(this);
        mRepeatAnimator.addListener(this);
        mRepeatAnimator.start();
    }

    public void setStop() {
        mRepeatAnimator.end();
        mRepeatAnimator.removeUpdateListener(this);
        mRepeatAnimator.removeListener(this);
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        final long currentTime = animation.getCurrentPlayTime(); // Hawk: Android N changed this API, this value will NOT rest anymore.
        final long totalTime = animation.getDuration();

        float percent = (float) currentTime / (float) totalTime;
        percent = -4f * percent * (percent - 1f);
        percent = percent * mInterpolator.getInterpolation(percent);
        // SMLog.i("currentTime="+currentTime+"   totalTime="+totalTime+"   percent="+percent);

        for (ScaleAnimObject object : mAnimObjects) {
            float toScale = object.getScale() * percent;
            object.scaleY(toScale);
            // SMLog.i("toScale="+toScale);
        }
    }

    @Override
    public void onAnimationRepeat(Animator animation) {
        updateScales();
    }

    @Override
    public void onAnimationStart(Animator animation) {
        updateScales();
    }

    private void updateScales() {
        if(android.os.Build.VERSION.SDK_INT>=24) // Hawk: for solving Android N, the play time will NOT reset.
            mRepeatAnimator.setCurrentPlayTime(0L);
        s_Random.setSeed(System.currentTimeMillis());

        for (ScaleAnimObject object : mAnimObjects) {
            final float nextFloat = s_Random.nextFloat();
            object.setScale(nextFloat < m_fMinScale ? m_fMinScale : nextFloat);
        }
    }
}

