package tasks.g4g

import spock.lang.Specification

class FourSumSpecification extends Specification {
    void 'g4g'() {
        when:
        int result = FourSum.fourSum([10, 2, 3, 4, 5, 9, 7, 8] as int[], 23).get()
        then:
        result.inject(0, {a,b -> a+b}).intValue() == 23
    }
}
