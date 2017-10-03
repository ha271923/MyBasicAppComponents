package sample.hawk.com.mybasicappcomponents.data_structure.advance_ForMultiThread.Volatile_Sync_Atom;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/5/11.
 */

public class MyMultiThreadTest {
    boolean mEnable;

    public void Test(boolean bEnable){
        mEnable= bEnable;
        init_all();
        Executor executor = Executors.newFixedThreadPool(10);  // 10 threads in the pool
        for(int idx=1; idx<=10; idx++) {
            executor.execute(new Work(idx));
        }
    }

    public class Work implements Runnable {
        private int id;

        public Work(int id) {
            this.id = id;
        }

        public void run() {
            SMLog.i("[" + this.id + "] ThreadName =" + Thread.currentThread().getName() + " ++++++");
            final int select = 3;
            switch(select){ // SELECT a test case at HERE!!
                case 1:
                    Test_data_concurrency(mEnable); // For data accuracy  at Multi-Thread
                    break;
                case 2:
                    Test_Volatile(mEnable); // For Time critical at Multi-Thread
                    break;
                case 3:
                    Test_Atom(mEnable); // For Performance at Multi-Thread
                    break;

            }
            SMLog.i("[" + this.id + "] ThreadName =" + Thread.currentThread().getName() + " ------");
        }

        /**
         * volatile, sync, Atomic在Multi-Thread時, 各自有不同用途:
         * 1. volatile: 保最新
         *    當Multi-Thread時, 某一變數可能因為Context-Switch發生的位置, 導致如下組語
         *      MOV EAX,counter
         *      INC EAX  <-------- Context-Switch occurring
         *      MOV counter,EAX
         *    所欲取得的記憶體變數counter值尚未被取回, 就被其他Thread更改了, 所以為了避免這樣的問題, 需要volatile 確保
         *    組合語言編譯時, counter直接從暫存器進行存取
         *
         * 2. synchronized: 保同步
         *    程式碼區間 Lock, 保證該段區間同一時間只有一個Thread能進入, 效能會下降很多
         *
         * 3. Atomic: 增效能
         *    Multi-Thread下, 要進行 i++, ++i, i--, --i 可以用這個方式, 相較於synchronized來說, 對於效能提升超過 10x 倍
         */
        void Test_data_concurrency(boolean bEnable) {
            int setId;
            setId = set_Volatile(bEnable, this.id);
            SMLog.i("[" + this.id + "]   setId= " + setId + "   VolatileVar = " + get_Volatile(bEnable));
            do_something();
            setId = set_Sync(bEnable, this.id);
            SMLog.i("[" + this.id + "]   setId= " + setId + "   SyncVar = " + get_Sync(bEnable));
            do_something();
            setId = set_Atomic(bEnable, this.id);
            SMLog.i("[" + this.id + "]   setId= " + setId + "   AtomVar = " + get_Atomic(bEnable));
            do_something();
        }
        /**
         * 1. Thread 1 呼叫 setID(5);
         * 2. Thread 2 呼叫 setID(10);
         * 3. Thread 1 呼叫 getID();  ERROR: "我們預期是10, 結果程式回傳數值5"
         * 為了達到以上預期的最新value, 所以要改用 volatile_setID
         */
        void Test_Volatile(boolean bEnable) {
            setId_Volatile(bEnable);
            SMLog.i("[" + this.id + "]   VolatileVar = " + getId_Volatile(bEnable)); // 我們預期得到的是最新的ID數值，也就是被Thread-X複寫的最後值
            do_something();

        }

        void setId_Volatile(boolean bEnable) {
            if (bEnable == true)
                MyVolatile.volatile_setID(this.id);
            else
                MyVolatile.setID(this.id);
        }

        int getId_Volatile(boolean bEnable) {
            if (bEnable == true)
                return MyVolatile.volatile_getID();
            else
                return MyVolatile.getID();
        }

        /**
         * < Performance Test Result>
         * Test_Atom(true):  Time Elapsed < 140ms
         * Test_Atom(false): Time Elapsed > 17000ms
         */
        void Test_Atom(boolean bEnable) {
            int ret = 0;
            long start = System.currentTimeMillis();
            ret = increase_Atom(bEnable);
            long end = System.currentTimeMillis();
            SMLog.i("Atom(" + bEnable + ")  increase() time elapse:" + (end - start) + "    total=" + ret);
        }

        int increase_Atom(boolean bEnable) {
            int total = 0;
            for (int i = 0; i < 100000; i++) {
                if (bEnable == true) {
                    total = MyAtomic.increase_Atom(); // GOOD performance by Atom operation
                } else {
                    total = MyAtomic.increase(); // BAD performance, since "synchronized" keyword
                }
            }
            return total;
        }

    }
    // simulating the job is wasting CPU time.
    static void do_something(){
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    static void init_all(){
        MyVolatile.init();
        MySync.init();
        MyAtomic.init();
    }

    static int set_Volatile(boolean Enable, int id){
        if(Enable ==true)
            return MyVolatile.volatile_set(id);
        else
            return MyVolatile.set(id);
    }
    static synchronized int get_Volatile(boolean Enable){
        if(Enable ==true)
            return MyVolatile.volatile_get();
        else
            return MyVolatile.get();
    }

    static int set_Sync(boolean Enable, int id){
        if(Enable ==true)
            return MySync.sync_set(id);
        else
            return MySync.set(id);
    }
    static synchronized int get_Sync(boolean Enable){
        if(Enable ==true)
            return MySync.sync_get();
        else
            return MySync.get();
    }

    static int set_Atomic(boolean Enable, int id){
        if(Enable ==true)
            return MyAtomic.atom_set(id);
        else
            return MyAtomic.set(id);
    }
    static synchronized int get_Atomic(boolean Enable){
        if(Enable ==true)
            return MyAtomic.atom_get();
        else
            return MyAtomic.get();
    }

}
