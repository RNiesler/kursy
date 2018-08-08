package tasks.g4g;

import java.util.Arrays;

public class MinPalindromeInsert {
    public static int minPalindromeInsert(String str) {
        int memo[][] = new int[str.length()][str.length()];
        for (int i = 0; i < str.length(); i++) {
            Arrays.fill(memo[i], -1);
        }
        return minPalindromeInsertRecursive(str, 0, str.length() - 1, memo);
    }

    private static int minPalindromeInsertRecursive(String str, int first, int last, int[][] memo) {
        if (first >= last) {
            return 0;
        } else if (memo[first][last] != -1) {
            return memo[first][last];
        } else {
            int minInsert = Integer.MAX_VALUE;
            if (str.charAt(first) == str.charAt(last)) {
                minInsert = minPalindromeInsertRecursive(str, first + 1, last - 1, memo);
            }
            // add to the front char that's matching the last
            int minWhenAddedToFront = minPalindromeInsertRecursive(str, first, last - 1, memo);
            if (minWhenAddedToFront < minInsert - 1) {
                minInsert = minWhenAddedToFront + 1;
            }
            // add to the back char that's matching the first
            int minWhenAddedToBack = minPalindromeInsertRecursive(str, first + 1, last, memo);
            if (minWhenAddedToBack < minInsert - 1) {
                minInsert = minWhenAddedToBack + 1;
            }
            memo[first][last] = minInsert;
            return minInsert;
        }
    }
}
