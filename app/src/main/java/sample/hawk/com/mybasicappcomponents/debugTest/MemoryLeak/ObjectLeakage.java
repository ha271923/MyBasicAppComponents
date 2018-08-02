package sample.hawk.com.mybasicappcomponents.debugTest.MemoryLeak;

/**
 * Created by ha271 on 2016/10/19.
 */

public class ObjectLeakage implements MemoryActions {

    private byte[] byteArray;
    private int number;

    @Override
    public void create(int num){
         byteArray = new byte[num];
         number = num;
    }

    @Override
    public void fill(Object obj){
        if(obj instanceof ObjectLeakage){
            for(int i=0;i<number;i++){
                ((ObjectLeakage) obj).byteArray[i]=0x33;
            }
        }
    }


    @Override
    public void release(int num) {

    }

}
