package sample.hawk.com.mybasicappcomponents.data_structure.advance_ForMultiThread;

/**
 * Created by ha271 on 2017/4/6.
 */

public interface MultiThreadAccessIF {
    void error_case(boolean error);

    Runnable getReadRunnable(Object obj);
    Runnable getWriteRunnable(Object obj, int i);
}
