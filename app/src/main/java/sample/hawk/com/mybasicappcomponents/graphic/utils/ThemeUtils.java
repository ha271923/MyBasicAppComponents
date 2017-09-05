package sample.hawk.com.mybasicappcomponents.graphic.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.support.annotation.AttrRes;
import android.util.TypedValue;
import android.view.View;

import sample.hawk.com.mybasicappcomponents.R;

import static sample.hawk.com.mybasicappcomponents.utils.Util.CallHideAPI;

/**
 * Created by ha271 on 2017/9/5.
 */

public class ThemeUtils {

    public static int getCurrentThemeId(Context context){
        return (Integer)CallHideAPI(context, "getThemeResId");
    }

    public static int getThemeAttrColor(Context context, @AttrRes int colorAttr) {
        TypedArray array = context.obtainStyledAttributes(null, new int[]{colorAttr});
        try {
            return array.getColor(0, 0);
        } finally {
            array.recycle();
        }
    }

    // Set the theme of the activity, according to the configuration.
    public static int changeToTheme(Activity activity, int themeId) {
        activity.finish();
        activity.startActivity(new Intent(activity, activity.getClass()));
        return themeId;
    }


    public static void setViewToAlphaColorPrimary(Context context, boolean enable, View v){
        TypedValue typedValueDrawerSelected = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.colorPrimary, typedValueDrawerSelected, true);
        int colorDrawerItemSelected = typedValueDrawerSelected.data;
        if(enable == true)
            colorDrawerItemSelected = (colorDrawerItemSelected & 0x00FFFFFF) | 0x40000000;
        v.setBackgroundColor(colorDrawerItemSelected);
    }
}
