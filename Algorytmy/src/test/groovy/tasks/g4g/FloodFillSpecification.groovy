package tasks.g4g

import spock.lang.Specification

class FloodFillSpecification extends Specification {
    void 'g4g'() {
        setup:
        final input = [[1, 1, 1, 1, 1, 1, 1, 1],
                       [1, 1, 1, 1, 1, 1, 0, 0],
                       [1, 0, 0, 1, 1, 0, 1, 1],
                       [1, 2, 2, 2, 2, 0, 1, 0],
                       [1, 1, 1, 2, 2, 0, 1, 0],
                       [1, 1, 1, 2, 2, 2, 2, 0],
                       [1, 1, 1, 1, 1, 2, 1, 1],
                       [1, 1, 1, 1, 1, 2, 2, 1]
        ] as int[][]
        when:
        FloodFill.floodFill(input, 4, 4, 3)
        then:
        input[0] == [1, 1, 1, 1, 1, 1, 1, 1] as int[]
        input[1] == [1, 1, 1, 1, 1, 1, 0, 0] as int[]
        input[2] == [1, 0, 0, 1, 1, 0, 1, 1] as int[]
        input[3] == [1, 3, 3, 3, 3, 0, 1, 0] as int[]
        input[4] == [1, 1, 1, 3, 3, 0, 1, 0] as int[]
        input[5] == [1, 1, 1, 3, 3, 3, 3, 0] as int[]
        input[6] == [1, 1, 1, 1, 1, 3, 1, 1] as int[]
        input[7] == [1, 1, 1, 1, 1, 3, 3, 1] as int[]
    }
}
