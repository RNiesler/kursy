package tasks.hackerrank

import spock.lang.Specification

class SherlockAndCostSpecification extends Specification {
    void 'test case 0'() {
        expect:
        SherlockAndCost.cost([100, 2, 100, 2, 100] as int[]) == 396
    }


    void 'test case 0 efficient'() {
        expect:
        SherlockAndCost.costEfficient([100, 2, 100, 2, 100] as int[]) == 396
    }
}
