package sample.hawk.com.mybasicappcomponents.basic.Polymorphism;

/**
 * Created by ha271 on 2016/10/18.
 */

public interface ILegActions extends IActions {

    // for Leg
    void run(int distance);
    void jump(int distance);
    void walk(int distance);
}
