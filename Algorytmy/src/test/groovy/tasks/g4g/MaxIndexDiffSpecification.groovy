package tasks.g4g

import spock.lang.Specification

class MaxIndexDiffSpecification extends Specification {
    void 'g4g'() {
        expect:
        MaxIndexDiff.maxIndexDiff([34, 8, 10, 3, 2, 80, 30, 33, 1] as int[]) == 6
        MaxIndexDiff.maxIndexDiff([9, 2, 3, 4, 5, 6, 7, 8, 18, 0] as int[]) == 8
        MaxIndexDiff.maxIndexDiff([1, 2, 3, 4, 5, 6] as int[]) == 5
        MaxIndexDiff.maxIndexDiff([6, 5, 4, 3, 2, 1] as int[]) == -1
    }
}
