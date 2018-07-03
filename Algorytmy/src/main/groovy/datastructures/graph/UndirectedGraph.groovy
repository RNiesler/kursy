package datastructures.graph

import java.util.function.Supplier;

class UndirectedGraph<T> implements Graph<T> {
    @Delegate
    private Graph<T> delegate

    private UndirectedGraph(Graph<T> delegate) {
        this.delegate = delegate
    }

    /**
     * @param suppliern Supplier of empty graph instance.
     */
    static UndirectedGraph<T> of(Supplier<? extends Graph<T>> supplier) {
        return new UndirectedGraph<T>(supplier.get())
    }

    @Override
    void addEdge(T v1, T v2, int weight) {
        delegate.addEdge(v1, v2, weight)
        delegate.addEdge(v2, v1, weight)
    }

    @Override
    void addEdge(T v1, T v2) {
        this.addEdge(v1, v2, 1)
    }
}
