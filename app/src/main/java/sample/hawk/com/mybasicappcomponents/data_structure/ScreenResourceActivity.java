package sample.hawk.com.mybasicappcomponents.data_structure;

import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * 1. Activity will be destroyed if rotate the device
 * 2. Screen
 *      a. Orientation ( port,land )
 *      b. Inches ( small, normal, large, xlarge )
 *      c. Resolution ( 1440 * 2560 ....)
 *      d. Density ( ldpi,mdpi,hdpi,xhdpi, xxhdpi, xxxhdpi... )
 *
 *      Device: HTC 10 is (port/land)+normal+(1440*2560)+xxxhdpi
 *      Qualifier: port: values-normal,drawable-xxxhdpi
 *                 land: values-w600dp,drawable-xxxhdpi
 *
 *      https://developer.android.com/guide/practices/screens_support.html#DeclaringTabletLayouts
 */

public class ScreenResourceActivity extends Activity {
    EditText et_input;
    String editing_text = new String();
    int local_var1 = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) { // Only First-Time to create Activity will make savedInstanceState==NULL.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myresourcefilter_activity);

        screenRotate(savedInstanceState);
        getDeviceInfo();
        resQualifier();
        SMLog.i("local_var1="+local_var1); // Hawk: this value will always reset to '0' if the screen ever rotated.
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        editing_text = et_input.getText().toString(); // Save: STEP1
        outState.putString("editing_text", editing_text);
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        // TODO Auto-generated method stub
        super.onConfigurationChanged(newConfig);
        TextView screen_orientation = (TextView) findViewById(R.id.tv_screen_orientation);

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            screen_orientation.setText("Landscape");
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            screen_orientation.setText("Portrait");
        }
    }
    void screenRotate(Bundle savedInstanceState){

        et_input = (EditText) findViewById(R.id.et_input);
        if (savedInstanceState != null) {  // Restore: STEP1
            editing_text = savedInstanceState.getString("editing_text");
        } else {
            editing_text = "Try to input text and rotate screen";
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        SMLog.i("onResume --- ");
        et_input.setText(editing_text); // Restore: STEP2
        local_var1 = 1; // Hawk: this value will always reset to '0' if the screen ever rotated.
    }

    @Override
    protected void onPause() {
        super.onPause();
        SMLog.i("onPause --- ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        SMLog.i("onStop --- ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMLog.i("onDestroy --- "); // Rotate screen will destroy the Activity.
    }

    void getDeviceInfo(){
        Display display = getWindowManager().getDefaultDisplay();
        String displayName = display.getName();  // minSdkVersion=17+
        SMLog.i("displayName  = " + displayName);
        ((TextView)findViewById(R.id.tv_display_name))
                .setText(displayName);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int device_height = displayMetrics.heightPixels;
        int device_width = displayMetrics.widthPixels;
        ((TextView)findViewById(R.id.tv_device_width_pixel))
                .setText(String.valueOf(device_width));
        ((TextView)findViewById(R.id.tv_device_height_pixel))
                .setText(String.valueOf(device_height));
        String strDensity = new String();
        if(displayMetrics.density == 0.75){
            strDensity = "ldpi";
        } else if(displayMetrics.density == 1.0){
            strDensity = "mdpi";
        } else if(displayMetrics.density == 1.5){
            strDensity = "hdpi";
        } else if(displayMetrics.density == 2.0){
            strDensity = "xhdpi";
        } else if(displayMetrics.density == 3.0){
            strDensity = "xxhdpi";
        } else if(displayMetrics.density == 4.0){
            strDensity = "xxxhdpi";
        }
        ((TextView)findViewById(R.id.tv_device_density))
                .setText(strDensity);

        String strDensityDPI = new String();
        switch(displayMetrics.densityDpi){
            case DisplayMetrics.DENSITY_LOW:
                strDensityDPI = "DENSITY_LOW";
                break;
            case DisplayMetrics.DENSITY_MEDIUM:
                strDensityDPI = "DENSITY_MEDIUM";
                break;
            case DisplayMetrics.DENSITY_HIGH:
                strDensityDPI = "DENSITY_HIGH";
                break;
            case DisplayMetrics.DENSITY_280:
                strDensityDPI = "DENSITY_280";
                break;
            case DisplayMetrics.DENSITY_XHIGH:
                strDensityDPI = "DENSITY_XHIGH";
                break;
            case DisplayMetrics.DENSITY_XXHIGH:
                strDensityDPI = "DENSITY_XXHIGH";
                break;
            case DisplayMetrics.DENSITY_XXXHIGH:
                strDensityDPI = "DENSITY_XXXHIGH";
                break;
        }
        ((TextView)findViewById(R.id.tv_device_densityDpi))
                .setText(strDensityDPI);

        int screenSize = getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK;
        String strScreenSize = new String();
        switch(screenSize) {
            case Configuration.SCREENLAYOUT_SIZE_XLARGE:
                strScreenSize = "xLarge";
                break;
            case Configuration.SCREENLAYOUT_SIZE_LARGE:
                strScreenSize = "Large";
                break;
            case Configuration.SCREENLAYOUT_SIZE_NORMAL:
                strScreenSize = "Normal";
                break;
            case Configuration.SCREENLAYOUT_SIZE_SMALL:
                strScreenSize = "Small";
                break;
            default:
                strScreenSize = "Unknown";
        }
        ((TextView)findViewById(R.id.tv_device_screenSize))
                .setText(strScreenSize);
    }

    void resQualifier(){
        ((ImageView)findViewById(R.id.iv_mipmap))
                .setImageDrawable(this.getResources().getDrawable(R.mipmap.ic_launcher));

        Resources res = getResources();

        int values_integer_value1= res.getInteger(R.integer.values_integer_value1);
        ((TextView)findViewById(R.id.tv_values_integer_value1))
                .setText(String.valueOf(values_integer_value1));

        String res_value_folder= res.getString(R.string.res_value_folder);
        ((TextView)findViewById(R.id.tv_res_value_folder))
                .setText(res_value_folder);

        boolean screen_portrait= res.getBoolean(R.bool.screen_portrait);
        ((TextView)findViewById(R.id.tv_screen_portrait))
                .setText(screen_portrait? "true":"false"); // Dynamically load bools.xml:id=screen_portrait depends on the current screen orientation.

        ((ImageView)findViewById(R.id.iv_drawable))
                .setImageDrawable(this.getResources().getDrawable(R.drawable.android_robot));

    }


}
