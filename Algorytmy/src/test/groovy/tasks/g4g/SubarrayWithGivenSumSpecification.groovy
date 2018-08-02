package tasks.g4g

import spock.lang.Specification

class SubarrayWithGivenSumSpecification extends Specification {
    void 'g4g 1'() {
        setup:
        final input = [1, 4, 20, 3, 10, 5] as int[]
        when:
        final pair = SubarrayWithGivenSum.findSubarrayWithGivenSum(input, 33).get()
        then:
        pair.start == 2
        pair.end == 4
    }

    void 'g4g 2'() {
        setup:
        final input = [1, 4, 0, 0, 3, 10, 5] as int[]
        when:
        final pair = SubarrayWithGivenSum.findSubarrayWithGivenSum(input, 7).get()
        then:
        pair.start == 1
        pair.end == 4
    }

    void 'g4g empty'() {
        setup:
        final input = [1, 4] as int[]
        expect:
        !SubarrayWithGivenSum.findSubarrayWithGivenSum(input, 0).isPresent()
    }
}
