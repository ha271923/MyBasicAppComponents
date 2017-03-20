package sample.hawk.com.mybasicappcomponents.debugTest;

import android.app.Activity;
import android.os.Bundle;

import sample.hawk.com.mybasicappcomponents.designpattern.callback.MyCallBack;
import sample.hawk.com.mybasicappcomponents.designpattern.callback.SupportCallBack;

/**
 * How does it release the Anonymous object's memory allocate?
 * STUDY JAVA at https://blogs.oracle.com/olaf/entry/memory_leaks_made_easy
 *
 * How is this?
 *  Every non-static Inner Class has an implicit reference to its surrounding class. Anonymous Classes
 *  are similar. To successfully create a memory leak simply pass an Inner Class object to a method which
 *  keeps references to the provided objects and you're done.
 *
 * How to prevent this style of Memory Leaks?
 *  If you're about to use Inner Classes or Anonymous Classes think carefully. Don't use Anonymous Classes
 *  until you're very sure and can prove that they are not causing a Memory Leak.
 *  Use a static Inner Class to get rid of the implicit outer class reference.
 *
 * Suggestions:
 *  WeakReference can be used for some conditions.
 */

public class MemLeak_Anonymous_Callback_Activity extends Activity {
    MemoryEater mMemoryEater = new MemoryEater();

    // Q: 在Anonymous呼叫時, 誰擁有這個Inner Class object的reference,誰決定釋放
    //  A: MemLeak_Anonymous_Callback_Activity擁有此MemInnerClass的reference結構, 因為surrounding此class
    //  B: 外部直接呼叫此MemInnerClass的情況時, reference被呼叫物件擁有
    class MemInnerClass {
        public void func(){
            mMemoryEater.waste_StaticVar(20*1024*1024);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Questions: When to release memory of these Anonymous class,callback api,interface class
        MemoryLeakTest(1);
        System.gc();
        MemoryLeakTest(2);
        System.gc();
        MemoryLeakTest(3);
        System.gc();

    }

    // Ans: case 1&2&3 will release these Anonymous objects which referred by implicit reference
    // if the surrounding class/object(this Activity) destroyed.
    void MemoryLeakTest(int testCase){
        switch(testCase){
            case 1:
                // Type1 - Anonymous class
                new MemInnerClass().func(); // <-- JAVA has an implicit reference to its surrounding class.
            break;
            case 2:
                // Type2 - class with callback condition, two Anonymous object condition.
                // Q. If OS release this Anonymous object, how can other process call the callback API without handle?
                // Q. When to release this memory of SupportCallBack object?
                new SupportCallBack().register(new MyCallBack() {  // <-- JAVA has an implicit reference to its surrounding class.
                    @Override
                    public void onCall_API() {
                        mMemoryEater.waste_StaticVar(20*1024*1024);  // no caller, no waste
                    }
                });
            break;
            case 3:
                // Type3 - interface class
                // Q. If OS release this Anonymous object, how can other process call the callback API without handle?
                // Q. When to release this memory of MemoryActions object?
                new MemoryActions(){  // <-- JAVA has an implicit reference to its surrounding class.
                    @Override
                    public void create(int num) {
                        mMemoryEater.waste_StaticVar(20*1024*1024);  // no caller, no waste
                    }

                    @Override
                    public void fill(Object obj) {
                    }

                    @Override
                    public void release(int num) {
                    }
                };
            break;
            default:
        }
    }

    // Ans: case 1&2&3 will release these Anonymous objects which referred by implicit reference
    // if the surrounding class/object(this Activity) destroyed.
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

