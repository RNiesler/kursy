package tasks.hackerrank;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class KruskalReallySpecialSubtree {
    static class Graph {
        private static class Node {
            int parent = -1;
        }

        private static class Edge implements Comparable<Edge> {
            public Edge(int a, int b, int weight) {
                this.a = a;
                this.b = b;
                this.weight = weight;
            }

            int a;
            int b;
            int weight;

            int otherEnd(int i) {
                return i == a ? b : a;
            }

            public int compareTo(Edge other) {
                if (weight == other.weight) {
                    return a + b - other.a - other.b;
                } else {
                    return weight - other.weight;
                }
            }
        }

        List<Node> nodes;
        List<Edge> edges;

        public Graph(int size, int edgeCount) {
            nodes = new ArrayList<>(size);
            edges = new ArrayList<>(edgeCount);
            for (int i = 0; i < size; i++) {
                nodes.add(new Node());
            }
        }

        public void addEdge(int a, int b, int weight) {
            a--;
            b--;
            edges.add(new Edge(a, b, weight));
        }

        private boolean union(int a, int b) {
            int rootA = find(a);
            int rootB = find(b);
            if (rootA == rootB) {
                return false;
            } else {
                Node nodeRootB = nodes.get(rootB);
                nodeRootB.parent = rootA;
                return true;
            }
        }

        private int find(int a) {
            Node nodeA = nodes.get(a);
            if (nodeA.parent == -1) {
                return a;
            } else {
                int root = find(nodeA.parent);
                nodeA.parent = root;
                return root;
            }
        }

        public int mstWeight() {
            PriorityQueue<Edge> queue = new PriorityQueue<>();
            queue.addAll(edges);
            int edgesLeft = nodes.size() - 1;
            int weight = 0;
            while (edgesLeft > 0 && !queue.isEmpty()) {
                Edge edge = queue.poll();
                if (union(edge.a, edge.b)) {
                    weight += edge.weight;
                    edgesLeft--;
                }
            }
            return weight;
        }
    }

    static int mst(int n, int e, int[] from, int[] to, int[] weights) {
        Graph graph = new Graph(n, e);
        for (int i = 0; i < from.length; i++) {
            graph.addEdge(from[i], to[i], weights[i]);
        }
        return graph.mstWeight();
    }
}
