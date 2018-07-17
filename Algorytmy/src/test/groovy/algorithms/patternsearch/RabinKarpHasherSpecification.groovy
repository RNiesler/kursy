package algorithms.patternsearch

import spock.lang.Specification

class RabinKarpHasherSpecification extends Specification {
    def 'hasher test'() {
        setup:
        final test = 'test'
        final atest = 'atest'
        final hasher = new RabinKarpSubstringSearch.RabinKarpHasher(test)
        when:
        final testHash = hasher.hash(test, 0, test.length())
        final atesHash = hasher.hash('ates', 0, 4)
        then:
        testHash == hasher.rehash(atesHash, atest, 0, 4)
    }
}
