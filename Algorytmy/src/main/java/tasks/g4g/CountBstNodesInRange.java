package tasks.g4g;

public class CountBstNodesInRange {
    static class Tree {
        Tree(int value) {
            this.value = value;
        }

        int value;
        Tree left;
        Tree right;
    }

    static int countInRange(Tree tree, int rangeStart, int rangeEnd) {
        if (tree == null) {
            return 0;
        } else {
            if (tree.value < rangeStart) {
                return countInRange(tree.right, rangeStart, rangeEnd);
            } else if (tree.value > rangeEnd) {
                return countInRange(tree.left, rangeStart, rangeEnd);
            } else {
                int count = 1;
                count += countInRange(tree.left, rangeStart, rangeEnd);
                count += countInRange(tree.right, rangeStart, rangeEnd);
                return count;
            }
        }
    }

}
