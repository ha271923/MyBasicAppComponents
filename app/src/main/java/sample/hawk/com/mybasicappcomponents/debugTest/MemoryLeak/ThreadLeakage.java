package sample.hawk.com.mybasicappcomponents.debugTest.MemoryLeak;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2016/10/19.
 */

public class ThreadLeakage implements MemoryActions {
    private final int MAX_THREAD_NUM = 10;
    Thread[] ThreadArrays = new Thread[MAX_THREAD_NUM];
    private int number;

    @Override
    public void create(int num){
        for(int i=0;i<num && i<MAX_THREAD_NUM ;i++){
            ThreadArrays[i] = new Thread(String.valueOf(i)){
                @Override
                public void run() {
                    super.run();
                    SMLog.i("Thread["+getName()+"] +++++++");
                    try {
                        Thread.sleep(30*1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    SMLog.i("Thread["+getName()+"] ------");
                }
            };
            ThreadArrays[i].start();
            number = i;
        }
    }

    @Override
    public void fill(Object obj) {
        if(obj instanceof ThreadLeakage){
            for(int i=0;i<number;i++){
                ((ThreadLeakage) obj).ThreadArrays[i].getName();
            }
        }
    }

    @Override
    public void release(int num) {

    }
}
