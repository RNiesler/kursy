package tasks.g4g;

import java.util.Objects;

public class SumNumbersInTree {
    public static class Node {
        public Node(int value) {
            this.value = value;
        }

        int value;
        Node left;
        Node right;
    }

    public static int sumNumbersInTree(Node tree) {
        Objects.requireNonNull(tree);
        return traverse(tree, 0);
    }

    private static int traverse(Node tree, int parentNumber) {
        int value = 10 * parentNumber + tree.value;
        if (tree.left == null && tree.right == null) {
            return value;
        } else {
            int sum = 0;
            if (tree.left != null) {
                sum += traverse(tree.left, value);
            }
            if (tree.right != null) {
                sum += traverse(tree.right, value);
            }
            return sum;
        }
    }

}
