package tasks.g4g;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class LongestSubstring {
    public static List<String> longestSubstrings(String input, int m) {
        List<String> result = new LinkedList<>();
        int max = 0;
        HashMap<Character, Integer> characterCount = new HashMap<>();
        int start = 0;
        int end = 0;
        while (start < input.length() - max + 1 && end < input.length()) { //TODO review condition
            char c = input.charAt(end);
            if (characterCount.containsKey(c) && characterCount.get(c) > 0) {
                characterCount.compute(c, (k, v) -> v + 1);
                end++;
            } else if (characterCount.size() < m) {
                characterCount.compute(c, (k, v) -> v == null ? 1 : v + 1);
                end++;
            } else {
                char c2 = input.charAt(start);
                characterCount.compute(c2, (k, v) -> v - 1);
                if (characterCount.get(c2) == 0) {
                    characterCount.remove(c2);
                }
                start++;
            }

            if (characterCount.size() == m) {
                int currentLength = end - start;
                if (currentLength > max) {
                    result.clear();
                    result.add(input.substring(start, end));
                    max = currentLength;
                } else if (currentLength == max) {
                    result.add(input.substring(start, end));
                }
            }

        }
        return result;
    }

}
