package heap

import spock.lang.Specification

class HeapSpecification extends Specification {
    def 'test heapify'() {
        setup:
        def heap = new Heap([6, 5, 4, 3, 2, 1])
        expect:
        heap.extract() == 1
    }

    def 'test insert'() {
        setup:
        def heap = new Heap([6, 5, 2])
        when:
        heap.insert(1)
        heap.insert(3)
        then:
        heap.extract() == 1
        heap.extract() == 2
        heap.extract() == 3
    }
}
