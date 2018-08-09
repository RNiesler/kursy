package tasks.g4g

import spock.lang.Specification

class MatrixFillSpecification extends Specification {
    void 'g4g 1'() {
        setup:
        final matrix = [['X', 'O', 'X', 'X', 'X', 'X'],
                        ['X', 'O', 'X', 'X', 'O', 'X'],
                        ['X', 'X', 'X', 'O', 'O', 'X'],
                        ['O', 'X', 'X', 'X', 'X', 'X'],
                        ['X', 'X', 'X', 'O', 'X', 'O'],
                        ['O', 'O', 'X', 'O', 'O', 'O']
        ] as char[][]

        when:
        MatrixFill.matrixFill(matrix)
        then:
        matrix == [['X', 'O', 'X', 'X', 'X', 'X'],
                   ['X', 'O', 'X', 'X', 'X', 'X'],
                   ['X', 'X', 'X', 'X', 'X', 'X'],
                   ['O', 'X', 'X', 'X', 'X', 'X'],
                   ['X', 'X', 'X', 'O', 'X', 'O'],
                   ['O', 'O', 'X', 'O', 'O', 'O']
        ] as char[][]
    }


    void 'g4g 2'() {
        setup:
        final matrix = [['X', 'X', 'X', 'X'],
                        ['X', 'O', 'X', 'X'],
                        ['X', 'O', 'O', 'X'],
                        ['X', 'O', 'X', 'X'],
                        ['X', 'X', 'O', 'O']
        ] as char[][]

        when:
        MatrixFill.matrixFill(matrix)
        then:
        matrix == [['X', 'X', 'X', 'X'],
                   ['X', 'X', 'X', 'X'],
                   ['X', 'X', 'X', 'X'],
                   ['X', 'X', 'X', 'X'],
                   ['X', 'X', 'O', 'O']
        ] as char[][]
    }
}
