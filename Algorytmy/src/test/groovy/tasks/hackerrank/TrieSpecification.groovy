package tasks.hackerrank

import spock.lang.Specification

class TrieSpecification extends Specification {
    void 'test case 0'() {
        setup:
        final trie = new Trie();
        trie.add('hack')
        trie.add('hackerrank')

        expect:
        trie.countCompleteWordsStartingWith('hac') == 2
        trie.countCompleteWordsStartingWith('hak') == 0
    }
}
