package sample.hawk.com.mybasicappcomponents.debugTest.Exceptions;

import java.util.ArrayList;
import java.util.Iterator;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * http://www.iteye.com/topic/124788
 *
 * Iterator
 * 　　是工作在一個獨立的線程中，並且擁有一個mutex鎖。Iterator被創建之後會建立一個指向原來對象的單鏈索引表，當
 *    原來的對像數量發生變化時，這個索引表的內容不會同步改變，所以當索引指針往後移動的時候就找不到要迭代的對象，
 *    所以按照fail-fast原則Iterator會馬上拋出java.util.ConcurrentModificationException異常。
 * 　　所以Iterator在工作的時候是不允許被迭代的對像被改變的。但你可以使用Iterator本身的方法remove（）來刪除對
 *    象，Iterator.remove（）方法會在刪除當前迭代對象的同時維護索引的一致性。
 */

public class ConcurrentModificationException implements DemoException {

    public void Demo(){
        try{
            SMLog.i("ConcurrentModificationException+++++++++++++");
            SMLog.i("Solve_ForSingleThread()");
            Solve_ForSingleThread();
            SMLog.i("Solve_ForMultiThread()");
            Solve_ForMultiThread();
            SMLog.i("Show_Bug()");
            Show_Bug();
            SMLog.i("ConcurrentModificationException------------");
        } catch (RuntimeException re){
            re.printStackTrace();
        }
    }

    public void Show_Bug() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        // init list
        for(int i=0;i<1000;i++){
            list.add(i);
        }
        // use list by iterator and ArrayList<> both, it will cause this exception
        Iterator<Integer> iterator = list.iterator();
        while(iterator.hasNext()){
            Integer integer = iterator.next(); // control by iterator
            if((integer%2)==1)
                list.remove(integer); // control by ArrayList<> both
        }
    }

    public void Solve_ForSingleThread() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        // init list
        for(int i=0;i<1000;i++){
            list.add(i);
        }
        // use list by iterator only, it will NOT cause this exception
        Iterator<Integer> iterator = list.iterator(); // control by iterator
        while(iterator.hasNext()){
            Integer integer = iterator.next();
            if((integer%2)==1)
                iterator.remove(); // control by iterator only
        }
    }

    public void Solve_ForMultiThread() {
        final ArrayList<Integer> list = new ArrayList<Integer>();
        // init list
        for(int i=0;i<1000;i++){
            list.add(i);
        }
        // use list by iterator only, it will NOT cause this exception
        Thread thread1 = new Thread(){
            public void run() {
                Iterator<Integer> iterator = list.iterator();
                while(iterator.hasNext()){
                    Integer integer = iterator.next(); // control by iterator
                    System.out.println(integer);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread thread2 = new Thread(){
            public void run() {
                Iterator<Integer> iterator = list.iterator();
                while(iterator.hasNext()){
                    Integer integer = iterator.next(); // control by iterator
                    if((integer%2)==1)
                        iterator.remove(); // control by iterator only
                }
            }
        };
        thread1.start();
        thread2.start();
    }


}
