package sample.hawk.com.mybasicappcomponents.view;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

import static android.graphics.Color.BLUE;
import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;

/**
 * Created by ha271 on 2017/1/4.
 */

public class MyAdapter extends BaseAdapter {

    int[] colors = {RED,BLUE,GREEN};
    // Note: Integers must be in the range 0 to getViewTypeCount() - 1. IGNORE_ITEM_VIEW_TYPE can also be returned.
    final int TYPE_TEXT = 0;
    final int TYPE_IMAGE = 1;
    private final LayoutInflater mInflater;
    private final String[] mItems;
    Context mContext;

    public MyAdapter(Context context, String[] objects) {
        mContext = context;
        mInflater = ((Activity)context).getLayoutInflater();
        mItems = objects;
    }

    @Override
    public Object getItem(int position) {
        return mItems[position];
    }

    public int getCount() {
        return mItems.length;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position)
    {
        if(position%2==0)
            return TYPE_IMAGE;
        else
            return TYPE_TEXT;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Holder1 holder1 = null;
        Holder2 holder2 = null;
        int type = getItemViewType(position);
        if(convertView == null)
        {
            switch (type) {
                case TYPE_TEXT:
                    SMLog.i("convertView == null" + " position:" + position);
                    holder1=new Holder1();
                    convertView=LayoutInflater.from(mContext).inflate(R.layout.mylistview_text, null);
                    holder1.textView=(TextView)convertView.findViewById(R.id.textView);
                    convertView.setTag(holder1);
                    break;
                case TYPE_IMAGE:
                    SMLog.i("convertView == null" + " position:" + position);
                    holder2=new Holder2();
                    convertView=LayoutInflater.from(mContext).inflate(R.layout.mylistview_image, null);
                    holder2.imageView=(ImageView)convertView.findViewById(R.id.imageView);
                    convertView.setTag(holder2);
                    break;
                default:
                    break;
            }
        }
        else
        {
            SMLog.i("reuse view!!!");
            switch (type) {
                case TYPE_TEXT:
                    holder1=(Holder1)convertView.getTag();
                    break;
                case TYPE_IMAGE:
                    holder2=(Holder2)convertView.getTag();
                    break;
                default:
                    break;
            }
        }

        switch (type)
        {
            case TYPE_TEXT:
                holder1.textView.setText("position: "+position);
                break;
            case TYPE_IMAGE:
                holder2.imageView.setBackgroundColor(colors[position%3]);
                break;
            default:
                break;
        }
        return convertView;
    }



    @Override
    public int getViewTypeCount()
    {
        return 2;
    }

    class Holder1
    {
        public TextView textView;
    }

    class Holder2
    {
        public ImageView imageView;
    }


}
