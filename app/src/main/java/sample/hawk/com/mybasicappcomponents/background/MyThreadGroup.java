package sample.hawk.com.mybasicappcomponents.background;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * 每一個 thread 都隸屬於某一個 ThreadGroup，若產生的 thread 沒有指定 ThreadGroup，則此 thread 則屬於
 * 產生它的 thread (一般來說是 main thread)。而必須注意的是，當 thread 歸入某個 ThreadGroup 後，就無法
 * 再更換到其他 ThreadGroup 了!
 * 而透過 java.lang.ThreadGroup，可以管理整個 group 中的 thread，例如 interrupt 群組中所有的 thread，
 * 或是設定優先權....等等，詳細的操作方式可以參考官方文件。
 * 此外，若在 ThreadGroup 中有 thread 發生了例外，可透過實作 Thread.UncaughtExceptionHandler 的方式來
 * 進行處理
 *
 */

public class MyThreadGroup {
    Thread[] threads = new Thread[30];

    public void test(){
        // create threadgroups
        ThreadGroup group_A = new ThreadGroup("MyThreadGroup-A");
        Thread tg_A[] = new Thread[10];
        ThreadGroup group_B = new ThreadGroup("MyThreadGroup-B");
        Thread tg_B[] = new Thread[10];
        ThreadGroup group_C = new ThreadGroup("MyThreadGroup-C");
        Thread tg_C[] = new Thread[10];
        // create threads
        for(int i=0;i<10;i++){
            tg_A[i] = new_Thread_to_Group(group_A);
        }

        for(int i=0;i<10;i++){
            tg_B[i] = new_Thread_to_Group(group_B);
        }
        for(int i=0;i<10;i++){
            tg_C[i] = new_Thread_to_Group(group_C);
        }
        // list
        for(int i = 0; i < tg_A.length; i++) {
            SMLog.i("["+i+"] Group="+tg_A[i].getThreadGroup()+"    TID="+tg_A[i].getId()+"   TName="+tg_A[i].getName());
            tg_A[i].start();
        }
        for(int i = 0; i < tg_B.length; i++) {
            SMLog.i("["+i+"] Group="+tg_B[i].getThreadGroup()+"    TID="+tg_B[i].getId()+"   TName="+tg_B[i].getName());
            tg_B[i].start();
        }
        for(int i = 0; i < tg_C.length; i++) {
            SMLog.i("["+i+"] Group="+tg_C[i].getThreadGroup()+"    TID="+tg_C[i].getId()+"   TName="+tg_C[i].getName());
            tg_C[i].start();
        }
    }

    private class ThreadExceptionHandler implements Thread.UncaughtExceptionHandler {
        public void uncaughtException(Thread thread, Throwable e) {
            SMLog.i(thread.getName() + " : " + e.getMessage());
        }
    }

    private Thread new_Thread_to_Group(ThreadGroup tgroup){
        ThreadExceptionHandler handler = new ThreadExceptionHandler();
        Thread thread = new Thread(tgroup, new Runnable() {
            public void run() {
                SMLog.i("Runnable{}  TID=" + Thread.currentThread().getId()+" run(){...}");
                throw new RuntimeException("Force an Exception");
            }
        });
        thread.setUncaughtExceptionHandler(handler);
        return thread;
    }
}
