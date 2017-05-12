package sample.hawk.com.mybasicappcomponents.background;

import android.app.Activity;
import android.os.Bundle;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * class FutureTask<V> (parent) 類別
 *  public class FutureTask<V> implements RunnableFuture<V> {...}
 *      public interface RunnableFuture<V> extends Runnable, Future<V> {...}
 *                                                 interface Future<V> 介面
 *
 * 創建Thread的2種方式，一種是直接繼承Thread，另外一種就是實現Runnable接口。
 * 這2種方式都有一個缺陷就是：在執行完任務之後無法獲取 return value結果。
 * 如果需要獲取執行結果，就必須通過share variable 或者使用Thread communicate的方式來達到效果，這樣使用起來就比較麻煩。
 * 而自從Java 1.5開始，就提供了Callable和Future，通過它們可以在任務執行完畢之後得到任務執行結果。
 *
 * 值哲注意的是 Future, FutureTask 都會永久性 Block Original Thread(可能是UI Thread), 因為submit後, 必須等
 * 待 call執行完畢, 才能進行下一行get(), 所以也提供了get(TIME_OUT_Exception)的處理,
 *
 *
 *   submit(...) == wait
 *   background process finished == Trigger Event
 *   get(...) == get Event return value
 *
 */

public class MyFutureTaskActivity extends Activity {
    static ExecutorService mExecutor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mExecutor = Executors.newCachedThreadPool();
        // 把call寫在裡面的寫法, 雜亂
        Thread_runnable_Demo();
        Future_runnable_Demo();
        Future_Callable_Demo();
        FutureTask_Callable_Demo();
        // 把call寫在外面的寫法, 乾淨
        Future_Callable_Demo2();
        FutureTask_Callable_Demo2();
        mExecutor.shutdown();
    }

    /**
     * thread並無法 return value
     */
    void Thread_runnable_Demo() {
        SMLog.i("Thread_runnable_Demo +++");
        new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO: PUT background job at here!
                SMLog.i("    This is Never BLOCKING the original Thread...");
                SMLog.i("    WorkerThread ID="+Thread.currentThread().getId()+" is executing!");
                SMLog.i("    Thread result only in runnable = " + fibc(20));
            }
        }).start();
        SMLog.i("Thread_runnable_Demo ---");
    }

    /**
     * Runnable可以提交给Thread来包装下，直接启动一个Thread 来执行，
     * 而Callable则一般都是提交给ExecuteService来执行。
     */
    void Future_runnable_Demo() {
        SMLog.i("Future_runnable_Demo +++");
        try {
            Future<?> result = mExecutor.submit(new Runnable() { // 無return value時, 可用Future<?>代替
                @Override
                public void run() {
                    // TODO: PUT background job at here!
                    SMLog.i("    Now is BLOCKING the original Thread...");
                    SMLog.i("    WorkerThread ID="+Thread.currentThread().getId()+" is executing!");
                    fibc(20);
                }
            });
            SMLog.i("    Now is UNBLOCK the original Thread");
            if(result.get(CALCUTE_TIMEOUT, TimeUnit.MILLISECONDS)==null) // Future will block MainThread,so set timeout.
                SMLog.i("    Future CANNOT return result from runnable");
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        SMLog.i("Future_runnable_Demo ---");
    }
    /**
     * Future
     * 提交Callable, 有返回值, future中能够获取返回值
     */
    void Future_Callable_Demo() {
        SMLog.i("Future_Callable_Demo +++");
        try {
            Future<Integer> result = mExecutor.submit(
                    new Callable<Integer>() {
                        @Override
                        public Integer call() throws Exception {
                            // TODO: PUT background job at here!
                            SMLog.i("    Now is BLOCKING the original Thread...");
                            SMLog.i("    WorkerThread ID="+Thread.currentThread().getId()+" is executing!");
                            return fibc(20);  // IMPORTANT!!! it can return value!
                        }
                    });
            SMLog.i("    Now is UNBLOCK the original Thread");
            SMLog.i("    FutureTask result from callable =" + result.get(CALCUTE_TIMEOUT, TimeUnit.MILLISECONDS)); // Future will block MainThread,so set timeout.
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        SMLog.i("Future_Callable_Demo ---");
    }
    /**
     * FutureTask则是一个RunnableFuture<V>，即实现了Runnbale又实现了Futrue<V>这两个接口，
     * 另外它还可以包装Runnable(实际上会转换为Callable)和Callable
     * <V>，所以一般来讲是一个符合体了，
     * 1. 它可以通过Thread包装来直接执行，
     * 2. 也可以提交给ExecuteService来执行
     * 3. 并且还可以通过v get()返回执行结果，在线程体没有执行完成的时候，主线程一直阻塞等待，执行完则直接返回结果。
     */
    void FutureTask_Callable_Demo() {
        SMLog.i("FutureTask_Callable_Demo +++");
        try {
            FutureTask<Integer> futureTask = new FutureTask<Integer>(
                    new Callable<Integer>() {
                        @Override
                        public Integer call() throws Exception {
                            // TODO: PUT background job at here!
                            SMLog.i("    Now is BLOCKING the original Thread...");
                            SMLog.i("    WorkerThread ID=" + Thread.currentThread().getId() + " is executing!");
                            return fibc(20);
                        }
                    });
            mExecutor.submit(futureTask);
            SMLog.i("    Now is UNBLOCK the original Thread");
            SMLog.i("    FutureTask result from callable =" + futureTask.get(CALCUTE_TIMEOUT, TimeUnit.MILLISECONDS)); // FutureTask will block MainThread,so set timeout.
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        SMLog.i("FutureTask_Callable_Demo ---");
    }

    // 效率底下的斐波那契数列, 耗时的操作, 若CALCUTE_TIMEOUT設定過小, 則計算未完成將觸發 TimeoutException
    static final int CALCUTE_TIMEOUT = 1*1000;
    int fibc(int num) {
        if (num == 0) {
            return 0;
        }
        if (num == 1) {
            return 1;
        }
        return fibc(num - 1) + fibc(num - 2);
    }

    ////////////////////////////////////////////////////////////////////////////////////////

    class Task implements Callable<Integer> { // Callable
        @Override
        public Integer call() throws Exception {
            // TODO: PUT background job at here!
            SMLog.i("    Now is BLOCKING the original Thread...");
            SMLog.i("    WorkerThread ID="+Thread.currentThread().getId()+" is executing!");
            return fibc(20);
        }
    }

    void Future_Callable_Demo2(){
        SMLog.i("Future_Callable_Demo2 +++");
        Future<Integer> result = mExecutor.submit(new Task()); // 無return value時, 可用Future<?>代替
        SMLog.i("    Now is UNBLOCK the original Thread");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        try {
            SMLog.i("    Task result="+result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        SMLog.i("Future_Callable_Demo2 ---");
    }

    void FutureTask_Callable_Demo2(){
        SMLog.i("FutureTask_Callable_Demo2 +++");
        FutureTask<Integer> result = new FutureTask<Integer>(new Task()); // 無return value時, 可用Future<?>代替
        mExecutor.submit(result);
        SMLog.i("    Now is UNBLOCK the original Thread");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        try {
            SMLog.i("    Task result="+result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        SMLog.i("FutureTask_Callable_Demo2 ---");
    }

}
