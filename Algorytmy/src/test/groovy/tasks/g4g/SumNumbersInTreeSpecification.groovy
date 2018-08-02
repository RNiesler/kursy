package tasks.g4g

import spock.lang.Specification

class SumNumbersInTreeSpecification extends Specification {
    //                                          6
    //                                      /      \
    //                                    3          5
    //                                  /   \          \
    //                                 2     5          4
    //                                      /   \
    //                                     7     4
    void 'g4g'() {
        setup:
        SumNumbersInTree.Node root = new SumNumbersInTree.Node(6)
        root.left = new SumNumbersInTree.Node(3)
        root.left.left = new SumNumbersInTree.Node(2)
        root.left.right = new SumNumbersInTree.Node(5)
        root.left.right.left = new SumNumbersInTree.Node(7)
        root.left.right.right = new SumNumbersInTree.Node(4)
        root.right = new SumNumbersInTree.Node(5)
        root.right.right = new SumNumbersInTree.Node(4)
        expect:
        SumNumbersInTree.sumNumbersInTree(root) == 13997
    }
}
