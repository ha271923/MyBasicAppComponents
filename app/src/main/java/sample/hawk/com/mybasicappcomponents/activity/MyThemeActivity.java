package sample.hawk.com.mybasicappcomponents.activity;

import android.app.Activity;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

import java.io.File;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.data_structure.cache.basic.FileCache;
import sample.hawk.com.mybasicappcomponents.graphic.utils.BitmapUtils;
import sample.hawk.com.mybasicappcomponents.graphic.utils.BlurBuilder;
import sample.hawk.com.mybasicappcomponents.graphic.utils.DrawableUtils;
import sample.hawk.com.mybasicappcomponents.graphic.utils.ImageUtils;
import sample.hawk.com.mybasicappcomponents.graphic.utils.ThemeUtils;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;
import sample.hawk.com.mybasicappcomponents.view.MyTimeView;

import static sample.hawk.com.mybasicappcomponents.graphic.utils.DrawableUtils.drawableToBitmap;

/**
 * Created by ha271 on 2017/5/31.
 */

public class MyThemeActivity extends Activity implements View.OnClickListener {
    private static int mCurrentThemeId;
    private static int mDefaultThemeId;
    private static String mDefaultThemeName;
    private Context mContext;
    private Window  mWind;
    private View mDecorView;
    private LayerDrawable mWindowBkg = null;
    private static final int STATUS_BAR_BKG_ID = -1;
    FileCache mFileCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setTheme(mCurrentThemeId);
        setContentView(R.layout.mytheme_activity);
        // setAlpha_views();
        mFileCache = new FileCache(mContext);
        if(!mFileCache.isFileCached("bluredWallpaper"))
            createBackgroundImage_HwBlur();

        Button button_set_default = (Button) findViewById(R.id.button_set_default);
        Button button_apply_mytheme = (Button) findViewById(R.id.button_apply_mytheme);
        button_set_default.setOnClickListener(this);
        button_apply_mytheme.setOnClickListener(this);
        Button button_set_statusbar_transparent = (Button) findViewById(R.id.button_set_statusbar_transparent);
        button_set_statusbar_transparent.setOnClickListener(this);
        Button button_set_statusbar_bkg = (Button) findViewById(R.id.button_set_statusbar_bkg);
        button_set_statusbar_bkg.setOnClickListener(this);

        CheckBox cb_backgroundImage = (CheckBox) findViewById(R.id.cb_backgroundImage);
        CheckBox cb_uielement_alpha = (CheckBox) findViewById(R.id.cb_uielement_alpha);
        CheckBox cb_colorprimary_alpha = (CheckBox) findViewById(R.id.cb_colorprimary_alpha);
        CheckBox cb_activitybackground_alpha = (CheckBox) findViewById(R.id.cb_activitybackground_alpha);
        cb_backgroundImage.setOnClickListener(this);
        cb_uielement_alpha.setOnClickListener(this);
        cb_colorprimary_alpha.setOnClickListener(this);
        cb_activitybackground_alpha.setOnClickListener(this);

        int[] ThemeColors = new int[]{
                ThemeUtils.getThemeAttrColor(mContext, R.attr.colorPrimary),
                ThemeUtils.getThemeAttrColor(mContext, R.attr.colorAccent),
        };

        mWind= this.getWindow();

