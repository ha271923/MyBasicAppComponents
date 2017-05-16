package sample.hawk.com.mybasicappcomponents.background;

import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * 定時自動產生Thread執行一個任務
 *
 * ThreadPoolExecutor與ScheduledThreadPoolExecutor的區別：
 *      和ThreadPoolExecutor相比，ScheduledThreadPoolExecutor 它可另行安排在給定的延遲後運行命令，或者定期執行命令。
 *      需要Multi-Thread時，或者要求的ThreadPoolExecutor具有額外的靈活性或功能時，此類要優於定時器。
 *      一旦啟用已延遲的任務就執行它，但是有關何時啟用，啟用後何時執行則沒有任何實時保證。按照提交的先進先出（FIFO）順序來
 *      啟用那些被安排在同一執行時間的任務。
 *
 * Timer與ScheduledThreadPoolExecutor的區別：
 *      Timer 對調度的支持是基於絕對時間的，因此任務對系統時間的改變是敏感的；而ScheduledThreadPoolExecutor支持相對時間。
 *      Timer使用單線程方式來執行所有的TimerTask，如果某個TimerTask很耗時則會影響到其他TimerTask的執行；
 *      而ScheduledThreadPoolExecutor則可以構造一個固定大小的線程池來執行任務。
 *      Timer 不會捕獲由TimerTask拋出的未檢查異常，故當有異常拋出時，Timer會終止，導致未執行完的TimerTask不再執行，新的TimerTask也不能被調度；
 *      ScheduledThreadPoolExecutor對這個問題進行了妥善的處理，不會影響其他任務的執行。
 *
 */

public class MyScheduledThreadPoolExecutor {
    ScheduledFuture<?> mOneShotFuture;
    ScheduledFuture<?> mDelayFuture;
    ScheduledFuture<?> mPeriodicFuture;
    private class MyRunnable implements Runnable {
        int count;
        String mTypeName;
        public MyRunnable(String typeName){
            this.mTypeName = typeName;
        }
        @Override
        public void run() {
            try{
                SMLog.i("["+this.count+"] TID="+Thread.currentThread().getId()+"   "+this.mTypeName+" Execution Time: " + fmt.format(new Date()));
                Thread.sleep(5*1000);
                SMLog.i("["+this.count+"] TID="+Thread.currentThread().getId()+"   "+this.mTypeName+" End Time: " + fmt.format(new Date()));
                this.count++;
                if(mTypeName.equals("  delayTask")){
                    if(this.count>5){
                        mDelayFuture.cancel(false);
                        SMLog.i("cancel delayTask!!!");
                    }
                } else if (mTypeName.equals("periodicTask")){
                    if(this.count>10){
                        mPeriodicFuture.cancel(false);
                        SMLog.i("cancel mPeriodicFuture!!!");
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

        final static DateFormat fmt = DateFormat.getTimeInstance(DateFormat.LONG);
        public void test() {
            // Create a scheduled thread pool with 5 core threads
            ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = (ScheduledThreadPoolExecutor)Executors.newScheduledThreadPool(5);

            // CREATE runnable
            Runnable oneShotTask  = new MyRunnable("    oneShotTask");
            Runnable delayTask    = new MyRunnable("  delayTask");
            Runnable periodicTask = new MyRunnable("periodicTask");

            SMLog.i("Submission Time: " + fmt.format(new Date()));
            // SCHEDULE runnable
            mOneShotFuture  = scheduledThreadPoolExecutor.schedule(oneShotTask, 5, TimeUnit.SECONDS);
            SMLog.i("scheduled oneShotFuture");
            // scheduleAtFixedRate與scheduleWithFixedDelay兩個函數的區別主要看第三個參數的區別:
            //   scheduleWithFixedDelay 表示上一個任務"結束"到下一個任務"開始"的時間
            mDelayFuture    = scheduledThreadPoolExecutor.scheduleWithFixedDelay(delayTask, 30, 10, TimeUnit.SECONDS);
            SMLog.i("scheduled delayTask");
            //   scheduleAtFixedRate    表示上一個任務"開始"到下一個任務"開始"的時間
            mPeriodicFuture = scheduledThreadPoolExecutor.scheduleAtFixedRate(periodicTask, 15, 20, TimeUnit.SECONDS);
            SMLog.i("scheduled periodicTask");
        }
    }