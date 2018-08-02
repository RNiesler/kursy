package tasks.g4g;

public class CountAbcStrings {
    public static int countAbc(int n) {
        int[][] memo = new int[3][n]; // [0] 1 1 - [1] 2 1 - [2] 2 0
        return countAbcRecursive(n, 2, 1, memo);
    }

    private static int countAbcRecursive(int n, int remainingC, int remainingB, int[][] memo) {
        if (n == 0) {
            return 1;
        } else if (remainingC == 0 && remainingB == 0) {
            return 1;
        } else if (remainingC == 0) {
            return n + 1; // n places to put b and one string without b
        } else if (remainingB == 0) {
            if (remainingC == 1) {
                return n + 1; // n places to put c and one string without c
            } else if (memo[2][n - 1] != 0) {
                return memo[2][n - 1];
            } else {
                int result = countAbcRecursive(n - 1, remainingC, 0, memo) +
                        countAbcRecursive(n - 1, remainingC - 1, 0, memo);
                memo[2][n - 1] = result;
                return result;
            }
        } else {
            int memoInd = remainingC - 1;
            if (memo[memoInd][n - 1] != 0) {
                return memo[memoInd][n - 1];
            } else {
                int result = countAbcRecursive(n - 1, remainingC, remainingB, memo) +
                        countAbcRecursive(n - 1, remainingC - 1, remainingB, memo) +
                        countAbcRecursive(n - 1, remainingC, remainingB - 1, memo);
                memo[memoInd][n - 1] = result;
                return result;
            }
        }

    }
}
