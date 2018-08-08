package tasks.g4g;

public class CoinGameStrategy {
    public static int maxWin(int[] coins) {
        int[][] memo = new int[coins.length][coins.length];
        return maxWinDynamic(coins, 0, coins.length - 1, memo);
    }

    private static int maxWinDynamic(int[] coins, int first, int last, int[][] memo) {
        if (first > last) {
            return 0;
        } else if (first == last) {
            return coins[first];
        } else if (memo[first][last] != 0) {
            return memo[first][last];
        } else {
            // we choose first
            int maxWithFirst = coins[first] + Math.min(
                    // opponent chooses first as well
                    maxWinDynamic(coins, first + 2, last, memo),
                    //opponent chooses last
                    maxWinDynamic(coins, first + 1, last - 1, memo)
            );
            // we choose last
            int maxWithLast = coins[last] + Math.min(
                    // opponent chooses first
                    maxWinDynamic(coins, first + 1, last - 1, memo),
                    // opponent chooses last as well
                    maxWinDynamic(coins, first, last - 2, memo)
            );
            int result = Math.max(maxWithFirst, maxWithLast);
            memo[first][last] = result;
            return result;
        }
    }

    private static int[] maxWinRecursive(int[] coins, int first, int last, boolean firstPlayer) {
        if (first > last) {
            return new int[]{0, 0};
        } else if (first == last) {
            int[] result = new int[2];
            result[firstPlayer ? 0 : 1] = coins[first];
            return result;
        } else {
            int[] maxWithFirst = maxWinRecursive(coins, first + 1, last, !firstPlayer);
            int[] maxWithLast = maxWinRecursive(coins, first, last - 1, !firstPlayer);
            int resultInd = firstPlayer ? 0 : 1;
            if (maxWithFirst[resultInd] + coins[first] > maxWithLast[resultInd] + coins[last]) {
                maxWithFirst[resultInd] += coins[first];
                return maxWithFirst;
            } else {
                maxWithLast[resultInd] += coins[last];
                return maxWithLast;
            }
        }
    }
}
