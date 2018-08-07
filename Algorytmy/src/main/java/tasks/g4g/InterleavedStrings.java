package tasks.g4g;

public class InterleavedStrings {
    static boolean isInterleave(String a, String b, String c) {
        Boolean[][] memo = new Boolean[a.length() + 1][b.length() + 1];
        return isInterleaveRecursive(a, 0, b, 0, c, 0, memo);
    }

    private static boolean isInterleaveRecursive(String a, int aPos, String b, int bPos, String c, int cPos, Boolean[][] memo) {
        if (memo[aPos][bPos] != null) {
            return memo[aPos][bPos];
        } else {
            boolean result;
            if (cPos == c.length()) {
                result = aPos == a.length() && bPos == b.length();
            } else if (aPos == a.length() || bPos == b.length()) {
                while (cPos < c.length() && bPos < b.length() && c.charAt(cPos) == b.charAt(bPos)) {
                    cPos++;
                    bPos++;
                }
                while (cPos < c.length() && aPos < a.length() && c.charAt(cPos) == a.charAt(aPos)) {
                    cPos++;
                    aPos++;
                }
                result = aPos == a.length() && bPos == b.length() && cPos == c.length();
            } else if (a.charAt(aPos) == c.charAt(cPos)) {
                if (b.charAt(bPos) == c.charAt(cPos)) {
                    // check with a first
                    if (isInterleaveRecursive(a, aPos + 1, b, bPos, c, cPos + 1, memo)) {
                        result = true;
                    } else {
                        // check with b
                        result = isInterleaveRecursive(a, aPos, b, bPos + 1, c, cPos + 1, memo);
                    }
                } else {
                    // only a matches
                    result = isInterleaveRecursive(a, aPos + 1, b, bPos, c, cPos + 1, memo);
                }
            } else if (b.charAt(bPos) == c.charAt(cPos)) {
                // only b matches
                result = isInterleaveRecursive(a, aPos, b, bPos + 1, c, cPos + 1, memo);
            } else { // mismatch in characters
                result = false;
            }
            memo[aPos][bPos] = result;
            return result;
        }
    }

    public static boolean isInterleavedIterative(String a, String b, String c) {
        if (a.length() + b.length() != c.length()) {
            return false;
        }
        boolean solution[][] = new boolean[a.length() + 1][b.length() + 1];

        // when all strings are empty
        solution[0][0] = true;

        // when a is empty
        for (int i = 0; i < b.length(); i++) {
            solution[0][i + 1] = (b.charAt(i) == c.charAt(i));
        }

        //when b is empty
        for (int i = 0; i < a.length(); i++) {
            solution[i + 1][0] = (a.charAt(i) == c.charAt(i));
        }

        for (int aPos = 1; aPos <= a.length(); aPos++) {
            for (int bPos = 1; bPos <= b.length(); bPos++) {
                int cPos = aPos + bPos - 1;
                char cc = cPos  < c.length() ? c.charAt(cPos) : 0; // the 0 will never be used
                if (aPos <= a.length() && a.charAt(aPos-1) == cc) {
                    if (bPos <= b.length() && b.charAt(bPos-1) == cc) {
                        // a and b match
                        solution[aPos][bPos] = solution[aPos - 1][bPos] || solution[aPos][bPos - 1];
                    } else {
                        // a matches
                        solution[aPos][bPos] = solution[aPos - 1][bPos];
                    }
                } else if (bPos <= b.length() && b.charAt(bPos-1) == cc) {
                    solution[aPos][bPos] = solution[aPos][bPos - 1];
                }
                // no else needed as all are initialized to false
            }
        }
        return solution[a.length()][b.length()];
    }
}
