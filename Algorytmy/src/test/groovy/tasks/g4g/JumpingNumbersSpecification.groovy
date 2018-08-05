package tasks.g4g

import spock.lang.Specification

class JumpingNumbersSpecification extends Specification {
    void 'g4g 1'() {
        expect:
        JumpingNumbers.jumpingNumbers(20).sort() == [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12]
        JumpingNumbers.jumpingNumbers(105).sort() == [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12,
                                                      21, 23, 32, 34, 43, 45, 54, 56, 65,
                                                      67, 76, 78, 87, 89, 98, 101]
    }
}
