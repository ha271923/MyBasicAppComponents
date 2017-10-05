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
 *    • Object和T不同點在於，Object是一個實打實的類,並沒有泛指誰，可以直接給List中add(new Object())，而且Object也不能代表所有類，
 *    比方說String和Integer就不是Object的子類，所以Object不屬於泛型類，而T是泛指所有類;
 *    ?和T區別是？是一個不確定類，？和T都表示不確定的類型，但如果是T的話，函數里面可以對T進行操作，比方T car = getCar()，而不能用？car = getCar()。
 */

public class UnkonwnType {
    List<? extends ParentClass> mlistA;
    List<? extends ParentClass> mlistB;
    List<? extends ParentClass> mlistC;
    List<? extends CommonResources> mlistD;

    public UnkonwnType(){
        mlistA = new ArrayList<ParentClass>();
        mlistB = new ArrayList<ChildClass>();
        // mlistC = new ArrayList<MyInterface>(); // ERROR: incompatible types.
        // mlistD = new ArrayList<CommonResources.InnerClass>(); // ERROR: incompatible types.
    }

    // Object也不能代表所有類，比方說String和Integer就不是Object的子類，所以Object不屬於泛型類
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

        // showListO(li); // ERROR: because Integer and String are not subtypes of Object
        // showListO(ls); // ERROR: because Integer and String are not subtypes of Object
        showListT(li);
        showListT(ls);
        showListQ(li);
        showListQ(ls);
    }

}
