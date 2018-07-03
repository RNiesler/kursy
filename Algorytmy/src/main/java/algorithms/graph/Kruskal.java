package algorithms.graph;

import datastructures.graph.Graph;
import datastructures.graph.UndirectedGraph;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Calculates minimum spanning tree of the undirected graph with use of Kruskal's algorithm
 */
public class Kruskal<T> {
    private final UndirectedGraph<T> graph;

    public Kruskal(UndirectedGraph<T> graph) {
        this.graph = graph;
    }

    private static class UnionFind<U> {
        UnionFind(Graph.Node<U> parent) {
            this.parent = parent;
            this.rank = 1;
        }

        Graph.Node<U> parent;
        int rank;
    }

    public Collection<Graph.Edge<T>> getMinimumSpanningTree() {
        if (graph.size() == 0) {
            return Collections.emptyList();
        }
        PriorityQueue<Graph.Edge<T>> queue = new PriorityQueue<>();
        Map<Graph.Node<T>, UnionFind<T>> unionFindMap = graph.getNodes().stream()
                .collect(Collectors.toMap(Function.identity(), node -> new UnionFind<>(node)));
        List<Graph.Edge<T>> solution = new LinkedList<>();
        HashSet<Integer> edgeHashes = new HashSet<>();
        for (Graph.Node<T> node : graph.getNodes()) {
            for (Graph.Edge edge : node.getAdjacent()) {
                // calculate the hash to avoid adding the edge twice (once from the source, once from the target)
                Integer edgeHash = edge.getSource().hashCode() + edge.getTarget().hashCode();
                if (!edgeHashes.contains(edgeHash)) {
                    edgeHashes.add(edgeHash);
                    queue.add(edge);
                }
            }
        }
        while (!queue.isEmpty()) {
            Graph.Edge<T> edge = queue.poll();
            if (union(edge, unionFindMap)) {
                solution.add(edge);
            }
        }
        return solution;
    }

    /**
     * Checks if source and target nodes are in the same set (thus the edge would create a cycle)
     * and if not, it does the union operation.
     *
     * @return false if adding the edge would create a cycle.
     */
    private boolean union(Graph.Edge<T> edge, Map<Graph.Node<T>, UnionFind<T>> unionFindMap) {
        Graph.Node<T> sourceSetRoot = find(edge.getSource(), unionFindMap);
        Graph.Node<T> targetSetRoot = find(edge.getTarget(), unionFindMap);
        if (sourceSetRoot.equals(targetSetRoot)) {
            // the edge would create a cycle
            return false;
        } else {
            UnionFind<T> targetSet = unionFindMap.get(targetSetRoot);
            UnionFind<T> sourceSet = unionFindMap.get(sourceSetRoot);
            // union by rank
            if (sourceSet.rank < targetSet.rank) {
                sourceSet.parent = targetSetRoot;
            } else {
                targetSet.parent = sourceSetRoot;
                // increase rank when both branches are equal - consider it as height of the tree
                if (sourceSet.rank == targetSet.rank) {
                    sourceSet.rank++;
                }
            }
            return true;
        }
    }

    /**
     * UnionFind's find
     */
    private Graph.Node<T> find(Graph.Node<T> node, Map<Graph.Node<T>, UnionFind<T>> unionFindMap) {
        UnionFind<T> unionSet = unionFindMap.get(node);
        Graph.Node<T> unionSetRoot = node;
        if (unionSet.parent.equals(node)) {
            return node;
        } else {
            Graph.Node<T> root = find(unionSet.parent, unionFindMap);
            // path compression
            unionSet.parent = root;
            return root;
        }
    }
}
