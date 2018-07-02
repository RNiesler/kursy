package datastructures.graph;

import spock.lang.Specification;

class AdjacencyMatrixGraphSpecification extends Specification {
    void 'test grow'() {
        setup:
        final graph = new AdjacencyMatrixGraph<String>(1)
        when:
        graph.insert('a')
        graph.insert('b')
        graph.addEdge('a', 'b')
        graph.insert('c')

        graph.addEdge('a', 'c')
        graph.addEdge('c', 'b')
        then:
        3 == graph.size()
        def aNode = graph.nodes.find { it.value == 'a' }
        def bNode = graph.nodes.find { it.value == 'b' }
        def cNode = graph.nodes.find { it.value == 'c' }
        aNode.adjacent.value.contains('b')
        aNode.adjacent.value.contains('c')
        bNode.adjacent.empty
        cNode.adjacent.value.contains('b')
    }

    void 'test shrink'() {
        setup:
        final graph = new AdjacencyMatrixGraph<String>(8)
        graph.insert('a')
        graph.insert('b')
        graph.insert('c')
        graph.insert('d')
        graph.insert('e')
        graph.insert('f')
        graph.insert('g')
        graph.insert('h')
        graph.addEdge('a', 'b') // both removed
        graph.addEdge('a', 'd') // source removed
        graph.addEdge('d', 'b') // target removed
        graph.addEdge('f', 'g') // none removed
        when:
        graph.remove('a')
        graph.remove('b')
        graph.remove('c')
        graph.remove('e')
        graph.remove('h')
        then:
        3 == graph.size()
        graph.all.findAll { it in ['a', 'b', 'c', 'e', 'h'] }.empty
        def dNode = graph.nodes.find { it.value == 'd'}
        def fNode = graph.nodes.find { it.value == 'f'}
        dNode.adjacent.empty
        'g' in fNode.adjacent.value
    }
}
