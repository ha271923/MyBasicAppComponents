package sample.hawk.com.mybasicappcomponents.designpattern.generic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import sample.hawk.com.mybasicappcomponents.basic.ChildClass;
import sample.hawk.com.mybasicappcomponents.basic.CommonResources;
import sample.hawk.com.mybasicappcomponents.basic.ParentClass;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 *    ArrayList<T> al=new ArrayList<T>(); 指定集合元素只能是T類型
 *    ArrayList<?> al=new ArrayList<?>(); 集合元素可以是任意類型，這種沒有意義，一般是方法中，只是為了說明用法
 *    ArrayList<? extends E> al=new ArrayList<? extends E>();
 *    泛型的限定：
 *    ? extends E :接收E類型或者E的子類型。
 *    ? super E :接收E類型或者E的父類型
 *    ?和T區別是？是一個不確定類，？和T都表示不確定的類型，但如果是T的話，函數里面可以對T進行操作，比方T car = getCar()，而不能用？car = getCar()。
 */

public class UnknownType {
    List<? extends ParentClass> mlistA;
    List<? extends ParentClass> mlistB;
    List<? extends ParentClass> mlistC;
    List<? extends CommonResources> mlistD;

    public UnknownType(){
        mlistA = new ArrayList<ParentClass>();
        mlistB = new ArrayList<ChildClass>();
        // mlistC = new ArrayList<MyInterface>(); // ERROR: incompatible types.
        // mlistD = new ArrayList<CommonResources.InnerClass>(); // ERROR: incompatible types.
    }


    public void showListO(List<Object> list) {
        list.add(new Object());
        for (Object elem : list)
            SMLog.i(elem + " ");
        SMLog.i();
    }
    // T是泛指所有類; ? 和 T 區別是 ? 是一個不確定類，?和T都表示不確定的類型
    // 但如果是T的話，函數里面可以對T進行操作，比方:
    // T car = getCar() // OK
    // ? car = getCar() // ERR
    public <T> void showListT(List<T> list) {
        for (T elem : list)
            SMLog.i(elem + " ");
        SMLog.i();
    }
    public void showListQ(List<?> list) {
        for (int i = 0;i<list.size();i++)
            SMLog.i(list.get(i) + " ");
        SMLog.i();
    }
    public void show() {
        List<Integer> li = Arrays.asList(1, 2, 3);
        List<String>  ls = Arrays.asList("one", "two", "three");

        // showListO(li); // ERROR: because List<Integer> and List<String> are not subtypes of List<Object>
        // showListO(ls); // ERROR: because List<Integer> and List<String> are not subtypes of List<Object>
        showListT(li);
        showListT(ls);
        showListQ(li);
        showListQ(ls);

        ObjectVsGenericType("MyObject", "MyGeneric");
    }

    // Different between Object with Generic Type
    public <T> void ObjectVsGenericType(Object obj, T t){
        SMLog.i("Object="+obj+"   , T="+t);

        ObjectAsParam(1);
        ObjectAsParam("1");
        ObjectAsParam(this);

        GenericTypeAsParam(1);
        GenericTypeAsParam("1");
        GenericTypeAsParam(this);
    }

    public Object ObjectAsParam(Object obj) {
        SMLog.i("Obj="+obj);
        return obj;
    }

    /*
    * Two advantages at Generic Type:
    (1) no need of casting (the compiler hides this from you)
    (2) compile time safety that works. If Object can't be casting, you'll have a ClassCastException, at runtime.
    * */
    public <T> T GenericTypeAsParam(T t) {
        SMLog.i("T="+t);
        return t;
    }


}
