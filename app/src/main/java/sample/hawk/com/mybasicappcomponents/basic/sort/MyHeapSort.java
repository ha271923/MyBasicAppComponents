package sample.hawk.com.mybasicappcomponents.basic.sort;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * HeapSort Algorithm
 *   OmegaO = nlog(n)
 *   Theta  = nlog(n)
 *   BigO   = nlog(n)
 */

public class MyHeapSort {
    int[] mIntArray;
    private static int N;

    public MyHeapSort(int[] IntArray){
        mIntArray = IntArray;
    }

    public void algorithm(){
        long begin = System.currentTimeMillis();
        Sort();
        long end = System.currentTimeMillis();
        long elapsed = end-begin;
        SMLog.i("Algorithm elapsed = "+elapsed +"ms");
    }

    public void Sort()
    {
        heapify(mIntArray);
        for (int i = N; i > 0; i--)
        {
            swap(mIntArray,0, i);
            N = N-1;
            maxheap(mIntArray, 0);
        }
    }
    // Function to build a heap
    static public void heapify(int arr[])
    {
        N = arr.length-1;
        for (int i = N/2; i >= 0; i--)
            maxheap(arr, i);
    }
    // Function to swap largest element in heap
    static public void maxheap(int arr[], int i)
    {
        int left = 2*i ;
        int right = 2*i + 1;
        int max = i;
        if (left <= N && arr[left] > arr[i])
            max = left;
        if (right <= N && arr[right] > arr[max])
            max = right;

        if (max != i)
        {
            swap(arr, i, max);
            maxheap(arr, max);
        }
    }
    // Function to swap two numbers in an array
    static public void swap(int arr[], int i, int j)
    {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}
