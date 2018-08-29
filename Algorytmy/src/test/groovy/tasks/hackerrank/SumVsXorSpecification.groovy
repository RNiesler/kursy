package tasks.hackerrank

import spock.lang.Specification

class SumVsXorSpecification extends Specification {
    void 'test case 0'() {
        expect:
        SumVsXor.sumXor(5) == 2
    }

    void 'test case 10'() {
        expect:
        SumVsXor.sumXor(1099511627776) == 1099511627776
    }
}
