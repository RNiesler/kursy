package algorithms.graph;

import datastructures.graph.Graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
            throw new CycleDetectedException();
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

    public static class CycleDetectedException extends RuntimeException {
    }

    public static <T> List<T> sort2(Graph<T> graph) {
        Objects.requireNonNull(graph);
        LinkedList<Graph.Node<T>> stack = new LinkedList<>();
        LinkedList<T> result = new LinkedList<>();
        HashMap<Graph.Node<T>, State> states = new HashMap<>();
        for (Graph.Node<T> node : graph.getNodes()) {
            if (states.getOrDefault(node, State.UNMARKED) == State.UNMARKED) {
                dfsWithStack(node, stack, states);
            }
        }
        while (!stack.isEmpty()) {
            result.add(stack.pop().getValue());
        }
        return result;
    }

    private static <T> void dfsWithStack(Graph.Node<T> node,
                                         LinkedList<Graph.Node<T>> stack,
                                         Map<Graph.Node<T>, State> states) {
        State state = states.getOrDefault(node, State.UNMARKED);
        if (state == State.VISITED) {
            throw new CycleDetectedException();
        } else if (state == State.UNMARKED) {
            states.put(node, State.VISITED);
            for (Graph.Edge<T> adjacentEdge : node.getAdjacent()) {
                dfsWithStack(adjacentEdge.getTarget(), stack, states);
            }
            states.put(node, State.FINISHED);
            stack.push(node);
        }
    }
}
