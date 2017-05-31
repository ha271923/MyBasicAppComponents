package sample.hawk.com.mybasicappcomponents.view;

import android.app.ExpandableListActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * copied from ApiDemos ExpandableList1 sample
 */

public class MyExpandableListViewActivity1 extends ExpandableListActivity {


    ExpandableListAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set up our adapter
        mAdapter = new MyExpandableListAdapter();
        setListAdapter(mAdapter);
        registerForContextMenu(getExpandableListView());
    }
    /**
     * Support LongPress Sub-Menu
     *
     * */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.setHeaderTitle("LongPress Sub-Menu:");
        ExpandableListView.ExpandableListContextMenuInfo info = (ExpandableListView.ExpandableListContextMenuInfo) menuInfo;
        int type = ExpandableListView.getPackedPositionType(info.packedPosition);
        int group = ExpandableListView.getPackedPositionGroup(info.packedPosition);
        int child = ExpandableListView.getPackedPositionChild(info.packedPosition);

        if (type == ExpandableListView.PACKED_POSITION_TYPE_GROUP) {
            SMLog.i("LongPress at Group Item");
        } else if (type == ExpandableListView.PACKED_POSITION_TYPE_CHILD) {
            SMLog.i("LongPress at Child Item");
        } else {
            SMLog.i("LongPress at OTHER space");
        }
        menu.add(0, 0, 0, "type=" + type + ", group=" + group + ", child=" + child);
        menu.add(1, 1, 1, "LongPress Item-1");
        menu.add(1, 2, 2, "LongPress Item-2");
        menu.add(1, 3, 2, "LongPress Item-3");

    }

    /**
     * Single Click on Sub-Menu
     * */
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        ExpandableListView.ExpandableListContextMenuInfo info = (ExpandableListView.ExpandableListContextMenuInfo) item.getMenuInfo();
        String title = ((TextView) info.targetView).getText().toString();
        SMLog.i("title=" + title + "item.getItemId()= "+ item.getItemId());
        int type = ExpandableListView.getPackedPositionType(info.packedPosition);
        if (type == ExpandableListView.PACKED_POSITION_TYPE_CHILD) {
            int groupPos = ExpandableListView.getPackedPositionGroup(info.packedPosition);
            int childPos = ExpandableListView.getPackedPositionChild(info.packedPosition);
            SMLog.i("onContextItemSelected()    groupPos="+groupPos+"    childPos="+childPos);
            Toast.makeText(this, title + ": Child " + childPos + " clicked in group " + groupPos, Toast.LENGTH_SHORT).show();
            return true;
        } else if (type == ExpandableListView.PACKED_POSITION_TYPE_GROUP) {
            int groupPos = ExpandableListView.getPackedPositionGroup(info.packedPosition);
            SMLog.i("onContextItemSelected()    groupPos="+groupPos);
            Toast.makeText(this, title + ": Group " + groupPos + " clicked", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onContextItemSelected(item);
    }

    /**
     * Single Click on Menu
     * */
    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        Toast.makeText(this, "child => group=" + groupPosition + ", child=" + childPosition, Toast.LENGTH_SHORT).show();
        SMLog.i("onChildClick()    groupPos="+groupPosition+"    childPos="+childPosition);
        return super.onChildClick(parent, v, groupPosition, childPosition, id);
    }

    /**
     * A simple adapter which maintains an ArrayList of photo resource Ids.
     * Each photo is displayed as an image. This adapter supports clearing the
     * list of photos and adding a new photo.
     *
     */
    public class MyExpandableListAdapter extends BaseExpandableListAdapter {
        // Sample data set.  children[i] contains the children (String[]) for groups[i].
        private String[] groups = {
                "GROUP-0",    // group-0
                "GROUP-1",    // group-1
                "GROUP-2",    // group-2
                "GROUP-3" };  // group-3
        private String[][] children = {
                { "G0_item0", "G0_item1", "G0_item2", "G0_item3", "G0_item4" }, // for group-0
                { "G1_item0", "G1_item1", "G1_item2", "G1_item3", "G1_item4" }, // for group-1
                { "G2_item0", "G2_item1", "G2_item2", "G2_item3", "G2_item4" }, // for group-2
                { "G3_item0", "G3_item1", "G3_item2", "G3_item3", "G3_item4" }  // for group-3
        };
        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return children[groupPosition][childPosition];
        }
        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }
        @Override
        public int getChildrenCount(int groupPosition) {
            return children[groupPosition].length;
        }

        public TextView getGenericView() {
            // Layout parameters for the ExpandableListView
            AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, 200);

            TextView textView = new TextView(MyExpandableListViewActivity1.this);
            textView.setLayoutParams(lp);
            // Center the text vertically
            textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
            // Set the text starting position
            textView.setPaddingRelative(36, 0, 0, 0);
            // Set the text alignment
            textView.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
            return textView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                                 View convertView, ViewGroup parent) {
            TextView textView = getGenericView();
            textView.setText(getChild(groupPosition, childPosition).toString());
            textView.setPadding(140,0,0,0);
            return textView;
        }
        @Override
        public Object getGroup(int groupPosition) {
            return groups[groupPosition];
        }
        @Override
        public int getGroupCount() {
            return groups.length;
        }
        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }
        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
                                 ViewGroup parent) {
            TextView textView = getGenericView();
            textView.setText(getGroup(groupPosition).toString());
            textView.setTextSize(40);
            textView.setTextColor(Color.RED);
            textView.setPadding(100,0,0,0);
            return textView;
        }
        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
        @Override
        public boolean hasStableIds() {
            return true;
        }

    }
}

