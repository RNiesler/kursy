package datastructures.graph;

import java.util.Collection;
import java.util.Iterator;
import java.util.stream.Collectors;

/**
 * Traverse Graph Strategy that would traverse the graph nodes in no particular order.
 * The order is not random though.
 *
 * @param <T> Element type in the graph
 */
public class SimpleGraphTraverseStrategy<T> implements GraphTraverseStrategy<T> {
    @Override
    public Iterator<? extends T> iterator(Collection<? extends Graph.Node<? extends T>> nodes) {
        return nodes.stream()
                .map(Graph.Node::getValue)
                .collect(Collectors.toList())
                .iterator();
    }
}
