package tasks.hackerrank;

import java.util.Arrays;
import java.util.Objects;

public class CommonChild {
    static int commonChild(String s1, String s2) {
        Objects.requireNonNull(s1);
        Objects.requireNonNull(s2);
        int[][] memo = new int[s1.length()][s2.length()];
        for (int i = 0; i < memo.length; i++) {
            Arrays.fill(memo[i], -1);
        }
        return commonChildRecursive(s1, s2, 0, 0, memo);
    }

    private static int commonChildRecursive(final String s1, final String s2,
                                            final int pos1, final int pos2,
                                            final int[][] memo) {
        if (pos1 == s1.length() || pos2 == s2.length()) {
            return 0;
        } else if (memo[pos1][pos2] != -1) {
            return memo[pos1][pos2];
        } else {
            int maxChildLength = 0;
            if (s1.charAt(pos1) == s2.charAt(pos2)) {
                maxChildLength = 1 + commonChildRecursive(s1, s2, pos1 + 1, pos2 + 1, memo);
            }
            int temp = commonChildRecursive(s1, s2, pos1 + 1, pos2, memo); // remove from 1
            if (temp > maxChildLength) {
                maxChildLength = temp;
            }
            temp = commonChildRecursive(s1, s2, pos1, pos2 + 1, memo); // remove from 2
            if (temp > maxChildLength) {
                maxChildLength = temp;
            }
            memo[pos1][pos2] = maxChildLength;
            return maxChildLength;
        }
    }

    static int commonChildIterative(final String s1, final String s2) {
        int[][] solution = new int[s1.length() + 1][s2.length() + 1];


        for (int i1 = 1; i1 <= s1.length(); i1++) {
            for (int i2 = 1; i2 <= s2.length(); i2++) {
                char c1 = s1.charAt(i1 - 1);
                char c2 = s2.charAt(i2 - 1);
                int maxChildLength = 0;
                if (c1 == c2) {
                    maxChildLength = 1 + solution[i1 - 1][i2 - 1];
                }
                int temp = solution[i1 - 1][i2];
                if (temp > maxChildLength) {
                    maxChildLength = temp;
                }
                temp = solution[i1][i2 - 1];
                if (temp > maxChildLength) {
                    maxChildLength = temp;
                }
                solution[i1][i2] = maxChildLength;
            }
        }

        return solution[s1.length()][s2.length()];
    }
}
