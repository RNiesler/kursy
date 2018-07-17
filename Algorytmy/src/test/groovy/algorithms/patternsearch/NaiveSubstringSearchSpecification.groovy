package algorithms.patternsearch

import spock.lang.Specification

class NaiveSubstringSearchSpecification extends Specification {
    void 'simple test'() {
        setup:
        final text = 'THIS IS A TEST TEXT'
        final pattern = 'TEST'
        when:
        def matcher = new NaiveSubstringSearch(pattern).search(text)
        then:
        matcher.matched()
        matcher.matchIndexIterator().size() == 1
        matcher.matchIndexIterator().next() == 10
        matcher.matchIterator().size() == 1
        matcher.matchIterator().next() == 'TEST'
    }

    void 'match at beginning and finish'() {
        setup:
        final text = "AABAACAADAABAABA"
        final pattern = "AABA"
        when:
        def matcher = new NaiveSubstringSearch(pattern).search(text)
        then:
        matcher.matched()
        matcher.matchIndexIterator().size() == 3
        matcher.matchIndexIterator().toList() == [0, 9, 12]

    }
}
