package sample.hawk.com.mybasicappcomponents.activity.Tips;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import sample.hawk.com.mybasicappcomponents.R;

/**
 * Created by ha271 on 2017/6/27.
 */

public class ToolTipsActivity extends Activity implements OnClickListener {

    ToolTipsWindow tipWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tooltips_activity);
        tipWindow = new ToolTipsWindow(ToolTipsActivity.this);
    }

    @Override
    public void onClick(View anchor) {
        if (!tipWindow.isTooltipShown())
            tipWindow.showToolTip(anchor);
    }

    @Override
    protected void onDestroy() {
        if (tipWindow != null && tipWindow.isTooltipShown())
            tipWindow.dismissTooltip();
        super.onDestroy();
    }
}