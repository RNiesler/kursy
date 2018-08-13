package tasks.g4g

import spock.lang.Specification

class AllWordBreaksSpecification extends Specification{
    void 'g4g'() {
        setup:
        final dictionary = new AllWordBreaks.Dictionary() {

            @Override
            boolean isWord(String word) {
                return word in ['i', 'like', 'sam', 'sung', 'samsung', 'mobile', 'ice',
                                'cream', 'icecream', 'man', 'go', 'mango', 'and']
            }
        }

        when:
        final result = AllWordBreaks.wordBreaks('ilikesamsungmobile', dictionary)
        then:
        'i like sam sung mobile' in result
        'i like samsung mobile' in result

        when:
        final result2 = AllWordBreaks.wordBreaks('ilikeicecreamandmango', dictionary)
        then:
        'i like ice cream and man go' in result2
        'i like ice cream and mango' in result2
        'i like icecream and man go' in result2
        'i like icecream and mango' in result2
    }
}
