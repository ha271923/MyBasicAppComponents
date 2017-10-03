package sample.hawk.com.mybasicappcomponents.data_structure.advance_ForMultiThread.concerent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import sample.hawk.com.mybasicappcomponents.data_structure.advance_ForMultiThread.MultiThreadAccessIF;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/4/6.
 */

public class MyCopyOnWriteArrayList implements MultiThreadAccessIF {

    public void InitData(List<String> list, int num) {
        for (int i = 0; i < num; i++) {
            list.add("Reading data["+i+"]rrr");
        }
    }

    public void ThreadsAccessData(List<String> list, int num) {
        ExecutorService executorService = Executors.newFixedThreadPool(num);
        for (int i=0; i<num; i++) {
            executorService.execute(getReadRunnable(list));
            executorService.execute(getWriteRunnable(list, i));
        }
        executorService.shutdown();
    }

    @Override
    public Runnable getReadRunnable(Object obj){
        return new ReadTask((List<String>)obj);
    }

    @Override
    public Runnable getWriteRunnable(Object obj, int i){
        return new WriteTask((List<String>)obj, i);
    }

    private static class ReadTask implements Runnable {
        List<String> list;
        public ReadTask(List<String> list) {
            this.list = list;
        }
        public void run() {
            for(String str:list) {
                SMLog.i(str);
            }
        }
    }

    private static class WriteTask implements Runnable {
        List<String> list;
        int index;
        public WriteTask(List<String> list, int index) {
            this.list = list;
            this.index = index;
        }
        public void run() {
            list.remove(index);
            list.add(index, "Writing data["+index+"]www");
        }
    }

    @Override
    public void error_case(boolean error) {
        final int data_num = 100;
        try{
            if(error == true){  // BUG: ConcurrentModificationException if Multi-Threading accesses
                List<String> arrayList = new ArrayList<String>();
                InitData(arrayList, data_num);
                ThreadsAccessData(arrayList, data_num);
            }
            else{  // ANS: use this data structure
                CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList<String>();
                InitData(copyOnWriteArrayList, data_num);
                ThreadsAccessData(copyOnWriteArrayList, data_num);
            }
        } catch (RuntimeException re){
            re.printStackTrace();
        }
    }

}
