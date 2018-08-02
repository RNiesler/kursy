package tasks.g4g

import spock.lang.Specification

class CountAbcStringSpecification extends Specification {
    void 'g4g'() {
        expect:
        CountAbcStrings.countAbc(3) == 19
        CountAbcStrings.countAbc(4) == 39
    }
}
