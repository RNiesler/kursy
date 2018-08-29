package tasks.hackerrank

import spock.lang.Specification

class JourneyToTheMoonSpecification extends Specification {
    void 'test case 0 graph'() {
        setup:
        final astronauts = [[0, 1],
                            [2, 3],
                            [0, 4]] as int[][]
        expect:
        JourneyToTheMoon.journeyToMoonGraph(5, astronauts) == 6
    }

    void 'test case 0 union find'() {
        setup:
        final astronauts = [[0, 1],
                            [2, 3],
                            [0, 4]] as int[][]
        expect:
        JourneyToTheMoon.journeyToMoon(5, astronauts) == 6
    }

    void 'test case 4 union find'() {
        setup:
        final astronauts = [[0, 1],
                             [0, 3],
                             [0, 4],
                             [1, 2],
                             [1, 3],
                             [1, 5],
                             [1, 7],
                             [1, 8],
                             [1, 9],
                             [2, 8],
                             [2, 7],
                             [3, 5],
                             [3, 8],
                             [3, 7],
                             [4, 9],
                             [4, 5],
                             [4, 6],
                             [4, 7],
                             [6, 8],
                             [6, 7]] as int[][]
        expect:
        JourneyToTheMoon.journeyToMoon(10, astronauts) == 0
    }
}
