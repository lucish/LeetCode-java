/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2021-2021. All rights reserved.
 */

package xyz.lucis.leetcode.q1001;

/**
 * https://leetcode-cn.com/problems/capacity-to-ship-packages-within-d-days/
 * 1011. 在 D 天内送达包裹的能力
 * 传送带上的包裹必须在 D 天内从一个港口运送到另一个港口。
 * <p>
 * 传送带上的第 i 个包裹的重量为 weights[i]。每一天，我们都会按给出重量的顺序往传送带上装载包裹。我们装载的重量不会超过船的最大运载重量。
 * <p>
 * 返回能在 D 天内将传送带上的所有包裹送达的船的最低运载能力。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：weights = [1,2,3,4,5,6,7,8,9,10], D = 5
 * 输出：15
 * 解释：
 * 船舶最低载重 15 就能够在 5 天内送达所有包裹，如下所示：
 * 第 1 天：1, 2, 3, 4, 5
 * 第 2 天：6, 7
 * 第 3 天：8
 * 第 4 天：9
 * 第 5 天：10
 * <p>
 * 请注意，货物必须按照给定的顺序装运，因此使用载重能力为 14 的船舶并将包装分成 (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) 是不允许的。
 */
public class Q1001 {

    public int shipWithinDays(int[] weights, int maxDay) {
        int max = findMax(weights);
        int sum = sum(weights);

        return search(weights, maxDay, max, sum, max);
    }

    private int search(int[] weights, int maxDay, int left, int right, int minLoad) {
        int mid = (left + right) / 2;
        int day = getDay(weights, mid);
        if (day <= maxDay) {
            if (mid == minLoad || getDay(weights, mid - 1) > maxDay) {
                return mid;
            } else {
                return search(weights, maxDay, left, mid - 1, minLoad);
            }
        } else {
            return search(weights, maxDay, mid + 1, right, minLoad);
        }
    }

    private int getDay(final int[] weights, final int load) {
        int day = 1;
        int temp = load;
        for (int i = 0; i < weights.length; i++) {
            int weight = weights[i];
            if (temp >= weight) {
                temp -= weight;
            } else {
                day++;
                temp = load;
                temp -= weight;
            }
        }
        return day;
    }

    private int findMax(int[] weights) {
        int max = 0;
        for (int i : weights) {
            if (i > max) {
                max = i;
            }
        }
        return max;
    }

    private int sum(int[] weights) {
        int sum = 0;
        for (int i : weights) {
            sum += i;
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] weights = {3, 2, 2, 4, 1, 4};
        int res = new Q1001().shipWithinDays(weights, 3);
        System.out.println(res);
    }

}
