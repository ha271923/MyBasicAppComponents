package sample.hawk.com.mybasicappcomponents.data_structure;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/*
Output Result: 仍然有重複資料,因為你並沒有告訴Set，什麼樣的Student實例才算是重複
    show_by_forloop2() show_by_forloop ----
    show_by_forloop2() [0]=(BBBBBB, 22222222)
    show_by_forloop2() [1]=(CCCCCC, 33333333)
    show_by_forloop2() [2]=(AAAAAA, 11111111)
    show_by_forloop2() [3]=(BBBBBB, 22222222) <-- 重複!!
    show_by_foreach2() show_by_foreach ----
    show_by_foreach2() [0]=(BBBBBB),(22222222)
    show_by_foreach2() [1]=(CCCCCC),(33333333)
    show_by_foreach2() [2]=(AAAAAA),(11111111)
    show_by_foreach2() [3]=(BBBBBB),(22222222) <-- 重複!!
    show_by_iterator2() show_by_iterator ----
    show_by_iterator2() [0]=((BBBBBB, 22222222)
    show_by_iterator2() [1]=((CCCCCC, 33333333)
    show_by_iterator2() [2]=((AAAAAA, 11111111)
    show_by_iterator2() [3]=((BBBBBB, 22222222) <-- 重複!!
*/
public class MyHashSet2 implements AccessIF {

    class Student {
        private String name;
        private String number;
        Student(String name, String number) {
            this.name = name;
            this.number = number;
        }
        @Override
        public String toString()  {
            return String.format("(%s, %s)", name, number);
        }
    }

    Set<Student> m_collector;

    public MyHashSet2(){
        m_collector = addData();
    }


    public Set<Student> addData()  // data type2 = Student
    {
        Set<Student> set = new HashSet<>();
        set.add(new Student("AAAAAA", "11111111"));
        set.add(new Student("BBBBBB", "22222222"));
        set.add(new Student("CCCCCC", "33333333"));
        set.add(new Student("BBBBBB", "22222222")); // Hawk: 注意! 這筆資料雖然內容重覆, 但是Hash值是不同的, 這將導致Set會將其視為新資料
        return set;
    }

    public void show_by_forloop(){ //使用 for-loop 列出所有元素
        SMLog.i("show_by_forloop ----");
        Object[] array = m_collector.toArray(); // for indexing, convert to array first.
        if(!m_collector.isEmpty()){
            for(int i=0;i<m_collector.size();i++){
                SMLog.i("["+i+"]="+array[i]);
            }
        }
    }
    public void show_by_foreach(){ //使用 for each 列出所有元素
        int i=0;
        SMLog.i("show_by_foreach ----");
        if(!m_collector.isEmpty()){
            for(Student student : m_collector){
                SMLog.i("["+i+"]=("+student.name+"),("+student.number+")"); // show by my format
                i++;
            }
        }
    }
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
    public void show() {
        show_by_forloop();
        show_by_foreach();
        show_by_iterator();
    }
    public void use_case(){

    }


}
