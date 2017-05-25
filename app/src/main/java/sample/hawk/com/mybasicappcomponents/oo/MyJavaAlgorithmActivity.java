package sample.hawk.com.mybasicappcomponents.oo;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.oo.sort.MyMergeSort;
import sample.hawk.com.mybasicappcomponents.oo.sort.MyQuickSort;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;
import sample.hawk.com.mybasicappcomponents.view.MyListRowDataItem;

/**
 * Created by ha271 on 2017/5/24.
 */

public class MyJavaAlgorithmActivity extends ListActivity {
    private Context mContext;
    private ListView listV;
    List<MyListRowDataItem> rowItems = new ArrayList<MyListRowDataItem>();
    MyJavaAlgorithmActivity.MyListDataAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        /**
         * Create ListView Items by:
         *   MyListRowDataItem(int type, int photoId, String name, String time)
         */
        rowItems.add(new MyListRowDataItem(0,0 , "Sort","")); // Channel 1
        rowItems.add(new MyListRowDataItem(1,R.drawable.time_complexity_curve, "QuickSort","Om[nlog(n)],T[nlog(n)],bO[n^2]"));
        rowItems.add(new MyListRowDataItem(1,R.drawable.time_complexity_curve, "MergeSort","Om[nlog(n)],T[nlog(n)],bO[nlog(n)]"));
        rowItems.add(new MyListRowDataItem(1,R.drawable.time_complexity_curve, "HeapSort", "Om[nlog(n)],T[nlog(n)],bO[nlog(n)]"));
        rowItems.add(new MyListRowDataItem(0,0,"Search","")); // Channel 2
        rowItems.add(new MyListRowDataItem(1,R.drawable.time_complexity_curve, "",""));
        rowItems.add(new MyListRowDataItem(1,R.drawable.time_complexity_curve, "",""));
        rowItems.add(new MyListRowDataItem(1,R.drawable.time_complexity_curve, "",""));
        rowItems.add(new MyListRowDataItem(1,R.drawable.time_complexity_curve, "",""));
        rowItems.add(new MyListRowDataItem(0,0,"Other","")); // Channel 3
        rowItems.add(new MyListRowDataItem(1,R.drawable.time_complexity_curve, "",""));
        rowItems.add(new MyListRowDataItem(1,R.drawable.time_complexity_curve, "",""));
        rowItems.add(new MyListRowDataItem(1,R.drawable.time_complexity_curve, "",""));
        rowItems.add(new MyListRowDataItem(1,R.drawable.time_complexity_curve, "",""));

