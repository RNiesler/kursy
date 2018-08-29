package tasks.hackerrank

import spock.lang.Specification

class LongestCommonSubsequenceSpecification extends Specification {
    void 'test case 0 recursive'() {
        expect:
        LongestCommonSubsequence.longestCommonSubsequenceRecursive([1, 2, 3, 4, 1] as int[], [3, 4, 1, 2, 1, 3] as int[]) == [3, 4, 1]
    }

    void 'test case 0 dynamic length'() {
        expect:
        LongestCommonSubsequence.longestCommonSubsequenceLength([1, 2, 3, 4, 1] as int[], [3, 4, 1, 2, 1, 3] as int[]) == 3
    }

    void 'test case 0 dynamic'() {
        expect:
        LongestCommonSubsequence.longestCommonSubsequence([1, 2, 3, 4, 1] as int[], [3, 4, 1, 2, 1, 3] as int[]) == [3, 4, 1] as int[]
    }

}
