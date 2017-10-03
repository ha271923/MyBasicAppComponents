package sample.hawk.com.mybasicappcomponents.basic.Polymorphism;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2016/10/18.
 */

public class Brian implements IIQActions{
    private static String className="Brian";
    private Knowledge Know;
    public int brian_var;

    public Brian(){
        Know = new Knowledge();
        Know.getName();
        Know.getAge();
    }

    @Override
    public Knowledge learn(Knowledge source, Knowledge dest){
        dest = source;
        return dest;
    }

    @Override
    public void think() {
        SMLog.i();
    }

    public void brian_api() {
        SMLog.i();
    }

}
