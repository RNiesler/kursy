package datastructures.trie;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

public class CompressedTrie<T, U> implements Trie<T, U> {
    private final Splitter<T, U> splitter;
    private Node<U> root;

    @Getter
    @Setter
    static class Node<V> {
        private Node<V> parent;
        private List<V> path;
        private Map<V, Node<V>> children;
        private boolean leaf;

        public Node(Node<V> parent, List<V> path, boolean leaf) {
            this.parent = parent;
            this.path = path;
            this.leaf = leaf;
            this.children = new HashMap<>();
        }
    }

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

    public CompressedTrie(Splitter<T, U> splitter) {
        this.splitter = splitter;
        this.root = new Node<>(null, Collections.emptyList(), false);
    }

    public CompressedTrie(Iterable<T> values, Splitter<T, U> splitter) {
        this(splitter);
        for (T t : values) {
            put(t);
        }
    }

    @Override
    public boolean contains(T t) {
        return get(t).isPresent();
    }

    @Override
    public Optional<T> get(T t) {
        Iterator<U> iterator = splitter.split(t).iterator();
        if (iterator.hasNext()) {
            U key = iterator.next();
            Node<U> nearestAncestor = getNearestAncestor(key, iterator);
            Optional<T> valueOpt = getValueFromNode(nearestAncestor);
            if (valueOpt.isPresent()) {
                T value = valueOpt.get();
                return t.equals(value) ? Optional.of(value) : Optional.empty();
            } else {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }

    protected Node<U> getNearestAncestor(U key, Iterator<U> iterator) {
        if (root.children.containsKey(key)) {
            return getNearestAncestor(root.children.get(key), key, iterator);
        } else {
            return root;
        }
    }

    protected Node<U> getNearestAncestor(Node<U> ancestor, U key, Iterator<U> iterator) {
        if (!iterator.hasNext()) {
            return ancestor;
        } else {
            Iterator<U> ancestorPathIterator = ancestor.path.iterator();
            U ancestorSegment = ancestorPathIterator.next(); // the first path segment is the key
            U segment = key;
            while (ancestorPathIterator.hasNext() && iterator.hasNext() &&
                    ancestorSegment.equals(segment)) {
                ancestorSegment = ancestorPathIterator.next();
                segment = iterator.next();
            }

            if (!ancestorPathIterator.hasNext() && iterator.hasNext()) {
                // we only look for children if the ancestor path is finished while the searched is not
                segment = iterator.next();
                if (ancestor.children.containsKey(segment)) {
                    return getNearestAncestor(ancestor.children.get(segment), segment, iterator);
                } else {
                    return ancestor;
                }
            } else {
                return ancestor;
            }
        }
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
            segmentList.addAll(node.path);
        }
        return segmentList;
    }

    protected static boolean isNodeRoot(Node node) {
        return node.parent == null;
    }

    @Override
    public boolean hasPrefix(T t) {
        Iterable<U> splitted = splitter.split(t);
        Iterator<U> iterator = splitted.iterator();
        if (!iterator.hasNext()) {
            return true;
        }
        U key = iterator.next();
        Node<U> nearestAncestor = getNearestAncestor(key, iterator);
        T preprefix = getPrefixFromNode(nearestAncestor);
        iterator = splitted.iterator();
        Iterator<U> preIterator = splitter.split(preprefix).iterator();
        while (preIterator.hasNext() && iterator.hasNext() && preIterator.next().equals(iterator.next())) {
            // just loop through
        }
        Iterator<U> nodePathIterator = nearestAncestor.path.iterator();
        while (iterator.hasNext() && nodePathIterator.hasNext() && iterator.next().equals(nodePathIterator.next())) {
            // just loop through
        }
        return !iterator.hasNext(); // if iterated through the whole iterator, then the prefix is present in the trie
    }

    @Override
    public void put(T t) {
        Iterator<U> iterator = splitter.split(t).iterator();
        if (iterator.hasNext()) {
            U key = iterator.next();
            if (root.children.containsKey(key)) {
                putToNode(root.children.get(key), key, iterator);
            } else {
                addChildNode(root, key, iterator);
            }
        }
    }

    protected void putToNode(Node<U> ancestor, U key, Iterator<U> iterator) {
        Iterator<U> ancestorPathIterator = ancestor.path.iterator();
        U ancestorSegment = ancestorPathIterator.next(); // the first path segment is the key
        U segment = key;
        while (ancestorPathIterator.hasNext() && iterator.hasNext() &&
                ancestorSegment.equals(segment)) {
            ancestorSegment = ancestorPathIterator.next();
            segment = iterator.next();
        }

        if (ancestorPathIterator.hasNext()) {
            // either the new is a prefix of ancestor...
            if (!iterator.hasNext()) {
                // split the ancestor
                LinkedList<U> remainingPath = new LinkedList<>();
                ancestorPathIterator.forEachRemaining(remainingPath::add);
                splitNode(ancestor, remainingPath);
                // mark the node as leaf, as the new is in the ancestor now
                ancestor.leaf = true;
            } else { // ... or the two paths diverged in the middle of ancestor node path
                // if we got here then ancestorSegment != segment so we need to split before this point
                LinkedList<U> divergedPath = new LinkedList<>();
                divergedPath.add(ancestorSegment);
                ancestorPathIterator.forEachRemaining(divergedPath::add);
                splitNode(ancestor, divergedPath);
                addChildNode(ancestor, segment, iterator);
            }
        } else if (iterator.hasNext()) {
            // the ancestor is a prefix of the new
            segment = iterator.next();
            if (ancestor.children.containsKey(segment)) {
                putToNode(ancestor.children.get(segment), segment, iterator);
            } else {
                // add new leaf child node
                addChildNode(ancestor, segment, iterator);

            }
        } else {
            // the ancestor node and the new are the same
            ancestor.leaf = true;
        }
    }

    protected Node<U> addChildNode(Node<U> parent, U key, Iterator<U> iterator) {
        LinkedList<U> path = new LinkedList<>();
        path.add(key); // key go first
        iterator.forEachRemaining(path::add);
        Node<U> child = new Node<>(parent, path, true);
        parent.children.put(key, child);
        return child;
    }

    protected Node<U> splitNode(Node<U> node, List<U> path) {
        Node<U> childNode = new Node(node, path, node.leaf);
        childNode.children = node.children;
        node.children = new HashMap<>();
        node.children.put(path.get(0), childNode);
        return node;
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
