package tasks.hackerrank;

import java.util.Arrays;

public class CoinChange {
    static long getWays(final int n, final int[] c) {
        long[][] memo = new long[c.length][n];
        for (int i = 0; i < c.length; i++) {
            Arrays.fill(memo[i], -1);
        }
        return getWaysRec(n, c, 0, memo);
    }

    static long getWaysRec(final int n, final int[] c, final int cMin, long[][] memo) {
        if (n == 0) {
            return 1;
        } else if (n < 0 || cMin == c.length) {
            return 0;
        } else if (memo[cMin][n - 1] != -1) {
            return memo[cMin][n - 1];
        } else {
            long result = getWaysRec(n - c[cMin], c, cMin, memo) + getWaysRec(n, c, cMin + 1, memo);
            memo[cMin][n - 1] = result;
            return result;
        }
    }

    static long getWaysIter(final int n, final int[] c) {
        long[][] result = new long[c.length+1][n + 1];
        for(int i=0; i<=c.length; i++) {
            result[i][0] = 1; // whenever the n == 0
        }

        for(int rest=1; rest<=n; rest++) {
            for(int ci = 1; ci<=c.length; ci++) {
                int coin = c[ci-1];
                result[ci][rest] = result[ci-1][rest];
                if(coin <= rest) {
                    result[ci][rest] += result[ci][rest - coin];
                }
            }
        }
        return result[c.length][n];
    }
}
