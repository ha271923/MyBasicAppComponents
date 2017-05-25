package sample.hawk.com.mybasicappcomponents.oo.sort;

import java.util.ArrayList;
import java.util.List;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * QuickSort Algorithm
 *   OmegaO = nlog(n)
 *   Theta  = nlog(n)
 *   BigO   = n^2
 */

public class MyQuickSort implements IAlgorithm {
    int[] mIntArray;
    public MyQuickSort(int[] IntArray){
        mIntArray = IntArray;
    }

    /**
     * Create a set of random number,and the number value may repeat.
     */

    public void algorithm(){
        Sort();
    }

    private void Sort() {
        long begin = System.currentTimeMillis();
        List<Integer> list = new ArrayList<Integer>();
        for (int n : mIntArray)
            list.add(n);
        list = Sort(list);
        for (int i = 0;i < mIntArray.length;++i)
            mIntArray[i] = list.get(i);
        long end = System.currentTimeMillis();
        long elapsed = end-begin;
        SMLog.i("Sort algorithm elapsed = "+elapsed +"ms");
    }

    public List<Integer> Sort(List<Integer> list)
    {
        if (list.size() < 2)
            return list;

        // middle pivot
        int pivot = list.get(list.size() / 2);
        list.remove(list.size() / 2);
        List<Integer> less = new ArrayList<Integer>();
        List<Integer> greater = new ArrayList<Integer>();
        List<Integer> result = new ArrayList<Integer>();
        for (Integer n : list)
        {
            if (n > pivot)
                greater.add(n);
            else
                less.add(n);
        }
        result.addAll(Sort(less));
        result.add(pivot);
        result.addAll(Sort(greater));
        return result;
    }
}