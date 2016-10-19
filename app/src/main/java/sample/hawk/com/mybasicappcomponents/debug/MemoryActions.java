package sample.hawk.com.mybasicappcomponents.debug;

/**
 * Created by ha271 on 2016/10/19.
 */

public interface MemoryActions {

    void create(int num);
    void fill(Object obj);
    void release(int num);
}
