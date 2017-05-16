package sample.hawk.com.mybasicappcomponents.background;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * 如果需要一個智慧型自動安排啟動的ThreadPool時!!
 *
 * ThreadPoolExecutor是Executors類的底層實現。
 *
 *  在JDK幫助文檔中，有如此一段話：
 *  “ 強烈建議程序員使用較為方便的Executors
 *      工廠方法Executors.newCachedThreadPool()（無界線程池，可以進行自動線程回收）、
 *      Executors.newFixedThreadPool(int)（固定大小線程池）和
 *      Executors.newSingleThreadExecutor()（單個後台線程），
 *      它們均為大多數使用場景預定義了設置。 ”
 */

/**
 *    corePoolSize：指的是同時執行任務的Thread數量
 *    maximumPoolSize：指的是ThreadPool的最大大小。
 *    keepAliveTime：指的是idle Thread結束的超時時間。
 *    unit：keepAliveTime的時間單位。
 *    workQueue：表示存放任務的queue
 *
 *  < 任務順序 >
 *  這樣的過程說明，並不是先ADD任務就一定會先執行。假設Queue大小為10，corePoolSize 為3，maximumPoolSize 為6，那麼當加入20 個任務時
 *  ，執行的順序就是這樣的：首先同時有3個Thread會去執行任務1、2、3，然後任務4~13 被放入Queue。這時候Queue滿了，任務14、15、16 會被馬
 *  上執行，而任務17~20則會拋出Exception。
 *  最終順序是：1、2、3、14、15、16、4、5、6、7、8、9、10、11、12、13。
 */
public class MyThreadPoolExecutor {
    private class MyRunnable implements Runnable {
        int mId;
        public MyRunnable(int Id){
            this.mId = Id;
        }
        @Override
        public void run() {
            SMLog.i("    ["+this.mId+"]  TID="+Thread.currentThread().getId() + "   this="+this+"  " + String.format("obj.hash=%d run() ++++", this.hashCode()));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            SMLog.i("    ["+this.mId+"]  TID="+Thread.currentThread().getId() + "   this="+this+"  " + String.format("obj.hash=%d run() ----", this.hashCode()));
        }
    }


    public void test() {
        BlockingQueue queue = new LinkedBlockingQueue();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 6, 1, TimeUnit.DAYS, queue){
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                super.beforeExecute(t, r);
                SMLog.i("  beforeExecute   TID="+Thread.currentThread().getId()+"  r="+r.toString());
            }
            @Override
            protected void terminated() {
                super.terminated();
                SMLog.i("  terminated   TID="+Thread.currentThread().getId());
            }
            @Override
            protected void finalize() {
                super.finalize();
                SMLog.i("  finalize   TID="+Thread.currentThread().getId());
            }
            @Override
            public void execute(Runnable command) {
                super.execute(command);
                SMLog.i("  execute   TID="+Thread.currentThread().getId()+"  r="+command.toString());
            }
            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                super.afterExecute(r, t);
                SMLog.i("  afterExecute   TID="+Thread.currentThread().getId()+"  r="+r.toString());
            }
        };

        for (int Id = 0; Id < 20; Id++) {
            SMLog.i("["+Id+"] ++++++ ");
            executor.execute(new MyRunnable(Id));
            SMLog.i("["+Id+"] ------ ");
        }
        executor.shutdown(); // 呼叫shutdown()後，主線程就馬上結束了，而ThreadPool會繼續運行直到所有任務執行完才會停止。
    }
}
