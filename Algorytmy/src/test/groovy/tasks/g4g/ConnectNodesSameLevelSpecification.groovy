package tasks.g4g

import spock.lang.Specification

class ConnectNodesSameLevelSpecification extends Specification {
    //       A
    //      / \
    //     B   C
    //    / \   \
    //   D   E   F
    void 'g4g'() {
        setup:
        final tree = new ConnectNodesSameLevel.Node<String>('A')
        tree.left = new ConnectNodesSameLevel.Node<String>('B')
        tree.left.left = new ConnectNodesSameLevel.Node<String>('D')
        tree.left.right = new ConnectNodesSameLevel.Node<String>('E')
        tree.right = new ConnectNodesSameLevel.Node<String>('C')
        tree.right.right = new ConnectNodesSameLevel.Node<String>('F')
        when:
        ConnectNodesSameLevel.setSiblings(tree)
        then:
        !tree.nextSibling
        tree.left.nextSibling == tree.right
        !tree.right.nextSibling
        tree.left.left.nextSibling == tree.left.right
        tree.left.right.nextSibling == tree.right.right
        !tree.right.right.nextSibling
    }
}
