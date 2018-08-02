package algorithms.graph

import datastructures.graph.LinkedGraph
import spock.lang.Specification

class TopologicalSortSpecification extends Specification {
    // a-->b-->c
    def 'test simple'() {
        setup:
        final graph = new LinkedGraph<String>()
        graph.insert('a')
        graph.insert('b')
        graph.insert('c')
        graph.addEdge('a', 'b')
        graph.addEdge('b', 'c')

        when:
        final order = TopologicalSort.sort(graph)

        then:
        ['a', 'b', 'c'] == order
    }
    def 'test simple2'() {
        setup:
        final graph = new LinkedGraph<String>()
        graph.insert('a')
        graph.insert('b')
        graph.insert('c')
        graph.addEdge('a', 'b')
        graph.addEdge('b', 'c')

        when:
        final order = TopologicalSort.sort2(graph)

        then:
        ['a', 'b', 'c'] == order
    }

    //
    // a ---          --> d
    //      \        /
    //       --> c --
    //      /        \
    // b ---          --> e
    def 'test branched'() {
        setup:
        final graph = new LinkedGraph<String>()
        graph.insert('a')
        graph.insert('b')
        graph.insert('c')
        graph.insert('d')
        graph.insert('e')
        graph.addEdge('a', 'c')
        graph.addEdge('b', 'c')
        graph.addEdge('c', 'd')
        graph.addEdge('c', 'e')

        when:
        final order = TopologicalSort.sort(graph)

        then:
        order[2] == 'c'
        order[0] in ['a', 'b']
        order[1] in (['a', 'b'] - order[0])
        order[3] in ['d', 'e']
        order[4] in (['d', 'e'] - order[3])
    }
    def 'test branched2'() {
        setup:
        final graph = new LinkedGraph<String>()
        graph.insert('a')
        graph.insert('b')
        graph.insert('c')
        graph.insert('d')
        graph.insert('e')
        graph.addEdge('a', 'c')
        graph.addEdge('b', 'c')
        graph.addEdge('c', 'd')
        graph.addEdge('c', 'e')

        when:
        final order = TopologicalSort.sort2(graph)

        then:
        order[2] == 'c'
        order[0] in ['a', 'b']
        order[1] in (['a', 'b'] - order[0])
        order[3] in ['d', 'e']
        order[4] in (['d', 'e'] - order[3])
    }

    //         --------
    //        /        \
    // a --> b          --> d
    //        \        /
    //         --> c --
    def 'test with closed'() {
        setup:
        final graph = new LinkedGraph<>()
        graph.insert('a')
        graph.insert('b')
        graph.insert('c')
        graph.insert('d')
        graph.addEdge('a', 'b')
        graph.addEdge('b', 'c')
        graph.addEdge('b', 'd')
        graph.addEdge('c', 'd')

        when:
        final order = TopologicalSort.sort(graph)
        then:
        ['a', 'b', 'c', 'd'] == order
    }
    def 'test with closed2'() {
        setup:
        final graph = new LinkedGraph<>()
        graph.insert('a')
        graph.insert('b')
        graph.insert('c')
        graph.insert('d')
        graph.addEdge('a', 'b')
        graph.addEdge('b', 'c')
        graph.addEdge('b', 'd')
        graph.addEdge('c', 'd')

        when:
        final order = TopologicalSort.sort2(graph)
        then:
        ['a', 'b', 'c', 'd'] == order
    }

    //      --> b <--------
    //     /    |          |
    // a --      --> c --> d
    //
    def 'test with cycle'() {
        setup:
        final graph = new LinkedGraph<String>()
        graph.insert('a')
        graph.insert('b')
        graph.insert('c')
        graph.insert('d')
        graph.addEdge('a', 'b')
        graph.addEdge('b', 'c')
        graph.addEdge('c', 'd')
        graph.addEdge('d', 'b')

        when:
        TopologicalSort.sort(graph)
        then:
        thrown(TopologicalSort.CycleDetectedException)
    }
    def 'test with cycle2'() {
        setup:
        final graph = new LinkedGraph<String>()
        graph.insert('a')
        graph.insert('b')
        graph.insert('c')
        graph.insert('d')
        graph.addEdge('a', 'b')
        graph.addEdge('b', 'c')
        graph.addEdge('c', 'd')
        graph.addEdge('d', 'b')

        when:
        TopologicalSort.sort2(graph)
        then:
        thrown(TopologicalSort.CycleDetectedException)
    }

}
