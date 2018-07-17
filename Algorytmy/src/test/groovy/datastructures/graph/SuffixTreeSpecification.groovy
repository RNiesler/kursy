package datastructures.graph

import datastructures.suffixtree.SuffixTree
import spock.lang.Specification

class SuffixTreeSpecification extends Specification {
    def 'test contains'() {
        setup:
        final suffixTree = new SuffixTree();

        when:
        suffixTree.put('abc')
        then:
        suffixTree.contains('abc')
        suffixTree.contains('bc')
        suffixTree.contains('c')
        !suffixTree.contains('ab')
        !suffixTree.contains('a')

        when:
        suffixTree.put('dbc')
        then:
        suffixTree.contains('dbc')
        suffixTree.contains('abc')
        suffixTree.contains('bc')
        suffixTree.contains('c')
        !suffixTree.contains('ab')
        !suffixTree.contains('a')
        4 == suffixTree.size()
    }
}
