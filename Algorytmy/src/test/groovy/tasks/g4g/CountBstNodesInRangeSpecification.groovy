package tasks.g4g

import spock.lang.Specification

class CountBstNodesInRangeSpecification extends Specification {
    //        10
    //      /    \
    //    5       50
    //   /       /  \
    // 1       40   100
    void 'g4g'() {
        setup:
        final tree = new CountBstNodesInRange.Tree(10)
        tree.left = new CountBstNodesInRange.Tree(5)
        tree.left.left = new CountBstNodesInRange.Tree(1)
        tree.right = new CountBstNodesInRange.Tree(50)
        tree.right.left = new CountBstNodesInRange.Tree(40)
        tree.right.right = new CountBstNodesInRange.Tree(100)

        expect:
        CountBstNodesInRange.countInRange(tree, 5, 45) == 3
    }
}
