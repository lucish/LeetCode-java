/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2021-2021. All rights reserved.
 */

package xyz.lucis.leetcode.xorofsubarr;

import java.util.Arrays;

public class XorOfSubarray {

    public int[] xorQueries(int[] arr, int[][] queries) {
        int n = arr.length;
        int m = queries.length;

        int[] sum = new int[n + 1];
        // 计算前缀和 xorRes[i]中为 目标数组中前i-1个元素的前缀和
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] ^ arr[i - 1];
        }

        int[] resList = new int[m];
        for (int i = 0; i < m; i++) {
            int[] query = queries[i];
            int start = query[0];
            int end = query[1];
            int res = sum[start] ^ sum[end + 1];
            resList[i] = res;
        }
        return resList;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {1, 3, 4, 8};
        int[][] queries = new int[][] {{0, 1}, {1, 2}, {0, 3}, {3, 3}};
        int[] res = new XorOfSubarray().xorQueries(arr, queries);
        System.out.println(Arrays.toString(res));
    }

}
