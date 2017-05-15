package sample.hawk.com.mybasicappcomponents.background;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Thread:
 * 因為ticket變數是每個Thread自己私有的, 所以每個線程都各賣了自己的ticket
 *
 * Runnable:
 * 因為ticket變數是Runnable共用的,所以每個線程都賣這個共用的ticket, 最終賣完30張
 */

public class Thread_vs_Runnable {
//////////////////////////////////////////////////////////////////
    class ExtThread extends Thread {
        private int ticket=30;
        public void run() {
            for(int i=0; i<10 ; i++){
                if(this.ticket>0)
                    SMLog.i("Thread{}  TID=" + Thread.currentThread().getId()+" 賣票: ticket="+ticket--);
            }
        }
    }

    public void UseExtThread() {
        ExtThread et1 = new ExtThread(); // Thread instance-1
        ExtThread et2 = new ExtThread(); // Thread instance-2
        ExtThread et3 = new ExtThread(); // Thread instance-3
        et1.start(); // Thread-1
        et2.start(); // Thread-2
        et3.start(); // Thread-3
    }
//////////////////////////////////////////////////////////////////
    class ExtRunnable implements Runnable {
        private int ticket=30;
        public void run() {
            for(int i=0; i<10 ; i++){
                if(this.ticket>0)
                    SMLog.i("Runnable{}  TID=" + Thread.currentThread().getId()+" 賣票: ticket="+ticket--);
            }
        }
    }

    public void UseExtRunnable() {
        ExtRunnable er = new ExtRunnable(); // Runnable instance-1
        new Thread(er).start(); // Thread-1
        new Thread(er).start(); // Thread-2
        new Thread(er).start(); // Thread-3
    }
}
