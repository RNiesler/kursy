package tasks.g4g

import spock.lang.Specification

class InterleavedStringsSpecification extends Specification {
    void 'g4g'() {
        setup:
        final a = 'XXY'
        final b = 'XXZ'
        final c = 'XXZXXY'
        expect:
        InterleavedStrings.isInterleave(a, b, c)
    }


    void 'g4g for iterative'() {
        setup:
        final a = 'XXY'
        final b = 'XXZ'
        final c = 'XXZXXY'
        expect:
        InterleavedStrings.isInterleavedIterative(a, b, c)
    }
}
