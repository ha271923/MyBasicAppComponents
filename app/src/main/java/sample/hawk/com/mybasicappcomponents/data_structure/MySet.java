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
    @Override
    public void show() {
        show_by_forloop();
        show_by_foreach();
        show_by_iterator();
    }
    @Override
    public void use_case(){
        Set<String> admins = new HashSet<>(Arrays.asList("Justin", "caterpillar"));
        Set<String> users = new HashSet<>(Arrays.asList("momor", "hamini", "Justin"));
        SMLog.i("Has Justin in the admin group? "+admins.contains("Justin")); // 是否在站長群? true
        Set<String> intersection = new HashSet<>(admins);
        intersection.retainAll(users); // 同時是站長群也是使用者群的?
        SMLog.i("Who has admin AND usr account both? "+intersection);  // [Justin]
        Set<String> union = new HashSet<>(admins);
        union.addAll(users);  // 是站長群或是使用者群的?
        SMLog.i("Who is  admin OR usr account? "+union);  // [momor, hamini, Justin, caterpillar]
        Set<String> adminsButNotUsers = new HashSet<>(admins);
        adminsButNotUsers.removeAll(users);  // 站長群但不使用者群的?
        SMLog.i("Who has the ONLY account? "+adminsButNotUsers);  // [caterpillar]
        Set<String> xor = new HashSet<>(union);
        xor.removeAll(intersection);  // 只有站長群或使用者群唯一身分?
        SMLog.i("Who has admin XOR usr account? "+xor); // [momor, hamini, caterpillar]
        SMLog.i("Have all usr   accounts in the admin group? "+admins.containsAll(users));  //
        SMLog.i("Have all admin accounts in the usr   group? "+users.containsAll(admins));  //
    }
}
