package tasks.hackerrank

import spock.lang.Specification

class EqualSpecification extends Specification {
    void 'test case 1'() {
        expect:
        Equal.equal([2, 2, 3, 7] as int[]) == 2
    }

    void 'test case 11'() {
        expect:
        Equal.equal([1, 5, 5] as int[]) == 3
    }

    void 'test case 12'() {
        expect:
        Equal.equal([1, 5, 5, 10, 10] as int[]) == 7
    }
}
//
//
//2 5 5 5
//2 5 5 0
//2 5 0 0
//2 0 0 0
//0 0 0 0
//
//4
//
//2 5 5 5
//3 5 8 8
//5 5 10 10
//10 10 15 10
//15 15 15 15 4
//
//2 2 3 7
//2 2 3 2
//2 2 2 2 2
//
//10 7 12
//10 7 7
//8 7 7
//7 7 7
//
// 1 5 5
// 1 5 0
// 1 0 0
// 0 0 0 3