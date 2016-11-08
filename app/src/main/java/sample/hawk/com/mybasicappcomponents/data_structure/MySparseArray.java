package sample.hawk.com.mybasicappcomponents.data_structure;

import android.util.SparseArray;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 Android:
     SparseArray是為了替換Map而設計的。當你要使用Map時可以考慮使用SparseArray。
     SparseArray的基本使用方法與Map一樣，方法名也一樣。只是remove沒有返回值。
     SparseArray消耗內存比HashMap少，但是速度稍慢。使用的時候做好權衡。時間複雜度大概O(logn)和O(1)的差別。
 */

public class MySparseArray implements AccessIF{

    SparseArray<Object> m_sparseArray;

    public MySparseArray(){
        //new obj
        m_sparseArray = new SparseArray<>() ;
        //put
        m_sparseArray.put(1,"Value1");
        m_sparseArray.put(2,"Value2");
        m_sparseArray.put(3,"Value3");
        m_sparseArray.put(4,"Value4");
        m_sparseArray.put(5,"Value5");
        m_sparseArray.put(6,"Value6");
        m_sparseArray.put(7,"Value7");
        m_sparseArray.put(8,"Value8");
        m_sparseArray.put(9,"Value9");
        m_sparseArray.put(10,"Value10");

        //get
        Object o1 = m_sparseArray.get(1) ;
    }
    @Override
    public void show_by_forloop(){ //使用 for-loop 列出所有元素
        SMLog.i("show_by_forloop ----");
        for(int i=1;i<(m_sparseArray.size()+1);i++){
            SMLog.i("["+i+"]="+m_sparseArray.get(i));
        }
    }
    @Override
    public void show_by_foreach(){ //使用 for each 列出所有元素
        // Hawk: NOT support foreach
    }
    @Override
    public void show_by_iterator(){  //使用 iterator 列出所有元素
        // Hawk: NOT support iterator
    }
    @Override
    public void show() {
        show_by_forloop();
        show_by_foreach();
        show_by_iterator();
    }
}
