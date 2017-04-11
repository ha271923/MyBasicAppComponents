package sample.hawk.com.mybasicappcomponents.data_structure.advance_ForMultiThread.Collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import sample.hawk.com.mybasicappcomponents.data_structure.advance_ForMultiThread.MultiThreadAccessIF;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/4/6.
 */

public class MysynchronizedCollection implements MultiThreadAccessIF {

    public void InitData(Collection<String> collect, int num) {
        for (int i = 0; i < num; i++) {
            collect.add("Reading data["+i+"]rrr");
        }
    }

    public void ThreadsAccessData(Collection<String> collect, int num) {
        ExecutorService executorService = Executors.newFixedThreadPool(num);
        for (int i=0; i<num; i++) {
            executorService.execute(new MysynchronizedCollection.ReadTask(collect));
            executorService.execute(new MysynchronizedCollection.WriteTask(collect, i));
        }
        executorService.shutdown();
    }

    private static class ReadTask implements Runnable {
        Collection<String> collect;
        public ReadTask(Collection<String> collect) {
            this.collect = collect;
        }
        public void run() {
            synchronized(collect) {
                for(String str:collect) {
                    SMLog.i(str);
                }
            }
        }
    }

    private static class WriteTask implements Runnable {
        Collection<String> collect;
        int index;
        public WriteTask(Collection<String> collect, int index) {
            this.collect = collect;
            this.index = index;
        }
        public void run() {
            synchronized(collect) {
                collect.remove(index);
                collect.add("Writing data["+index+"]www");
            }
        }
    }

    @Override
    public void error_case(boolean error) {
        final int data_num = 100;
        try{
            if(error == true){  // BUG: ConcurrentModificationException if Multi-Threading accesses
                Collection<String> arrayList = new ArrayList<String>();
                InitData(arrayList, data_num);
                ThreadsAccessData(arrayList, data_num);
            }
            else{  // ANS: use this data structure
                Collection syncCollect = Collections.synchronizedCollection(new ArrayList<String>());
                InitData(syncCollect, data_num);
                ThreadsAccessData(syncCollect, data_num);
            }
        } catch (RuntimeException re){
            re.printStackTrace();
        }
    }

}
