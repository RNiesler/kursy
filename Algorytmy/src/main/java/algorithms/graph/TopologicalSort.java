package algorithms.graph;

import datastructures.graph.Graph;

import java.util.*;

/**
 * Topological sorting of directed graphs
 */
public class TopologicalSort {
    private enum State {
        UNMARKED, VISITED, FINISHED
    }

    private TopologicalSort() {
    }

    public static <T> List<T> sort(Graph<T> graph) {
        Object[] nodes = graph.getNodes().toArray();
        State[] states = new State[nodes.length];
        for (int i = 0; i < nodes.length; i++) {
            states[i] = State.UNMARKED;
        }
        boolean[][] reverseAdjacencyMatrix = reverseEdges(nodes);
        LinkedList<T> result = new LinkedList<>();
        for (int i = 0; i < states.length; i++) {
            if (states[i] == State.UNMARKED) {
                visit(i, nodes, states, reverseAdjacencyMatrix, result);
            }
        }
        return result;
    }

    private static <T> void visit(int i, Object[] nodes, State[] states, boolean[][] reverseAdjacencyMatrix, LinkedList<T> result) {
        // finished state means that the node is a predecessor already in correct place in the result list
        if (states[i] == State.FINISHED) {
            return;
        } else if (states[i] == State.VISITED) {
            // if the node is in the visited state, it means that we're visiting it for the second time,
            // so it's a predecessor of itself - meaning there's a cycle
            throw new IllegalStateException("The graph contains a cycle. Cannot sort it topologically.");
        } else {
            states[i] = State.VISITED;
            for (int j = 0; j < nodes.length; j++) {
                if (reverseAdjacencyMatrix[i][j]) { // this is a reverse edge so visit predecessor in topological order
                    visit(j, nodes, states, reverseAdjacencyMatrix, result);
                }
            }
            // reaching this means that all the predecessors are already in the result list
            states[i] = State.FINISHED;
            result.add(((Graph.Node<T>) nodes[i]).getValue());
        }


    }

    private static <T> boolean[][] reverseEdges(Object[] nodes) {
        boolean[][] adjacencyMatrix = new boolean[nodes.length][nodes.length];
        HashMap<Graph.Node<T>, Integer> nodeIndexMap = new HashMap<>();
        for (int i = 0; i < nodes.length; i++) {
            nodeIndexMap.put((Graph.Node<T>) nodes[i], i);
        }
        for (int i = 0; i < nodes.length; i++) {
            for (Graph.Edge<T> edge : ((Graph.Node<T>) nodes[i]).getAdjacent()) {
                int j = nodeIndexMap.get(edge.getTarget());
                adjacencyMatrix[j][i] = true;
            }
        }
        return adjacencyMatrix;

    }
}
