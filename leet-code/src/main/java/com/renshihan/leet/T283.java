package com.renshihan.leet;

/**
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 */
public class T283 {
    public void moveZeroes(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return;
        }
        int next = 0;
        for (int i = 0; i < len; i++) {
            if (nums[i] != 0) {
                //遵守循环不变式：[0,i)非零，所以先赋值，后自增
                nums[next] = nums[i];
                next++;
            }
        }
    }

    public static void main(String[] args) {
        int len=10;
        for (int i = 0; i < len; i++) {
            System.out.println("i++"+(i++));
            System.out.println("++i"+(++i));
        }
    }
}
