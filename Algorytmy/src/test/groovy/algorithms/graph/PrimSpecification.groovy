package algorithms.graph

import datastructures.graph.Graph
import datastructures.graph.LinkedGraph
import datastructures.graph.UndirectedGraph
import spock.lang.Specification

import java.util.function.Supplier

class PrimSpecification extends Specification {
    def 'test on empty'() {
        setup:
        Supplier<Graph<String>> supplier = { new LinkedGraph<>() }
        final undirectedGraph = UndirectedGraph.of(supplier)
        when:
        def tree = new Prim<String>(undirectedGraph).minimumSpanningTree
        then:
        tree.empty
    }

    //
    //          /-(2)- c-(5)-\
    // a -(1)- b -(8)- d-(1)--f
    //          \-(4)- e-(1)-/
    //
    def 'test on linked'() {
        setup:
        Supplier<Graph<String>> supplier = { new LinkedGraph<>() }
        final undirectedGraph = UndirectedGraph.of(supplier)
        undirectedGraph.insert('a')
        undirectedGraph.insert('b')
        undirectedGraph.insert('c')
        undirectedGraph.insert('d')
        undirectedGraph.insert('e')
        undirectedGraph.insert('f')
        undirectedGraph.addEdge('a', 'b', 1)
        undirectedGraph.addEdge('b', 'c', 2)
        undirectedGraph.addEdge('b', 'd', 8)
        undirectedGraph.addEdge('b', 'e', 4)
        undirectedGraph.addEdge('c', 'f', 5)
        undirectedGraph.addEdge('d', 'f', 1)
        undirectedGraph.addEdge('e', 'f', 1)

        when:
        def minimumSpanningTree = new Prim(undirectedGraph).minimumSpanningTree
        then:
        1 + 2 + 4 + 1 + 1 == minimumSpanningTree.collect { it.weight }.sum()
        5 == minimumSpanningTree.size()
        !minimumSpanningTree.find {
            (it.target.value == 'd' && it.source.value == 'b') ||
                    it.target.value == 'b' && it.source.value == 'd'
        }
        !minimumSpanningTree.find {
            (it.target.value == 'c' && it.source.value == 'f') ||
                    it.target.value == 'f' && it.source.value == 'c'
        }


    }
}
