package tasks.hackerrank;

import java.util.Objects;

public class DynamicProgrammingCoins {
    static long ways(int n, int[] coins) {
        Objects.requireNonNull(coins);
        if (coins.length == 0) {
            return 0;
        }
        long[][] cache = new long[coins.length][n];
        return waysRecursive(n, coins, 0, cache);
    }

    static long waysRecursive(int n, int[] coins, int minCoinIndex, long[][] cache) {
        if (n < 0) {
            return 0;
        } else if (n == 0) {
            return 1;
        } else if (cache[minCoinIndex][n - 1] != 0) {
            return cache[minCoinIndex][n - 1];
        } else {
            long sum = 0;
            for (int i = minCoinIndex; i < coins.length; i++) {
                sum += waysRecursive(n - coins[i], coins, i, cache);
            }
            cache[minCoinIndex][n - 1] = sum;
            return sum;
        }
    }
}
