package tasks.g4g;

import java.util.LinkedList;
import java.util.Objects;

public class ConnectNodesSameLevel {
    static class Node<T> {
        public Node(T data) {
            this.data = data;
        }

        T data;
        Node<T> left;
        Node<T> right;
        Node<T> nextSibling;
    }


    static <T> void setSiblings(Node<T> root) {
        class NodeLevel {
            NodeLevel(Node<T> node, int level) {
                this.node = node;
                this.level = level;
            }

            Node<T> node;
            int level;
        }

        Objects.requireNonNull(root);
        NodeLevel previous = null;
        LinkedList<NodeLevel> bfsQueue = new LinkedList<>();
        bfsQueue.addLast(new NodeLevel(root, 0));
        while (!bfsQueue.isEmpty()) {
            NodeLevel nodeLevel = bfsQueue.removeFirst();
            if (previous != null && previous.level == nodeLevel.level) {
                previous.node.nextSibling = nodeLevel.node;
            }
            previous = nodeLevel;
            if (nodeLevel.node.left != null) {
                bfsQueue.addLast(new NodeLevel(nodeLevel.node.left, nodeLevel.level + 1));
            }
            if (nodeLevel.node.right != null) {
                bfsQueue.addLast(new NodeLevel(nodeLevel.node.right, nodeLevel.level + 1));
            }
        }
    }
}
