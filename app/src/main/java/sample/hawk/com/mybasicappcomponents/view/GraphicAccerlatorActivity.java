package sample.hawk.com.mybasicappcomponents.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import sample.hawk.com.mybasicappcomponents.MyApplication;
import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

import static sample.hawk.com.mybasicappcomponents.utils.ImageUtils.isHardwareAccelerated;

/**
 * https://developer.android.com/guide/topics/graphics/hardware-accel.html
 *
 *  用眼睛觀察, 在開啟HwAccelerator前後的動畫表現平滑度表現, 而非TimeCount數據!
 *
 * 硬件加速並不支持所有2D畫的操作，所以開啟硬件加速可能會對使用自定義組件的應用程序造成影響，問題常常表現在不可見的元素異常和錯誤的像素渲染，
 * 為了解決這個問題android可以讓你選擇啟動或者禁用以下級別的硬件加速：Application, Activity, Window, View
 * 1. Application 級別
 *      在你的Android Manifest 文件中添加  <application>  屬性標記，以便為整個應用程序使用硬件加速。
 *      < applicationandroid:hardwareAccelerated = "true" ...>
 * 2. Activity 級別
 *      如果你的應用程序不能在Application 應用級別表現良好的話，則可以使用對Activity 進行單獨控制。要啟動或者禁用一個Activity 的硬件加速
 *      ，你可以使用activity 的android:hardwareAccelerated 屬性。下面的一個列子使整個Application 啟用硬件加速，但是對一個Activity 禁
 *      止使用硬件加速。
 *      < activity android:hardwareAccelerated = "false" /> </ application >
 * 3. Window 級別
 *      如果你需要更細粒度的控制，你可以通過如下代碼給window 進行加速。
 *      getWindow (). setFlags ( WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED , WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED );
 *      注意：現階段你不能在Window 級別對它禁用硬件加速。
 * 4. View 級別
 *      我們可以對單獨的View 在運行時階段禁用硬件加速。我們可以使用如下代碼：
 *      myView . setLayerType ( View.LAYER_TYPE_HARDWARE , null );
 *
 * */
