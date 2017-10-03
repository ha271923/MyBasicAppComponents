package sample.hawk.com.mybasicappcomponents.basic.Objects;

import sample.hawk.com.mybasicappcomponents.debugTest.MemoryLeak.MemoryEater;

/**
 * Created by ha271 on 2017/1/12.
 */

public class Reference_1_1_1 implements RefMemLeakActions {
    MemoryEater memoryEater;
    static MemoryEater s_memoryEater;

    private byte[] leak_obj_var;
    private static byte[] leak_static_class_var;

    @Override
    protected void finalize() throws Throwable { // During GC(), this callback will be called.
        // remark the following code to create a memory leakage
        //if(s_memoryEater!=null)
        //    s_memoryEater = null;   // B2: fix B1 memory leak
        //if(leak_static_class_var!=null)
        //    leak_static_class_var = null; // C2: fix C1 memory leak
    }

    @Override
    public void MemoryLeak_Next(){
        // Hawk: static reference to hold 10MB memory leakage at here!
        // A1: No Leak, Caller(memoryEater is ObjVar), Callee(Return Value is saved by static REF, finalize() set null )
        // memoryEater = new MemoryEater();
        // leak_obj_var = memoryEater.waste_StaticVar(10*1024*1024);
        // B1: Leak, Caller(memoryEater is ObjVar), Callee(Return Value is saved by static REF, finalize() set null )
        // s_memoryEater = new MemoryEater();
        // leak_obj_var = s_memoryEater.waste_StaticVar(10*1024*1024);
        // C1: Leak, Caller(memoryEater is ObjVar, REF is static var), Callee(Return Value is saved by static REF, finalize() set null )
        memoryEater = new MemoryEater();
        leak_static_class_var = memoryEater.waste_StaticVar(10*1024*1024);
    }

}
