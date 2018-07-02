package datastructures.graph

import spock.lang.Specification

class LinkedGraphSpecification extends Specification {
    private LinkedGraph<String> graph

    void setup() {
        graph = new LinkedGraph<String>()
    }

    def 'test simple add & contains'() {
        setup:
        final testStr = 'test'

        when:
        graph.insert(testStr)
        then:
        graph.contains(testStr)
    }

    def 'test get all'() {
        setup:
        final testStr1 = 'test1'
        final testStr2 = 'test2'

        when:
        graph.insert(testStr1)
        graph.insert(testStr2)
        def all = graph.all
        then:
        all.size() == 2
        all.findAll { it == testStr1 || it == testStr2 }.size() == 2
    }

    def 'test size'() {
        setup:
        final testStr1 = 'test1'
        final testStr2 = 'test2'

        expect:
        graph.size() == 0

        when:
        graph.insert(testStr1)
        then:
        graph.size() == 1

        when:
        graph.insert(testStr2)
        then:
        graph.size() == 2
    }

    def 'test getNodes'() {
        setup:
        final testStr1 = 'test1'
        final testStr2 = 'test2'

        when:
        graph.insert(testStr1)
        graph.insert(testStr2)
        final nodes = graph.nodes
        then:
        nodes.size() == 2
        nodes.collect { it.value }.findAll { it == testStr1 || it == testStr2 }.size() == 2
    }

    def 'test inserting duplicate'() {
        setup:
        final testStr = 'test'

        when:
        graph.insert(testStr)
        then:
        graph.size() == 1

        when:
        graph.insert(testStr)
        then:
        graph.size() == 1
    }

    def 'test remove'() {
        setup:
        String testStr1 = 'test1'
        String testStr2 = 'test2'
        graph.insert(testStr1)
        graph.insert(testStr2)

        when:
        graph.remove(testStr1)
        then:
        graph.size() == 1
        !graph.getAll().contains(testStr1)

        when:
        graph.remove(testStr1)
        then:
        graph.size() == 1

        when:
        graph.remove(testStr2)
        then:
        graph.size() == 0
    }

    def 'test edges'() {
        setup:
        final testStr1 = 'test1'
        final testStr2 = 'test2'
        graph.insert(testStr1)
        graph.insert(testStr2)
        when:
        graph.addEdge(testStr1, testStr2)
        then:
        testStr2 in graph.getNodes().find({ it.value == testStr1 }).adjacent.target.value
    }

    def 'test remove with edges'() {
        setup:
        final testStr1 = 'test1'
        final testStr2 = 'test2'
        graph.insert(testStr1)
        graph.insert(testStr2)
        graph.addEdge(testStr1, testStr2)
        assert 1 == graph.getNodes().find { it.value == testStr1 }.adjacent.size()
        when:
        graph.remove(testStr2)
        then:
        graph.getNodes().first().adjacent.empty
    }

    def 'test set constructor'() {
        when:
        final setGraph = new LinkedGraph<String>(['test1', 'test2'] as Set<String>)
        then:
        2 == setGraph.getAll().size()
    }

}
