package sample.hawk.com.mybasicappcomponents.designpattern.generic;

/**
 * Created by ha271 on 2017/10/11.
 */

public interface IGenericIF<T1, T2> {
    public void setT1(T1 t1);
    public void setT2(T2 t2);
    public T1 getT1();
    public T2 getT2();
}
