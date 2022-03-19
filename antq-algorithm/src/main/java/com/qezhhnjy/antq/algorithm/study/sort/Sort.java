package com.qezhhnjy.antq.algorithm.study.sort;

import java.util.Arrays;

/**
 * @author zhaoyangfu
 * @date 2022/3/10-23:17
 */
public class Sort {

    public static void main(String[] args) {
        int[] a = new int[]{132, 1324, 4, 23, 51, 4312, 213, 1, 341, 39};
        insert(a, a.length);
    }

    /**
     * 插入排序
     */
    public static void insert(int[] arr, int l) {
        //将arr[0]作为一个有序数组,从arr[1]开始到arr[l-1]进行遍历
        for (int i = 1; i < l; i++) {

            //将arr[i]的值复制出来,防止当j=i-1且arr[i]<arr[j]时,将arr[j]往后移时将arr[i]的值覆盖.
            int temp = arr[i];

            //在for循环外声明变量j,方便在循环结束后将arr[j+1]赋值为初始的arr[i],即将arr[i]的值插入到arr[j+1].
            int j = i - 1;

            //从arr[i-1]开始循环如果arr[i]的初始值小于循环-1的arr[j],则将arr[j]后移一位.
            for (; j >= 0 && temp < arr[j]; j--) {
                arr[j + 1] = arr[j];
            }
            //直到j=0并arr[1]=arr[0]后,或arr[i]的初始值大于指定的arr[j],即将arr[i]的初始值赋给arr[j+1].
            arr[j + 1] = temp;
        }
        System.out.println(Arrays.toString(arr));
    }
}