        // Get DB Adapter
        adapter = new MyJavaAlgorithmActivity.MyListDataAdapter(this, rowItems);
        // Link DB with View
        setContentView(R.layout.mylist_activity);
        listV=(ListView)findViewById(R.id.myactivitylist);
        listV.setAdapter(adapter);
        listV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
                MyListDataAdapter.ViewHolder vholder = (MyListDataAdapter.ViewHolder)view.getTag();
                String className = ""+vholder.txtTitle.getText();
                startClassByName(className);
                startGifAnimationByName(className);
            }
        });
    }

    void startClassByName(String classname){
        // Sort Algorithm
        if(classname.contains("Sort")==true) {
            int[] IntArray = new int[1000];
            CreateRandomData(IntArray);
            SMLog.i("Before " + classname + " +++");
            show_data(IntArray);
            SMLog.i("Before " + classname + " ---");
            if (classname.equals("QuickSort")) {
                MyQuickSort quicksort = new MyQuickSort(IntArray);
                quicksort.algorithm();
            }
            if (classname.equals("MergeSort")) {
                MyMergeSort mergesort = new MyMergeSort(IntArray);
                mergesort.algorithm();
            }
            SMLog.i("After " + classname + " +++");
            show_data(IntArray);
            SMLog.i("After " + classname + " ---");
        }
    }

    void startGifAnimationByName(String classname){
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        WebView view = new WebView(mContext);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        builder.setTitle(classname);
        builder.setView(view);
        builder.create().show();
        if(classname.equals("QuickSort")){
            view.loadUrl("https://upload.wikimedia.org/wikipedia/commons/6/6a/Sorting_quicksort_anim.gif");
        }
        if(classname.equals("MergeSort")){
            view.loadUrl("https://upload.wikimedia.org/wikipedia/commons/c/cc/Merge-sort-example-300px.gif");
        }
        if(classname.equals("HeapSort")){
            view.loadUrl("https://en.wikipedia.org/wiki/Heapsort#/media/File:Sorting_heapsort_anim.gif");
        }

    }

    void startActivityByName(String activityname){
        if(activityname!=null){
            Class classObject = null;
            try {
                classObject = Class.forName(activityname);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if(classObject!=null){
                Intent intent=new Intent(mContext,classObject);
                mContext.startActivity(intent);
            }
        }
    }

    public void CreateRandomData(int[] IntArray){
        Random r=new Random();
        for(int i=0; i<IntArray.length; i++){
            IntArray[i]=r.nextInt(1000);
        }
    }

    public class MyListDataAdapter extends BaseAdapter {
        private LayoutInflater myInflater;
        private List<MyListRowDataItem> items;

        public MyListDataAdapter(Context context, List<MyListRowDataItem> items){
            myInflater = LayoutInflater.from(context);
            this.items = items;
        }
        @Override
        public int getCount() { return items.size(); }
        @Override
        public Object getItem(int arg0) { return items.get(arg0); }
        @Override
        public long getItemId(int position) { return items.indexOf(getItem(position)); }


        private class ViewHolder {
            TextView txtTitle;
            TextView txtTime;
            ImageView picPhoto;
            String startClass;
            public ViewHolder(ImageView picPhoto, TextView txtTitle, TextView txtTime){
                this.picPhoto = picPhoto;
                this.txtTitle = txtTitle;
                this.txtTime = txtTime;
                this.startClass = startClass;
            }
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            MyJavaAlgorithmActivity.MyListDataAdapter.ViewHolder holder = null;

            if(convertView==null){
                convertView = myInflater.inflate(R.layout.mylist_rowitem, null); // one listview item's view layout
                holder = new MyListDataAdapter.ViewHolder(
                        (ImageView) convertView.findViewById(R.id.photo),
                        (TextView) convertView.findViewById(R.id.title),
                        (TextView) convertView.findViewById(R.id.time)
                );
                convertView.setTag(holder); // setTag
            }else{
                holder = (MyJavaAlgorithmActivity.MyListDataAdapter.ViewHolder) convertView.getTag(); // getTag
            }

            MyListRowDataItem Item = (MyListRowDataItem)getItem(position);
            // type_num={      [0]:channel, [1]:Item,   [2]:time }
            int type_num = Item.getType();
            int image_vis[] =  {View.GONE,  View.VISIBLE,View.VISIBLE};
            int color_back[] = {Color.BLUE, Color.BLACK, Color.BLACK};

            int picPhotoId = Item.getPhotoId();
            holder.picPhoto.setImageResource(picPhotoId);
            holder.picPhoto.setVisibility(image_vis[type_num]);

            int color_title[] ={Color.WHITE,Color.WHITE, Color.YELLOW};
            String strTitle = Item.getName();
            holder.txtTitle.setText(strTitle);
            holder.txtTitle.setTextColor(color_title[type_num]);
            holder.txtTitle.setBackgroundColor(color_back[type_num]);

            int color_time[] = {Color.WHITE,Color.WHITE, Color.YELLOW};
            int time_vis[] =   {View.GONE,  View.VISIBLE,View.VISIBLE};
            String strTime = Item.getTime();
            holder.txtTime.setText(strTime);
            holder.txtTime.setTextColor(color_time[type_num]);
            holder.txtTime.setBackgroundColor(color_back[type_num]);
            holder.txtTime.setVisibility(time_vis[type_num]);
            holder.txtTime.setTextSize(14);

            return convertView;
        }
    }

    public void show_data(int[] IntArray) {
        for(int i=0; i<IntArray.length; i=i+10){
            SMLog.i("["+i+"] "+IntArray[i]+" "+IntArray[(i+1)]+" "+IntArray[(i+2)]+" "+IntArray[(i+3)]+" "+IntArray[(i+4)]+" "+IntArray[(i+5)]+" "+IntArray[(i+6)]+" "+IntArray[(i+7)]+" "+IntArray[(i+8)]+" "+IntArray[(i+9)]);
        }
    }

}
