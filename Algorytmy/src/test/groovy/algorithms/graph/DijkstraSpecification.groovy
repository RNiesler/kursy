package algorithms.graph

import datastructures.graph.Graph
import datastructures.graph.LinkedGraph
import spock.lang.Specification

class DijkstraSpecification extends Specification {

    //
    //
    //            /-(1)-> c -(2)-> e -(3)-> f
    //  b -(1)-> a                          ^
    //            \-(2)-> d --------(5)-----/
    //
    def 'test distances'() {
        setup:
        Graph<String> graph = new LinkedGraph<>()
        graph.insert('a')
        graph.insert('b')
        graph.insert('c')
        graph.insert('d')
        graph.insert('e')
        graph.insert('f')
        graph.addEdge('b', 'a', 1)
        graph.addEdge('a', 'c', 1)
        graph.addEdge('c', 'e', 2)
        graph.addEdge('e', 'f', 3)
        graph.addEdge('a', 'd', 2)
        graph.addEdge('d', 'f', 5)

        Dijkstra<String> dijkstra = new Dijkstra<>(graph)
        expect:
        1 == dijkstra.getShortestDistance('b', 'a')
        ['b', 'a'] == dijkstra.getShortestPath('b', 'a')
        3 == dijkstra.getShortestDistance('a', 'e')
        ['a', 'c', 'e'] == dijkstra.getShortestPath('a', 'e')
        6 == dijkstra.getShortestDistance('a', 'f')
        ['a', 'c', 'e', 'f'] == dijkstra.getShortestPath('a', 'f')
    }

    //
    // a -(1)-> b -(-1)-> c
    //
    def 'test negative weight'() {
        setup:
        Graph<String> graph = new LinkedGraph<>()
        graph.insert('a')
        graph.insert('b')
        graph.insert('c')
        graph.addEdge('a', 'b', 1)
        graph.addEdge('b', 'c', -1)
        Dijkstra<String> dijkstra = new Dijkstra<>(graph)
        when:
        dijkstra.getShortestDistance('a', 'c')
        then:
        thrown(IllegalArgumentException)
    }

    //
    // a -(1)-> b
    // c -(2)-> d
    def 'test disconnected nodes'() {
        setup:
        Graph<String> graph = new LinkedGraph<>()
        graph.insert('a')
        graph.insert('b')
        graph.insert('c')
        graph.insert('d')
        Dijkstra<String> dijkstra = new Dijkstra<>(graph)
        when:
        dijkstra.getShortestDistance('a','d')
        then:
        thrown(Dijkstra.NodeNotReachableException)
    }
}
