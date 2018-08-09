package tasks.g4g

import spock.lang.Specification

class InPlaceRotateSpecification extends Specification {
    void 'g4g'() {
        setup:
        final matrix = [[1, 2, 3],
                        [4, 5, 6],
                        [7, 8, 9]] as int[][]
        when:
        InPlaceRotate.inPlaceRotate(matrix)
        then:
        matrix == [[3, 6, 9],
                   [2, 5, 8],
                   [1, 4, 7]] as int[][]

    }

    void 'g4g flat'() {
        setup:
        final matrix = [1,2,3,4,5,6,7,8,9] as int[]
        when:
        InPlaceRotate.inPlaceRotateArithmetic(matrix)
        then:
        matrix == [3, 6, 9, 2, 5, 8, 1, 4, 7] as int[]
    }
}
