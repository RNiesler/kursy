package tasks.hackerrank;

public class CheckBST {
    public static class Node {
        int data;
        Node left;
        Node right;
    }

    private int currentHighest;

    boolean checkBST(Node root) {
        currentHighest = Integer.MIN_VALUE;
        return inorder(root);
    }

    private boolean inorder(Node root) {
        if (root == null) {
            return true;
        }
        if (!inorder(root.left)) {
            return false;
        }
        if (root.data <= currentHighest) {
            return false;
        } else {
            currentHighest = root.data;
        }
        return inorder(root.right);
    }
}
