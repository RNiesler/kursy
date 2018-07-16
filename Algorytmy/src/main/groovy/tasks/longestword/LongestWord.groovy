package tasks.longestword

class LongestWord {
    static String longestWord(final List<String> dictionary, final String source) {
        return dictionary.findAll { canBeTransformed(source, it) }
                .max { it.length() }
    }

    private static boolean canBeTransformed(String source, String target) {
        int sourceI = 0
        int targetI = 0
        while (sourceI < source.length() && targetI < target.length()) {
            if (source.charAt(sourceI) == target.charAt(targetI)) {
                sourceI++
                targetI++
            } else {
                sourceI++;
            }
        }
        return targetI == target.length();
    }
}
