package sample.hawk.com.mybasicappcomponents.misc;

import android.support.annotation.UiThread;
import android.support.annotation.WorkerThread;

/**
 * Created by ha271 on 2016/11/16.
 */

public interface AnnotationInterface {
    @UiThread
    void doInUiThread();
    @WorkerThread
    void doInWorkerThread();
}
