package tasks.hackerrank

import spock.lang.Specification

class HighestValuePalindromeSpecification extends Specification {
    void 'testcase 3'() {
        setup:
        final s = '932239'
        expect:
        HighestValuePalindrome.highestValuePalindrome(s, s.length(), 2) == '992299'
    }

    void 'testcase 2'() {
        setup:
        final s = '5'
        expect:
        HighestValuePalindrome.highestValuePalindrome(s, s.length(), 1) == '9'
    }
}
