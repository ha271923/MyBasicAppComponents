package sample.hawk.com.mybasicappcomponents.view.ListView;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/5/25.
 */

public class MyExpandableListViewActivity extends Activity implements ExpandableListView.OnGroupClickListener, ExpandableListView.OnChildClickListener {
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myexpendablelistview_activity);
        ExpandableListView elv = (ExpandableListView)findViewById(R.id.mExpandableListView);
        elv.setDividerHeight(2);
        elv.setGroupIndicator(null);
        elv.setClickable(true);
        elv.setOnChildClickListener(this);
        elv.setOnGroupClickListener(this);
        // Create 2 Groups
        List<Map<String, String>> groups = getGroupParents();
        List<List<Map<String, String>>> childs = getChildData();

        MyExpandableAdapter viewAdapter = new MyExpandableAdapter(this, groups, childs);
        elv.setAdapter(viewAdapter);
    }


    public List<Map<String, String>> getGroupParents() {
        List<Map<String, String>> groups = new ArrayList<Map<String, String>>();
        Map<String, String> group1 = new HashMap<String, String>();
        group1.put("group", "Expandable Group1");
        groups.add(group1);
        Map<String, String> group2 = new HashMap<String, String>();
        group2.put("group", "Expandable Group2");
        groups.add(group2);
        return groups;
    }
    private List<List<Map<String, String>>> getChildData() {
        List<List<Map<String, String>>> childs = new ArrayList<List<Map<String, String>>>();
        // Add Items for Group1
        List<Map<String, String>> child1 = new ArrayList<Map<String, String>>();
        Map<String, String> child1Data1 = new HashMap<String, String>();
        child1Data1.put("child", "child1Data1");
        child1.add(child1Data1);
        Map<String, String> child1Data2 = new HashMap<String, String>();
        child1Data2.put("child", "child1Data2");
        child1.add(child1Data2);
        // Add Items for Group2
        List<Map<String, String>> child2 = new ArrayList<Map<String, String>>();
        Map<String, String> child2Data1 = new HashMap<String, String>();
        child2Data1.put("child", "child2Data1");
        child2.add(child2Data1);
        //用一個list物件保存所有的二級清單資料
        childs.add(child1);
        childs.add(child2);
        return childs;
    }

    @Override
    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
        SMLog.i("onGroupClick()  id="+id+"   groupPosition="+groupPosition);
        return false;
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        SMLog.i("  onChildClick()  id="+id+"   groupPosition="+groupPosition+"    childPosition"+childPosition);
        return false;
    }

    class MyExpandableAdapter extends BaseExpandableListAdapter
    {
        private Context context;
        List<Map<String, String>> groups;
        List<List<Map<String, String>>> childs;

        public MyExpandableAdapter(Context context, List<Map<String, String>> groups, List<List<Map<String, String>>> childs)
        {
            this.groups = groups;
            this.childs = childs;
            this.context = context;
        }

        @Override
        public Object getChild(int groupPosition, int childPosition)
        {
            return childs.get(groupPosition).get(childPosition);
        }

        @Override
        public long getChildId(int groupPosition, int childPosition)
        {
            return childPosition;
        }
        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent)
        {
            String text = groups.get(groupPosition).get("group");
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            LinearLayout linearLayout = (LinearLayout) layoutInflater.inflate(R.layout.myexpandablelistview_group, null);
            TextView textView = (TextView)linearLayout.findViewById(R.id.group_tv);
            textView.setText(text);
            return linearLayout;
        }
        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent)
        {
            @SuppressWarnings("unchecked")
            String text = ((Map<String, String>) getChild(groupPosition, childPosition)).get("child");
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            LinearLayout linearLayout = (LinearLayout) layoutInflater.inflate(R.layout.myexpandablelistview_item, null);
            TextView textView = (TextView) linearLayout.findViewById(R.id.child_tv);
            textView.setText(text);
            ImageView imageView = (ImageView)linearLayout.findViewById(R.id.child_iv);
            imageView.setImageResource(R.drawable.android_robot);
            return linearLayout;
        }

        @Override
        public int getChildrenCount(int groupPosition)
        {
            return childs.get(groupPosition).size();
        }

        @Override
        public Object getGroup(int groupPosition)
        {
            return groups.get(groupPosition);
        }

        @Override
        public int getGroupCount()
        {
            return groups.size();
        }

        @Override
        public long getGroupId(int groupPosition)
        {
            return groupPosition;
        }



        @Override
        public boolean hasStableIds()
        {
            return false;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition)
        {
            return true;
        }

        @Override
        public void onGroupCollapsed(int groupPosition) {
            super.onGroupCollapsed(groupPosition);
        }

        @Override
        public void onGroupExpanded(int groupPosition) {
            super.onGroupExpanded(groupPosition);
        }
    }
}