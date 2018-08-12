package tasks.g4g

import spock.lang.Specification

class LongestValidSubstringSpecification extends Specification{
    void 'g4g'() {
        expect:
        LongestValidSubstring.lengthOfLongestValidSubstring('((()') == 2
        LongestValidSubstring.lengthOfLongestValidSubstring(')()())') == 4
        LongestValidSubstring.lengthOfLongestValidSubstring('()(()))))') == 6
    }
}
