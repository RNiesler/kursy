package tasks.g4g;

public class WordBreak {
    public interface Dictionary {
        boolean isWord(String word);
    }

    public static boolean wordBreak(String str, Dictionary dict) {
        Boolean[] memo = new Boolean[str.length()];
        return wordBreakRecursive(str, 0, dict, memo);
    }

    private static boolean wordBreakRecursive(String str, int start, Dictionary dict, Boolean[] memo) {
        if (start >= str.length()) {
            return true;
        } else if (memo[start] != null) {
            return memo[start];
        } else {
            int prefixEnd = start + 1;
            while (prefixEnd <= str.length() && dict.isWord(str.substring(start, prefixEnd))) {
                prefixEnd++;
            }
            if (prefixEnd > str.length()) {
                memo[start] = false;
                return false;
            } else {
                boolean result = wordBreakRecursive(str, prefixEnd + 1, dict, memo);
                memo[start] = result;
                return result;
            }
        }
    }

    public static boolean wordBreakIterative(String str, Dictionary dict) {
        boolean[] solution = new boolean[str.length() + 1];
        for (int i = str.length() - 1; i >= 0; i--) {
            if (dict.isWord(str.substring(i))) { // full suffix is a word
                solution[i] = true;
            } else {
                int j = i + 1;
                while (j <= str.length() && !solution[i]) {
                    if (solution[j] && dict.isWord(str.substring(i, j))) {
                        solution[i] = true;
                    }
                    j++;
                }
            }
        }
        return solution[0];
    }
}
