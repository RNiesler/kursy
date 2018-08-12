package tasks.g4g;

import java.util.LinkedList;

//Given a string consisting of opening and closing parenthesis, find length of the longest valid parenthesis substring.
public class LongestValidSubstring {
    public static int lengthOfLongestValidSubstring(final String str) {
        LinkedList<Character> stack = new LinkedList<>();
        int max = 0;
        int popCount = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (isOpen(c)) {
                stack.push(c);
            } else if (isClose(c)) {
                if (stack.isEmpty()) {
                    if (popCount > max) {
                        max = popCount;
                    }
                    popCount = 0;
                } else {
                    stack.pop();
                    popCount++;
                }
            } else {
                popCount = 0;
            }
        }
        if(popCount > max) {
            max = popCount;
        }
        return max * 2;
    }

    private static boolean isOpen(char c) {
        return c == '(';
    }

    private static boolean isClose(char c) {
        return c == ')';
    }
}
