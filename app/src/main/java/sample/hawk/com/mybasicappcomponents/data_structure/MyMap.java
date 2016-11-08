package sample.hawk.com.mybasicappcomponents.data_structure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2016/11/7.
 */

public class MyMap implements AccessIF {

    HashMap<Integer, String> m_hashmap;

    public MyMap(){
        m_hashmap = new HashMap<>();
        m_hashmap.put(1,"Value1");
        m_hashmap.put(2,"Value2");
        m_hashmap.put(3,"Value3");
        m_hashmap.put(4,"Value4");
        m_hashmap.put(5,"Value5");
        m_hashmap.put(6,"Value6");
        m_hashmap.put(7,"Value7");
        m_hashmap.put(8,"Value8");
        m_hashmap.put(9,"Value9");
        m_hashmap.put(10,"Value10");
    }
    @Override
    public void show_by_forloop(){ //使用 for-loop 列出所有元素
        //Iterator iter;
        //iter = m_hashmap.entrySet().iterator(); // Obtaining an iterator for the entry set
        SMLog.i("show_by_forloop ----");
        if(!m_hashmap.isEmpty()){
            for(int i=1;i<(m_hashmap.size()+1);i++){
                //m_hashmap.get(3);
                //Map.Entry me = (Map.Entry)iter.next();
                SMLog.i("["+i+"]="+m_hashmap.get(i));
            }
        }
    }
    @Override
    public void show_by_foreach(){ //使用 for each 列出所有元素
        SMLog.i("show_by_foreach ----");
        if(!m_hashmap.isEmpty()){
            for(Map.Entry<Integer, String> me : m_hashmap.entrySet()){
                SMLog.i("["+me.getKey()+"]="+me.getValue());
            }
        }
    }
    @Override
    public void show_by_iterator(){  //使用 iterator 列出所有元素
        Set entrySet;
        Iterator iter;
        entrySet = m_hashmap.entrySet(); // Getting a Set of Key-value pairs
        iter = entrySet.iterator(); // Obtaining an iterator for the entry set

        SMLog.i("show_by_iterator ----");
        if(!m_hashmap.isEmpty()){
            while(iter.hasNext()){
                Map.Entry me = (Map.Entry)iter.next();
                SMLog.i("["+me.getKey()+"]="+me.getValue());
            }
        }
    }
    @Override
    public void show() {
        show_by_forloop();
        show_by_foreach();
        show_by_iterator();
    }
}
