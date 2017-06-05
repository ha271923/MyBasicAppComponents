package sample.hawk.com.mybasicappcomponents.view;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import sample.hawk.com.mybasicappcomponents.R;

/**
 * Created by ha271 on 2016/12/1.
 */

public class MyLayoutActivity extends Activity {
    RelativeLayout m_rootLayout;
    LinearLayout m_layer2Layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mylayout_activity);
        layout();
        setUiAlpha(0.5f);
    }

    private void layout(){
        m_rootLayout = (RelativeLayout) findViewById(R.id.my_layout);
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            getLayoutInflater().inflate(R.layout.myscrollview, m_rootLayout);
                m_layer2Layout = (LinearLayout) findViewById(R.id.my_scrolllist);
        }
        else{
            setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            getLayoutInflater().inflate(R.layout.myscrollview_horizontal, m_rootLayout);
            m_layer2Layout = (LinearLayout) findViewById(R.id.my_scrolllist_horizontal);
        }
            getLayoutInflater().inflate(R.layout.specific_feedgridview_mopub_native_ads_two_column, m_layer2Layout);
            getLayoutInflater().inflate(R.layout.mylayout, m_layer2Layout);
            getLayoutInflater().inflate(R.layout.mylinearlayout, m_layer2Layout);
            getLayoutInflater().inflate(R.layout.myrelativelayout, m_layer2Layout);
            getLayoutInflater().inflate(R.layout.myframelayout, m_layer2Layout);
            getLayoutInflater().inflate(R.layout.mytablelayout, m_layer2Layout);
            getLayoutInflater().inflate(R.layout.mygridlayout, m_layer2Layout);
            getLayoutInflater().inflate(R.layout.myabsolutelayout, m_layer2Layout);
    }

    private void setUiAlpha(float alpha){
//      m_rootLayout.setBackground(getResources().getDrawable(R.drawable.android_robot));


        ScrollView scrollViewLayout = (ScrollView) findViewById(R.id.my_scrollview);
        scrollViewLayout.setBackground(getResources().getDrawable(R.drawable.android_robot));

        // scrollViewLayout.setAlpha(0.5f); // Alpha the View, layout will included all its child's view.

        FeedGridViewMopubNativeAds_TwoColumn twoColumnLayout = (FeedGridViewMopubNativeAds_TwoColumn) findViewById(R.id.twocolumn_layout);
        // twoColumnLayout.setAlpha(0.5f); // Alpha the View
        setAlpha_ColorPrimary(twoColumnLayout);

    }


    private void setAlpha_ColorPrimary(View v){
        TypedValue typedValueDrawerSelected = new TypedValue();
        getTheme().resolveAttribute(R.attr.colorPrimary, typedValueDrawerSelected, true);
        int colorDrawerItemSelected = typedValueDrawerSelected.data;
        colorDrawerItemSelected = (colorDrawerItemSelected & 0x00FFFFFF) | 0x40000000;
        v.setBackgroundColor(colorDrawerItemSelected);
    }


}
