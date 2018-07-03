package datastructures.graph

import spock.lang.Specification

import java.util.function.Supplier

class UndirectedGraphSpecification extends Specification {
    // a -- b--c
    //       \
    //        d
    def 'on linked graph'() {
        setup:
        final undirectedGraph = UndirectedGraph.of(new Supplier<Graph<String>>() {
            @Override
            Graph<String> get() {
                return new LinkedGraph<String>()
            }
        })
        undirectedGraph.insert('a')
        undirectedGraph.insert('b')
        undirectedGraph.insert('c')
        undirectedGraph.insert('d')
        undirectedGraph.addEdge('a', 'b')
        undirectedGraph.addEdge('c', 'b', 10)
        undirectedGraph.addEdge('d', 'b')

        expect:
        4 == undirectedGraph.size()
        ['a', 'c', 'd'] == undirectedGraph.nodes.find { it.value == 'b' }.adjacent.target.value.sort()
    }

    // a -- b--c
    //       \
    //        d
    def 'on adjacency graph'() {
        setup:
        final undirectedGraph = UndirectedGraph. of(new Supplier<Graph<String>>() {
            @Override
            Graph<String> get() {
                return new AdjacencyMatrixGraph<String>()
            }
        })
        undirectedGraph.insert('a')
        undirectedGraph.insert('b')
        undirectedGraph.insert('c')
        undirectedGraph.insert('d')
        undirectedGraph.addEdge('a', 'b')
        undirectedGraph.addEdge('c', 'b', 10)
        undirectedGraph.addEdge('d', 'b')

        expect:
        4 == undirectedGraph.size()
        ['a', 'c', 'd'] == undirectedGraph.nodes.find { it.value == 'b' }.adjacent.target.value.sort()
    }
}
