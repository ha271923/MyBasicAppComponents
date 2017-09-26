package sample.hawk.com.mybasicappcomponents.debugTest.MemoryLeak;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/1/20.
 */

public class Utility {
    private static Utility instance = null;
    private UpdateListener listener;

    //Make it a Singleton class
    private Utility(){}
    public static Utility getInstance() {
        if (instance == null)
            instance = new Utility();
        return instance;
    }

    public void setListener(UpdateListener listener) {
        this.listener = listener;
    }

    //Long running background thread
    public void startNewTread() {
        new Thread (new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000 * 10);
                    if (listener != null)
                        listener.onUpdate();
                } catch (InterruptedException e) {
                    SMLog.d(e.getMessage());
                }
            }
        }).start();
    }

    //Listener interface
    public interface UpdateListener {
        public void onUpdate();
    }
}