/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2021-2021. All rights reserved.
 */

package xyz.lucis.leetcode.treetraversal;

import java.util.LinkedList;
import java.util.List;

public class TreeTraversal {

    /**
     * bfs
     *
     * @param root root node of tree
     * @return Traversal List
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new LinkedList<>();
        }

        List<List<Integer>> res = new LinkedList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            LinkedList<Integer> nodes = new LinkedList<>();
            LinkedList<TreeNode> subNodes = new LinkedList<>();

            while (!queue.isEmpty()) {
                // travel this node
                TreeNode node = queue.removeFirst();
                nodes.add(node.val);
                if (node.left != null) {
                    subNodes.add(node.left);
                }
                if (node.right != null) {
                    subNodes.add(node.right);
                }
            }
            res.add(nodes);
            queue.addAll(subNodes);
        }

        return res;
    }

}

class TreeNode {
    int val;

    TreeNode left;

    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
