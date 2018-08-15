package tasks.hackerrank

import spock.lang.Specification

class CandiesSpecification extends Specification {
    void 'test case 0'() {
        expect:
        Candies.candies(8, [2, 4, 3, 5, 2, 6, 4, 5] as int[]) == 12
        Candies.candies2(8, [2, 4, 3, 5, 2, 6, 4, 5] as int[]) == 12
        Candies.candies3(8, [2, 4, 3, 5, 2, 6, 4, 5] as int[]) == 12
    }
}
