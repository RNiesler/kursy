package algorithms.divideandconquer;

import spock.lang.Specification;

class QuickSortSpecification extends Specification {
    void 'test simple int'() {
        setup:
        final t0 = [3, 2, 1] as Integer[]
        final t1 = [2, 1] as Integer[]
        final t2 = [9, 1, 2, 3, 4, 5, 6] as Integer[]
        final t3 = [8, 3, 9, 2, 2, 9, 1, 8, 4, 0] as Integer[]

        when:
        QuickSort.sort(t0)
        then:
        t0 == [1, 2, 3] as Integer[]

        when:
        QuickSort.sort(t1)
        then:
        t1 == [1, 2] as Integer[]

        when:
        QuickSort.sort(t2)
        then:
        t2 == [1, 2, 3, 4, 5, 6, 9] as Integer[]

        when:
        QuickSort.sort(t3)
        then:
        t3 == [0, 1, 2, 2, 3, 4, 8, 8, 9, 9] as Integer[]
    }


}
