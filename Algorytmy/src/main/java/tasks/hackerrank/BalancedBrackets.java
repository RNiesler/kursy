package tasks.hackerrank;

import java.util.LinkedList;

public class BalancedBrackets {
    static boolean isBalanced(final String str) {
        LinkedList<Character> stack = new LinkedList<>();
        char[] cArray = str.toCharArray();
        for (int i = 0; i < cArray.length; i++) {
            char c = cArray[i];
            if (isOpening(c)) {
                stack.push(c);
            } else if (isClosing(c)) {
                if (stack.isEmpty() || !isMatching(stack.pop(), c)) {
                    return false;
                }
            } else {
                throw new IllegalArgumentException("Unknown character");
            }
        }
        return stack.isEmpty();
    }

    private static boolean isOpening(final char c) {
        return c == '(' || c == '[' || c == '{';
    }

    private static boolean isMatching(final char opening, final char closing) {
        switch (opening) {
            case '(':
                return closing == ')';
            case '[':
                return closing == ']';
            case '{':
                return closing == '}';
            default:
                return false;
        }
    }

    private static boolean isClosing(final char c) {
        return c == ')' || c == ']' || c == '}';
    }
}
