package tasks.g4g

import spock.lang.Specification

class BinaryStringsSpecification extends Specification {
    void 'g4g'() {
        setup:
        final str = '1??0?101'
        when:
        final result = BinaryStrings.generateBinaryStringsEfficient(str)
        then:
        result.size() == 8
        result.containsAll([
                '10000101',
                '10001101',
                '10100101',
                '10101101',
                '11000101',
                '11001101',
                '11100101',
                '11101101'
        ])
    }
}
