package tasks.g4g

import spock.lang.Specification

class SudokuSpecification extends Specification {
    void 'g4g'() {
        setup:
        final board = [[3, 0, 6, 5, 0, 8, 4, 0, 0] as int[],
                       [5, 2, 0, 0, 0, 0, 0, 0, 0] as int[],
                       [0, 8, 7, 0, 0, 0, 0, 3, 1] as int[],
                       [0, 0, 3, 0, 1, 0, 0, 8, 0] as int[],
                       [9, 0, 0, 8, 6, 3, 0, 0, 5] as int[],
                       [0, 5, 0, 0, 9, 0, 6, 0, 0] as int[],
                       [1, 3, 0, 0, 0, 0, 2, 5, 0] as int[],
                       [0, 0, 0, 0, 0, 0, 0, 7, 4] as int[],
                       [0, 0, 5, 2, 0, 6, 3, 0, 0] as int[]] as int[][]
        when:
        final result = Sudoku.solve(board)
        then:
        result
    }
}
