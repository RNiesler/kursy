package tasks.hackerrank

import spock.lang.Specification

class DynamicProgrammingCoinsSpecification extends Specification {
    void 'test 9'() {
        setup:
        final coins = [41, 34, 46, 9, 37, 32, 42, 21, 7, 13, 1, 24, 3, 43, 2, 23, 8, 45, 19, 30, 29, 18, 35, 11] as int[]
        Arrays.sort(coins)
        final sum = 250
        expect:
        DynamicProgrammingCoins.ways(sum, coins) == 15685693751
    }

    void 'test 10'() {
        setup:
        final coins = [8, 47, 13, 24, 25, 31, 32, 35, 3, 19, 40, 48, 1, 4, 17, 38, 22, 30, 33, 15, 44, 46, 36, 9, 20, 49] as int[]
        final sum = 250
        expect:
        DynamicProgrammingCoins.ways(sum, coins) == 3542323427
    }

    void 'test case 1'() {
        setup:
        final coins = [2, 5, 3, 6] as int[]
        final sum = 10
        expect:
        DynamicProgrammingCoins.ways(sum, coins) == 5
    }

    void 'test case 0'() {
        setup:
        final coins = [1, 2, 3] as int[]
        final sum = 4
        expect:
        DynamicProgrammingCoins.ways(sum, coins) == 4
    }

    void 'test 9 iter'() {
        setup:
        final coins = [41, 34, 46, 9, 37, 32, 42, 21, 7, 13, 1, 24, 3, 43, 2, 23, 8, 45, 19, 30, 29, 18, 35, 11] as int[]
        Arrays.sort(coins)
        final sum = 250
        expect:
        DynamicProgrammingCoins.waysIter(sum, coins) == 15685693751
    }

    void 'test 10 iter'() {
        setup:
        final coins = [8, 47, 13, 24, 25, 31, 32, 35, 3, 19, 40, 48, 1, 4, 17, 38, 22, 30, 33, 15, 44, 46, 36, 9, 20, 49] as int[]
        final sum = 250
        expect:
        DynamicProgrammingCoins.waysIter(sum, coins) == 3542323427
    }

    void 'test case 1 iter'() {
        setup:
        final coins = [2, 5, 3, 6] as int[]
        final sum = 10
        expect:
        DynamicProgrammingCoins.waysIter(sum, coins) == 5
    }

    void 'test case 0 iter'() {
        setup:
        final coins = [1, 2, 3] as int[]
        final sum = 4
        expect:
        DynamicProgrammingCoins.waysIter(sum, coins) == 4
    }
}
