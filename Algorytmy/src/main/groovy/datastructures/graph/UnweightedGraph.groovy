package datastructures.graph

import java.util.function.Supplier

class UnweightedGraph<T> implements Graph<T> {
    @Delegate
    private Graph<T> delegate

    private UnweightedGraph(Graph<T> delegate) {
        this.delegate = delegate
    }

    /**
     * @param suppliern Supplier of empty graph instance.
     */
    static UnweightedGraph<T> of(Supplier<? extends Graph<T>> supplier) {
        return new UnweightedGraph<>(supplier.get())
    }

    @Override
    void addEdge(T v1, T v2, int weight) {
        if (weight != 1) {
            throw new IllegalArgumentException("UnweightedGraph's edge weight must be 1.")
        }
        delegate.addEdge(v1, v2)
    }

    @Override
    void addEdge(T v1, T v2) {
        this.addEdge(v1, v2, 1)
    }
}
