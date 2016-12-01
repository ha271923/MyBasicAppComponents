package sample.hawk.com.mybasicappcomponents.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;

import sample.hawk.com.mybasicappcomponents.R;

/**
 * Created by ha271 on 2016/12/1.
 */

public class MyLayoutActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mylayout);

        LinearLayout rootLayout = (LinearLayout) findViewById(R.id.my_layout);
        getLayoutInflater().inflate(R.layout.myscrollview, rootLayout);
        LinearLayout layer2Layout = (LinearLayout) findViewById(R.id.my_scrolllist);
            getLayoutInflater().inflate(R.layout.mylinearlayout, layer2Layout);
            getLayoutInflater().inflate(R.layout.myrelativelayout, layer2Layout);
            getLayoutInflater().inflate(R.layout.myframelayout, layer2Layout);
            getLayoutInflater().inflate(R.layout.mytablelayout, layer2Layout);
            getLayoutInflater().inflate(R.layout.mygridlayout, layer2Layout);
            getLayoutInflater().inflate(R.layout.myabsolutelayout, layer2Layout);

    }
}
