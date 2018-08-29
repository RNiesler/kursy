package tasks.hackerrank;

public class SherlockAndAnagrams {
    private static final int ALPHABET_SIZE = 26;

    // Complete the sherlockAndAnagrams function below.
    static int sherlockAndAnagrams(String s) {
        int count = 0;
        for (int length = 1; length < s.length(); length++) {
            for (int i = 0; i < s.length() - length; i++) {
                for (int j = i + 1; j < s.length() - length + 1; j++) {
                    if (isAnagram(s, i, j, length)) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    private static boolean isAnagram(String s, int start1, int start2, int length) {
        int[] charCount = new int[26];
        for (int i = start1; i < start1 + length; i++) {
            char c = s.charAt(i);
            charCount[c - 'a']++;
        }

        for (int i = start2; i < start2 + length; i++) {
            char c = s.charAt(i);
            charCount[c - 'a']--;
        }

        for (int i = 0; i < ALPHABET_SIZE; i++) {
            if (charCount[i] != 0) {
                return false;
            }
        }
        return true;
    }
}
