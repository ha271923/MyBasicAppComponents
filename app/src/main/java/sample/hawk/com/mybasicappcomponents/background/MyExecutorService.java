package sample.hawk.com.mybasicappcomponents.background;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/4/11.
 */

public class MyExecutorService {
    private static class MyJob implements Runnable {
        List<String> list;
        int index;
        MyExecutorServiceActivity.JobCallBack callback;
        public MyJob(List<String> list, int index, MyExecutorServiceActivity.JobCallBack cb) {
            this.list = list;
            this.index = index;
            this.callback = cb;
            SMLog.i("index="+index);
        }
        void Job(){
            // Hawk: if list is not CopyOnWriteArrayList, you MUST synchronized(list) all changes.

            // list.add(index, "Add Job["+index+"]"); // ERROR: it will cause ArrayIndexOutOfBoundsException, since Thread.start() is not sequentially. index order may large than the increasing list array.
            list.add("Add Job["+index+"]");
            try {
                Thread.sleep(200); // Task is processing parallel, so all job done is NOT 200ms*100job.
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        public void run() {
            try {
                Job();
                callback.onJobDone();
            } catch (RuntimeException re){
                re.printStackTrace();
            }
        }
    }

    public void CreateManyJobs_ByExecutorService(List<String> list, int num, MyExecutorServiceActivity.JobCallBack cb) {
        cb.onJobStart();
        ExecutorService executorService = Executors.newFixedThreadPool(num);
        for (int i=0; i<num; i++) {
            executorService.execute(new MyExecutorService.MyJob(list, i, cb));
        }
        executorService.shutdown();
    }

    public void CreateManyJobs_ByThread(List<String> list, int num, MyExecutorServiceActivity.JobCallBack cb) {
        cb.onJobStart();
        for (int i=0; i<num; i++) {
            new Thread(new MyExecutorService.MyJob(list, i, cb)).start();
        }
    }

}
