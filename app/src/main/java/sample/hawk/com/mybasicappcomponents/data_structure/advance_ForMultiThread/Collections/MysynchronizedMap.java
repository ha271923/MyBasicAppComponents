package sample.hawk.com.mybasicappcomponents.data_structure.advance_ForMultiThread.Collections;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import sample.hawk.com.mybasicappcomponents.data_structure.advance_ForMultiThread.MultiThreadAccessIF;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/4/6.
 */

public class MysynchronizedMap implements MultiThreadAccessIF {

    public void InitData(Map<Integer, String> map, int num) {
        for (int i = 0; i < num; i++) {
            map.put(i,"Reading data["+i+"]rrr");
        }
    }

    public void ThreadsAccessData(Map<Integer, String> map, int num) {
        ExecutorService executorService = Executors.newFixedThreadPool(num);
        for (int i=0; i<num; i++) {
            executorService.execute(new MysynchronizedMap.ReadTask(map));
            executorService.execute(new MysynchronizedMap.WriteTask(map, i));
        }
        executorService.shutdown();
    }

    private static class ReadTask implements Runnable {
        Map<Integer, String> map;
        public ReadTask(Map<Integer, String> map) {
            this.map = map;
        }
        public void run() {
            synchronized(map) {
                for (Map.Entry<Integer, String> me : map.entrySet()) {
                    SMLog.i(me.getValue());
                }
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
            synchronized(map) {
                map.remove(key);
                map.put(key,"Writing data["+key+"]www");
            }
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
                Map syncMap = Collections.synchronizedMap(new HashMap<Integer, String>());
                InitData(syncMap, data_num);
                ThreadsAccessData(syncMap, data_num);
            }
        } catch (RuntimeException re){
            re.printStackTrace();
        }
    }

}
