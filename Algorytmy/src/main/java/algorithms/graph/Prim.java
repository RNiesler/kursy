package algorithms.graph;

import datastructures.graph.Graph;
import datastructures.graph.UndirectedGraph;

import java.util.*;

/**
 * Calculates minimum spanning tree of an undirected graph with use of Prim's algorithm
 *
 * @param <T> Node type held by the graph
 */
public class Prim<T> {
    private UndirectedGraph<T> graph;

    public Prim(UndirectedGraph<T> graph) {
        this.graph = graph;
    }

    public Collection<Graph.Edge<T>> getMinimumSpanningTree() {
        if (graph.size() == 0) {
            return Collections.emptyList();
        }
        PriorityQueue<Graph.Edge<T>> queue = new PriorityQueue<>();
        HashSet<Graph.Node<T>> visitedNodes = new HashSet<>();
        List<Graph.Edge<T>> solution = new LinkedList<>();
        Collection<? extends Graph.Node<T>> nodes = graph.getNodes();
        Graph.Node<T> currentNode = nodes.iterator().next(); // start with any node
        currentNode.getAdjacent().stream().forEach(adjacentEdge -> queue.add(adjacentEdge));
        visitedNodes.add(currentNode);
        while (!queue.isEmpty()) {
            Graph.Edge<T> minEdge = queue.poll();
            if (!visitedNodes.contains(minEdge.getTarget())) {
                solution.add(minEdge);
                visitedNodes.add(minEdge.getTarget());
                minEdge.getTarget().getAdjacent().stream()
                        .filter(adjacentEdge -> !visitedNodes.contains(adjacentEdge.getTarget()))
                        .forEach(adjacentEdge -> queue.add(adjacentEdge));
            }
        }
        return solution;
    }
}
