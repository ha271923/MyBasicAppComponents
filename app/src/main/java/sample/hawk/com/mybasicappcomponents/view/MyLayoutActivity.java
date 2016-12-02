package sample.hawk.com.mybasicappcomponents.view;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

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
        setContentView(R.layout.mylayout);
        layout();
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
            getLayoutInflater().inflate(R.layout.mylinearlayout, m_layer2Layout);
            getLayoutInflater().inflate(R.layout.myrelativelayout, m_layer2Layout);
            getLayoutInflater().inflate(R.layout.myframelayout, m_layer2Layout);
            getLayoutInflater().inflate(R.layout.mytablelayout, m_layer2Layout);
            getLayoutInflater().inflate(R.layout.mygridlayout, m_layer2Layout);
            getLayoutInflater().inflate(R.layout.myabsolutelayout, m_layer2Layout);
    }

}
