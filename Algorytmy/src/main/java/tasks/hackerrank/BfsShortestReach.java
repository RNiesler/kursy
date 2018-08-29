package tasks.hackerrank;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class BfsShortestReach {
    static class Graph {
        List<Node> nodes;

        static class Node {
            Node(int value) {
                this.value = value;
            }

            int value;
            int distance = -1;
            Set<Node> adjacent = new HashSet<>();
        }

        public Graph(int size) {
            nodes = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                nodes.add(new Node(i));
            }
        }

        public void addEdge(int a, int b) {
            Node nodeA = nodes.get(a);
            Node nodeB = nodes.get(b);
            nodeA.adjacent.add(nodeB);
            nodeB.adjacent.add(nodeA);
        }

        public void bfs(int s) {
            LinkedList<Node> queue = new LinkedList<>();
            Node sNode = nodes.get(s);
            sNode.distance = 0;
            queue.add(sNode);
            while (!queue.isEmpty()) {
                Node node = queue.removeFirst();
                for (Node adj : node.adjacent) {
                    if (adj.distance == -1) {
                        adj.distance = node.distance + 6;
                        queue.add(adj);
                    }
                }
            }
        }
    }

    // Complete the bfs function below.
    static int[] bfs(int n, int m, int[][] edges, int s) {
        Graph graph = new Graph(n);
        for (int i = 0; i < m; i++) {
            graph.addEdge(edges[i][0] - 1, edges[i][1] - 1);
        }
        graph.bfs(s - 1);
        return graph.nodes.stream()
                .filter(node -> node.value != s - 1)
                .mapToInt(node -> node.distance)
                .toArray();
    }
}
