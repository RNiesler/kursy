package tasks.subarraysum

import spock.lang.Specification

class SubarraySumSpecification extends Specification {
    void 'simple test'() {
        expect:
        [2, 3, 7] as int[] == SubarraySum.solveFor([1, 2, 3, 7, 5] as int[], 12).get()
        [1, 2, 3, 4, 5] as int[] == SubarraySum.solveFor([1, 2, 3, 4, 5, 6, 7, 8, 9, 10] as int[], 15).get()
    }
}
