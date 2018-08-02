package tasks.g4g

import spock.lang.Specification

class LongestWordSpecification extends Specification {
    void 'g4g'() {
        setup:
        final dictionary = ["ale", "apple", "monkey", "plea"]
        expect:
        LongestWord.longestWord(dictionary, "abpcplea").get() == "apple"
    }


    void 'g4g 2'() {
        setup:
        final dictionary = ["pintu", "geeksfor", "geeksgeeks", "forgeek"]
        expect:
        LongestWord.longestWord(dictionary, "geeksforgeeks").get() == "geeksgeeks"
    }
}
