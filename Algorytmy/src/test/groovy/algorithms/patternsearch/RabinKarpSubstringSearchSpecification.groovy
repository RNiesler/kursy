package algorithms.patternsearch

import spock.lang.Specification

class RabinKarpSubstringSearchSpecification extends Specification {
    void 'simple test'() {
        setup:
        final text = 'THIS IS A TEST TEXT'
        final pattern = 'TEST'
        when:
        def matcher = new RabinKarpSubstringSearch(pattern).search(text)
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
        def matcher = new RabinKarpSubstringSearch(pattern).search(text)
        then:
        matcher.matched()
        matcher.matchIndexIterator().size() == 3
        matcher.matchIndexIterator().toList() == [0, 9, 12]
    }

    void 'many repeated subpatterns'() {
        setup:
        final text = 'ABABDABACDABABCABAB'
        final pattern = 'ABABCABAB'
        when:
        def matcher = new RabinKarpSubstringSearch(pattern).search(text)
        then:
        matcher.matched()
        matcher.matchIndexIterator().size() == 1
        matcher.matchIndexIterator().next() == 10
    }

    void 'worst for naive'() {
        setup:
        final text = 'AAAAAAAAAAAAAAAAAB'
        final pattern = 'AAAAB'
        when:
        def matcher = new RabinKarpSubstringSearch(pattern).search(text)
        then:
        matcher.matched()
        matcher.matchIndexIterator().size() == 1
        matcher.matchIndexIterator().next() == 13
    }

    void 'bad for naive'() {
        setup:
        final text = 'ABABABCABABABCABABABC'
        final pattern = 'ABABAC'
        when:
        def matcher = new RabinKarpSubstringSearch(pattern).search(text)
        then:
        !matcher.matched()
    }
}
