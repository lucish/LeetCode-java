/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2021-2021. All rights reserved.
 */

package xyz.lucis.leetcode.peakindex;

/**
 * https://leetcode-cn.com/problems/peak-index-in-a-mountain-array/
 * <p>
 * 符合下列属性的数组 arr 称为 山脉数组 ：
 * arr.length >= 3
 * 存在 i（0 < i < arr.length - 1）使得：
 * arr[0] < arr[1] < ... arr[i-1] < arr[i]
 * arr[i] > arr[i+1] > ... > arr[arr.length - 1]
 * 给你由整数组成的山脉数组 arr ，返回任何满足 arr[0] < arr[1] < ... arr[i - 1] < arr[i] > arr[i + 1] > ... > arr[arr.length - 1] 的下标 i 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/peak-index-in-a-mountain-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class PeakIndex {

    public int peakIndexInMountainArray(int[] arr) {
        int left = 1;
        int right = arr.length - 1;

        while (left < right) {
            int mid = (left + right + 1) >> 1;
            if (arr[mid - 1] < arr[mid]) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

}
