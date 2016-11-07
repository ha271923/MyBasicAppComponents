package sample.hawk.com.mybasicappcomponents.data_structure;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2016/11/7.
 */

public class MyLinkedList {

    List<String> m_collector;

    public MyLinkedList(){
        m_collector = new LinkedList<>();
        List<String> numbers2 = Arrays.asList( "9", "1", "8", "3", "2");
        List<String> numbers1 = Arrays.asList( "0", "7", "10", "6", "4");
        m_collector.addAll(numbers2);
        m_collector.add("5");
        m_collector.addAll(numbers1);
        m_collector.remove(4);
        Collections.sort(m_collector);
    }

    public void show_by_listIF(){ //使用 List interface 中所提供的方法列出所有元素
        SMLog.i("show_by_listIF ----");
        if(!m_collector.isEmpty()){
            for(int i=0;i<m_collector.size();i++){
                SMLog.i("["+i+"]="+m_collector.get(i));
            }
        }
    }

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

    public void show_by_iterator(){  //使用 iterator 列出所有元素
        ListIterator iter = m_collector.listIterator();
        SMLog.i("show_by_iterator ----");
        if(!m_collector.isEmpty()){
            while(iter.hasNext()){
                SMLog.i("["+iter.nextIndex()+"]="+iter.next());
            }
        }
    }


}
