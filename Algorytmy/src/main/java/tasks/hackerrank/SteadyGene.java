package tasks.hackerrank;

import java.util.HashMap;
import java.util.Map;

public class SteadyGene {
    private static int letterToIndex(char c) {
        switch (c) {
            case 'A':
                return 0;
            case 'C':
                return 1;
            case 'T':
                return 2;
            case 'G':
                return 3;
            default:
                return -1;
        }
    }

    private static char indexToLetter(int ind) {
        switch (ind) {
            case 0:
                return 'A';
            case 1:
                return 'C';
            case 2:
                return 'T';
            case 3:
                return 'G';
            default:
                return (char) -1;
        }
    }

    // Complete the steadyGene function below.
    static int steadyGene(String gene) {
        int[] count = new int[4];
        for (int i = 0; i < gene.length(); i++) {
            count[letterToIndex(gene.charAt(i))]++;
        }
        int n4 = gene.length() / 4;

        StringBuilder replacementBuilder = new StringBuilder();
        HashMap<Character, Integer> replacementCount = new HashMap<>();
        for (int i = 0; i < 4; i++) {
            count[i] -= n4;
            if (count[i] > 0) {
                replacementCount.put(indexToLetter(i), count[i]);
            }
        }
        return minSubstringContaining(gene, replacementCount);
    }

    private static int minSubstringContaining(String s, Map<Character, Integer> replacementCount) {
        int minLength = Integer.MAX_VALUE;
        final int toMatch = replacementCount.entrySet().stream().mapToInt(e -> e.getValue())
                .sum();
        if(toMatch == 0) {
            return 0;
        }
        int matched = 0;
        int start = 0;
        int end = 0;
        while (start < s.length() && end < s.length()) {
            if (matched == toMatch) {
                if (end - start < minLength) {
                    minLength = end - start;
                }
                char cStart = s.charAt(start);
                if (replacementCount.containsKey(cStart)) {
                    replacementCount.compute(cStart, (k, v) -> v + 1);
                    if (replacementCount.get(cStart) > 0) {
                        matched--;
                    }
                }
                start++;
            } else {
                char cEnd = s.charAt(end);
                if (replacementCount.containsKey(cEnd)) {
                    if (replacementCount.get(cEnd) > 0) {
                        matched++;
                    }
                    replacementCount.compute(cEnd, (k, v) -> v - 1);
                }
                end++;
            }
        }

        // check the end
        while (matched == toMatch && start < s.length()) {
            if (end - start < minLength) {
                minLength = end - start;
            }
            char cStart = s.charAt(start);
            if (replacementCount.containsKey(cStart)) {
                replacementCount.compute(cStart, (k, v) -> v + 1);
                if (replacementCount.get(cStart) > 0) {
                    matched--;
                }
            }
            start++;
        }
        return minLength;
    }
}