public class GraphicAccerlatorActivity extends Activity implements View.OnClickListener {
    private Context mContext;
    private View mRootLayout;
    private View mLayout1;
    //private View mLoremIpsum1;
    private View mLayout2;
    //private View mLoremIpsum2;
    private GridLayout mProgressbars1;
    private GridLayout mProgressbars2;
    private CheckBox mViewBoostCB,mWindowBoostCB,mActivityBoostCB,mAppBoostCB;
    private CheckBox mComplexAnimationCheckbox;
    private boolean mViewLayerBoost;
    long start_time,end_time;
    Window mWind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graphicaccelator_activity);
        isHardwareAccelerated(MyApplication.mApplication); // 1. Application Level Accelerator
        isHardwareAccelerated(this);  // 2. Activity Level Accelerator
        isHardwareAccelerated(mWind); // 3. Window Level Accelerator
        mWind = getWindow();
        mContext = this;
        mRootLayout = (LinearLayout) findViewById(R.id.viewlayer_root);
        // mAppBoostCB = (CheckBox) findViewById(R.id.app_boost_checkbox);
        // mAppBoostCB.setOnClickListener(this);
        // mActivityBoostCB = (CheckBox) findViewById(R.id.activity_boost_checkbox);
        // mActivityBoostCB.setOnClickListener(this);
        mWindowBoostCB = (CheckBox) findViewById(R.id.window_boost_checkbox);
        mWindowBoostCB.setOnClickListener(this);
        mViewBoostCB = (CheckBox) findViewById(R.id.view_layers_boost_checkbox);
        mViewBoostCB.setOnClickListener(this);
        mComplexAnimationCheckbox = (CheckBox) findViewById(R.id.complex_anim_checkbox);

        final GestureDetector gdt = new GestureDetector(new GestureListener());
        mRootLayout = configureLayout(R.id.viewlayer_root, R.drawable.android_robot, "Layout_root");
        mLayout1 = configureLayout(R.id.layout_1, R.drawable.bg_layout_1, "Layout_1");
        mLayout1.setAlpha(0.5f);
        mLayout1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(final View view, final MotionEvent event) {
                gdt.onTouchEvent(event);
                return true;
            }
        });

        mLayout2 = configureLayout(R.id.layout_2, R.drawable.bg_layout_2, "Layout_2");
        mLayout2.setAlpha(0); // Start with one of the two hidden
        mLayout2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(final View view, final MotionEvent event) {
                gdt.onTouchEvent(event);
                return true;
            }
        });

        mProgressbars1 = (GridLayout)mLayout1.findViewById(R.id.progressbars);
        mProgressbars2 = (GridLayout)mLayout2.findViewById(R.id.progressbars);

    }

    @Override
    public void onClick(View v) {
        int resId = v.getId();
        SMLog.i("resId=" + resId);
        switch (resId) {
            case R.id.app_boost_checkbox:
                // 1. App Level Accelerator
                //   ONLY <application  android:hardwareAccelerated = "true" /> , default ON
                Toast.makeText(this, "ONLY change it in XML", Toast.LENGTH_LONG).show();
                break;
            case R.id.activity_boost_checkbox:
                // 2. Activity Level Accelerator
                //   ONLY <activity     android:hardwareAccelerated = "true" /> , default ON
                Toast.makeText(this, "ONLY change it in XML", Toast.LENGTH_LONG).show();
                break;
            case R.id.window_boost_checkbox:
                // 3. Windows Level Accelerator
                if(((CheckBox)v).isChecked()){
                    mWind.setFlags( WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED ,
                                    WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED );
                } else {
                    mWind.setFlags( 0 ,
                                    WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED );
                }
                isHardwareAccelerated(mWind); // 3. Window Level Accelerator
                break;
            case R.id.view_layers_boost_checkbox:
                // 4. View Level Accelerator
                //    Now it will Enable/Disable during animating
                if(((CheckBox)v).isChecked()){
                    mViewLayerBoost = true;
                } else {
                    mViewLayerBoost = false;
                }
                break;
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }
    }

    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if(e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                animate();
                return false; // Right to left
            }  else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                animate();
                return false; // Left to right
            }

            if(e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                animate();
                return false; // Bottom to top
            }  else if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                animate();
                return false; // Top to bottom
            }
            return false;
        }
    }

    private View configureLayout(@IdRes int id, @DrawableRes int background, String title) {
        View layout = findViewById(id);
        layout.setBackgroundResource(background);

        TextView titleView = (TextView) layout.findViewById(R.id.title);
        titleView.setText(title);

        return layout;
    }

    private void animate() {
        float layout1Alpha = mLayout1.getAlpha();
        if (layout1Alpha != 0 && layout1Alpha != 0.5f) {
            // Mid-animation; don't animate
            return;
        }

        final View view_layoutIn = layout1Alpha == 0 ? mLayout1 : mLayout2;
        final View view_layoutOut = view_layoutIn == mLayout1 ? mLayout2 : mLayout1;

        int widthPixels = getResources().getDisplayMetrics().widthPixels;
        float scale = .5f;

        view_layoutIn.setTranslationX(widthPixels / 2);
        view_layoutIn.setScaleX(scale);
        view_layoutIn.setScaleY(scale);

        view_layoutIn.animate()
                .alpha(0.5f)
                .translationX(0)
                .scaleX(1)
                .scaleY(1)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        start_time = SystemClock.uptimeMillis();
                        if (mViewLayerBoost) {
                            // 4. View Level Accelerator ( LAYER_TYPE_NONE/SOFTWARE/HARDWARE )
                            // view_layoutIn.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
                            view_layoutIn.setLayerType(View.LAYER_TYPE_HARDWARE, null);
                        }
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if (mViewLayerBoost) {
                            view_layoutIn.setLayerType(View.LAYER_TYPE_NONE, null);
                        }
                    }
                })
                .start();

        view_layoutOut.animate()
                .alpha(0)
                .translationX(-widthPixels / 2)
                .scaleX(scale)
                .scaleY(scale)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        if (mViewLayerBoost) {
                            // 4. View Level Accelerator
                            view_layoutOut.setLayerType(View.LAYER_TYPE_HARDWARE, null);
                        }
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if (mViewLayerBoost) {
                            view_layoutOut.setLayerType(View.LAYER_TYPE_NONE, null);
                        }
                        end_time = SystemClock.uptimeMillis();
                        SMLog.i("TimeCost = " + (end_time-start_time));
                        SMLog.i("4. view_layoutOut.isHardwareAccelerated="+view_layoutOut.isHardwareAccelerated());
                    }
                })
                .start();

        if (mComplexAnimationCheckbox.isChecked()) {
            final View viewIn = view_layoutIn == mLayout1 ? mProgressbars1 : mProgressbars2;
            final View viewOut = view_layoutOut == mLayout2 ? mProgressbars2 : mProgressbars1;

            int heightPixels = getResources().getDisplayMetrics().heightPixels;
            int target = heightPixels / 2;

            viewIn.setTranslationY(target);
            viewIn.setAlpha(0);

            viewIn.animate()
                    .translationY(0)
                    .alpha(0.5f)
                    .start();
            viewOut.animate()
                    .translationY(target)
                    .alpha(0)
                    .start();
        }
        else {
            mProgressbars1.setTranslationY(0);
            mProgressbars1.setAlpha(0.5f);
            mProgressbars2.setTranslationY(0);
            mProgressbars2.setAlpha(0.5f);
        }
    }



}