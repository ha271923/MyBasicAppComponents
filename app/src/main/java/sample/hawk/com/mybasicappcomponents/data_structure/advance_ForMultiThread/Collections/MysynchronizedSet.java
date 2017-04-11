package sample.hawk.com.mybasicappcomponents.data_structure.advance_ForMultiThread.Collections;

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
            executorService.execute(new MysynchronizedSet.ReadTask(set));
            executorService.execute(new MysynchronizedSet.WriteTask(set, i));
        }
        executorService.shutdown();
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
