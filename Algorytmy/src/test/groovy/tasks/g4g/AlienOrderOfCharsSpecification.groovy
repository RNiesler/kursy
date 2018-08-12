package tasks.g4g

import spock.lang.Specification

class AlienOrderOfCharsSpecification extends Specification {
    void 'g4g'() {
        expect:
        AlienOrderOfChars.charOrder(["baa", "abcd", "abca", "cab", "cad"] as String[]) == ['b', 'd', 'a', 'c'] as char[]
        AlienOrderOfChars.charOrder(["caa", "aaa", "aab"] as String[]) == ['c', 'a', 'b'] as char[]
    }
}
