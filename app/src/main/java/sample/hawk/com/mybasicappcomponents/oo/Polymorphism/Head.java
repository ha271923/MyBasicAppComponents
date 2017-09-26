package sample.hawk.com.mybasicappcomponents.oo.Polymorphism;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2016/10/18.
 */

public class Head extends Brian implements IHeadActions, IIQActions{
    private static String className="Head";
    public int head_var;

    @Override
    public void brian_api() {
        SMLog.i();
    }

    public void head_api() {
        SMLog.i();
    }

    @Override
    public Knowledge learn(Knowledge source, Knowledge dest){
        dest = source;
        return dest;
    }

    @Override
    public void speak() {
        SMLog.i();
    }

    @Override
    public void see(String what){
        SMLog.i();
    }

    @Override
    public void see(String what, int color){
        SMLog.i();
    }

    @Override
    public void see(String what, int color, int size){
        SMLog.i();
    }

    @Override
    public void eat(Object obj) {
        SMLog.i();
    }

    @Override
    public void think() {
        SMLog.i();
    }

}
