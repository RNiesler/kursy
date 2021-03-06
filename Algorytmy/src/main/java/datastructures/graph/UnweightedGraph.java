package datastructures.graph;

import java.util.Collection;
import java.util.function.Supplier;

public class UnweightedGraph<T> implements Graph<T> {
    private final Graph<T> delegate;

    private UnweightedGraph(Graph<T> delegate) {
        this.delegate = delegate;
    }

    /**
     * @param supplier Supplier of empty graph instance.
     */
    static <U> UnweightedGraph<U> of(Supplier<? extends Graph<U>> supplier) {
        return new UnweightedGraph<>(supplier.get());
    }

    @Override
    public void addEdge(T v1, T v2, int weight) {
        if (weight != 1) {
            throw new IllegalArgumentException("UnweightedGraph's edge weight must be 1.");
        }
        delegate.addEdge(v1, v2);
    }

    @Override
    public void addEdge(T v1, T v2) {
        this.addEdge(v1, v2, 1);
    }

    @Override
    public void insert(T value) {
        delegate.insert(value);
    }

    @Override
    public void remove(T value) {
        delegate.remove(value);
    }

    @Override
    public boolean contains(T value) {
        return delegate.contains(value);
    }

    @Override
    public Iterable<T> getAll() {
        return delegate.getAll();
    }

    @Override
    public int size() {
        return delegate.size();
    }

    @Override
    public Collection<? extends Node<T>> getNodes() {
        return delegate.getNodes();
    }
}
