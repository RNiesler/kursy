package tasks.hackerrank;

import java.util.HashMap;
import java.util.Map;

public class RansomNote {
    static boolean checkMagazine(String[] magazine, String[] note) {
        Map<String, Integer> noteWordCount = new HashMap<>();
        for (int i = 0; i < note.length; i++) {
            noteWordCount
                    .compute(note[i], (key, value) -> value == null ? 1 : value + 1);
        }
        for (int i = 0; i < magazine.length; i++) {
            noteWordCount.computeIfPresent(magazine[i], (key, value) -> value == 0 ? 0 : value - 1);
        }
        return noteWordCount.values().stream().mapToInt(Integer::intValue).sum() == 0;
    }
}
