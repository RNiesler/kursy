package tasks.g4g

import spock.lang.Specification

class LongestSubstringSpecification extends Specification {
    void 'g4g'() {
        expect:
        LongestSubstring.longestSubstrings('aabbcc', 1) == ['aa', 'bb', 'cc']
        LongestSubstring.longestSubstrings('aabbcc', 2) == ['aabb', 'bbcc']
        LongestSubstring.longestSubstrings('aabbcc', 3) == ['aabbcc']
        LongestSubstring.longestSubstrings('aaabbb', 3) == []
    }
}
