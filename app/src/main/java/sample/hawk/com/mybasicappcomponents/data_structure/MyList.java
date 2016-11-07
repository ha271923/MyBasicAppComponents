package sample.hawk.com.mybasicappcomponents.data_structure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2016/11/7.
 */

public class MyList {
    List<String> m_collector;

    public MyList(){
        List<String> numbers2 = Arrays.asList( "9", "1", "8", "3", "2");
        List<String> numbers1 = Arrays.asList( "0", "7", "10", "6", "4");
        m_collector = new ArrayList<>();
        m_collector.addAll(numbers2);
        m_collector.add("5");
        m_collector.addAll(numbers1);
        m_collector.remove(3);
        Collections.sort(m_collector);

    }

    public void show(){
        if(!m_collector.isEmpty()){
            for(int i=0;i<m_collector.size();i++){
                SMLog.i("["+i+"]="+m_collector.get(i));
            }
        }
    }

    public boolean query(String str) {
         return m_collector.contains(str);
    }

    public int getIdx(String str) {
        return m_collector.indexOf(str);
    }
}
