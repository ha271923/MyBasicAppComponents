package sample.hawk.com.mybasicappcomponents.debugTest;

import java.util.Map;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2016/10/19.
 */

public class MemoryEater {

    MemoryLeakage leakObj;
    ThreadLeakage leakThread;
    private byte[] leak_obj_var;
    private static byte[] leak_static_class_var;

    public String key;

    public enum TYPE{
        LOCAL_VAR, OBJECT_VAR, STATIC_VAR, OBJECT, THREAD
    }

    public MemoryEater() {
    }

    public MemoryEater(String Key) {
        this.key =Key;
    }

    public void waste_Memory(TYPE type, int size){
        // stay ONE for verify the memory usage in the memory monitor.
        switch(type){
            case LOCAL_VAR:
                waste_LocalVar(size);     // A0: No leak: Auto Release after Var life-end and GC()
                break;
            case OBJECT_VAR:
                waste_ObjectVar(size);    // B0: No leak: Auto Release after Object destroy and GC().
                break;
            case STATIC_VAR:
                waste_StaticVar(size);    // C0: set NULL by you: Allow release if this static ref is NULL in finalize().
                break;
            case OBJECT:
                waste_Object(size);       // No leak: JAVA will release this objects if this object destroy.
                break;
            case THREAD:
                if(size > 20)
                    size = 20;
                waste_Thread(size);       // No leak: Thread objects will be released if its thread run() is END.
                break;
            default:
                // no support type
        }
    }


    @Override
    protected void finalize() throws Throwable { // During GC(), this callback will be called.
        SMLog.i("MemoryEater call finalize() +++");
        // leak_local_var = null;        // A2: no need, JAVA will auto release it.
        // leak_obj_var = null;          // B2: no need, JAVA will auto release it.
        if(leak_static_class_var!=null)  // C2: leak: You SHOULD release the static ref memory by null.
            leak_static_class_var = null;
        super.finalize();
        SMLog.i("MemoryEater call finalize() ---");
    }

    public byte[] waste_LocalVar(int size){ // Auto Release after Var life-end and GC()
        try{
            SMLog.i("waste_LocalVar +++++++++++");
            byte[] leak_local_var = new byte[size];
            fill_byte_array(leak_local_var);        // A1: Allow release if exit this brace{}. no leak.
            SMLog.i("waste_LocalVar -----------");
            return leak_local_var;
        } catch(OutOfMemoryError e) { // It's not an exception; it's an error: java.lang.OutOfMemoryError
            SMLog.i(e.toString());
            e.printStackTrace();
        }
        return null;
    }

    public byte[] waste_ObjectVar(int size){ // Auto Release after Object destroy and GC().
        try{
            SMLog.i("waste_ObjectVar +++++++++++");
            leak_obj_var = new byte[size];
            fill_byte_array(leak_obj_var);          // B1: Allow release if this object destroy.
            SMLog.i("waste_ObjectVar -----------");
            return leak_obj_var;
        } catch(OutOfMemoryError e) { // It's not an exception; it's an error: java.lang.OutOfMemoryError
            SMLog.i(e.toString());
            e.printStackTrace();
        }
        return null;
    }

    public byte[] waste_StaticVar(int size){ // Auto Release after Object destroy
        try{
            SMLog.i("waste_StaticVar +++++++++++");
            leak_static_class_var = new byte[size];
            fill_byte_array(leak_static_class_var);    // C1: Allow release if this static ref is NULL.
            SMLog.i("waste_StaticVar -----------");
            return leak_static_class_var;
        } catch(OutOfMemoryError e) { // It's not an exception; it's an error: java.lang.OutOfMemoryError
            SMLog.i(e.toString());
            e.printStackTrace();
        }
        return null;
    }

    public MemoryLeakage waste_Object(int size){
        try{
            SMLog.i("waste_Object +++++++++++");
            MemoryLeakage leakObj2;
            leakObj2 = new MemoryLeakage();
            leakObj2.create(size);
            leakObj2.fill(leakObj2);
            SMLog.i("waste_Object -----------");
            return leakObj2;
        } catch(OutOfMemoryError e) { // It's not an exception; it's an error: java.lang.OutOfMemoryError
            SMLog.i(e.toString());
            e.printStackTrace();
        }
        return null;
    }

    public ThreadLeakage waste_Thread(int size){
        try{
            SMLog.i("waste_Thread +++++++++++");
            ThreadLeakage leakThread2;
            leakThread2 = new ThreadLeakage();
            leakThread2.create(size);
            leakThread2.fill(leakThread2);
            SMLog.i("waste_Thread -----------");
            return leakThread2;
        } catch(OutOfMemoryError e) { // It's not an exception; it's an error: java.lang.OutOfMemoryError
            SMLog.i(e.toString());
            e.printStackTrace();
        }
        return null;
    }

    public static void ForceOutOfMemory() {
        try {
            for(;;) {
                Map map = System.getProperties();
                map.put(new MemoryEater("key"), "value");
            }
        } catch(OutOfMemoryError e) { // It's not an exception; it's an error: java.lang.OutOfMemoryError
            SMLog.i(e.toString());
            e.printStackTrace();
        }
    }

    private void fill_byte_array(byte[] byteArray){
        for(int i=0;i<byteArray.length;i++)
            byteArray[i]=0x33; // fill this patent
    }

}
