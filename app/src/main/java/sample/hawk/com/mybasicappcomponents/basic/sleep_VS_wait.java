package sample.hawk.com.mybasicappcomponents.basic;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

import static sample.hawk.com.mybasicappcomponents.basic.sleep_VS_wait.TEST_CASE.SLEEP;
import static sample.hawk.com.mybasicappcomponents.basic.sleep_VS_wait.TEST_CASE.WAIT;

/**

 sleep()
     The thread which calls sleep() method does NOT release the lock it holds.
     No question of regaining the lock as thread does NOT release the lock.
     sleep() method CAN be called within or outside the synchronized block.
     sleep() method is a member of java.lang.Thread class.
     sleep() method is always called on threads.
     sleep() is a static method of Thread class.
     Sleeping threads can not be woken up by other threads. If done so, thread will throw InterruptedException.
     To call sleep() method, thread need not to have object lock.

 wait()
     The thread which calls wait() method releases the lock it holds.
     The thread regains the lock after other threads call either notify() or notifyAll() methods on the same lock.
     wait() method MUST be called within the synchronized block.
     wait() method is a member of java.lang.Object class.
     wait() method is always called on objects.
     wait() is a non-static method of Object class.
     Waiting threads can be woken up by other threads by calling notify() or notifyAll() methods.
     To call wait() method, thread must have object lock.


 */

public class sleep_VS_wait {

    enum TEST_CASE {
        SLEEP, WAIT
    }

    Test_Thread t1;
    Test_Thread t2;

    public sleep_VS_wait() {
        t1 = new Test_Thread(SLEEP);
        t2 = new Test_Thread(WAIT);
    }
    public void start_tests(){
        t1.start();
        t2.start();
    }

    public class Test_Thread extends Thread {
        TEST_CASE m_test_case;
        public Test_Thread(TEST_CASE test_case) {
            m_test_case = test_case;
        }

        public void Test_Sleep1(){
            TestSleep.Sleep1();
        }

        public void Test_Wait1(){
            TestWait obj = new TestWait();
            obj.Wait1();
        }

        @Override
        public void run() {
            super.run();
            for(int i=1;i<10000;i++) {
                switch (m_test_case) {
                    case SLEEP:
                        Test_Sleep1();
                        break;
                    case WAIT:
                        Test_Wait1();
                        break;
                }
            }
        }
    }

    static public class TestSleep extends Thread {
        static public void Sleep1(){
            try {
                SMLog.i("Thread.sleep()++++  tid="+Thread.currentThread().getId());
                Thread.sleep(5000); // 如果時間不到你能調用interreput()強制喚起
                SMLog.i("Thread.sleep()----  tid="+Thread.currentThread().getId());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public class TestWait {
        public void Wait1(){
            synchronized (this) { // MUST otherwise it will java.lang.IllegalMonitorStateException: object not locked by thread before wait()
                try {
                    SMLog.i("this.wait()++++  this="+this);
                    this.wait(5000);
                    // this.wait(); // INFINITE wait, notify() and notifyAll() can cancel the wait api.
                    SMLog.i("this.wait()----  this="+this);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
