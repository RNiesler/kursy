package tasks.g4g

import spock.lang.Specification

class MinPaginationSpecification extends Specification {
    void 'g4g'() {
        expect:
        MinPagination.allocateMinPages([12, 34, 67, 90] as int[], 2) == 113;
    }


    void 'iterative'() {
        expect:
        MinPagination.allocateMinPagesIterative([12, 34, 67, 90] as int[], 2) == 113;
    }

    // [ 12, 46, 113, 203]
}
