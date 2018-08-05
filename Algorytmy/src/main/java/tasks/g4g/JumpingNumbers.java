package tasks.g4g;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class JumpingNumbers {
    public static List<Integer> jumpingNumbers(int limit) {
        LinkedList<Integer> result = new LinkedList<>();
        if (limit > 0) {
            result.add(0);
        }
        for (byte i = 1; i < 10; i++) {
            jumpingNumbersRecursive(limit, new byte[]{i}, result);
        }
        return result;
    }

    private static void jumpingNumbersRecursive(int limit, byte[] prefix, List<Integer> result) {
        int currentNumber = digitsToNumber(prefix);
        if (currentNumber < limit) {
            result.add(currentNumber);
            byte[] digits = Arrays.copyOf(prefix, prefix.length + 1);
            int lastDigit = prefix[prefix.length - 1];
            if (lastDigit > 0) {
                digits[prefix.length] = (byte) (lastDigit - 1);
                jumpingNumbersRecursive(limit, digits, result);

            }
            if (lastDigit < 9) {
                digits[prefix.length] = (byte) (lastDigit + 1);
                jumpingNumbersRecursive(limit, digits, result);

            }
        }
    }

    private static int digitsToNumber(byte[] digits) {
        int number = 0;
        for (int i = 0; i < digits.length; i++) {
            number = number * 10 + digits[i];
        }
        return number;
    }

}
