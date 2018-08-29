package tasks.hackerrank;

public class HighestValuePalindrome {
    // Complete the highestValuePalindrome function below.
    static String highestValuePalindrome(String s, int n, int allowedChanges) {
        int leftN = n / 2 + n % 2;
        char[] leftChars = new char[leftN];
        boolean[] singleChange = new boolean[leftN];
        if (n % 2 == 1) {
            singleChange[leftN - 1] = true;
        }
        for (int i = 0; i < leftN; i++) {
            char left = s.charAt(i);
            char right = s.charAt(s.length() - i - 1);
            if (left != right) {
                singleChange[i] = true;
                allowedChanges--;
                leftChars[i] = left < right ? right : left;
            } else {
                leftChars[i] = left;
            }
        }
        if (allowedChanges < 0) {
            return "-1";
        }
        int i = 0;
        while (allowedChanges > 0 && i < leftN) {
            if (leftChars[i] != '9') {
                if (singleChange[i]) {
                    leftChars[i] = '9';
                    allowedChanges--;
                } else if (allowedChanges >= 2) {
                    leftChars[i] = '9';
                    allowedChanges -= 2;
                }
            }
            i++;
        }

        char[] palindrome = new char[s.length()];
        System.arraycopy(leftChars, 0, palindrome, 0, leftN);
        for (int x = n % 2, j = leftN; x < leftN; x++, j++) {
            palindrome[j] = leftChars[leftN - x - 1];
        }
        return new String(palindrome);
    }
}
