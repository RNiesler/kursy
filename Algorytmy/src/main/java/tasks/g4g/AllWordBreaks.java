package tasks.g4g;

import java.util.LinkedList;
import java.util.List;

//Given a valid sentence without any spaces between the words and a dictionary of valid English words,
// find all possible ways to break the sentence in individual dictionary words.
public class AllWordBreaks {
    interface Dictionary {
        boolean isWord(String word);
    }

    public static List<String> wordBreaks(final String input, final Dictionary dict) {
        LinkedList<String> result = new LinkedList<>();
        wordBreaksRecursive(input, dict, 0, "", result);
        return result;
    }

    private static void wordBreaksRecursive(final String input, final Dictionary dict, final int start, final String prefix, final List<String> result) {
        if (start == input.length()) {
            result.add(prefix);
        } else {
            for (int i = start; i < input.length(); i++) {
                String word = input.substring(start, i + 1);
                if (dict.isWord(word)) {
                    String suffix = (start > 0 ? " " : "") + word;
                    wordBreaksRecursive(input, dict, i + 1, prefix + suffix, result);
                }
            }
        }
    }
}
