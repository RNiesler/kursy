package tasks.hackerrank;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class RoadsAndLibraries {
    static class Graph {
        private List[] adjacencyLists;
        private int n;
        private int roadCost;
        private int libraryCost;

        Graph(int cityCount, int roadCost, int libraryCost) {
            this.n = cityCount;
            this.roadCost = roadCost;
            this.libraryCost = libraryCost;
            this.adjacencyLists = new List[n];
            for (int i = 0; i < n; i++) {
                this.adjacencyLists[i] = new LinkedList();
            }
        }

        void addEdge(int city1, int city2) {
            adjacencyLists[city1].add(city2);
            adjacencyLists[city2].add(city1);
        }

        long minCost() {
            boolean[] visited = new boolean[n];
            long sum = 0;
            for (int i = 0; i < n; i++) {
                if (!visited[i]) {
                    sum += libraryCost + stCost(i, visited);
                }
            }
            return sum;
        }

        private long stCost(int node, boolean[] visited) {
            visited[node] = true;
            long total = 0;
            for (Object adjObj : adjacencyLists[node]) {
                int adj = (Integer) adjObj;
                if (!visited[adj]) {
                    total += roadCost + stCost(adj, visited);
                }
            }
            return total;
        }

        //BFS
//        private long stCost(int startFrom, boolean[] visited) {
//            LinkedList<Integer> nodeQueue = new LinkedList<>();
//            nodeQueue.addLast(startFrom);
//            long total = 0;
//            while (!nodeQueue.isEmpty()) {
//                int node = nodeQueue.removeFirst();
//                if (!visited[node]) {
//                    if (node != startFrom) {
//                        total += roadCost;
//                    }
//                    for (int adj = 0; adj < n; adj++) {
//                        if (!visited[adj] && adjacencyMatrix[node][adj]) {
//                            nodeQueue.addLast(adj);
//                        }
//                    }
//                    visited[node] = true;
//                }
//            }
//            return total;
//        }
    }

    // Complete the roadsAndLibraries function below.
    static long roadsAndLibraries(int n, int c_lib, int c_road, int[][] cities) {
        if (c_lib < c_road) {
            return (long) n * (long) c_lib;
        }
        Objects.requireNonNull(cities);
        Graph graph = new Graph(n, c_road, c_lib);
        for (int i = 0; i < cities.length; i++) {
            graph.addEdge(cities[i][0] - 1, cities[i][1] - 1);
        }

        return graph.minCost();
    }
}
