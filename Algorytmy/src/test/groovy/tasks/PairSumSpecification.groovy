package tasks

import spock.lang.Specification
import tasks.pairsum.PairSum

class PairSumSpecification extends Specification {
    void 'sorted test'() {
        setup:
        final array = [1, 2, 4, 5, 7, 9, 10] as int[]
        when:
        final result = PairSum.pairSumSorted(array, 10)
        then:
        result.size() == 1
        result.first().i1 == 0
        result.first().i2 == 5
    }

    void 'sorted test no pair'() {
        setup:
        final array = [2, 4, 5, 7, 9, 10] as int[]
        when:
        final result = PairSum.pairSumSorted(array, 10)
        then:
        result.size() == 0
    }

    void 'sorted test many'() {
        setup:
        final array = [1, 2, 3, 4] as int[]
        when:
        final result = PairSum.pairSumSorted(array, 5)
        then:
        result.size() == 2
        result.find { it.i1 == 0}.i2 == 3
        result.find { it.i1 == 1}.i2 == 2
    }

    void 'unsorted'() {
        setup:
        final list = [1, 2, 4, 5, 7, 9, 10]
        Collections.shuffle(list)
        final array = list as int[]
        when:
        final result = PairSum.pairSumUnsorted(array, 10)
        then:
        result.size() == 1
        result.first().i1 == array.findIndexOf {  it == 1 } || result.first().i1 == array.findIndexOf { it == 9 }
        result.first().i2 == array.findIndexOf { it == 1 } || result.first().i2 == array.findIndexOf { it == 9 }
    }
}
