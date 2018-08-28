package tasks.s;

import java.util.LinkedList;

public class Sam {

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

    public int solution(int A) {
        // write your code in Java SE 8
        if (A < 0) {
            throw new IllegalArgumentException("Input cannot be negative");
        }

        // special case: 0
        if (A == 0) {
            return 0;
        }

        LinkedList<Integer> digits = new LinkedList<>();
        while (A > 0) {
            digits.add(A % 10);
            A /= 10;
        }

        int result = 0;
        boolean mostSignificant = true;
        while (!digits.isEmpty()) {
            result *= 10;
            if (mostSignificant) {
                result += digits.removeLast(); // the most-significant digits are at the end of the list
                mostSignificant = false;
            } else {
                result += digits.removeFirst();
                mostSignificant = true;
            }
        }

        return result;
    }

}
