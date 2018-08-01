package tasks.hackerrank

import spock.lang.Specification

class BfsSpecification extends Specification {
    void 'test case 0'() {
        setup:
        final testInput = '''2
4 2
1 2
1 3
1
3 1
2 3
2'''
        final scanner = new Scanner(new StringReader(testInput))

        expect:
        Bfs.fromInput(scanner) == '''6 6 -1
-1 6
'''
    }
}
