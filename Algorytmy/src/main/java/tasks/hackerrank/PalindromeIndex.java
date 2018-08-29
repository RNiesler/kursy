package tasks.hackerrank;

import java.util.Objects;

public class PalindromeIndex {
    static int palindromeIndex(String s) {
        Objects.requireNonNull(s);
        int start = 0;
        int end = s.length() - 1;
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                if (isPalindrome(s, start + 1, end)) {
                    return start;
                } else if (isPalindrome(s, start, end - 1)) {
                    return end;
                } else {
                    return -1;
                }
            } else {
                start++;
                end--;
            }
        }
        return -1;
    }

    private static boolean isPalindrome(String s, int start, int end) {
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}
