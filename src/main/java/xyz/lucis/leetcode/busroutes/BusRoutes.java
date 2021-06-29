/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2021-2021. All rights reserved.
 */

package xyz.lucis.leetcode.busroutes;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class BusRoutes {

    public static void main(String[] args) {
        BusRoutes instance = new BusRoutes();
        int[] route1 = new int[] {2};
        int[] route2 = new int[] {2, 8};
        int[][] routes = new int[][] {route1, route2};
        System.out.println(instance.numBusesToDestination(routes, 8, 2));
    }

    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) {
            return 0;
        }

        Graph<BusRoute> graph = buildGraph(routes);

        boolean targetExist = false;
        for (GraphNode<BusRoute> node : graph.getNodes().values()) {
            if (node.getValue().getStations().contains(target)) {
                targetExist = true;
                break;
            }
        }
        if (!targetExist) {
            return -1;
        }
        List<GraphNode<BusRoute>> startNodes = new LinkedList<>();
        for (GraphNode<BusRoute> node : graph.getNodes().values()) {
            if (node.getValue().getStations().contains(source)) {
                startNodes.add(node);
            }
        }
        int minDistance = -1;
        for (GraphNode<BusRoute> startNode : startNodes) {
            int distance = distance(graph, startNode, target);
            if (distance == -1) {
                continue;
            }
            if (minDistance == -1 || minDistance > distance) {
                minDistance = distance;
            }
        }
        return minDistance;
    }

    private int distance(Graph<BusRoute> graph, GraphNode<BusRoute> from, int target) {
        if (from.getValue().getStations().contains(target)) {
            return 1;
        }
        HashSet<Integer> visitedNode = new HashSet<>();
        LinkedList<GraphNode<BusRoute>> queue = new LinkedList<>();
        queue.add(from);
        visitedNode.add(from.getValue().getKey());
        int step = 1;
        // bfs
        while (!queue.isEmpty()) {
            step++;
            List<GraphNode<BusRoute>> tempQueue = new LinkedList<>();
            GraphNode<BusRoute> node = queue.removeFirst();
            for (GraphNode<BusRoute> connectNode : node.getConnectList()) {
                if (visitedNode.contains(connectNode.getValue().getKey())) {
                    // node has been visited
                    continue;
                }
                if (connectNode.getValue().getStations().contains(target)) {
                    return step;
                }
                tempQueue.add(connectNode);
                visitedNode.add(connectNode.getValue().getKey());
            }
            queue.addAll(tempQueue);
        }
        return -1;
    }

    private Graph<BusRoute> buildGraph(int[][] routes) {
        Graph<BusRoute> graph = new Graph<>();
        for (int i = 0; i < routes.length; i++) {
            int busRouteNum = i;
            BusRoute busRoute = new BusRoute();
            busRoute.setKey(busRouteNum);
            HashSet<Integer> stations = new HashSet<>(routes[busRouteNum].length);
            busRoute.setStations(stations);
            for (int station : routes[busRouteNum]) {
                stations.add(station);
            }
            // build node
            GraphNode<BusRoute> node = new GraphNode<>();
            node.setValue(busRoute);
            // add node to graph
            graph.getNodes().put(node.getValue().getKey(), node);
        }

        for (GraphNode<BusRoute> node1 : graph.getNodes().values()) {
            for (GraphNode<BusRoute> node2 : graph.getNodes().values()) {
                if (node2.getValue().getKey() == node1.getValue().getKey()) {
                    continue;
                }
                // 如果两个节点有公共的车站 则说明联通
                HashSet<Integer> stations1 = node1.getValue().getStations();
                HashSet<Integer> stations2 = node2.getValue().getStations();
                for (Integer station : stations1) {
                    if (stations2.contains(station)) {
                        node1.getConnectList().add(node2);
                    }
                }
            }
        }

        for (GraphNode<BusRoute> node : graph.getNodes().values()) {
            System.out.print(node.getValue().getKey() + "  ");
            for (GraphNode<BusRoute> connectNode : node.getConnectList()) {
                System.out.print(connectNode.getValue().getKey() + " ");
            }
            System.out.println();
        }
        return graph;
    }

}

class BusRoute {
    private int key;

    private HashSet<Integer> stations;

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public HashSet<Integer> getStations() {
        return stations;
    }

    public void setStations(HashSet<Integer> stations) {
        this.stations = stations;
    }
}

class Graph<T> {
    private HashMap<Integer, GraphNode<T>> nodes;

    Graph() {
        this.nodes = new HashMap<>();
    }

    Graph(int initialNodeSize) {
        this.nodes = new HashMap<>(initialNodeSize);
    }

    public GraphNode<T> getNodeByValue(Integer value) {
        return nodes.get(value);
    }

    public HashMap<Integer, GraphNode<T>> getNodes() {
        return nodes;
    }

    public void setNodes(HashMap<Integer, GraphNode<T>> nodes) {
        this.nodes = nodes;
    }
}

class GraphNode<T> {

    private T value;

    private HashSet<GraphNode<T>> connectList;

    GraphNode() {
        this.connectList = new HashSet<>();
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public HashSet<GraphNode<T>> getConnectList() {
        return connectList;
    }

    public void setConnectList(HashSet<GraphNode<T>> connectList) {
        this.connectList = connectList;
    }
}
