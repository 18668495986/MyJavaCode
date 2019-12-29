package exerice.test2.arithmetic;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Description：排序源程序<br/>
 * Copyright (c) ，2019 ， xu <br/>
 * This program is protected by copyright laws. <br/>
 * Date： 2019年12月29日
 *
 * @author 徐威
 * @version : 1.0
 */

//排序
public class MySort {
    public static void main(String[] args) {
        int[] nums = {5, 7, 1, 9, 3};
        //bubSort(nums);
        choice(nums);
        System.out.println(Arrays.toString(nums));

        System.out.println(find(nums, 1));
        System.out.println(myBinarySearch(nums, 111));

        suShu(100);
        suShu2(100);
    }

    //冒泡排序
    public static void bubSort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {//控制轮询次数   3  2  1   需要比较2轮  每轮比出最后一位的最大值
            //控制比较的下标  每轮比较完最后一位就排好了，需要比较的个数就少一位（nums.length-1-i）
            for (int j = 0; j < nums.length - 1 - i; j++) {
                if (nums[j] > nums[j + 1]) {
                    //异或换位法
                    nums[j + 1] = nums[j + 1] ^ nums[j];
                    nums[j] = nums[j + 1] ^ nums[j];
                    nums[j + 1] = nums[j + 1] ^ nums[j];
                }
            }
        }
    }

    //选择排序 固定第一个的下标，依次与后面的数进行比较 每次排出最小值  轮询次数为nums.length-1
    public static void choice(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) { // 每次都从i 的后一位开始比
                if (nums[j] < nums[i]) {
                    //加和换位法
                    nums[j] = nums[j] + nums[i];
                    nums[i] = nums[j] - nums[i];
                    nums[j] = nums[j] - nums[i];
                }
            }
        }
    }

    //查找
    public static int find(int[] nums, int key) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == key) {
                return i;
            }
        }
        return -1; //表示没有找到
    }

    //二分查找法 3 1 9 5
    public static int myBinarySearch(int[] nums, int key) {
        bubSort(nums); //先排序 1 3 5 9
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int middle = (left + right) / 2;
            if (nums[middle] > key) { //所要找的值在左半边 让right=middle-1
                right = middle - 1;
            } else if (nums[middle] < key) { //所要找的值在右半边 让left=middle+1
                left = middle + 1;
            } else {  // nums[middle]刚好=key 则该值就是要找的key
                return middle;
            }
        }
        return -1;
    }

    //求素数
    public static void suShu(int num) {
        int sum = 0; //求素数和
        ArrayList<Integer> list = new ArrayList<>(); //用于存放素数
        for (int i = 2; i <= num; i++) { //最小的素数为2，从2开始
            int j = 2;
            for (; j < i; j++) {
                if (i % j == 0) break; //不是素数
            }
            if (i == j) { //走到这一步的说明一定是素数
                list.add(j);
                sum += j;
            }
        }
        System.out.println("是素数的是" + list);
        System.out.println("素数和为" + sum);
    }

    //求素数  方法二：
    public static void suShu2(int num) {
        int sum = 0; //求素数和
        ArrayList<Integer> list = new ArrayList<>(); //用于存放素数
        for (int i = 2; i <= num; i++) {
            int flag = 1; //作标记(也可以用boolean来作标记) 1：是素数     2：不是素数
            for (int j = 2; j < i; j++) {
                if (i % j == 0) { //不是素数
                    flag =2;
                    break;
                }
            }
            if(flag == 1){
                list.add(i);
                sum+=i;
            }
        }
        System.out.println("是素数的是" + list);
        System.out.println("素数和为" + sum);
    }
}


