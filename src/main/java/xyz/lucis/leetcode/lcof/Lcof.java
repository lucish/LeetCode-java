/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2021-2021. All rights reserved.
 */

package xyz.lucis.leetcode.lcof;

import java.util.LinkedList;

/**
 * https://leetcode-cn.com/problems/gu-piao-de-zui-da-li-run-lcof/submissions/
 * <p>
 * 假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖该股票一次可能获得的最大利润是多少？
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/gu-piao-de-zui-da-li-run-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Lcof {

    public int maxProfit(int[] prices) {

        int maxProfit = 0;

        int minPrice = Integer.MAX_VALUE;

        for (int price : prices) {
            // 本日最大收益
            int maxProfitToday = price - minPrice;
            if (maxProfitToday > maxProfit) {
                maxProfit = maxProfitToday;
            }

            // 更新本日前历史最低价
            if (price < minPrice) {
                minPrice = price;
            }

        }

        return maxProfit;
    }

    /**
     * 历史版本 7 ms 37.5 MB
     */
    public int maxProfit2(int[] prices) {

        LinkedList<Integer> stack = new LinkedList<>();

        int maxVal = 0;

        for (int price : prices) {
            if (stack.size() == 0) {
                stack.add(price);
                continue;
            }
            // 入栈 维护单调栈
            while (stack.size() > 0) {
                Integer top = stack.getLast();
                if (top < price) {
                    break;
                }
                stack.removeLast();
            }
            stack.add(price);

            int val = getVal(stack);
            if (val > maxVal) {
                maxVal = val;
            }
        }
        return maxVal;
    }

    private int getVal(LinkedList<Integer> stack) {
        if (stack.size() <= 1) {
            return 0;
        }
        int top = stack.getLast();
        int bottom = stack.getFirst();
        return top - bottom;
    }

}
