package tasks.longestword

import spock.lang.Specification

class LongestWordSpecification extends Specification {

    void 'test simple'() {
        expect:
        'apple' == LongestWord.longestWord(['ale', 'apple', 'monkey', 'plea'], 'abpcplea')
        'geeksgeeks' == LongestWord.longestWord(['pintu', 'geeksfor', 'geeksgeeks', 'forgeek'], 'geeksforgeeks')
    }
}
