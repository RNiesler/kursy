package tasks.hackerrank;

import java.util.Map;
import java.util.stream.Collectors;

public class StringAnagrams {
    static int makeAnagram(String a, String b) {
        Map<Integer, Integer> aCharacterCount =
                a.chars()
                        .mapToObj(Integer::valueOf)
                        .collect(Collectors.groupingBy(c -> c, Collectors.reducing(0, c -> 1, Integer::sum)));
        int deleteCount = 0;
        for (int i = 0; i < b.length(); i++) {
            Integer c = Integer.valueOf(b.charAt(i));
            if (aCharacterCount.containsKey(c)) {
                int currentCount = aCharacterCount.get(c);
                if (currentCount > 0) {
                    aCharacterCount.put(c, currentCount - 1);
                } else {
                    deleteCount++; // need to delete from b
                }
            } else {
                deleteCount++; // need to delete from b
            }
        }
        return deleteCount + aCharacterCount.values().stream().mapToInt(Integer::intValue).sum();
    }

}
