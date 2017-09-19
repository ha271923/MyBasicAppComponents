package sample.hawk.com.mybasicappcomponents.oo;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class MyJavaOCJPClass {
    private static final String TAG = "[MyOCJPJavaClass]";

    public MyJavaOCJPClass(Context context, int param){
        int idx=0;
        switch(param){
            case 10000:

                break;
            case 10001:
                List<Integer> intlist = new ArrayList<Integer>();
                ArrayList alist = new ArrayList();
                alist.add(0);alist.add(1);
                sum(alist);
                break;
            case 10002:
                break;
            case 10003:
                break;
            case 10004:
                break;
            case 10005:
                break;
            case 10006:
                break;
            case 10007:
                break;

            case 10008:
                break;
            case 10009:
                break;
            default:

        }
    }

    public static int sum(List<Integer> intList) {
        int sum = 0;
        for (int i : intList) {
            sum += i;
        }
        return sum;
    }
}
