package tasks.hackerrank

import spock.lang.Specification

class MaximizingXorSpecification extends Specification {
    void 'test case 3'() {
        expect:
        MaximizingXor.maximizingXor(304,313) == 15
        // 100110000 - 304
        // 100111001 - 313
        // 000001001
    }
}
