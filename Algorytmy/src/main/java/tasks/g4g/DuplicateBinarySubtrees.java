package tasks.g4g;

import java.util.HashSet;

public class DuplicateBinarySubtrees<T> {
    public static class Node<U> {
        public Node(U value) {
            this.value = value;
        }

        U value;
        Node<U> left;
        Node<U> right;
    }

    private Node<T> root;

    public DuplicateBinarySubtrees(Node<T> root) {
        this.root = root;
    }

    private static class NodePair<V> {
        Node<V> parent;
        Node<V> child;
        boolean leftChild;

        NodePair(Node<V> parent, Node<V> child, boolean leftChild) {
            this.parent = parent;
            this.child = child;
            this.leftChild = leftChild;
        }

        public boolean equals(Object obj) {
            if (obj instanceof NodePair) {
                NodePair<V> other = (NodePair<V>) obj;
                if (!this.parent.value.equals(other.parent.value)) {
                    return false;
                } else if (this.leftChild != other.leftChild) {
                    return false;
                } else {
                    return this.child.value.equals(other.child.value);
                }
            } else {
                return false;
            }
        }

        public int hashCode() {
            int hash = parent.value.hashCode();
            hash = hash * 31 + child.value.hashCode();
            hash = hash * 31 + (leftChild ? 1 : 0);
            return hash;
        }
    }

    public boolean containsDuplicateSubtree() {
        HashSet<NodePair<T>> subtrees = new HashSet<>();
        if (root == null) {
            return false;
        } else {
            return duplicateSubtreesRecursive(root, subtrees);
        }
    }

    private boolean duplicateSubtreesRecursive(Node<T> parent, HashSet<NodePair<T>> subtrees) {
        if (parent.left != null) {
            NodePair<T> nodePair = new NodePair<>(parent, parent.left, true);
            if (subtrees.contains(nodePair)) {
                return true;
            } else {
                subtrees.add(nodePair);
                if (duplicateSubtreesRecursive(parent.left, subtrees)) {
                    return true;
                }
            }
        }
        if (parent.right != null) {
            NodePair<T> nodePair = new NodePair<>(parent, parent.right, false);
            if (subtrees.contains(nodePair)) {
                return true;
            } else {
                subtrees.add(nodePair);
                if (duplicateSubtreesRecursive(parent.right, subtrees)) {
                    return true;
                }
            }
        }
        return false;
    }
}