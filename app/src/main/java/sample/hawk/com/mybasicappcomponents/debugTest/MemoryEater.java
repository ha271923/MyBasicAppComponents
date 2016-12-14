package sample.hawk.com.mybasicappcomponents.debugTest;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2016/10/19.
 */

public class MemoryEater {
    @Override
    protected void finalize() throws Throwable {
        SMLog.i("MemoryEater call finalize() +++");
        super.finalize();
        SMLog.i("MemoryEater call finalize() ---");
    }

    MemoryLeakage leak1;
    ThreadLeakage leak2;
    static  private byte[] leak_obj_var = new byte[1*1024*1024];
    private byte[] leak_static_obj_var = new byte[2*1024*1024];

    public void waste_Variable(){ // Auto Release after Object destroy
        SMLog.i("waste_Variable +++++++++++");
        byte[] leak_local_var = new byte[3*1024*1024];
        fill_byte_array(leak_local_var);
        fill_byte_array(leak_obj_var);
        fill_byte_array(leak_static_obj_var);
        SMLog.i("waste_Variable -----------");
    }

    public void waste_Object(){
        SMLog.i("waste_Object +++++++++++");
        MemoryLeakage leak1;
        leak1 = new MemoryLeakage();
        leak1.create(3*1024*1024);
        leak1.fill(leak1);
        SMLog.i("waste_Object -----------");
    }

    public void waste_Thread(){
        SMLog.i("waste_Thread +++++++++++");
        ThreadLeakage leak2;
        leak2 = new ThreadLeakage();
        leak2.create(20);
        leak2.fill(leak2);
        SMLog.i("waste_Thread -----------");
    }


    private void fill_byte_array(byte[] byteArray){
        for(int i=0;i<byteArray.length;i++)
            byteArray[i]=0x33; // fill this patent
    }




}
