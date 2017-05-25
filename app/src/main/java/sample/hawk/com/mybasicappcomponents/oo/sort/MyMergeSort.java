package sample.hawk.com.mybasicappcomponents.oo.sort;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * MergeSort Algorithm
 *   OmegaO = nlog(n)
 *   Theta  = nlog(n)
 *   BigO   = nlog(n)
 */

public class MyMergeSort implements IAlgorithm {
    int[] mIntArray;

    private int[] numbers;
    private int number;
    private int[] helper;

    public MyMergeSort(int[] IntArray){
        mIntArray = IntArray;
    }

    public void algorithm(){
        long begin = System.currentTimeMillis();
        Sort();
        long end = System.currentTimeMillis();
        long elapsed = end-begin;
        SMLog.i("Algorithm elapsed = "+elapsed +"ms");
    }

    public void Sort() {
        this.numbers = mIntArray;
        number = mIntArray.length;
        this.helper = new int[number];
        mergesort(0, number - 1);
    }

    private void mergesort(int low, int high) {
        // check if low is smaller than high, if not then the array is sorted
        if (low < high) {
            // Get the index of the element which is in the middle
            int middle = low + (high - low) / 2;
            // Sort the left side of the array
            mergesort(low, middle);
            // Sort the right side of the array
            mergesort(middle + 1, high);
            // Combine them both
            merge(low, middle, high);
        }
    }

    private void merge(int low, int middle, int high) {

        // Copy both parts into the helper array
        for (int i = low; i <= high; i++) {
            helper[i] = numbers[i];
        }

        int i = low;
        int j = middle + 1;
        int k = low;
        // Copy the smallest values from either the left or the right side back
        // to the original array
        while (i <= middle && j <= high) {
            if (helper[i] <= helper[j]) {
                numbers[k] = helper[i];
                i++;
            } else {
                numbers[k] = helper[j];
                j++;
            }
            k++;
        }
        // Copy the rest of the left side of the array into the target array
        while (i <= middle) {
            numbers[k] = helper[i];
            k++;
            i++;
        }

    }
}
