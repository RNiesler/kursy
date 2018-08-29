package tasks.hackerrank

import spock.lang.Specification

class MaximumPalindromesSpecification extends Specification{
    void 'test case 0'() {
        setup:
        MaximumPalindromes.initialize('week')
        expect:
        MaximumPalindromes.answerQuery(1,4) == 2
        MaximumPalindromes.answerQuery(2,3) == 1
    }
    void 'test case 1'() {
        setup:
        MaximumPalindromes.initialize('abab')
        expect:
        MaximumPalindromes.answerQuery(1,4) == 2
    }
}
