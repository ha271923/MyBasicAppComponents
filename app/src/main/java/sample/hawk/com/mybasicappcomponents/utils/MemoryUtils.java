package sample.hawk.com.mybasicappcomponents.utils;

/**
 * Created by ha271 on 2017/1/17.
 */

public class MemoryUtils {

    private static long fSLEEP_INTERVAL = 100;
    public static long getMemoryUse(){
        putOutTheGarbage();
        long totalMemory = Runtime.getRuntime().totalMemory();
        putOutTheGarbage();
        long freeMemory = Runtime.getRuntime().freeMemory();
        return (totalMemory - freeMemory);
    }
    private static void putOutTheGarbage() {
        collectGarbage();
        collectGarbage();
    }
    private static void collectGarbage() {
        try {
            System.gc();
            Thread.currentThread().sleep(fSLEEP_INTERVAL);
            System.runFinalization();
            Thread.currentThread().sleep(fSLEEP_INTERVAL);
        }
        catch (InterruptedException ex){
            ex.printStackTrace();
        }
    }
}
