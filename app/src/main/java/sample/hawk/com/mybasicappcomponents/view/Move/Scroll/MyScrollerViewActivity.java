package sample.hawk.com.mybasicappcomponents.view.Move.Scroll;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import sample.hawk.com.mybasicappcomponents.R;

/**
 * Created by ha271 on 2017/9/25.
 */

public class MyScrollerViewActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String scrollDirection = getIntent().getStringExtra("ScrollDirection");

        if(scrollDirection.equals("Horizontal"))
            setContentView(R.layout.scrollview_horizontal);
        else
            setContentView(R.layout.scrollview_vertical);

    }
}
