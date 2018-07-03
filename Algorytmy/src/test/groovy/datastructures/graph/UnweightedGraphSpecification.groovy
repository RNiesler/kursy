package datastructures.graph

import spock.lang.Specification

import java.util.function.Supplier

class UnweightedGraphSpecification extends Specification {
    def 'test on linked'() {
        setup:
        final unweightedGraph = UnweightedGraph.of(new Supplier<Graph<String>>() {
            @Override
            Graph<String> get() {
                return new LinkedGraph<String>()
            }
        })
        unweightedGraph.insert('a')
        unweightedGraph.insert('b')
        unweightedGraph.insert('c')
        when:
        unweightedGraph.addEdge('a', 'b')
        then:
        noExceptionThrown()
        unweightedGraph.nodes.find { it.value == 'a'}.adjacent.first().weight == 1

        when:
        unweightedGraph.addEdge('b', 'c', 1)
        then:
        noExceptionThrown()
        unweightedGraph.nodes.find { it.value == 'b'}.adjacent.first().weight == 1

        when:
        unweightedGraph.addEdge('a', 'c', 2)
        then:
        thrown(IllegalArgumentException)
    }

    def 'test on adjacency graph'() {
        setup:
        final unweightedGraph = UnweightedGraph.of(new Supplier<Graph<String>>() {
            @Override
            Graph<String> get() {
                return new AdjacencyMatrixGraph<String>()
            }
        })
        unweightedGraph.insert('a')
        unweightedGraph.insert('b')
        unweightedGraph.insert('c')
        when:
        unweightedGraph.addEdge('a', 'b')
        then:
        noExceptionThrown()
        unweightedGraph.nodes.find { it.value == 'a'}.adjacent.first().weight == 1

        when:
        unweightedGraph.addEdge('b', 'c', 1)
        then:
        noExceptionThrown()
        unweightedGraph.nodes.find { it.value == 'b'}.adjacent.first().weight == 1

        when:
        unweightedGraph.addEdge('a', 'c', 2)
        then:
        thrown(IllegalArgumentException)
    }
}