        mDecorView = this.getWindow().getDecorView();
        // hideSystemUI(true, this); // hide status bar and nav bar
    }

    void setStatusBarTransparent(Window wind, boolean enable){
        wind.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        wind.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if(enable==true){
            wind.setStatusBarColor(ContextCompat.getColor(this, android.R.color.transparent));
        }else{
            TypedValue typedValueDrawerSelected = new TypedValue();
            getTheme().resolveAttribute(R.attr.colorPrimary, typedValueDrawerSelected, true);
            int colorDrawerItemSelected = typedValueDrawerSelected.data;
            wind.setStatusBarColor(ContextCompat.getColor(this, colorDrawerItemSelected));
        }
    }

    @Override
    public void onClick(View v) {
        int resId = v.getId();
        SMLog.i("resId="+resId);
        switch(resId){
            case R.id.button_set_statusbar_transparent:
                setStatusBarTransparent(mWind, true);
                break;
            case R.id.button_set_statusbar_bkg:
                // setStatusBarBackground();
                setStatusBarBackground((Activity) mContext);
                break;
            case R.id.button_set_default:
                // mCurrentThemeId = ThemeUtils.changeToTheme((Activity)mContext, mDefaultThemeId);
                mCurrentThemeId = ThemeUtils.changeToTheme((Activity)mContext, R.style.AppTheme);
                break;
            case R.id.button_apply_mytheme:
                mCurrentThemeId = ThemeUtils.changeToTheme((Activity)mContext, R.style.MyThemeStyle);
                break;
            case R.id.cb_uielement_alpha:
                if(((CheckBox)v).isChecked())
                    setAlpha_UiElement(true);
                else
                    setAlpha_UiElement(false);
                break;
            case R.id.cb_colorprimary_alpha:
                if(((CheckBox)v).isChecked())
                    setViewToAlphaColorPrimary(true);
                else
                    setViewToAlphaColorPrimary(false);
                break;
            case R.id.cb_activitybackground_alpha:
                if(((CheckBox)v).isChecked())
                    setAlpha_ActivityBackground(true);
                else
                    setAlpha_ActivityBackground(false);
                break;
            case R.id.cb_backgroundImage:
                if(((CheckBox)v).isChecked()) {
                    // set_BackgroundImage_NoneBlur(true);
                    set_BackgroundImage_HwBlur(true);
                    // set_BackgroundImage_Blur(true);

                    //set_statusbarBackground((Activity)mContext);
                }
                else {
                    // set_BackgroundImage_NoneBlur(false);
                    set_BackgroundImage_HwBlur(false);
                    // set_BackgroundImage_Blur(false);
                    //set_statusbarBackground((Activity)mContext);
                }
                break;

        }
    }


    private Drawable getStatusBarBackground() {
        return geStatusBarDrawable();
    }

    private void setStatusBarBackground(Activity activity){
        Window window = activity.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setBackgroundDrawable(getStatusBarBackground());
    }

    // TODO: implementing
    private void setStatusBarBackground() {
        View v = findViewById(android.R.id.primary);
        if (v != null) {
            v.setFitsSystemWindows(true);
        }

        Window win = getWindow();
        if (win != null) {
            // enable translucent mode for status bar
            win.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            // setup the window background color
            if (mWindowBkg == null) {
                // TODO: LayerDrawable to compose a background drawable here!
                win.setBackgroundDrawable(mWindowBkg);
                mWindowBkg.setId(0, STATUS_BAR_BKG_ID);
            }

        }
        switchStatusBarBkg(getResources().getConfiguration().orientation);
    }

    // TODO: implementing
    private void switchStatusBarBkg(int orientation) {
        if (mWindowBkg == null)
            return;
        Resources res = getResources();
        mWindowBkg.setLayerInset(0, 0, 0, 0,
                res.getDisplayMetrics().heightPixels - ImageUtils.getStatusBarHeight(mContext));
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            //Show color
            mWindowBkg.setDrawableByLayerId(STATUS_BAR_BKG_ID, getColorPrimaryDrawable(mContext));
        } else {
            //Show the textture
            Drawable texture = getColorPrimaryDrawable(mContext);
            if (texture != null) {
                mWindowBkg.setDrawableByLayerId(STATUS_BAR_BKG_ID, texture);
            }
        }
    }



    private static Drawable getColorPrimaryDrawable(Context context)
    {
        return new ColorDrawable(ThemeUtils.getThemeAttrColor(context, R.attr.colorPrimary));
    }

    private Drawable getWallPaper() {
        final WallpaperManager wallpaperManager = WallpaperManager.getInstance(mContext);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int screen_Width = metrics.widthPixels;
        int screen_Height = metrics.heightPixels;

        Drawable sourceDrawable = wallpaperManager.getDrawable();

        //int minimumWidth  = sourceDrawable.getMinimumWidth();
        //int minimumHeight = sourceDrawable.getMinimumHeight();

        int intrinsicWidth  = sourceDrawable.getIntrinsicWidth();
        int intrinsicHeight = sourceDrawable.getIntrinsicHeight();


        // diff
        int offsetWidth  = 0;
        int offsetHeight = 0;
        if(intrinsicWidth - screen_Width > 0 ) // image > screen
            offsetWidth = (intrinsicWidth - screen_Width);
        if(intrinsicHeight - screen_Height > 0 ) // image > screen
            offsetHeight = (intrinsicHeight - screen_Height);

        int cropedWidth  = intrinsicWidth-offsetWidth;
        int cropedHeight = intrinsicHeight-offsetHeight;

        int imageOffset_x = 0;
        int imageOffset_y = 0;
        if(offsetWidth>0)
            imageOffset_x = offsetWidth/2;
        if(offsetHeight>0)
            imageOffset_y = offsetHeight/2;

        Bitmap sourceBitmap = drawableToBitmap(sourceDrawable);
        Bitmap targetBitmap = Bitmap.createBitmap(sourceBitmap, imageOffset_x, imageOffset_y, cropedWidth, cropedHeight);
        BitmapDrawable targetDrawable = new BitmapDrawable(getResources(), targetBitmap);

        return targetDrawable;
    }

    private Drawable geStatusBarDrawable() {
        final int statusHeight = 96;
        final WallpaperManager wallpaperManager = WallpaperManager.getInstance(mContext);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int screen_Width = metrics.widthPixels;
        int screen_Height = metrics.heightPixels;

        Drawable sourceDrawable = wallpaperManager.getDrawable();

        //int minimumWidth  = sourceDrawable.getMinimumWidth();
        //int minimumHeight = sourceDrawable.getMinimumHeight();

        int intrinsicWidth  = sourceDrawable.getIntrinsicWidth();
        int intrinsicHeight = sourceDrawable.getIntrinsicHeight();


        // diff
        int offsetWidth  = 0;
        int offsetHeight = 0;
        if(intrinsicWidth - screen_Width > 0 ) // image > screen
            offsetWidth = (intrinsicWidth - screen_Width);
        if(intrinsicHeight - screen_Height > 0 ) // image > screen
            offsetHeight = (intrinsicHeight - screen_Height);

        int cropedWidth  = intrinsicWidth-offsetWidth;
        int cropedHeight = intrinsicHeight-offsetHeight;

        int imageOffset_x = 0;
        int imageOffset_y = 0;
        if(offsetWidth>0)
            imageOffset_x = offsetWidth/2;
        if(offsetHeight>0)
            imageOffset_y = offsetHeight/2;

        Bitmap sourceBitmap = drawableToBitmap(sourceDrawable);
        Bitmap targetBitmap = Bitmap.createBitmap(sourceBitmap, imageOffset_x, imageOffset_y, cropedWidth, statusHeight);
        BitmapDrawable targetDrawable = new BitmapDrawable(getResources(), targetBitmap);

        return targetDrawable;
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

    private void setRootBackgroundImage(boolean enable) {
        LinearLayout RootView = (LinearLayout)findViewById(R.id.mythemeactivity_layout);
        if(enable == true){
            RootView.setBackground(getWallPaper());
            //RootView.setBackground(getResources().getDrawable(R.drawable.android_robot));
        }
        else {
            RootView.setBackground(null);
        }
    }


    private Bitmap JavaBlur(Bitmap bmp, int paramCase){
        long start_time = SystemClock.uptimeMillis();
        switch(paramCase){
            case 1: // 66.5s
                bmp = BitmapUtils.javaBlur( bmp, 0.5f, 50);
                break;
            case 2: // 7.6s
                bmp = BitmapUtils.javaBlur( bmp, 0.2f, 10);
                break;
            case 3: // 2.1s
                bmp = BitmapUtils.getResizedBitmap(bmp, bmp.getWidth()/2, bmp.getHeight()/2);
                bmp = BitmapUtils.javaBlur( bmp, 0.2f, 10);
                break;
            case 4: // BitmapUtils.1.1s
                bmp = BitmapUtils.getResizedBitmap(bmp, bmp.getWidth()/3, bmp.getHeight()/3);
                bmp = BitmapUtils.javaBlur( bmp, 0.2f, 10);
                break;
            case 5: // BitmapUtils.0.74s
                bmp = BitmapUtils.getResizedBitmap(bmp, bmp.getWidth()/4, bmp.getHeight()/4);
                bmp = BitmapUtils.javaBlur( bmp, 0.2f, 10);
                break;
            case 6: // 0.175s
                bmp = BitmapUtils.getResizedBitmap(bmp, bmp.getWidth()/10, bmp.getHeight()/10);
                bmp = BitmapUtils.javaBlur( bmp, 0.2f, 10);
                break;
            case 7: // 0.116s
                bmp = BitmapUtils.getResizedBitmap(bmp, bmp.getWidth()/10, bmp.getHeight()/10);
                bmp = BitmapUtils.javaBlur( bmp, 0.55f, 20);
                break;
        }
        long  end_time = SystemClock.uptimeMillis();
        SMLog.i("Blur paramCase ID= "+paramCase+"     TimeCost = " + (end_time-start_time));
        return bmp;
    }
    // 0.12s , Best Performance, API17+ supported
    private void set_BackgroundImage_HwBlur(boolean enable){
        final LinearLayout RootView = (LinearLayout)findViewById(R.id.mythemeactivity_layout);
        if(enable == true) {
            long start_time = SystemClock.uptimeMillis();
            File bluredWallpaper = mFileCache.getFile("bluredWallpaper");
            if( bluredWallpaper != null ) {
                String imagePath = bluredWallpaper.getPath();
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                Bitmap bitmap = BitmapFactory.decodeFile(imagePath, options);
                RootView.setBackground(new BitmapDrawable(getResources(), bitmap));
                long end_time = SystemClock.uptimeMillis();
                SMLog.i("Apply cached wallpaper     TimeCost = " + (end_time-start_time));
            } else {
                SMLog.i("No cached blurred image for wallpaper");
            }
        }
        else {
            RootView.setBackground(null);
        }
    }

    private void getBackgroundImage_HwBlur(){
        final LinearLayout RootView = (LinearLayout)findViewById(R.id.mythemeactivity_layout);
        long start_time = SystemClock.uptimeMillis();
        File bluredWallpaper = mFileCache.getFile("bluredWallpaper");
        String imagePath = bluredWallpaper.getPath();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath, options);
        RootView.setBackground(new BitmapDrawable(getResources(), bitmap));
        long end_time = SystemClock.uptimeMillis();
        SMLog.i("Apply cached wallpaper     TimeCost = " + (end_time-start_time));
    }

    private void createBackgroundImage_HwBlur(){
        final Drawable wallpaper = getWallPaper();
        Bitmap bmp = DrawableUtils.drawableToBitmap(wallpaper);
        final long start_time = SystemClock.uptimeMillis();
        BlurBuilder.asyncBlur(mContext, bmp, new BlurBuilder.AsyncResponse() {
            @Override
            public void processFinish(Bitmap processedbmp) {
                long end_time = SystemClock.uptimeMillis();
                SMLog.i("Blur HW Algorithm    TimeCost = " + (end_time-start_time));
                BitmapDrawable processedBitmapDrawable = new BitmapDrawable(getResources(), processedbmp);
                Bitmap processedBitmap = DrawableUtils.drawableToBitmap(processedBitmapDrawable);
                if( mFileCache != null )
                    mFileCache.put("bluredWallpaper", processedBitmap);
                // RootView.setBackground(processedBitmapDrawable);
            }
        });
    }


    // 0.12s , Best Performance, API17+ supported
    private void set_BackgroundImage_NoneBlur(boolean enable){
        final LinearLayout RootView = (LinearLayout)findViewById(R.id.mythemeactivity_layout);
        if(enable == true) {
            final Drawable wallpaper = getWallPaper();
            Bitmap bmp = DrawableUtils.drawableToBitmap(wallpaper);
            BitmapDrawable processedBitmapDrawable = new BitmapDrawable(getResources(), bmp);
            RootView.setBackground(processedBitmapDrawable);
        }
        else {
            RootView.setBackground(null);
        }
    }

    private void set_BackgroundImage_Blur(boolean enable) {
        SMLog.i();
        LinearLayout RootView = (LinearLayout)findViewById(R.id.mythemeactivity_layout);
        if(enable == true){
            Drawable wallpaper = getWallPaper();
            Bitmap bmp = DrawableUtils.drawableToBitmap(wallpaper);
            bmp = JavaBlur( bmp, 7);
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
            setTheme(R.style.MyTransparentTheme); // Set here
        }
        else {
            setTheme(mDefaultThemeId); //
            TypedValue typedValueDrawerSelected = new TypedValue();
            getTheme().resolveAttribute(R.attr.colorPrimary, typedValueDrawerSelected, true);
            int colorDrawerItemSelected = typedValueDrawerSelected.data;
            wnd.setBackgroundDrawable(new ColorDrawable(colorDrawerItemSelected));
        }
    }

    private void setViewToAlphaColorPrimary(boolean enable){
        ThemeUtils.setViewToAlphaColorPrimary(mContext, enable,findViewById(R.id.toggleButton));
        ThemeUtils.setViewToAlphaColorPrimary(mContext, enable,findViewById(R.id.mytimeview_id));
    }

    private static boolean bGotThemeId = false;
    @Override // onApplyThemeResource == onThemeChange, Android has no callback named onThemeChange.
    protected void onApplyThemeResource(Resources.Theme theme, int resid, boolean first) {
        super.onApplyThemeResource(theme, resid, first);
        if(mContext != null) { // this callback may early than onCreate()
            if(bGotThemeId == false) { // run only first-enter activity
                mDefaultThemeId = ThemeUtils.getCurrentThemeId(mContext); // the first-time resid is the Default Theme Id too.
                bGotThemeId = true;
            }
            if(mDefaultThemeId != 0) {
                mDefaultThemeName = getResources().getResourceName(mDefaultThemeId);
                SMLog.i(" mDefaultThemeName = " + mDefaultThemeName);
                mCurrentThemeId = mDefaultThemeId;
            }
            if(resid != 0)
                SMLog.i("Current Theme Name = " + getResources().getResourceName(resid));
            createBackgroundImage_HwBlur();
        }
    }



}
