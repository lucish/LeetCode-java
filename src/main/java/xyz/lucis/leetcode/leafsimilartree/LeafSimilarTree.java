/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2021-2021. All rights reserved.
 */

package xyz.lucis.leetcode.leafsimilartree;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/leaf-similar-trees/
 * <p>
 * 872. 叶子相似的树
 * 请考虑一棵二叉树上所有的叶子，这些叶子的值按从左到右的顺序排列形成一个 叶值序列 。
 * <p>
 * <p>
 * <p>
 * 举个例子，如上图所示，给定一棵叶值序列为 (6, 7, 4, 9, 8) 的树。
 * <p>
 * 如果有两棵二叉树的叶值序列是相同，那么我们就认为它们是 叶相似 的。
 * <p>
 * 如果给定的两个根结点分别为 root1 和 root2 的树是叶相似的，则返回 true；否则返回 false 。
 */
public class LeafSimilarTree {

    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> leafList1 = new ArrayList<>();
        traverseTreeLead(root1, leafList1);
        List<Integer> leafList2 = new ArrayList<>();
        traverseTreeLead(root2, leafList2);

        if (leafList1.size() != leafList2.size()) {
            return false;
        }
        for (int i = 0; i < leafList2.size(); i++) {
            int leaf1 = leafList1.get(i);
            int leaf2 = leafList2.get(i);
            if (leaf1 != leaf2) {
                return false;
            }
        }
        return true;
    }

    public void traverseTreeLead(TreeNode treeNode, List<Integer> leafList) {
        // is leaf node, add and return
        if (treeNode.left == null && treeNode.right == null) {
            leafList.add(treeNode.val);
            return;
        }
        if (treeNode.left != null) {
            traverseTreeLead(treeNode.left, leafList);
        }
        if (treeNode.right != null) {
            traverseTreeLead(treeNode.right, leafList);
        }
    }

}
