/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2021-2021. All rights reserved.
 */

package xyz.lucis.leetcode.msgtransfer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * 小朋友 A 在和 ta 的小伙伴们玩传信息游戏，游戏规则如下：
 * <p>
 * 有 n 名玩家，所有玩家编号分别为 0 ～ n-1，其中小朋友 A 的编号为 0
 * 每个玩家都有固定的若干个可传信息的其他玩家（也可能没有）。传信息的关系是单向的（比如 A 可以向 B 传信息，但 B 不能向 A 传信息）。
 * 每轮信息必须需要传递给另一个人，且信息可重复经过同一个人
 * 给定总玩家数 n，以及按 [玩家编号,对应可传递玩家编号] 关系组成的二维数组 relation。返回信息从小 A (编号 0 ) 经过 k 轮传递到编号为 n-1 的小伙伴处的方案数；若不能到达，返回 0。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/chuan-di-xin-xi
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MsgTransfer {

    public int numWays(int n, int[][] relation, int k) {
        // 建立邻接矩阵 以表示图的连通性
        List<Edge> edges = new ArrayList<>(relation.length);
        for (int[] row : relation) {
            int start = row[0];
            int end = row[1];
            Edge edge = new Edge();
            edge.start = start;
            edge.end = end;
            edges.add(edge);
        }
        Map<Integer, List<Edge>> edgeMap = edges.stream().collect(Collectors.groupingBy((Edge edge) -> edge.start));
        AtomicInteger numWays = new AtomicInteger(0);
        dfs(0, n - 1, edgeMap, 0, k, numWays);
        return numWays.get();
    }

    private void dfs(int node, int target, Map<Integer, List<Edge>> edgeMap, int step, int k, AtomicInteger numWays) {
        if (step == k) {
            if (node == target) {
                numWays.incrementAndGet();
            }
            return;
        }
        if (!edgeMap.containsKey(node)) {
            return;
        }
        List<Edge> edges = edgeMap.get(node);
        for (Edge edge : edges) {
            dfs(edge.end, target, edgeMap, step + 1, k, numWays);
        }
    }

    static class Edge {
        int start;

        int end;
    }

}
