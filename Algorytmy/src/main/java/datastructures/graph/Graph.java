package datastructures.graph;

import java.util.Collection;
import java.util.Iterator;

public interface Graph<T> {
    interface Node<T> {
        Iterable<? extends Node<T>> getAdjacent();

        T getValue();
    }


    void insert(T value);

    void addEdge(T v1, T v2);

    void remove(T value);

    boolean contains(T value);

    Iterable<T> getAll();

    int size();

    Collection<? extends Node<? extends T>> getNodes();

    default Iterator<? extends T> iterator(GraphTraverseStrategy<T> strategy) {
        return strategy.iterator(getNodes());
    }
}
