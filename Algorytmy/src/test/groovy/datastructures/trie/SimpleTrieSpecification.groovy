package datastructures.trie

import spock.lang.Specification

class SimpleTrieSpecification extends Specification {
    static class StringSplitter implements Trie.Splitter<String, Character> {

        @Override
        Iterable<Character> split(String str) {
            ArrayList<Character> arrayList = new ArrayList<>(str.length())
            str.toCharArray().each { arrayList.add(it) }
            return arrayList
        }

        @Override
        String join(Iterable<Character> segments) {
            ArrayList<Character> arrayList = new ArrayList<>(segments)
            return new String(arrayList.toArray(new char[0]))
        }
    }

    StringSplitter splitter

    void setup() {
        splitter = new StringSplitter()
    }

    void 'test contains'() {
        setup:
        def trie = new SimpleTrie<String, Character>(splitter)

        when:
        trie.put('abc')
        then:
        trie.contains('abc')

        when:
        trie.put('abcdef')
        then:
        trie.contains('abc')
        trie.contains('abcdef')
    }

    void 'test prefix'() {
        setup:
        def trie = new SimpleTrie<String, Character>(splitter)

        when:
        trie.put('abc')
        then:
        trie.hasPrefix('abc')
        trie.hasPrefix('ab')
        trie.hasPrefix('a')
        trie.hasPrefix('')
        !trie.hasPrefix('b')
        !trie.hasPrefix('bc')
        !trie.hasPrefix('c')
    }

    void 'test get'() {
        setup:
        def trie = new SimpleTrie<String, Character>(splitter)

        when:
        trie.put('abc')
        then:
        trie.get('abc').isPresent()
        !trie.get('ab').isPresent()
    }

    void 'test size'() {
        setup:
        def trie = new SimpleTrie<String, Character>(splitter)

        expect:
        0 == trie.size()

        when:
        trie.put('abc')
        then:
        1 == trie.size()

        when:
        trie.put('de')
        then:
        2 == trie.size()

        when:
        trie.put('abcd')
        then:
        3 == trie.size()

        when:
        trie.put('abc')
        then:
        3 == trie.size()
    }
}
