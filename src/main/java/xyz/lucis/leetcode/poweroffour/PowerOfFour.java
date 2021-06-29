/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2021-2021. All rights reserved.
 */

package xyz.lucis.leetcode.poweroffour;

public class PowerOfFour {

    public boolean isPowerOfFour(int n) {
        if (n == 1) {
            return true;
        }
        int base = 4;
        int des = 1;
        int maxOfDes = Integer.MAX_VALUE / base;
        while (des <= maxOfDes) {
            des *= base;
            if (des == n) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        boolean res = new PowerOfFour().isPowerOfFour(1073741824);
        System.out.println(res);
    }

}
