package datastructures.graph

import spock.lang.Specification

class SimpleGraphTraverseStrategySpecification extends Specification {
    def 'test on linked graph'() {
        setup:
        final graph = new LinkedGraph<String>()
        final testStr1 = 'test1'
        final testStr2 = 'test2'
        final testStr3 = 'test3'
        final testStrs = [testStr1, testStr2, testStr3]
        graph.insert(testStr1)
        graph.insert(testStr2)
        graph.insert(testStr3)
        graph.addEdge(testStr1, testStr3)
        graph.addEdge(testStr3, testStr2)
        final strategy = new SimpleGraphTraverseStrategy<String>()
        when:
        final iterator = graph.iterator(strategy)
        final list = iterator.toList()
        then:
        testStrs.size() == list.size()
        list.each { testStrs.contains(it) }
    }

    def 'test on not connected'() {
        setup:
        final graph = new LinkedGraph<String>()
        final testStr1 = 'test1'
        final testStr2 = 'test2'
        final testStr3 = 'test3'
        final testStrs = [testStr1, testStr2, testStr3]
        graph.insert(testStr1)
        graph.insert(testStr2)
        graph.insert(testStr3)
        final strategy = new SimpleGraphTraverseStrategy<String>()
        when:
        final iterator = graph.iterator(strategy)
        final list = iterator.toList()
        then:
        testStrs.size() == list.size()
        list.each { testStrs.contains(it) }
    }

    def 'test on empty'() {
        final graph = new LinkedGraph<String>()
        final strategy = new SimpleGraphTraverseStrategy<String>()
        when:
        final iterator = graph.iterator(strategy)
        then:
        0 == iterator.size()
    }
}
