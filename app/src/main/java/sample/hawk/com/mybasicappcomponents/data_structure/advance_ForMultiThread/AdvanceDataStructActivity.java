package sample.hawk.com.mybasicappcomponents.data_structure.advance_ForMultiThread;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.data_structure.advance_ForMultiThread.Volatile_Sync_Atom.MyMultiThreadTest;
import sample.hawk.com.mybasicappcomponents.data_structure.advance_ForMultiThread.synchronized_keyword.MyHashTable;
import sample.hawk.com.mybasicappcomponents.data_structure.advance_ForMultiThread.synchronized_keyword.MyStack2;
import sample.hawk.com.mybasicappcomponents.data_structure.advance_ForMultiThread.synchronized_keyword.MyVector;
import sample.hawk.com.mybasicappcomponents.data_structure.advance_ForMultiThread.synchronized_keyword.MysynchronizedCollection;
import sample.hawk.com.mybasicappcomponents.data_structure.advance_ForMultiThread.synchronized_keyword.MysynchronizedList;
import sample.hawk.com.mybasicappcomponents.data_structure.advance_ForMultiThread.synchronized_keyword.MysynchronizedMap;
import sample.hawk.com.mybasicappcomponents.data_structure.advance_ForMultiThread.synchronized_keyword.MysynchronizedSet;
import sample.hawk.com.mybasicappcomponents.data_structure.advance_ForMultiThread.concerent.MyConcurrentHashMap;
import sample.hawk.com.mybasicappcomponents.data_structure.advance_ForMultiThread.concerent.MyCopyOnWriteArrayList;
import sample.hawk.com.mybasicappcomponents.data_structure.advance_ForMultiThread.concerent.MyCopyOnWriteArraySet;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/10/3.
 */

public class AdvanceDataStructActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.advancedatastructure_activity);

    }

    public void onClick_AdvanceDataStructClass(View view) {
        AdvanceDataStructure(Integer.parseInt(view.getTag().toString()));
    }

    private void AdvanceDataStructure(int datastruct_type) {
        switch (datastruct_type) {
            case 20005: // WITH volatile, synchronized, Atom
                MyMultiThreadTest mmtt = new MyMultiThreadTest();
                mmtt.Test(true);
                break;

            case 20006: // WITHOUT volatile, synchronized, Atom
                MyMultiThreadTest mmtt2 = new MyMultiThreadTest();
                mmtt2.Test(false);
                break;

            case 50001: // Collection structure
                MysynchronizedCollection msc = new MysynchronizedCollection();
                msc.error_case(false);
                //msc.error_case(true);
                break;
            case 50005:
                MysynchronizedList msl = new MysynchronizedList();
                msl.error_case(false);
                msl.error_case(true);
                break;
            case 50006:
                MyCopyOnWriteArrayList mcowal = new MyCopyOnWriteArrayList();
                mcowal.error_case(false);
                //mcowal.error_case(true);
                break;
            case 50007:
                MyVector mv = new MyVector();
                mv.error_case(false);
                // mv.error_case(true);
                break;
            case 50008:
                MyStack2 mstk = new MyStack2();
                mstk.error_case(false);
                // ms.error_case(true);
                break;
            case 50013:
                MysynchronizedSet mss = new MysynchronizedSet();
                mss.error_case(false);
                //mss.error_case(true);
                break;
            case 50014:
                MyCopyOnWriteArraySet mcowas = new MyCopyOnWriteArraySet();
                mcowas.error_case(false);
                //mcowas.error_case(true);
                break;
            case 50016:
                MysynchronizedMap msm = new MysynchronizedMap();
                msm.error_case(false);
                //msm.error_case(true);
                break;
            case 50017:
                MyConcurrentHashMap mcchm = new MyConcurrentHashMap();
                mcchm.error_case(false);
                // mcchm.error_case(true);
                break;
            case 50018:
                MyHashTable mht = new MyHashTable();
                mht.error_case(false);
                //mht.error_case(true);
                break;

            default:
                SMLog.e("Not support this data structure yet");
        }
    }
}