package tasks.g4g

import spock.lang.Specification

class WordBreakSpecification extends Specification {
    void 'g4g'() {
        setup:
        final dict = new WordBreak.Dictionary() {
            @Override
            boolean isWord(String word) {
                return word in ['i', 'like', 'sam', 'sung', 'samsung', 'mobile', 'ice', 'cream', 'icecream', 'man', 'go', 'mango']
            }
        }
        expect:
        WordBreak.wordBreak('ilike', dict)
    }


    void 'g4g iterative'() {
        setup:
        final dict = new WordBreak.Dictionary() {
            @Override
            boolean isWord(String word) {
                return word in ['i', 'like', 'sam', 'sung', 'samsung', 'mobile', 'ice', 'cream', 'icecream', 'man', 'go', 'mango']
            }
        }
        expect:
        WordBreak.wordBreakIterative('ilike', dict)
    }
}
