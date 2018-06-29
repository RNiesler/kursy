package datastructures.graph;

import java.util.Collection;
import java.util.Iterator;

public interface GraphTraverseStrategy<T> {
    Iterator<? extends T> iterator(Collection<? extends Graph.Node<? extends T>> nodes);
}
