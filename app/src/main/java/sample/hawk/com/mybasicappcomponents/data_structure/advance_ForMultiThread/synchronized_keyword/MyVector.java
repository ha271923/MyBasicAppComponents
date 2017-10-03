package sample.hawk.com.mybasicappcomponents.data_structure.advance_ForMultiThread.synchronized_keyword;

import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import sample.hawk.com.mybasicappcomponents.data_structure.advance_ForMultiThread.MultiThreadAccessIF;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/4/12.
 */

public class MyVector implements MultiThreadAccessIF {

    public void InitData(Vector<String> vector, int num) {
        for (int i = 0; i < num; i++) {
            vector.add("Reading data["+i+"]rrr");
        }
    }

    public void ThreadsAccessData(Vector<String> vector, int num) {
        ExecutorService executorService = Executors.newFixedThreadPool(num);
        for (int i=0; i<num; i++) {
            executorService.execute(getReadRunnable(vector));
            executorService.execute(getWriteRunnable(vector, i));
        }
        executorService.shutdown();
    }

    @Override
    public Runnable getReadRunnable(Object obj){
        return new ReadTask((Vector<String>)obj);
    }

    @Override
    public Runnable getWriteRunnable(Object obj, int i){
        return new WriteTask((Vector<String>)obj, i);
    }

    private static class ReadTask implements Runnable {
        Vector<String> vector;
        public ReadTask(Vector<String> vector) {
            this.vector = vector;
        }
        public void run() {
            synchronized (vector) {
                for(String str:vector) {
                    SMLog.i(str);
                }
            }
        }
    }

    private static class WriteTask implements Runnable {
        Vector<String> vector;
        int index;
        public WriteTask(Vector<String> vector, int index) {
            this.vector = vector;
            this.index = index;
        }
        public void run() {
            synchronized (vector) {
                vector.remove(index);
                vector.add(index, "Writing data["+index+"]www");
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
                Vector<String> vector = new Vector<String>();
                InitData(vector, data_num);
                ThreadsAccessData(vector, data_num);
            }
        } catch (RuntimeException re){
            re.printStackTrace();
        }
    }

}
