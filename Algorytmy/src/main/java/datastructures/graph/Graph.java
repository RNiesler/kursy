package datastructures.graph;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public interface Graph<T> {
    interface Node<T> {
        List<Edge<T>> getAdjacent();

        T getValue();

        boolean hasEdgeTo(Node<T> target);
    }

    final class Edge<T> implements Comparable<Edge<T>> {
        private final Node<T> source;
        private final Node<T> target;
        private final int weight;

        public Edge(Node<T> source, Node<T> target, int weight) {
            this.source = source;
            this.target = target;
            this.weight = weight;
        }

        public Node<T> getSource() {
            return source;
        }

        public Node<T> getTarget() {
            return target;
        }

        public int getWeight() {
            return weight;
        }

        @Override
        public int compareTo(Edge<T> o) {
            return weight - o.getWeight();
        }
    }

    void insert(T value);

    void addEdge(T v1, T v2, int weight);

    default void addEdge(T v1, T v2) {
        addEdge(v1, v2, 1);
    }

    void remove(T value);

    boolean contains(T value);

    Iterable<T> getAll();

    int size();

    Collection<? extends Node<T>> getNodes();

    default Iterator<? extends T> iterator(GraphTraverseStrategy<T> strategy) {
        return strategy.iterator(getNodes());
    }
}
