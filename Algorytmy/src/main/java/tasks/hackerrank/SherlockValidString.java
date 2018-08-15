package tasks.hackerrank;

import java.util.HashMap;
import java.util.Objects;

public class SherlockValidString {
    static String isValid(String s) {
        Objects.requireNonNull(s);
        HashMap<Character, Integer> charCount = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            charCount.compute(s.charAt(i), (k, v) -> v == null ? 1 : v + 1);
        }

        int count1 = 0;
        int count1Count = 0;
        int count2 = 0;
        int count2Count = 0;
        boolean singleNonMatchingFound = false;
        for (Character c : charCount.keySet()) {
            int count = charCount.get(c);
            if (count1 == 0 || count == count1) {
                count1 = count;
                count1Count++;
            } else if (count2 == 0 || count == count2) {
                count2 = count;
                count2Count++;
            } else {
                return "NO";
            }
        }

        if (count1Count < count2Count) {
            int temp = count1Count;
            count1Count = count2Count;
            count2Count = temp;
            temp = count1;
            count1 = count2;
            count2 = temp;
        }

        if (count2 == 0 ||
                (count2Count == 1 && (count2 == 1 || count2 == count1 + 1))) {
            return "YES";
        } else {
            return "NO";
        }
    }
}
