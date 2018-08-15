package tasks.hackerrank

import spock.lang.Specification

class ArrayManipulationSpecification extends Specification {
    void 'test case 1'() {
        setup:
        final queries = [[1, 2, 100],
                         [2, 5, 100],
                         [3, 4, 100]] as int[][]
        expect:
        ArrayManipulation.arrayManipulation(5, queries) == 200
    }

    void 'test case 3'() {
        setup:
        final queries = [[2, 6, 8],
                         [3, 5, 7],
                         [1, 8, 1],
                         [5, 9, 15]] as int[][]
        expect:
        ArrayManipulation.arrayManipulation(10, queries) == 31
    }


    void 'test case big'() {
        setup:
        final queries = [[29, 40, 787],
                         [9, 26, 219],
                         [21, 31, 214],
                         [8, 22, 719],
                         [15, 23, 102],
                         [11, 24, 83],
                         [14, 22, 321],
                         [5, 22, 300],
                         [11, 30, 832],
                         [5, 25, 29],
                         [16, 24, 577],
                         [3, 10, 905],
                         [15, 22, 335],
                         [29, 35, 254],
                         [9, 20, 20],
                         [33, 34, 351],
                         [30, 38, 564],
                         [11, 31, 969],
                         [3, 32, 11],
                         [29, 35, 267],
                         [4, 24, 531],
                         [1, 38, 892],
                         [12, 18, 825],
                         [25, 32, 99],
                         [3, 39, 107],
                         [12, 37, 131],
                         [3, 26, 640],
                         [8, 39, 483],
                         [8, 11, 194],
                         [12, 37, 502]] as int[][]
        expect:
        ArrayManipulation.arrayManipulation(40, queries) == 8628
    }

    void 'test case big simple'() {
        setup:
        final queries = [[29, 40, 787],
                         [9, 26, 219],
                         [21, 31, 214],
                         [8, 22, 719],
                         [15, 23, 102],
                         [11, 24, 83],
                         [14, 22, 321],
                         [5, 22, 300],
                         [11, 30, 832],
                         [5, 25, 29],
                         [16, 24, 577],
                         [3, 10, 905],
                         [15, 22, 335],
                         [29, 35, 254],
                         [9, 20, 20],
                         [33, 34, 351],
                         [30, 38, 564],
                         [11, 31, 969],
                         [3, 32, 11],
                         [29, 35, 267],
                         [4, 24, 531],
                         [1, 38, 892],
                         [12, 18, 825],
                         [25, 32, 99],
                         [3, 39, 107],
                         [12, 37, 131],
                         [3, 26, 640],
                         [8, 39, 483],
                         [8, 11, 194],
                         [12, 37, 502]] as int[][]
        expect:
        ArrayManipulation.arrayManipulationSimple(40, queries) == 8628

    }
}
