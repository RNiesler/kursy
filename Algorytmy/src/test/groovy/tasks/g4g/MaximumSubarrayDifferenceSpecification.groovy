package tasks.g4g

import spock.lang.Specification

class MaximumSubarrayDifferenceSpecification extends Specification {
    void 'trivial'() {
        expect:
        MaximumSubarrayDifference.maxSubarrayDiff([1, 2] as int[]) == 1
    }

    void 'simple'() {
        expect:
        MaximumSubarrayDifference.maxSubarrayDiff([1, 2, 3] as int[]) == 4
    }

    void 'simple negative'() {
        expect:
        MaximumSubarrayDifference.maxSubarrayDiff([2, -1, 4] as int[]) == 5
    }

    void 'g4g'() {
        expect:
        MaximumSubarrayDifference.maxSubarrayDiff([-2, -3, 4, -1, -2, 1, 5, -3] as int[]) == 12
        MaximumSubarrayDifference.maxSubarrayDiff([2, -1, -2, 1, -4, 2, 8] as int[]) == 16

    }
}
