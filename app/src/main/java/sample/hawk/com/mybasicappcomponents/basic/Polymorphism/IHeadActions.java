package sample.hawk.com.mybasicappcomponents.basic.Polymorphism;

/**
 * Created by ha271 on 2016/10/18.
 */

public interface IHeadActions extends IActions{
    // for Head
    void speak();
    void see(String what);
    void see(String what, int color);
    void see(String what, int color, int size);
    void eat(Object obj);
}
