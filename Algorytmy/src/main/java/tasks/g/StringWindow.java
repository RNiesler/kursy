package tasks.g;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Optional;

public class StringWindow {
    //Given two strings s and t,
    // write a function that will find the minimum window in s which will contain all the characters of t in order
    static class Window {
        Window(int start, int end) {
            this.start = start;
            this.end = end;
        }

        int start;
        int end;
    }

    //Given two strings s and t,
    // write a function that will find the minimum window in s which will contain all the characters of t in order
    public static Optional<Window> findMinimum(String t, String s) {
        if (t.length() > s.length()) {
            return Optional.empty();
        }
        HashMap<Character, Integer> counts = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            counts.compute(t.charAt(i), (k, v) -> v == null ? 1 : v + 1);
        }

        Window minWindow = null;
        int start = 0;
        int matched = 0;
        int end = 0;
        while (start < s.length() && end < s.length()) {
            if (matched == t.length()) {
                if (minWindow == null || minWindow.end - minWindow.start > end - start) {
                    minWindow = new Window(start, end);
                }
                char cStart = s.charAt(start);
                if (counts.containsKey(cStart)) {
                    counts.compute(cStart, (k, v) -> v + 1);
                    if (counts.get(cStart) > 0) {
                        matched--;
                    }
                }
                start++;
            } else { // matched < t.length()
                char cEnd = s.charAt(end);
                if (counts.containsKey(cEnd)) {
                    counts.compute(cEnd, (k, v) -> v - 1);
                    if (counts.get(cEnd) >= 0) {
                        matched++;
                    }
                }
                end++;
            }
        }
        // verify that the min is not at the end
        while (matched == t.length() && start < s.length() - t.length()) {
            if (minWindow == null || minWindow.end - minWindow.start > end - start) {
                minWindow = new Window(start, end);
            }
            char cStart = s.charAt(start);
            if (counts.containsKey(cStart)) {
                counts.compute(cStart, (k, v) -> v + 1);
                if (counts.get(cStart) > 0) {
                    matched--;
                }
            }
            start++;
        }
        return Optional.ofNullable(minWindow);
    }

    // assume no repeated characters in the pattern
    public static Optional<Window> findMinimumInOrder(String t, String s) {
        if (t.length() > s.length()) {
            return Optional.empty();
        }
        HashMap<Character, Integer> positionsInPattern = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            positionsInPattern.put(t.charAt(i), i);
        }

        ArrayList<LinkedList<Integer>> positions = new ArrayList<>(t.length());
        for (int i = 0; i < t.length(); i++) {
            positions.add(new LinkedList<>());
        }

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (positionsInPattern.containsKey(c)) {
                positions.get(positionsInPattern.get(c)).add(i);
            }
        }


        Window minWindow = null;

        while (!positions.get(0).isEmpty()) {
            int first = positions.get(0).removeFirst();
            int last = first;
            for (int i = 1; i < t.length(); i++) {
                int iPos = positions.get(i).get(0);
                while (iPos < last && !positions.get(i).isEmpty()) {
                    positions.get(i).removeFirst();
                    iPos = positions.get(i).get(0);

                }
                if (iPos < last) {
                    break; // we've run out of characters for this position
                } else {
                    last = iPos;
                }
            }
            if (minWindow == null || minWindow.end - minWindow.start > last - first) {
                minWindow = new Window(first, last + 1);
            }
        }

        return Optional.ofNullable(minWindow);

    }

    public static int findMinLengthInOrder(String t, String s) {
        if (t.length() > s.length()) {
            return -1;
        }
        int minLength = Integer.MAX_VALUE;
        for (int i = 0; i < s.length() - t.length(); i++) {
            if (s.charAt(i) == t.charAt(0)) {
                int closestEnd = findClosestEnd(t, 1, s, i + 1);
                if (closestEnd > 0 && closestEnd - i < minLength) {
                    minLength = closestEnd - i;
                }
            }
        }
        return minLength == Integer.MAX_VALUE ? -1 : minLength;
    }

    private static int findClosestEnd(String t, int tPos, String s, int sPos) {
        if (tPos == t.length()) {
            return sPos;
        } else if (sPos == s.length()) {
            return -1;
        } else {
            int limit = s.length() - t.length() + tPos;
            int closestEnd = Integer.MAX_VALUE;
            for (int i = sPos; i < limit; i++) {
                if (s.charAt(i) == t.charAt(tPos)) {
                    int end = findClosestEnd(t, tPos + 1, s, i + 1);
                    if (end > 0 && end < closestEnd) {
                        closestEnd = end;
                    }
                }
            }
            return closestEnd == Integer.MAX_VALUE ? -1 : closestEnd;
        }
    }
}
