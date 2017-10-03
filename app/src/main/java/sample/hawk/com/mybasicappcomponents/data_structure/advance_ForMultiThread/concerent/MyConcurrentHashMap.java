package sample.hawk.com.mybasicappcomponents.data_structure.advance_ForMultiThread.concerent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import sample.hawk.com.mybasicappcomponents.data_structure.advance_ForMultiThread.MultiThreadAccessIF;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/4/6.
 */

public class MyConcurrentHashMap implements MultiThreadAccessIF {

    public void InitData(Map<Integer, String> map, int num) {
        for (int i = 0; i < num; i++) {
            map.put(i,"Reading data["+i+"]rrr");
        }
    }

    public void ThreadsAccessData(Map<Integer, String> map, int num) {
        ExecutorService executorService = Executors.newFixedThreadPool(num);
        for (int i=0; i<num; i++) {
            executorService.execute(getReadRunnable(map));
            executorService.execute(getWriteRunnable(map, i));
        }
        executorService.shutdown();
    }

    @Override
    public Runnable getReadRunnable(Object obj){
        return new ReadTask((Map<Integer, String>)obj);
    }

    @Override
    public Runnable getWriteRunnable(Object obj, int i){
        return new WriteTask((Map<Integer, String>)obj, i);
    }

    private static class ReadTask implements Runnable {
        Map<Integer, String> map;
        public ReadTask(Map<Integer, String> map) {
            this.map = map;
        }
        public void run() {
            for(Map.Entry<Integer, String> me : map.entrySet()){
                SMLog.i(me.getValue());
            }
        }
    }

    private static class WriteTask implements Runnable {
        Map<Integer, String> map;
        int key;
        public WriteTask(Map<Integer, String> map, int key) {
            this.map = map;
            this.key = key;
        }
        public void run() {
            map.remove(key);
            map.put(key,"Writing data["+key+"]www");
        }
    }

    @Override
    public void error_case(boolean error) {
        final int data_num = 100;
        try{
            if(error == true){  // BUG: ConcurrentModificationException if Multi-Threading accesses
                Map<Integer, String> collector = new HashMap<Integer, String>();
                InitData(collector, data_num);
                ThreadsAccessData(collector, data_num);
            }
            else{  // ANS: use this data structure
                ConcurrentHashMap<Integer, String> concurrentHashMap = new ConcurrentHashMap<>();
                InitData(concurrentHashMap, data_num);
                ThreadsAccessData(concurrentHashMap, data_num);
            }
        } catch (RuntimeException re){
            re.printStackTrace();
        }
    }

}
