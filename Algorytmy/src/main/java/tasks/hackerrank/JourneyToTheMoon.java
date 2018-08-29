package tasks.hackerrank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class JourneyToTheMoon {
    static class Graph {
        private static class Node {
            Node(int value) {
                this.value = value;
            }

            int value;
            boolean marked;
            List<Node> adjacent = new LinkedList<>();
        }

        private int n;
        private ArrayList<Node> nodes;

        public Graph(int n) {
            this.n = n;
            nodes = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                nodes.add(new Node(i));
            }
        }

        public void addEdge(int n1, int n2) {
            Node node1 = nodes.get(n1);
            Node node2 = nodes.get(n2);
            node1.adjacent.add(node2);
            node2.adjacent.add(node1);
        }

        public int dfsCount(int nodeI) {
            Node node = nodes.get(nodeI);
            if (node.marked) {
                return 0;
            } else {
                int count = 1;
                node.marked = true;
                for (Node adj : node.adjacent) {
                    count += dfsCount(adj.value);
                }
                return count;
            }
        }
    }

    // Complete the journeyToMoon function below.
    static int journeyToMoonGraph(int n, int[][] astronaut) {
        Graph graph = new Graph(n);
        for (int i = 0; i < astronaut.length; i++) {
            graph.addEdge(astronaut[i][0], astronaut[i][1]);
        }
        List<Integer> countryCounts = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            int count = graph.dfsCount(i);
            if (count > 0) {
                countryCounts.add(count);
            }
        }
        int result = 0;
        for (int i = 0; i < countryCounts.size() - 1; i++) {
            for (int j = i + 1; j < countryCounts.size(); j++) {
                result += countryCounts.get(i) * countryCounts.get(j);
            }
        }
        return result;

    }

    static class UnionFind {
        int[] parents;
        private int[] counts;

        public UnionFind(int n) {
            parents = new int[n];
            counts = new int[n];
            Arrays.fill(parents, -1);
            Arrays.fill(counts, 1);
        }

        public int find(int x) {
            if(parents[x] == -1) {
                return x;
            } else {
                parents[x] = find(parents[x]);
                return parents[x];
            }
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                if (counts[rootX] > counts[rootY]) {
                    parents[rootY] = rootX;
                    counts[rootX] += counts[rootY];
                } else {
                    parents[rootX] = rootY;
                    counts[rootY] += counts[rootX];
                }
            }
        }

        public int count(final int x) {
            return counts[x];
        }
    }

    static int journeyToMoon(int n, int[][] astronaut) {
        UnionFind unionFind = new UnionFind(n);
        for (int i = 0; i < astronaut.length; i++) {
            unionFind.union(astronaut[i][0], astronaut[i][1]);
        }

        List<Integer> countryCounts = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (unionFind.parents[i] == -1) {
                countryCounts.add(unionFind.count(i));
            }
        }
        int result = 0;
        for (int i = 0; i < countryCounts.size() - 1; i++) {
            for (int j = i + 1; j < countryCounts.size(); j++) {
                result += countryCounts.get(i) * countryCounts.get(j);
            }
        }
        return result;
    }
}
