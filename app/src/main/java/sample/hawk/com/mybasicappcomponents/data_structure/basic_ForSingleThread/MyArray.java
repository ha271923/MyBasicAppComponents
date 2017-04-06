package sample.hawk.com.mybasicappcomponents.data_structure.basic_ForSingleThread;

import java.util.Arrays;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/2/20.
 */

public class MyArray implements AccessIF {
    @Override
    public void show(){
        sort_and_search();
        compare_1D_array();
        compare_2D_array();
    }

    @Override
    public void use_case(){}
        public void sort_and_search() {
            int[] arr = {93, 5, 3, 55, 57, 7, 2 ,73, 41, 91};
            int key = 57; // 想要找到該value存放於array的哪個index
            SMLog.i("排序前: ");
            for(int i = 0; i < arr.length; i++){
                SMLog.i(arr[i] + " ");
                SMLog.i();
            }
            SMLog.i("排序後: ");
            Arrays.sort(arr);
            for(int i = 0; i < arr.length; i++){
                SMLog.i(arr[i] + " ");
            }
            int find = -1;
            if((find = Arrays.binarySearch(arr, key)) > -1) {
                SMLog.i("找到值於索引 " + find + " 位置");
            }
            else
                SMLog.i("找不到指定值");
        }

        public void compare_1D_array() {
            int[] arr1 = new int[10];
            int[] arr2 = new int[10];
            int[] arr3 = new int[10];
            Arrays.fill(arr1, 5);
            Arrays.fill(arr2, 5);
            Arrays.fill(arr3, 10);
            SMLog.i("arr1[ ");
            for(int i = 0; i < arr1.length; i++){
                SMLog.i(arr1[i] + " , ");
            }
            SMLog.i("]");

            SMLog.i("arr1 = arr2 ? " + Arrays.equals(arr1, arr2));
            SMLog.i("arr1 = arr3 ? " + Arrays.equals(arr1, arr3));
        }

        public void compare_2D_array() {
            int[][] arr1 = {{1, 2, 3},
                            {4, 5, 6},
                            {7, 8, 9}};
            int[][] arr2 = {{1, 2, 3},
                            {4, 5, 6},
                            {7, 8, 9}};
            int[][] arr3 = {{0, 1, 3},
                            {4, 6, 4},
                            {7, 8, 9}};
            SMLog.i("arr1 equals arr2? " + Arrays.deepEquals(arr1, arr2));
            SMLog.i("arr1 equals arr3? " + Arrays.deepEquals(arr1, arr3));
            SMLog.i("arr1 deepToString()" + Arrays.deepToString(arr1));
        }

    @Override
    public void show_by_forloop(){} //使用 for-loop 列出所有元素
    @Override
    public void show_by_foreach(){} //使用 for each 列出所有元素
    @Override
    public void show_by_iterator(){}  //使用 iterator 列出所有元素

}
