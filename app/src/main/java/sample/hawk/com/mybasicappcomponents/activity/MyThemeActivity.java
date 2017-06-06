package sample.hawk.com.mybasicappcomponents.activity;

import android.app.Activity;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.AttrRes;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;
import sample.hawk.com.mybasicappcomponents.view.MyTimeView;

import static sample.hawk.com.mybasicappcomponents.utils.ImageUtils.drawableToBitmap;
import static sample.hawk.com.mybasicappcomponents.utils.ImageUtils.fastblur;
import static sample.hawk.com.mybasicappcomponents.utils.ImageUtils.getResizedBitmap;
import static sample.hawk.com.mybasicappcomponents.utils.Util.CallHideAPI;

/**
 * Created by ha271 on 2017/5/31.
 */

public class MyThemeActivity extends Activity implements View.OnClickListener {
    private static int mCurrentThemeId;
    private static int mDefaultThemeId;
    private static String mDefaultThemeName;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setTheme(mCurrentThemeId);
        setContentView(R.layout.mytheme_activity);
        // setAlpha_views();

        Button button_set_default = (Button) findViewById(R.id.button_set_default);
        Button button_apply_mytheme = (Button) findViewById(R.id.button_apply_mytheme);
        button_set_default.setOnClickListener(this);
        button_apply_mytheme.setOnClickListener(this);

        CheckBox cb_backgroundImage = (CheckBox) findViewById(R.id.cb_backgroundImage);
        CheckBox cb_uielement_alpha = (CheckBox) findViewById(R.id.cb_uielement_alpha);
        CheckBox cb_colorprimary_alpha = (CheckBox) findViewById(R.id.cb_colorprimary_alpha);
        CheckBox cb_activitybackground_alpha = (CheckBox) findViewById(R.id.cb_activitybackground_alpha);
        cb_backgroundImage.setOnClickListener(this);
        cb_uielement_alpha.setOnClickListener(this);
        cb_colorprimary_alpha.setOnClickListener(this);
        cb_activitybackground_alpha.setOnClickListener(this);

