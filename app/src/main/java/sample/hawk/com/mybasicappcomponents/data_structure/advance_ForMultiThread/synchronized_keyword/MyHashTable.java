package sample.hawk.com.mybasicappcomponents.data_structure.advance_ForMultiThread.synchronized_keyword;

import java.util.Hashtable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import sample.hawk.com.mybasicappcomponents.data_structure.advance_ForMultiThread.MultiThreadAccessIF;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/4/12.
 */

public class MyHashTable implements MultiThreadAccessIF {

    public void InitData(Hashtable<Integer, String> htable, int num) {
        for (int i = 0; i < num; i++) {
            htable.put(i,"Reading data["+i+"]rrr");
        }
    }

    public void ThreadsAccessData(Hashtable<Integer, String> htable, int num) {
        ExecutorService executorService = Executors.newFixedThreadPool(num);
        for (int i=0; i<num; i++) {
            executorService.execute(getReadRunnable(htable));
            executorService.execute(getWriteRunnable(htable, i));
        }
        executorService.shutdown();
    }

    @Override
    public Runnable getReadRunnable(Object obj){
        return new ReadTask((Hashtable<Integer, String>)obj);
    }

    @Override
    public Runnable getWriteRunnable(Object obj, int i){
        return new WriteTask((Hashtable<Integer, String>)obj, i);
    }

    private static class ReadTask implements Runnable {
        Hashtable<Integer, String> htable;
        public ReadTask(Hashtable<Integer, String> htable) {
            this.htable = htable;
        }
        public void run() {
            synchronized(htable) {
                for (Hashtable.Entry<Integer, String> me : htable.entrySet()) {
                    SMLog.i(me.getValue());
                }
            }
        }
    }

    private static class WriteTask implements Runnable {
        Hashtable<Integer, String> htable;
        int key;
        public WriteTask(Hashtable<Integer, String> htable, int key) {
            this.htable = htable;
            this.key = key;
        }
        public void run() {
            synchronized(htable) {
                htable.remove(key);
                htable.put(key,"Writing data["+key+"]www");
            }
        }
    }

    @Override
    public void error_case(boolean error) {
        final int data_num = 100;
        try{
            if(error == true){  // BUG: ConcurrentModificationException if Multi-Threading accesses
                // TODO:
            }
            else{  // ANS: use this data structure
                Hashtable<Integer, String> syncHtable = new Hashtable<Integer, String>();
                InitData(syncHtable, data_num);
                ThreadsAccessData(syncHtable, data_num);
            }
        } catch (RuntimeException re){
            re.printStackTrace();
        }
    }

}
