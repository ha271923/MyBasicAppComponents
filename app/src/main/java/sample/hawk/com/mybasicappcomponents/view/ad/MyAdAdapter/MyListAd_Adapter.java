package sample.hawk.com.mybasicappcomponents.view.ad.MyAdAdapter;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

import static sample.hawk.com.mybasicappcomponents.view.ad.MyAdAdapter.VisibilityTracker.VisibilityTrackerListener;

/**
 * Created by ha271 on 2017/2/21.
 */

// TODO: not finish yet.
public class MyListAd_Adapter extends BaseAdapter{
    private Context mContext;
    private Cursor mCursor=null;
    private ArrayList<Contact> mContacts;
    private MyListAd_Loader mAd_loader;
    @NonNull private final VisibilityTracker mVisibilityTracker;

    // demo for callback initialization
    private IMyListAd_CallBack EMPTY_LISTENER =
            new IMyListAd_CallBack() {
                @Override
                public void onAdLoaded(final int position) {}
            };
    @NonNull
    private IMyListAd_CallBack mMyListAd_Activity_Listener = EMPTY_LISTENER;
    @NonNull
    private IMyListAd_CallBack mMyListAd_Loader_Listener = EMPTY_LISTENER;


    public void setClassListener(@Nullable final IMyListAd_CallBack listener) {
        SMLog.i();
        mMyListAd_Activity_Listener = (listener == null) ? EMPTY_LISTENER : listener;
    }

    static public class Contact {
        String Name;
        String Phone;
    }
    class ViewHolder
    {
        public TextView tvName;
        public TextView tvPhone;
    }

    public MyListAd_Adapter(Context context) {
        super();
        mContext = context;
        mContacts = new ArrayList<Contact>();

        // callback for Loader ------------------------------
        mMyListAd_Loader_Listener = new IMyListAd_CallBack() {
            @Override
            public void onAdLoaded(final int position) { // B: same API name, diff behavior by diff callback listener
                mMyListAd_Activity_Listener.onAdLoaded(position);
                notifyDataSetChanged();
            }
        };
        mAd_loader = new MyListAd_Loader(context);
        mAd_loader.setClassListener(mMyListAd_Loader_Listener);
        ((MyListAd_Activity)mContext).setClassListener(mMyListAd_Activity_Listener);

        mVisibilityTracker = new VisibilityTracker((MyListAd_Activity)mContext);
        mVisibilityTracker.setVisibilityTrackerListener(new VisibilityTrackerListener() {
            @Override
            public void onVisibilityChanged(@NonNull final List<View> visibleViews,
                                            final List<View> invisibleViews) {

                SMLog.i("VisibilityTracker's onVisibilityChanged()");
            }
        });
    }

    public void LoadAd(){
        // Start to execute after setListeners done.
        mContacts = mAd_loader.getAllContacts();
    }

    ////////////// Callbacks for Android ListView ///////////////////////////////////////////////////////////////////////////////
    @Override
    public Object getItem(int position) {
        return mContacts.get(position);
    }
    @Override
    public int getCount() {
        return mContacts.size();
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if(convertView == null){ // new view
            viewHolder = new ViewHolder();
            convertView= LayoutInflater.from(mContext).inflate(R.layout.mylistviewholder, null);
            viewHolder.tvName=(TextView)convertView.findViewById(R.id.tvName);
            String Name = mContacts.get(position).Name;
            viewHolder.tvName.setText(Name);
            viewHolder.tvPhone=(TextView)convertView.findViewById(R.id.tvPhone);
            String Phone = mContacts.get(position).Phone;
            viewHolder.tvPhone.setText(Phone);
            convertView.setTag(viewHolder);
        }
        else { // reuse view
            viewHolder=(ViewHolder)convertView.getTag();
            viewHolder.tvName=(TextView)convertView.findViewById(R.id.tvName);
            viewHolder.tvName.setText(mContacts.get(position).Name);
            viewHolder.tvPhone=(TextView)convertView.findViewById(R.id.tvPhone);
            viewHolder.tvPhone.setText(mContacts.get(position).Phone);
        }

        // Tracker Item21
        if(viewHolder.tvPhone.getText().equals("21")) {
            mVisibilityTracker.addView(convertView, 50); // minPercentageViewed >= 50%
            SMLog.i("VisibilityTracker is tracking Phone="+viewHolder.tvPhone.getText());
        }
        return convertView;
    }

    public void destroy() {
        mVisibilityTracker.destroy();
    }

}

