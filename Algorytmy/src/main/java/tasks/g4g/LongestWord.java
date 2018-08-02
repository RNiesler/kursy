package tasks.g4g;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class LongestWord {
    public static Optional<String> longestWord(List<String> dictionary, String pattern) {
        Objects.requireNonNull(dictionary);
        int max = 0;
        String maxWord = null;
        for (String dictWord : dictionary) {
            if (dictWord.length() > max && matches(pattern, dictWord)) {
                max = dictWord.length();
                maxWord = dictWord;
            }
        }
        return Optional.ofNullable(maxWord);

    }

    private static boolean matches(String pattern, String dictWord) {
        int patternInd = 0;
        int wordInd = 0;
        while (wordInd < dictWord.length() && patternInd < pattern.length()) {
            if (pattern.charAt(patternInd) == dictWord.charAt(wordInd)) {
                wordInd++;
            }
            patternInd++;
        }
        return wordInd == dictWord.length();
    }

}
