package tasks.hackerrank;

import java.util.Objects;

public class Abbreviation {
    private static final int CASE_DIFF = 'a' - 'A';

    static boolean abbreviation(String src, String target) {
        Boolean[][] memo = new Boolean[src.length() + 1][target.length() + 1];
        return abbreviationRecursive(src, target, 0, 0, memo);
    }

    private static boolean abbreviationRecursive(final String input, final String target,
                                                 int inSrc, int inTarget,
                                                 final Boolean[][] memo) {
        if (inSrc == input.length() && inTarget == target.length()) {
            return true;
        } else if (inSrc == input.length()) {
            return false;
        } else if (memo[inSrc][inTarget] != null) {
            return memo[inSrc][inTarget];
        } else if (inTarget == target.length()) {
            while (inSrc < input.length() && !isCapital(input.charAt(inSrc)))
                inSrc++;
            boolean result = (inSrc == input.length());
            memo[inSrc][inTarget] = result;
            return result;
        } else {
            char srcChar = input.charAt(inSrc);
            char targetChar = target.charAt(inTarget);
            boolean result = false;
            if (isCapital(srcChar)) {
                if (srcChar == targetChar) {
                    result = abbreviationRecursive(input, target, inSrc + 1, inTarget + 1, memo);
                } else {
                    result = false;
                }
            } else {
                // try with deleting srcChar
                if (abbreviationRecursive(input, target, inSrc + 1, inTarget, memo)) {
                    result = true;
                } else if (toCapital(srcChar) == targetChar) {
                    // try with capitalizing
                    result = abbreviationRecursive(input, target, inSrc + 1, inTarget + 1, memo);
                }

            }
            memo[inSrc][inTarget] = result;
            return result;
        }
    }

    private static char toCapital(char c) {
        return (char) (c - CASE_DIFF);
    }

    //!! fails for bBC <-> BBC
    public static boolean canTransformLinear(final String src, final String target) {
        Objects.requireNonNull(src);
        Objects.requireNonNull(target);
        boolean[] capitalMatched = new boolean[target.length()];
        int inSrc = 0;
        int inTarget = 0;
        while (inSrc < src.length() && inTarget < target.length()) {
            char srcChar = src.charAt(inSrc);
            char targetChar = target.charAt(inTarget);
            if (isCapital(srcChar)) {
                if (srcChar == targetChar) {
                    inSrc++;
                    capitalMatched[inTarget] = true;
                }
                inTarget++;
            } else {
                inSrc++;
            }
        }

        inSrc = 0;
        inTarget = 0;
        while (inSrc < src.length() && inTarget < target.length()) {
            char srcChar = src.charAt(inSrc);
            char targetChar = target.charAt(inTarget);
            if (capitalMatched[inTarget]) {
                if (srcChar == targetChar) {
                    inTarget++;
                }
                inSrc++;
            } else if (srcChar - CASE_DIFF == targetChar) {
                inSrc++;
                inTarget++;
            } else {
                inSrc++;
            }
        }
        while (inSrc < src.length() && !isCapital(src.charAt(inSrc)))
            inSrc++;
        return inTarget == target.length() && inSrc == src.length();
    }

    private static boolean isCapital(char c) {
        return c < 'a';
    }
}
