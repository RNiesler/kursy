package datastructures.graph

import algorithms.graph.DepthFirstSearch
import spock.lang.Specification

class DepthFirstSearchSpecification extends Specification {
    def 'test on linked graph'() {
        setup:
        final graph = new LinkedGraph<String>()
        final testStrs = ['test1', 'test2', 'test3', 'test4', 'test5'] as List<String>
        testStrs.each { graph.insert(it) }
        graph.addEdge(testStrs[0], testStrs[1])
        graph.addEdge(testStrs[0], testStrs[3])
        graph.addEdge(testStrs[1], testStrs[2])
        graph.addEdge(testStrs[2], testStrs[4])
        final strategy = new DepthFirstSearch<String>(testStrs[0])
        when:
        final iterator = graph.iterator(strategy)
        final list = iterator.toList()
        then:
        [testStrs[0], testStrs[1], testStrs[2], testStrs[4], testStrs[3]] == list
    }

    def 'test on not connected - linked'() {
        setup:
        final graph = new LinkedGraph<String>()
        final testStr1 = 'test1'
        final testStr2 = 'test2'
        final testStr3 = 'test3'
        graph.insert(testStr1)
        graph.insert(testStr2)
        graph.insert(testStr3)
        final strategy = new DepthFirstSearch<String>(testStr1)
        when:
        final iterator = graph.iterator(strategy)
        final list = iterator.toList()
        then:
        [testStr1] == list
    }

    def 'test on empty - linked'() {
        final graph = new LinkedGraph<String>()
        final strategy = new DepthFirstSearch<String>('test')
        when:
        graph.iterator(strategy)
        then:
        thrown(IllegalArgumentException)
    }

    def 'test on adjacency graph'() {
        setup:
        final graph = new AdjacencyMatrixGraph<String>()
        final testStrs = ['test1', 'test2', 'test3', 'test4', 'test5'] as List<String>
        testStrs.each { graph.insert(it) }
        graph.addEdge(testStrs[0], testStrs[1])
        graph.addEdge(testStrs[0], testStrs[3])
        graph.addEdge(testStrs[1], testStrs[2])
        graph.addEdge(testStrs[2], testStrs[4])
        final strategy = new DepthFirstSearch<String>(testStrs[0])
        when:
        final iterator = graph.iterator(strategy)
        final list = iterator.toList()
        then:
        [testStrs[0], testStrs[1], testStrs[2], testStrs[4], testStrs[3]] == list
    }

    def 'test on not connected - adjacency'() {
        setup:
        final graph = new AdjacencyMatrixGraph<String>()
        final testStr1 = 'test1'
        final testStr2 = 'test2'
        final testStr3 = 'test3'
        graph.insert(testStr1)
        graph.insert(testStr2)
        graph.insert(testStr3)
        final strategy = new DepthFirstSearch<String>(testStr1)
        when:
        final iterator = graph.iterator(strategy)
        final list = iterator.toList()
        then:
        [testStr1] == list
    }

    def 'test on empty - adjacency'() {
        final graph = new AdjacencyMatrixGraph<String>()
        final strategy = new DepthFirstSearch<String>('test')
        when:
        graph.iterator(strategy)
        then:
        thrown(IllegalArgumentException)
    }
}
