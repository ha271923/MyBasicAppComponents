package sample.hawk.com.mybasicappcomponents.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hawk.app2.ValueBar;
import com.hawk.app2.ValueSelector;

import sample.hawk.com.mybasicappcomponents.R;

/**
 * Created by ha271 on 2016/12/7.
 */

public class MyViewActivity2 extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myviewactivity2);

        final ValueSelector valueSelector = (ValueSelector) findViewById(com.hawk.app2.R.id.valueSelector);
        valueSelector.setMinValue(0);
        valueSelector.setMaxValue(100);

        final ValueBar valueBar = (ValueBar) findViewById(com.hawk.app2.R.id.valueBar);
        valueBar.setMaxValue(100);
        valueBar.setAnimated(true);
        valueBar.setAnimationDuration(4000l);

        Button updateButton = (Button) findViewById(com.hawk.app2.R.id.updateButton);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int value = valueSelector.getValue();
                valueBar.setValue(value);

                //code to use Object Animation instead of the built-in ValueBar animation
                //if you use this, be sure the call valueBar.setAnimated(false);
                /*
                ObjectAnimator anim = ObjectAnimator.ofInt(valueBar, "value", valueBar.getValue(), value);
                anim.setDuration(1000);
                anim.start();
                */
            }
        });
    }
}