        int[] ThemeColors = new int[]{
                getThemeAttrColor(mContext, R.attr.colorPrimary),
                getThemeAttrColor(mContext, R.attr.colorAccent),
        };


    }

    void getThemeParameters(Context context){
        int colorPrimary = getThemeAttrColor(context, R.attr.colorPrimary);
        SMLog.i("colorPrimary= "+ colorPrimary );
    }
    private static int getThemeAttrColor(Context context, @AttrRes int colorAttr) {
        TypedArray array = context.obtainStyledAttributes(null, new int[]{colorAttr});
        try {
            return array.getColor(0, 0);
        } finally {
            array.recycle();
        }
    }

    @Override
    public void onClick(View v) {
        int resId = v.getId();
        SMLog.i("resId="+resId);
        switch(resId){
            case R.id.button_set_default:
                changeToTheme((Activity)mContext, mDefaultThemeId);
                break;
            case R.id.button_apply_mytheme:
                changeToTheme((Activity)mContext, R.style.MyThemeStyle);
                break;
            case R.id.cb_uielement_alpha:
                if(((CheckBox)v).isChecked())
                    setAlpha_UiElement(true);
                else
                    setAlpha_UiElement(false);
                break;
            case R.id.cb_colorprimary_alpha:
                if(((CheckBox)v).isChecked())
                    setAlpha_ColorPrimary(true);
                else
                    setAlpha_ColorPrimary(false);
                break;
            case R.id.cb_activitybackground_alpha:
                if(((CheckBox)v).isChecked())
                    setAlpha_ActivityBackground(true);
                else
                    setAlpha_ActivityBackground(false);
                break;
            case R.id.cb_backgroundImage:
                if(((CheckBox)v).isChecked())
                    set_BackgroundImage_Blur(true);
                else
                    set_BackgroundImage_Blur(false);
                break;
        }
    }


    // Set the theme of the activity, according to the configuration.
    public void changeToTheme(Activity activity, int theme) {
        mCurrentThemeId = theme;
        activity.finish();
        activity.startActivity(new Intent(activity, activity.getClass()));

    }

    private Drawable getWallPaper() {
        final WallpaperManager wallpaperManager = WallpaperManager.getInstance(mContext);
        return wallpaperManager.getDrawable();
    }

    private void setAlpha_UiElement(boolean enable){
        if(enable == true) {
            ((ToggleButton)findViewById(R.id.toggleButton)).getBackground().setAlpha(128); // Alpha for an UI element
            ((MyTimeView)findViewById(R.id.mytimeview_id)).setAlpha(0.5f); // Alpha for an UI element
        }
        else {
            ((ToggleButton)findViewById(R.id.toggleButton)).getBackground().setAlpha(255); // Alpha for an UI element
            ((MyTimeView)findViewById(R.id.mytimeview_id)).setAlpha(1.0f); // Alpha for an UI element
        }
    }

    private void set_BackgroundImage(boolean enable) {
        LinearLayout RootView = (LinearLayout)findViewById(R.id.mythemeactivity_layout);
        if(enable == true){
            RootView.setBackground(getWallPaper());
            //RootView.setBackground(getResources().getDrawable(R.drawable.android_robot));
        }
        else {
            RootView.setBackground(null);
        }
    }


    private Bitmap BlurAlgorithms(Bitmap bmp, int algorithmID){
        long start_time = SystemClock.uptimeMillis();
        switch(algorithmID){
            case 1: // 66.5s
                bmp = fastblur( bmp, 0.5f, 50);
                break;
            case 2: // 7.6s
                bmp = fastblur( bmp, 0.2f, 10);
                break;
            case 3: // 2.1s
                bmp = getResizedBitmap(bmp, bmp.getWidth()/2, bmp.getHeight()/2);
                bmp = fastblur( bmp, 0.2f, 10);
                break;
            case 4: // 1.1s
                bmp = getResizedBitmap(bmp, bmp.getWidth()/3, bmp.getHeight()/3);
                bmp = fastblur( bmp, 0.2f, 10);
                break;
            case 5: // 0.74s
                bmp = getResizedBitmap(bmp, bmp.getWidth()/4, bmp.getHeight()/4);
                bmp = fastblur( bmp, 0.2f, 10);
                break;
            case 6: // 0.175s
                bmp = getResizedBitmap(bmp, bmp.getWidth()/10, bmp.getHeight()/10);
                bmp = fastblur( bmp, 0.2f, 10);
                break;
        }
        long  end_time = SystemClock.uptimeMillis();
        SMLog.i("Blur Algorithm ID= "+algorithmID+"     TimeCost = " + (end_time-start_time));
        return bmp;
    }

    private void set_BackgroundImage_Blur(boolean enable) {
        SMLog.i();
        LinearLayout RootView = (LinearLayout)findViewById(R.id.mythemeactivity_layout);
        if(enable == true){
            Drawable wallpaper = getWallPaper();
            Bitmap bmp = drawableToBitmap(wallpaper);
            bmp = BlurAlgorithms( bmp, 6);
            wallpaper = new BitmapDrawable(getResources(), bmp);
            RootView.setBackground(wallpaper);
            //RootView.setBackground(getResources().getDrawable(R.drawable.android_robot));
        }
        else {
            RootView.setBackground(null);
        }
    }

    private void setAlpha_ActivityBackground(boolean enable){
        LinearLayout RootView = (LinearLayout)findViewById(R.id.mythemeactivity_layout);
        Window wnd = getWindow();
        if(enable == true){ // BUG: the transparent effect is show unknown background.
            wnd.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // Alpha for the Activity Background
        }
        else {
            TypedValue typedValueDrawerSelected = new TypedValue();
            getTheme().resolveAttribute(R.attr.colorPrimary, typedValueDrawerSelected, true);
            int colorDrawerItemSelected = typedValueDrawerSelected.data;
            wnd.setBackgroundDrawable(new ColorDrawable(colorDrawerItemSelected));
        }
    }

    private void setAlpha_ColorPrimary(boolean enable){
        setAlpha_ColorPrimary(enable,findViewById(R.id.toggleButton));
        setAlpha_ColorPrimary(enable,findViewById(R.id.mytimeview_id));
    }

    private void setAlpha_ColorPrimary(boolean enable, View v){
        TypedValue typedValueDrawerSelected = new TypedValue();
        getTheme().resolveAttribute(R.attr.colorPrimary, typedValueDrawerSelected, true);
        int colorDrawerItemSelected = typedValueDrawerSelected.data;
        if(enable == true)
            colorDrawerItemSelected = (colorDrawerItemSelected & 0x00FFFFFF) | 0x40000000;
        v.setBackgroundColor(colorDrawerItemSelected);
    }


    @Override
    protected void onApplyThemeResource(Resources.Theme theme, int resid, boolean first) {
        super.onApplyThemeResource(theme, resid, first);
        if(mDefaultThemeId == 0 ) { // run only first-enter activity
            mDefaultThemeId = getCurrentThemeId(); // the first-time resid is the Default Theme Id too.
            mDefaultThemeName = getResources().getResourceName(mDefaultThemeId);
            SMLog.i(" mDefaultThemeName = " + mDefaultThemeName);
            mCurrentThemeId = mDefaultThemeId;
        }
        SMLog.i("Current Theme Name = " + getResources().getResourceName(resid));
    }

    int getCurrentThemeId(){
        return (Integer)CallHideAPI(this,"getThemeResId");
    }

}
