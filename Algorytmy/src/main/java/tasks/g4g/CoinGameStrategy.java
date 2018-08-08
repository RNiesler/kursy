package tasks.g4g;

public class CoinGameStrategy {
    public static int maxWin(int[] coins) {
        int[][] memo = new int[coins.length][coins.length];
        return maxWinRecursive(coins, 0, coins.length - 1, true)[0];
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
