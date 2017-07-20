package sample.hawk.com.mybasicappcomponents.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import sample.hawk.com.mybasicappcomponents.R;

/**
 * Created by ha271 on 2017/7/20.
 */

public final class AppItemAdapter extends ArrayAdapter {
    private Context mContext;
    private AppItem[] appItems;

    public AppItemAdapter(Context context, int i, AppItem[] appItemArr) {
        super(context, i, appItemArr);
        this.mContext = context;
        this.appItems = appItemArr;
    }

    public final View getView(int i, View view, ViewGroup viewGroup) {
        View inflate = ((LayoutInflater) this.mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE))
                .inflate(R.layout.app_list_row, viewGroup, false);
        ((TextView) inflate.findViewById(R.id.app_name)).setText(this.appItems[i].appLabel);
        ((ImageView) inflate.findViewById(R.id.app_icon)).setImageDrawable(this.appItems[i].iconImage);
        return inflate;
    }
}