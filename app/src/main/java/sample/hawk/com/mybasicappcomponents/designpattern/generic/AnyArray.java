package sample.hawk.com.mybasicappcomponents.designpattern.generic;

import java.util.ArrayList;
import java.util.List;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * 不要把 Generic與Array混用
 *
 * You should not mix-up arrays and generics(Template).
 * 解釋如下:
 * @href=https://stackoverflow.com/questions/18581002/how-to-create-a-generic-array
 */

public class AnyArray<T> {
    T[] mArray;
    ArrayList<T> mArrayList;

    // 基本語法測試
    public AnyArray(){
           int[] intArr    = new    int[10]; // OK
//           T[] t         = new       T[5]; // Failed!!
        Object[] objArr    = new String[10]; // OK
        String[] strArr    = new String[10]; // OK
        List<String> list1 = new ArrayList<String>(); // OK
//      List<Object> list2 = new ArrayList<String>(); // Failed!!
        List<?>[] listArr  = new List<?>[10];
        listArr[0]         = new ArrayList<String>();  // OK
        listArr[1]         = new ArrayList<Integer>(); // OK
    }

    public void setArray(T[] array){
        mArray = array;
    }

    public void setArrayList(ArrayList<T> arrayList){
        mArrayList = arrayList;
    }

    public T[] getArray(){
        return mArray;
    }

    public ArrayList<T> getArrayList(){
        return mArrayList;
    }

    public void show(){
        for(int i=0; i<mArray.length; i++)
            SMLog.i("mArray= "+ mArray[i]);
        for(int j=0; j<mArrayList.size(); j++)
            SMLog.i("mArrayList= "+ mArrayList.get(j));
    }
}
