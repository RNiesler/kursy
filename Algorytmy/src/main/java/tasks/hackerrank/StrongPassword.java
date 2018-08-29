package tasks.hackerrank;

import java.util.Set;

public class StrongPassword {
    private static final int MIN_LENGTH = 6;

    private static final Set<Character> SPECIAL_CHARS = Set.of('!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '+');

    private static boolean isSpecial(char c) {
        return SPECIAL_CHARS.contains(c);
    }

    // Complete the minimumNumber function below.
    static int minimumNumber(int n, String password) {
        // Return the minimum number of characters to make the password strong
        boolean hasLowerCase = false;
        boolean hasUpperCase = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (Character.isDigit(c)) {
                hasDigit = true;
            } else if (Character.isLowerCase(c)) {
                hasLowerCase = true;
            } else if (Character.isUpperCase(c)) {
                hasUpperCase = true;
            } else if (isSpecial(c)) {
                hasSpecial = true;
            }
        }
        int missing = 0;
        if (!hasLowerCase) {
            missing++;
        }
        if (!hasUpperCase) {
            missing++;
        }
        if (!hasDigit) {
            missing++;
        }
        if (!hasSpecial) {
            missing++;
        }
        if (n + missing < MIN_LENGTH) {
            missing = MIN_LENGTH - n;
        }
        return missing;
    }
}
