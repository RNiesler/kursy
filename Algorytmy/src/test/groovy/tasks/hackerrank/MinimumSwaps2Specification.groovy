package tasks.hackerrank

import spock.lang.Specification

class MinimumSwaps2Specification extends Specification {
    void 'simple'() {
        expect:
        MinimumSwaps2.minimumSwapsLinear([4, 3, 1, 2] as int[]) == 3
        MinimumSwaps2.minimumSwapsLinear([1, 3, 5, 2, 4, 6, 8] as int[]) == 3
    }
}
