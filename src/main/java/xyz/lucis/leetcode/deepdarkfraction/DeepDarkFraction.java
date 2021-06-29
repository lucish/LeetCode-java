/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2021-2021. All rights reserved.
 */

package xyz.lucis.leetcode.deepdarkfraction;

/**
 * https://leetcode-cn.com/problems/deep-dark-fraction/
 *
 * @Date 2021/4/26
 */
public class DeepDarkFraction {

    public int[] fraction(int[] cont) {
        int lastNum = cont[cont.length - 1];
        Fraction fraction = new Fraction(lastNum, 1);
        for (int i = cont.length - 2; i >= 0; i--) {
            fraction = addPrefix(fraction, cont[i]);
        }

        int[] res = {fraction.n1, fraction.n2};
        return res;
    }

    private Fraction addPrefix(Fraction fraction, int prefix) {
        // 倒数
        int temp = fraction.n1;
        fraction.n1 = fraction.n2;
        fraction.n2 = temp;
        // 加上带分数前缀
        fraction.n1 += prefix * fraction.n2;
        return fraction;
    }

    public static void main(String[] args) {
        int[] cont = {0, 0, 3};
        new DeepDarkFraction().fraction(cont);
    }

    static class Fraction {
        int n1;

        int n2;

        Fraction(int n1, int n2) {
            this.n1 = n1;
            this.n2 = n2;
        }
    }

}
