package tasks.g4g

import spock.lang.Specification

class DuplicateBinarySubtreesSpecification extends Specification {
    //               A
    //             /    \
    //           B        C
    //         /   \       \
    //        D     E       B
    //                     /  \
    //                    D    E
    void 'g4g'() {
        setup:
        final root = new DuplicateBinarySubtrees.Node<String>('A')
        root.left = new DuplicateBinarySubtrees.Node<String>('B')
        root.left.left = new DuplicateBinarySubtrees.Node<String>('D')
        root.left.right = new DuplicateBinarySubtrees.Node<String>('E')
        root.right = new DuplicateBinarySubtrees.Node<String>('C')
        root.right.right = new DuplicateBinarySubtrees.Node<String>('B')
        root.right.right.left = new DuplicateBinarySubtrees.Node<String>('D')
        root.right.right.right = new DuplicateBinarySubtrees.Node<String>('E')
        final tree = new DuplicateBinarySubtrees(root)
        expect:
        tree.containsDuplicateSubtree()
    }

    //               A
    //             /    \
    //           B        C
    //         /   \       \
    //        D     E       F
    //                     /  \
    //                    D    E
    void 'no duplicate subtree'() {
        setup:
        final root = new DuplicateBinarySubtrees.Node<String>('A')
        root.left = new DuplicateBinarySubtrees.Node<String>('B')
        root.left.left = new DuplicateBinarySubtrees.Node<String>('D')
        root.left.right = new DuplicateBinarySubtrees.Node<String>('E')
        root.right = new DuplicateBinarySubtrees.Node<String>('C')
        root.right.right = new DuplicateBinarySubtrees.Node<String>('F')
        root.right.right.left = new DuplicateBinarySubtrees.Node<String>('D')
        root.right.right.right = new DuplicateBinarySubtrees.Node<String>('E')
        final tree = new DuplicateBinarySubtrees(root)
        expect:
        !tree.containsDuplicateSubtree()
    }
}
