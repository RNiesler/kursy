package tasks.hackerrank;

import java.util.LinkedList;
import java.util.Scanner;

public class Bfs {
    static class Graph {
        private int[][] adjacencyMatrix;

        public Graph(int n) {
            this.adjacencyMatrix = new int[n][n];
        }

        public void addEdge(int from, int to, int weight) {
            if (from < 0 || from >= adjacencyMatrix.length ||
                    to < 0 || to >= adjacencyMatrix.length) {
                throw new IllegalArgumentException("Index out of bounds");
            }
            this.adjacencyMatrix[from][to] = weight;
            this.adjacencyMatrix[to][from] = weight;
        }

        private static class Pair {
            Pair(int node, int distance) {
                this.node = node;
                this.distance = distance;
            }

            int node;
            int distance;
        }

        public int[] getDistancesFrom(int source) {
            LinkedList<Pair> queue = new LinkedList<>();
            boolean[] visited = new boolean[adjacencyMatrix.length];
            int[] result = new int[adjacencyMatrix.length];
            for (int i = 0; i < result.length; i++) {
                result[i] = -1;
            }
            queue.add(new Pair(source, 0));
            while (!queue.isEmpty()) {
                Pair pair = queue.removeFirst();
                if (!visited[pair.node]) {
                    result[pair.node] = pair.distance;
                    for (int i = 0; i < adjacencyMatrix[pair.node].length; i++) {
                        int weight = adjacencyMatrix[pair.node][i];
                        if (weight > 0 && !visited[i]) {
                            queue.addLast(new Pair(i, pair.distance + weight));
                        }
                    }
                    visited[pair.node] = true;
                }
            }
            return result;
        }

    }

    public static String fromInput(Scanner scanner) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Integer numberOfQueries = Integer.parseInt(scanner.nextLine());
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < numberOfQueries; i++) {
            String[] nm = scanner.nextLine().split(" ");
            int n = Integer.parseInt(nm[0]);
            int m = Integer.parseInt(nm[1]);
            Graph graph = new Graph(n);
            for (int j = 0; j < m; j++) {
                String[] edge = scanner.nextLine().split(" ");
                graph.addEdge(Integer.parseInt(edge[0]) - 1, Integer.parseInt(edge[1]) - 1, 6);
            }
            int source = Integer.parseInt(scanner.nextLine()) - 1;
            int[] distances = graph.getDistancesFrom(source);
            for (int j = 0; j < distances.length; j++) {
                if (j != source) {
//                    System.out.print(distances[i]);
                    stringBuilder.append(distances[j]);
                    if (j != distances.length - 1 &&
                            !(source == distances.length - 1 && j == distances.length - 2)) {
//                        System.out.print(" ");
                        stringBuilder.append(" ");
                    }
                }
            }
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }
}
