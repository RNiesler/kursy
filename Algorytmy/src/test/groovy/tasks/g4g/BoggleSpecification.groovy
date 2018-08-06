package tasks.g4g

import spock.lang.Specification

class BoggleSpecification extends Specification{
    class DictionaryImpl implements Boggle.Dfs.Dictionary {
        Set<String> dict

        @Override
        boolean isWord(String word) {
            return dict.contains(word)
        }
    }
    void 'g4g'() {
        setup:
        final dictionary = new DictionaryImpl()
        dictionary.dict = ["GEEKS", "FOR", "QUIZ", "GO"] as Set<String>
        final board = [['G','I','Z'] as char[],
            ['U','E','K'] as char[],
            ['Q','S','E'] as char[]] as char[][]
        final solver = new Boggle.Dfs(board, dictionary)

        when:
        final result = solver.solve()
        then:
        result == ['GEEKS', 'QUIZ'] as Set<String>

    }


    void 'trie'() {
        setup:
        final dictionary = ["GEEKS", "FOR", "QUIZ", "GO"] as String[]
        final board = [['G','I','Z'] as char[],
                       ['U','E','K'] as char[],
                       ['Q','S','E'] as char[]] as char[][]
        final solver = new Boggle.BoggleTrie(board, dictionary)

        when:
        final result = solver.solve()
        then:
        result == ['GEEKS', 'QUIZ'] as Set<String>

    }
}
