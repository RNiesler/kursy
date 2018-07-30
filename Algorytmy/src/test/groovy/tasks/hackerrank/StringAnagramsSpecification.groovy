package tasks.hackerrank

import spock.lang.Specification

class StringAnagramsSpecification extends Specification {
    void 'test basic'() {
        setup:
        final a = 'cde'
        final b = 'abc'
        expect:
        StringAnagrams.makeAnagram(a, b) == 4
    }
}
