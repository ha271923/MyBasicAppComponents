package sample.hawk.com.mybasicappcomponents.data_structure.basic_ForSingleThread;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/*
    Output Result: 沒有重複資料
    show_by_forloop() show_by_forloop ----
    show_by_forloop() [0]=22222222
    show_by_forloop() [1]=33333333
    show_by_forloop() [2]=11111111
    show_by_foreach() show_by_foreach ----
    show_by_foreach() [0]=22222222
    show_by_foreach() [1]=33333333
    show_by_foreach() [2]=11111111
    show_by_iterator() show_by_iterator ----
    show_by_iterator() [0]=22222222
    show_by_iterator() [1]=33333333
    show_by_iterator() [2]=11111111
*/
public class MyHashSet1 implements AccessIF {

    Set<String> m_collector;

    public MyHashSet1(){
        m_collector = addData();
    }

    public Set<String> addData() // data type1 = String
    {
        Set<String> set =new HashSet<String>();
        set.add("11111111");
        set.add("22222222");
        set.add("33333333");
        set.add("22222222");
        return set;
    }

    @Override
    public void show_by_forloop(){ //使用 for-loop 列出所有元素
        SMLog.i("show_by_forloop ----");
        Object[] array = m_collector.toArray(); // for indexing, convert to array first.
        if(!m_collector.isEmpty()){
            for(int i = 0; i< m_collector.size(); i++){
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
