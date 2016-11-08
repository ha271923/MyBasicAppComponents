package sample.hawk.com.mybasicappcomponents.data_structure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2016/11/7.
 */

public class MySet implements AccessIF {

    Set<String> m_collector;

    public MySet(){
        m_collector = new HashSet();
        List<String> numbers2 = Arrays.asList( "9", "1", "8", "3", "2");
        List<String> numbers1 = Arrays.asList( "0", "7", "10", "6", "4");
        m_collector.addAll(numbers2);
        m_collector.add("5");
        m_collector.addAll(numbers1);
        m_collector.remove(4);
        List m_collector_list = new ArrayList(m_collector); // for sort, you have to convert to List.
        Collections.sort(m_collector_list);
    }
    @Override
    public void show_by_forloop(){ //使用 for-loop 列出所有元素
        SMLog.i("show_by_forloop ----");
        Object[] array = m_collector.toArray(); // for indexing, convert to array first.
        if(!m_collector.isEmpty()){
            for(int i=0;i<m_collector.size();i++){
                SMLog.i("["+i+"]="+array[i]);
            }
        }
    }
    @Override
    public void show_by_foreach(){ //使用 for each 列出所有元素
        int i=0;
        SMLog.i("show_by_foreach ----");
        if(!m_collector.isEmpty()){
            for(String str : m_collector){
                SMLog.i("["+i+"]="+str);
                i++;
            }
        }
    }
    @Override
    public void show_by_iterator(){  //使用 iterator 列出所有元素
        int i=0;
        Iterator iter = m_collector.iterator();
        SMLog.i("show_by_iterator ----");
        if(!m_collector.isEmpty()){
            while(iter.hasNext()){
                SMLog.i("["+i+"]="+iter.next());
                i++;
            }
        }
    }
}
