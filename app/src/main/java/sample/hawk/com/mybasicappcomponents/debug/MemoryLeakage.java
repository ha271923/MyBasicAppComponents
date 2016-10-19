package sample.hawk.com.mybasicappcomponents.debug;

/**
 * Created by ha271 on 2016/10/19.
 */

public class MemoryLeakage implements MemoryActions {

    private byte[] byteArray;
    private int number;

    @Override
    public void create(int num){
         byteArray = new byte[num];
         number = num;
    }

    @Override
    public void fill(Object obj){
        if(obj instanceof MemoryLeakage){
            for(int i=0;i<number;i++){
                ((MemoryLeakage) obj).byteArray[i]=0x33;
            }
        }
    }


    @Override
    public void release(int num) {

    }

}
