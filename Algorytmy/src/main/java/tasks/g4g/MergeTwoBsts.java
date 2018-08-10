package tasks.g4g;

//Merge two BSTs with limited extra space

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

//Given two Binary Search Trees(BST), print the elements of both BSTs in sorted form.
// The expected time complexity is O(m+n) where m is the number of nodes in first tree and n is the number of nodes in second tree.
// Maximum allowed auxiliary space is O(height of the first tree + height of the second tree).
public class MergeTwoBsts {

    @RequiredArgsConstructor
    public static class Tree {
        final int value;
        Tree left;
        Tree right;

        public boolean equals(Object object) {
            if (object != null && object instanceof Tree) {
                Tree other = (Tree) object;
                return this.value == other.value;
            }
            return false;
        }
    }

    public static List<Integer> mergeBsts(Tree tree1, Tree tree2) {
        List<Integer> result = new LinkedList<>();
        Iterator<Integer> it1 = new TreeIterator(tree1);
        Iterator<Integer> it2 = new TreeIterator(tree2);

        Integer val1 = null;
        Integer val2 = null;
        while ((val1 != null || it1.hasNext()) && (val2 != null || it2.hasNext())) {
            if (val1 == null) {
                val1 = it1.next();
            }
            if (val2 == null) {
                val2 = it2.next();
            }
            if (val2 < val1) {
                result.add(val2);
                val2 = null;
            } else {
                result.add(val1);
                val1 = null;
            }
        }
        if (val1 == null) {
            result.add(val2);
        } else if (val2 == null) {
            result.add(val1);
        }
        while (it1.hasNext())
            result.add(it1.next());
        while (it2.hasNext())
            result.add(it2.next());
        return result;
    }
}

class TreeIterator implements Iterator<Integer> {
    @AllArgsConstructor
    private static class IteratorElem {
        int level;
        MergeTwoBsts.Tree tree;
    }

    private LinkedList<IteratorElem> stack = new LinkedList<>();
    private Integer previousLevel = -1;

    public TreeIterator(MergeTwoBsts.Tree tree) {
        stack.push(new IteratorElem(0, tree));
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public Integer next() {
        IteratorElem elem = stack.peek();
        if (elem.level < previousLevel) {
            stack.pop();
            if (elem.tree.right != null) {
                stack.push(new IteratorElem(elem.level + 1, elem.tree.right));
            }
            previousLevel = elem.level;
            return elem.tree.value;
        } else {
            MergeTwoBsts.Tree node = elem.tree;
            int level = elem.level;
            while (node.left != null) {
                node = node.left;
                stack.push(new IteratorElem(++level, node));
            }
            IteratorElem topElem = stack.pop();
            previousLevel = topElem.level;
            return topElem.tree.value;
        }
    }
}
