package sample.hawk.com.mybasicappcomponents.data_structure.advance_ForMultiThread.synchronized_keyword;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import sample.hawk.com.mybasicappcomponents.data_structure.advance_ForMultiThread.MultiThreadAccessIF;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/4/6.
 */

public class MysynchronizedSet implements MultiThreadAccessIF {

    public void InitData(Set<String> set, int num) {
        for (int i = 0; i < num; i++) {
            set.add("Reading data["+i+"]rrr");
        }
    }

    public void ThreadsAccessData(Set<String> set, int num) {
        ExecutorService executorService = Executors.newFixedThreadPool(num);
        for (int i=0; i<num; i++) {
            executorService.execute(getReadRunnable(set));
            executorService.execute(getWriteRunnable(set, i));
        }
        executorService.shutdown();
    }

    @Override
    public Runnable getReadRunnable(Object obj){
        return new ReadTask((Set<String>)obj);
    }

    @Override
    public Runnable getWriteRunnable(Object obj, int i){
        return new WriteTask((Set<String>)obj, i);
    }

    private static class ReadTask implements Runnable {
        Set<String> set;
        public ReadTask(Set<String> set) {
            this.set = set;
        }
        public void run() {
            synchronized(set) {
                for(String str:set) {
                    SMLog.i(str);
                }
            }
        }
    }

    private static class WriteTask implements Runnable {
        Set<String> set;
        int index;
        public WriteTask(Set<String> set, int index) {
            this.set = set;
            this.index = index;
        }
        public void run() {
            synchronized(set) {
                set.remove(index);
                set.add("Writing data["+index+"]www");
            }
        }
    }

    @Override
    public void error_case(boolean error) {
        final int data_num = 100;
        try{
            if(error == true){  // BUG: ConcurrentModificationException if Multi-Threading accesses
                Set<String> collector = new HashSet<String>();
                InitData(collector, data_num);
                ThreadsAccessData(collector, data_num);
            }
            else{  // ANS: use this data structure
                Set<String> syncSet = Collections.synchronizedSet(new HashSet<String>());
                InitData(syncSet, data_num);
                ThreadsAccessData(syncSet, data_num);
            }
        } catch (RuntimeException re){
            re.printStackTrace();
        }
    }

}
