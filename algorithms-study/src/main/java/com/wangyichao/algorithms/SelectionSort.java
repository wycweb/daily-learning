package com.wangyichao.algorithms;

import com.wangyichao.algorithms.utils.SortHelper;

public class SelectionSort {

    public static void main(String[] args) {
        int[] data = SortHelper.generateRandomArray(100, 0, 1000);

        int[] result = SelectionSort.sort(data);

        SortHelper.printArray(result);
    }


    public static int[] sort(int[] arr) {
        int length = arr.length;

        for (int i = 0; i < length; i++) {
            int minIndex = i;

            for (int j = i + 1; j < length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }


            swap(arr, minIndex, i);
        }

        return arr;
    }

    private static void swap(int[] arr, int i, int j) {
        int m = arr[i];

        arr[i] = arr[j];
        arr[j] = m;
    }
}
