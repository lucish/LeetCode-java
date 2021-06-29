/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2021-2021. All rights reserved.
 */

package xyz.lucis.leetcode.forgjump;

/**
 * https://leetcode-cn.com/problems/frog-jump/
 */
public class FrogJump {

    public boolean canCross(int[] stones) {
        int n = stones.length;
        boolean[][] dp = new boolean[n][n];

        // 初始状态一定是为true
        dp[0][0] = true;

        for (int i = 1; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                int k = stones[i] - stones[j];
                // 相邻石子的距离大于其编号的时候，必然跳不过去(存在一个距离递增的关系)
                if (k > j + 1) {
                    break;
                }
                // j对应的上一次所在的石子位置，判断在[k±1]的范围里面是否能够满足从j跳到i
                dp[i][k] = dp[j][k - 1] || dp[j][k] || dp[j][k + 1];

                // 到了数组最末尾的时候，进行一个判断，判断是否能跳到最后一个石子
                if (i == n - 1 && dp[i][k]) {
                    return true;
                }
            }
        }
        return false;
    }

}
