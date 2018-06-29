package datastructures.graph;

import java.util.Collection;
import java.util.Iterator;

public interface GraphTraverseStrategy<T> {
    Iterator<T> iterator(Collection<? extends Graph.Node<T>> nodes);
}
