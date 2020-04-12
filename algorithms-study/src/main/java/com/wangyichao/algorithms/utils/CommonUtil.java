package com.wangyichao.algorithms.utils;

public class CommonUtil {

    private static int[] swap(int a, int b) {
        int temp = a;
        a = b;
        b = temp;
        return new int[]{a, b};
    }
}
