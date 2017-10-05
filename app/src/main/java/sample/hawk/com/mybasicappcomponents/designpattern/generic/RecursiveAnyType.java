package sample.hawk.com.mybasicappcomponents.designpattern.generic;

import java.util.ArrayList;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/10/5.
 */

public class RecursiveAnyType<T> {
    ArrayList<T> mArrayList;
    public RecursiveAnyType (T item){
        mArrayList = new ArrayList<T>();
        mArrayList.add(item);
    }

    public RecursiveAnyType AddItem(T item){
        mArrayList.add(item);
        return this;
    }

    public void show(){
        SMLog.i("List<T> mArrayList="+ mArrayList);
    }
}
