package sample.hawk.com.mybasicappcomponents.oo.Objects;

import sample.hawk.com.mybasicappcomponents.debugTest.MemoryEater;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/1/12.
 */

public class Reference_1 implements RefMemLeakActions {
    private Reference_1_1 m_r11;
    private Reference_1_2 m_r12;
    static MemoryEater me;
    @Override
    public void MemoryLeak_Next(){
        m_r11 = new Reference_1_1();
        m_r11.MemoryLeak_Next();

        m_r12 = new Reference_1_2();
        m_r12.MemoryLeak_Next();
    }

    public void ForceOOM() throws Error {
        me = new MemoryEater();
        me.ForceOutOfMemory();
    }

    public void generateOOM() throws Error {
        int iteratorValue = 20;
        SMLog.i("\n=================> OOM test started..\n");
        for (int outerIterator = 1; outerIterator < 20; outerIterator++) {
            SMLog.i("Iteration " + outerIterator + " Free Mem: " + Runtime.getRuntime().freeMemory());
            int loop1 = 2;
            int[] memoryFillIntVar = new int[iteratorValue];
            // feel memoryFillIntVar array in loop..
            do {
                memoryFillIntVar[loop1] = 0;
                loop1--;
            } while (loop1 > 0);
            iteratorValue = iteratorValue * 5;
            SMLog.i("\nRequired Memory for next loop: " + iteratorValue);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
