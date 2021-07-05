/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2021-2021. All rights reserved.
 */

package xyz.lucis.leetcode.serializebinarytree;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 剑指 Offer 37. 序列化二叉树\
 * 请实现两个函数，分别用来序列化和反序列化二叉树。
 * <p>
 * 你需要设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 * <p>
 * 提示：输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅LeetCode序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/xu-lie-hua-er-cha-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * // Your Codec object will be instantiated and called as such:
 * // Codec codec = new Codec();
 * // codec.deserialize(codec.serialize(root));
 */
public class SerializeBinaryTree {

    public static void main(String[] args) {
        SerializeBinaryTree instance = new SerializeBinaryTree();
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        node1.left = node2;
        node1.right = node3;
        node3.left = node4;
        node3.right = node5;
        // TreeNode node1 = null;
        String tree = instance.serialize(node1);
        System.out.println(tree);
        TreeNode root = instance.deserialize(tree);
    }

    private static int INF = -2000;

    TreeNode emptyNode = new TreeNode(INF);

    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Deque<TreeNode> d = new ArrayDeque<>();
        d.addLast(root);
        while (!d.isEmpty()) {
            TreeNode poll = d.pollFirst();
            sb.append(poll.val).append("_");
            if (!poll.equals(emptyNode)) {
                d.addLast(poll.left != null ? poll.left : emptyNode);
                d.addLast(poll.right != null ? poll.right : emptyNode);
            }
        }
        return sb.toString();
    }

    public TreeNode deserialize(String data) {
        if (data.equals("")) {
            return null;
        }

        String[] ss = data.split("_");
        int n = ss.length;
        TreeNode root = new TreeNode(Integer.parseInt(ss[0]));
        Deque<TreeNode> d = new ArrayDeque<>();
        d.addLast(root);
        for (int i = 1; i < n - 1; i += 2) {
            TreeNode poll = d.pollFirst();
            int a = Integer.parseInt(ss[i]), b = Integer.parseInt(ss[i + 1]);
            if (a != INF) {
                poll.left = new TreeNode(a);
                d.addLast(poll.left);
            }
            if (b != INF) {
                poll.right = new TreeNode(b);
                d.addLast(poll.right);
            }
        }
        return root;
    }

}

class TreeNode {
    int val;

    TreeNode left;

    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}
