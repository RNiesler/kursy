package tasks.g4g

import spock.lang.Specification

//Given a set of time intervals in any order, merge all overlapping intervals into one and output the result which should have only mutually exclusive intervals.
// Let the intervals be represented as pairs of integers for simplicity.
class MergeOverlappingIntervalsSpecification extends Specification {
    void 'g4g'() {
        setup:
        final input = [[1, 3], [2, 4], [5, 7], [6, 8]] as int[][]

        expect:
        MergeOverlappingIntervals.mergeIntervals(input) == [[1, 4], [5, 8]] as List<int[]>

    }
}
