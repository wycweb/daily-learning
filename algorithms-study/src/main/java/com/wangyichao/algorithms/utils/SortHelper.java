package com.wangyichao.algorithms.utils;

public class SortHelper {

    // 生成有n个元素的随机数组,每个元素的随机范围为[rangeL, rangeR]
    public static int[] generateRandomArray(int n, int rangeL, int rangeR) {

        if (rangeL >= rangeR) {
            return null;
        }

        int[] arr = new int[n];

        for (int i = 0; i < n; i++)
            arr[i] = (int) (Math.random() * (rangeR - rangeL + 1) + rangeL);

        return arr;
    }

    // 打印arr数组的所有内容
    public static void printArray(int[] arr) {

        for (int i = 0; i < arr.length; i++) {
            System.out.println("index:" + i + "，value:" + arr[i]);
        }
        System.out.println();
    }
}
