package datastructures.trie;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

public class SimpleTrie<T, U> implements Trie<T, U> {
    interface Visitor<V> {
        void visit(Node<V> node);

        boolean isOnlyLeaves();
    }

    private class SimpleCountVisitor implements Visitor<U> {
        private int count = 0;

        @Override
        public void visit(Node<U> node) {
            count++;
        }

        @Override
        public boolean isOnlyLeaves() {
            return true;
        }

        int getCount() {
            return this.count;
        }
    }

    protected Node root;
    private final Splitter<T, U> splitter;

    @Getter
    @Setter
    static class Node<V> {
        private Node parent;
        private V key; // segment
        private Map<V, Node<V>> children;
        private boolean leaf;

        public Node(Node parent, V key, boolean leaf) {
            this.parent = parent;
            this.key = key;
            this.leaf = leaf;
            this.children = new HashMap<>();
        }
    }

    public SimpleTrie(Splitter<T, U> splitter) {
        this.root = new Node(null, null, false);
        this.splitter = splitter;
    }

    public SimpleTrie(Iterable<T> values, Splitter<T, U> splitter) {
        this(splitter);
        for (T t : values) {
            put(t);
        }
    }

    public boolean contains(T t) {
        return get(t).isPresent();
    }

    public Optional<T> get(T t) {
        Iterable<U> path = splitter.split(t);
        Iterator<U> iterator = path.iterator();
        Node nearestAncestor = getNearestAncestor(root, iterator);
        return getValueFromNode(nearestAncestor);
    }

    protected static boolean isNodeRoot(Node node) {
        return node.parent == null;
    }

    protected Optional<T> getValueFromNode(Node<U> node) {
        if (node.leaf) {
            return Optional.of(getPrefixFromNode(node));
        } else {
            return Optional.empty();
        }
    }

    protected T getPrefixFromNode(Node<U> node) {
        LinkedList<U> segmentList = getPrefixFromNode(node, new LinkedList<>());
        return splitter.join(segmentList);
    }

    /**
     * Fill in the list with segments
     */
    private LinkedList<U> getPrefixFromNode(Node<U> node, LinkedList<U> segmentList) {
        if (!isNodeRoot(node)) {
            getPrefixFromNode(node.parent, segmentList);
            segmentList.add(node.key);
        }
        return segmentList;
    }

    protected Node<U> getNearestAncestor(Node<U> ancestor, Iterator<U> iterator) {
        if (!iterator.hasNext()) {
            return ancestor;
        } else {
            U segment = iterator.next();
            if (ancestor.children.containsKey(segment)) {
                return getNearestAncestor(ancestor.children.get(segment), iterator);
            } else {
                return ancestor;
            }
        }
    }

    public boolean hasPrefix(T t) {
        Iterable<U> path = splitter.split(t);
        Iterator<U> iterator = path.iterator();
        Node<U> nearestAncestor = getNearestAncestor(root, iterator);
        T prefix = getPrefixFromNode(nearestAncestor);
        return t.equals(prefix);
    }

    public void put(T t) {
        Iterable<U> path = splitter.split(t);
        putToNode(root, path.iterator());
    }

    protected void putToNode(Node<U> parent, Iterator<U> iterator) {
        if (iterator.hasNext()) {
            U key = iterator.next();
            boolean hasNext = iterator.hasNext();
            Node<U> node = parent.children.computeIfAbsent(key, k -> new Node(parent, key, !hasNext));
            if (hasNext) {
                putToNode(node, iterator);
            }
        }
    }

    public int size() {
        SimpleCountVisitor visitor = new SimpleCountVisitor();
        visit(visitor);
        return visitor.getCount(); // could just count elements on put
    }


    protected void visit(Visitor<U> visitor) {
        visit(root, visitor);
    }

    protected void visit(Node<U> node, Visitor<U> visitor) {
        if (node.leaf || !visitor.isOnlyLeaves()) {
            visitor.visit(node);
        }
        node.children.forEach((childKey, childNode) -> visit(childNode, visitor));
    }
}
