package sample.hawk.com.mybasicappcomponents.view.TouchEvent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;

import sample.hawk.com.mybasicappcomponents.R;

public class MyTouchEventListViewActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private ListView mListView;
    CheckBox bigInter, bigDispatch, bigOntouch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mytoucheventlistview_activity);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.mytouchevent_textview,
                new String[]{ "0",  "1",  "2",  "3",  "4",  "5",  "6",  "7",  "8",  "9",
                             "10", "11", "12", "13", "14", "15", "16", "17", "18", "19",
                             "20", "21", "22", "23", "24", "25", "26", "27", "28", "29"});
        mListView = (ListView) findViewById(R.id.listview);
        mListView.setAdapter(adapter);
        bigInter = (CheckBox) findViewById(R.id.big_inter);
        bigDispatch = (CheckBox) findViewById(R.id.big_dispatch);
        bigOntouch = (CheckBox) findViewById(R.id.big_ontouch);

        bigInter.setOnCheckedChangeListener(this);
        bigDispatch.setOnCheckedChangeListener(this);
        bigOntouch.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView == bigInter) {
            TouchSettings.BIGINTERFLAG = isChecked;
        } else if (buttonView == bigDispatch) {
            TouchSettings.BIGDISPATCHFLAG = isChecked;
        } else if (buttonView == bigOntouch) {
            TouchSettings.BIGTOUFLAG = isChecked;
        }
    }
}
