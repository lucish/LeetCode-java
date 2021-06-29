/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2021-2021. All rights reserved.
 */

package xyz.lucis.leetcode.hanmingdistance;

public class HanMingDistance {

    public int hammingDistance(int x, int y) {
        int res = x ^ y;

        // count bit 1 in res

        int distance = 0;

        while (res > 0) {
            int lastBit = res & 1;
            distance += lastBit;
            res = res >> 1;
        }

        return distance;
    }

    public static void main(String[] args) {
        new HanMingDistance().hammingDistance(1, 4);
    }

}
