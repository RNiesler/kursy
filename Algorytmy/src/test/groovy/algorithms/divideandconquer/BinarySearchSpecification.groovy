package algorithms.divideandconquer


import spock.lang.Specification

class BinarySearchSpecification extends Specification {
    final integerComparator = { a, b -> a - b } as Comparator<Integer>

    void 'test on strings'() {
        setup:
        final ts = ['a', 'b', 'c', 'd', 'f', 'g'] as String[]
        final comparator = { a, b -> a.charAt(0) - b.charAt(0) } as Comparator<String>
        expect:
        BinarySearch.contains(ts, 'b', comparator)
        BinarySearch.containsIt(ts, 'b', comparator)
        !BinarySearch.contains(ts, 'e', comparator)
        !BinarySearch.containsIt(ts, 'e', comparator)
        !BinarySearch.contains(ts, 'h', comparator)
        !BinarySearch.containsIt(ts, 'h', comparator)
    }

    void 'test on integers'() {
        setup:
        final ts = [-5, -3, -2, -1, 0, 1, 2] as Integer[]
        expect:
        BinarySearch.contains(ts, -2, integerComparator)
        BinarySearch.containsIt(ts, -2, integerComparator)
        !BinarySearch.contains(ts, -4, integerComparator)
        !BinarySearch.containsIt(ts, -4, integerComparator)
        !BinarySearch.contains(ts, -7, integerComparator)
        !BinarySearch.containsIt(ts, -7, integerComparator)
        !BinarySearch.contains(ts, 3, integerComparator)
        !BinarySearch.containsIt(ts, 3, integerComparator)
    }

    void 'test on empty'() {
        expect:
        !BinarySearch.contains([] as Integer[], 1, integerComparator)
        !BinarySearch.containsIt([] as Integer[], 1, integerComparator)
    }

    void 'test with null element'() {
        expect:
        !BinarySearch.contains([] as Integer[], null, integerComparator)
        !BinarySearch.containsIt([] as Integer[], null, integerComparator)
    }

    void 'test with null array'() {
        when:
        BinarySearch.contains(null, 1, integerComparator)
        then:
        thrown(NullPointerException)

        when:
        BinarySearch.containsIt(null, 1, integerComparator)
        then:
        thrown(NullPointerException)
    }

    void 'test with null comparator'() {
        when:
        BinarySearch.contains([1] as Integer[], 1, null)
        then:
        thrown(NullPointerException)

        when:
        BinarySearch.containsIt([1] as Integer[], 1, null)
        then:
        thrown(NullPointerException)
    }
}
