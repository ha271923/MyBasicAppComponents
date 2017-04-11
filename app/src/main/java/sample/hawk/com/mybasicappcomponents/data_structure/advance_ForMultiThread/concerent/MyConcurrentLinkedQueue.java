package sample.hawk.com.mybasicappcomponents.data_structure.advance_ForMultiThread.concerent;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import sample.hawk.com.mybasicappcomponents.data_structure.advance_ForMultiThread.MultiThreadAccessIF;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/4/6.
 */

public class MyConcurrentLinkedQueue implements MultiThreadAccessIF {

    public void InitData(Queue<String> queue, int num) {
        for (int i = 0; i < num; i++) {
            queue.add("Reading data["+i+"]rrr");
        }
    }

    public void ThreadsAccessData(Queue<String> queue, int num) {
        ExecutorService executorService = Executors.newFixedThreadPool(num);
        for (int i=0; i<num; i++) {
            executorService.execute(new MyConcurrentLinkedQueue.ReadTask(queue));
            executorService.execute(new MyConcurrentLinkedQueue.WriteTask(queue, i));
        }
        executorService.shutdown();
    }

    private static class ReadTask implements Runnable {
        Queue<String> queue;
        public ReadTask(Queue<String> queue) {
            this.queue = queue;
        }
        public void run() {
            for(String str:queue) {
                SMLog.i(str);
            }
        }
    }

    private static class WriteTask implements Runnable {
        Queue<String> queue;
        int index;
        public WriteTask(Queue<String> queue, int index) {
            this.queue = queue;
            this.index = index;
        }
        public void run() {
            queue.remove(index);
            queue.add("Writing data["+index+"]www");
        }
    }

    @Override
    public void error_case(boolean error) {
        final int data_num = 100;
        try{
            if(error == true){  // BUG: ConcurrentModificationException if Multi-Threading accesses
                Queue<String> queue = new LinkedList<String>();
                InitData(queue, data_num);
                ThreadsAccessData(queue, data_num);
            }
            else{  // ANS: use this data structure
                ConcurrentLinkedQueue<String> concurrentLinkedQueue = new ConcurrentLinkedQueue<String>();
                InitData(concurrentLinkedQueue, data_num);
                ThreadsAccessData(concurrentLinkedQueue, data_num);
            }
        } catch (RuntimeException re){
            re.printStackTrace();
        }
    }

}
