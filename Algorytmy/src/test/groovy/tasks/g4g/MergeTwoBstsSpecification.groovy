package tasks.g4g

import spock.lang.Specification

class MergeTwoBstsSpecification extends Specification {
    //First BST
    //       3
    //    /     \
    //   1       5
    //Second BST
    //    4
    //  /   \
    //2       6
    void 'g4g'() {
        setup:
        final tree1 = new MergeTwoBsts.Tree(3)
        tree1.left = new MergeTwoBsts.Tree(1)
        tree1.right = new MergeTwoBsts.Tree(5)
        final tree2 = new MergeTwoBsts.Tree(4)
        tree2.left = new MergeTwoBsts.Tree(2)
        tree2.right = new MergeTwoBsts.Tree(6)

        expect:
        MergeTwoBsts.mergeBsts(tree1, tree2) == [1, 2, 3, 4, 5, 6]
    }


    //First BST
    //          8
    //         / \
    //        2   10
    //       /
    //      1
    //Second BST
    //          5
    //         /
    //        3
    //       /
    //      0
    void 'g4g 2'() {
        setup:
        final tree1 = new MergeTwoBsts.Tree(8)
        tree1.left = new MergeTwoBsts.Tree(2)
        tree1.left.left = new MergeTwoBsts.Tree(1)
        tree1.right = new MergeTwoBsts.Tree(10)

        final tree2 = new MergeTwoBsts.Tree(5)
        tree2.left = new MergeTwoBsts.Tree(3)
        tree2.left.left = new MergeTwoBsts.Tree(0)
        expect:
        MergeTwoBsts.mergeBsts(tree1, tree2) == [0, 1, 2, 3, 5, 8, 10]
    }
}
