package tasks.g4g

import spock.lang.Specification

class MinPalindromeInsertSpecification extends Specification {
    void 'g4g'() {
        expect:
        MinPalindromeInsert.minPalindromeInsert('ab') == 1
        MinPalindromeInsert.minPalindromeInsert('aa') == 0
        MinPalindromeInsert.minPalindromeInsert('abcd') == 3
        MinPalindromeInsert.minPalindromeInsert('abcda') == 2
        MinPalindromeInsert.minPalindromeInsert('abcde') == 4
    }
}
