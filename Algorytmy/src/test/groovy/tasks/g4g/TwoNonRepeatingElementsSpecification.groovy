package tasks.g4g

import spock.lang.Specification

class TwoNonRepeatingElementsSpecification extends Specification {
    void 'g4g'() {
        setup:
        final input = [2, 4, 7, 9, 2, 4] as int[]
        when:
        final pair = TwoNonRepeatingElements.findNonRepeatingPair(input)
        then:
        pair.x1 == 7
        pair.x2 == 9
    }
}
