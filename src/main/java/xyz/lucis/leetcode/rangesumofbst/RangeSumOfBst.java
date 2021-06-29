/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2021-2021. All rights reserved.
 */

package xyz.lucis.leetcode.rangesumofbst;

/**
 * https://leetcode-cn.com/problems/range-sum-of-bst/
 */
public class RangeSumOfBst {

    /**
     * 返回二叉搜索树指定node下val在[low,high]之间所有val的和
     *
     * @param node
     * @param low
     * @param high
     * @return
     */
    public int rangeSumBST(TreeNode node, int low, int high) {
        if (node == null) {
            return 0;
        }
        // 如果val在l,h之间, 则需要加上此节点val,并且左右子节点val都可能符合
        if (node.val >= low && node.val <= high) {
            return node.val + rangeSumBST(node.left, low, high) + rangeSumBST(node.right, low, high);
        } else if (node.val < low) {
            // 当前节点val<low 则本节点不符合 且左子节点不符合 只统计右子节点
            return rangeSumBST(node.right, low, high);
        } else {
            // 同上
            return rangeSumBST(node.left, low, high);
        }
    }

}
