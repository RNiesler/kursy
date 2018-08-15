package tasks.hackerrank

import spock.lang.Specification

class RoadsAndLibrariesSpecification extends Specification {
    void 'test case 0'() {
        expect:
        RoadsAndLibraries.roadsAndLibraries(3, 2, 1,
                [[1, 2],
                 [3, 1],
                 [2, 3]] as int[][]) == 4
        RoadsAndLibraries.roadsAndLibraries(6, 2, 5,
                [[1, 3],
                 [3, 4],
                 [2, 4],
                 [1, 2],
                 [2, 3],
                 [5, 6]] as int[][]) == 12
    }

}
