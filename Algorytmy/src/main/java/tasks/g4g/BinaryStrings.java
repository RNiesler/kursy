package tasks.g4g;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class BinaryStrings {
    private final static char WILDCARD = '?';

    public static List<String> generateBinaryStringsEfficient(String binaryStr) {
        Objects.requireNonNull(binaryStr);
        LinkedList<String> result = new LinkedList<>();
        generateBinaryStringsRecursive(binaryStr.toCharArray(), 0, result);
        return result;
    }

    private static void generateBinaryStringsRecursive(char[] chars, int start, List<String> result) {
        int i = start;
        while (i < chars.length && chars[i] != WILDCARD) {
            i++;
        }
        if (i < chars.length) {
            chars[i] = '0';
            generateBinaryStringsRecursive(chars, i + 1, result);
            chars[i] = '1';
            generateBinaryStringsRecursive(chars, i + 1, result);
            chars[i] = WILDCARD;
        } else {
            result.add(new String(chars));
        }
    }

}
