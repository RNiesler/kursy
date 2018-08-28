package tasks.hackerrank

import spock.lang.Specification

class CoinChangeSpecification extends Specification {
    void 'simple'() {
        expect:
        CoinChange.getWays(4, [1, 2, 3] as int[]) == 4
        CoinChange.getWays(10, [2, 5, 3, 6] as int[]) == 5
    }
    void 'iterative'() {
        expect:
        CoinChange.getWaysIter(4, [1, 2, 3] as int[]) == 4
        CoinChange.getWaysIter(10, [2, 5, 3, 6] as int[]) == 5
    }
}
