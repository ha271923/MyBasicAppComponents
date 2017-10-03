package sample.hawk.com.mybasicappcomponents.data_structure.basic_ForSingleThread;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/10/3.
 */

public class BasicDataStructActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basicdatastructure_activity);

    }

    public void onClick_BasicDataStructClass(View view){
        BasicDataStructure(Integer.parseInt(view.getTag().toString()));
    }

    private void BasicDataStructure(int datastruct_type){
        switch(datastruct_type){
            case 50000: // basic array
                // int[] arr = new int[5]={0,1,2,3,4}; // ERROR!
                int[] arr = {0,1,2,3,4}; // OK!
                SMLog.i("arr   [0]="+arr[0]+" [1]="+arr[1]+" [2]="+arr[2]+" [3]="+arr[3]+" [4]="+arr[4]);
                int[] arr2 = new int[10];
                // arr2 = {0,1,2,3,4}; // ERROR
                for (int i = 0; i < 10; i++) {
                    arr2[i] = i;
                }
                SMLog.i("arr2  [0]="+arr2[0]+" [1]="+arr2[1]+" [2]="+arr2[2]+" [3]="+arr2[3]+" [4]="+arr2[4]);
                break;
            case 50002: // List
                MyList ml = new MyList();
                ml.show();
                ml.use_case();
                break;
            case 50003: // ArrayList
                MyArrayList mal = new MyArrayList();
                mal.show();
                mal.use_case();
                break;
            case 50004:
                MyArray ae = new MyArray();
                ae.show();
                break;
            case 50009: // LinkedList
                MyLinkedList mll = new MyLinkedList();
                mll.show();
                mll.use_case();
                break;
            case 50011: // Set
                MySet ms = new MySet();
                ms.show();
                ms.use_case();
                break;
            case 50012: // HashSet
                MyHashSet1 mhs = new MyHashSet1();
                // String
                mhs.show();
                mhs.use_case();
                // Student <-- String與Student 兩種用法對比重複資料是如何發生的!
                MyHashSet2 mhs2 = new MyHashSet2();
                mhs2.show();
                mhs2.use_case();
                break;
            case 50015: // Map
                MyMap mm = new MyMap();
                mm.show();
                mm.use_case();

                MyWeakHashMap whm = new MyWeakHashMap();
                whm.show();
                whm.use_case();
                whm.test();
                break;

            case 50019: // SparseArray
                MySparseArray msa = new MySparseArray();
                msa.show();
                msa.use_case();
                break;
            case 50020: // Tree
                Tree tr1= new Tree("1");
                Tree tr11=tr1.addLeaf("1.1");
                Tree tr111=tr11.addLeaf("1.1.1");
                Tree tr112=tr11.addLeaf("1.1.2");
                Tree tr12=tr1.addLeaf("1.2");
                Tree tr121=tr12.addLeaf("1.2.1");
                Tree tr122=tr12.addLeaf("1.2.2");
                Tree tr1221=tr122.addLeaf("1.2.2.1");
                Tree tr1222=tr122.addLeaf("1.2.2.2");
                Tree tr123=tr12.addLeaf("1.2.3");
                tr1.show();
                tr1.use_case();
                break;

            case 50021:
                MyStack mystack = new MyStack();
                mystack.push("1");
                mystack.push("2");
                mystack.push("3");
                mystack.push("4");
                mystack.pop();
                mystack.show();
                break;
            default:
                SMLog.e("Not support this data structure yet");
        }
    }

}
