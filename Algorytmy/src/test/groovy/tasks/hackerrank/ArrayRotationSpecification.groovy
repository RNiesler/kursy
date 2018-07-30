package tasks.hackerrank

import spock.lang.Specification

class ArrayRotationSpecification extends Specification {
    void 'test 5 4'() {
        setup:
        final array = [1, 2, 3, 4, 5] as int[]
        when:
        final result = ArrayRotation.rotLeft(array, 4)
        then:
        result == [5, 1, 2, 3, 4]
    }
}
