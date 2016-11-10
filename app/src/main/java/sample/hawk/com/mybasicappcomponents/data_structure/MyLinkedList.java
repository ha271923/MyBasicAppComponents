package sample.hawk.com.mybasicappcomponents.data_structure;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 LinkedList and ArrayList are two different implementations of the List interface. 
 a. ArrayList implements it with a dynamically re-sizing array.
 b. LinkedList implements it with a doubly-linked list. 
     For LinkedList<E>
     • get(int index) is O(n/4) average
     • add(E element) is O(1)
     • add(int index, E element) is O(n/4) average
          but O(1) when index = 0 <--- main benefit of LinkedList<E>
     • remove(int index) is O(n/4) average
     • Iterator.remove() is O(1) <--- main benefit of LinkedList<E>
     ListIterator.add(E element) is O(1) <--- main benefit of LinkedList<E>
 */

public class MyLinkedList implements AccessIF {

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
    @Override
    public void show_by_forloop(){ //使用 for-loop 列出所有元素
        SMLog.i("show_by_forloop ----");
        if(!m_collector.isEmpty()){
            for(int i=0;i<m_collector.size();i++){
                SMLog.i("["+i+"]="+m_collector.get(i));
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
        ListIterator iter = m_collector.listIterator();
        SMLog.i("show_by_iterator ----");
        if(!m_collector.isEmpty()){
            while(iter.hasNext()){
                SMLog.i("["+iter.nextIndex()+"]="+iter.next());
            }
        }
    }
    @Override
    public void show() {
        show_by_forloop();
        show_by_foreach();
        show_by_iterator();
    }
    @Override
    public void use_case(){

    }
}
