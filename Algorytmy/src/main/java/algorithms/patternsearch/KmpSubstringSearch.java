package algorithms.patternsearch;

import java.util.Iterator;
import java.util.stream.Collectors;

public class KmpSubstringSearch implements SubstringSearch {
    private final String pattern;
    // this array contains indexes in the pattern that say where else in the pattern we could be
    private final int lps[];

    public KmpSubstringSearch(String pattern) {
        if (pattern.length() == 0)
            throw new IllegalArgumentException("The pattern cannot be empty");
        this.pattern = pattern;
        this.lps = initLps(pattern);
    }

    private static int[] initLps(final String pattern) {
        int[] lps = new int[pattern.length()];
        int len = 0; // current length of found prefix that's also a suffix
        int patInd = 1;
        lps[0] = 0;
        while (patInd < pattern.length()) {
            if (pattern.charAt(len) == pattern.charAt(patInd)) {
                // matched characters
                len++;
                lps[patInd] = len;
                patInd++;
            } else {
                //mismatched characters
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[patInd] = len; // which is 0
                    patInd++;
                }
            }
        }
        return lps;
    }

    class Matcher extends DefaultMatcher {

        protected Matcher(String str) {
            super(str);
        }

        @Override
        public Iterator<String> matchIterator() {
            return matchStartIndexes.stream()
                    .map(i -> str.substring(i, i + pattern.length()))
                    .collect(Collectors.toList())
                    .iterator();
        }
    }

    @Override
    public Matcher search(final String str) {
        Matcher matcher = new Matcher(str);
        int matchInd = 0;
        int strInd = matchInd;
        int patInd = 0;
        while (strInd < str.length()) {
            if (patInd == pattern.length()) {
                // word match
                matcher.matchStartIndexes.add(strInd - pattern.length());
                patInd = lps[patInd - 1];
            } else if (str.charAt(strInd) == pattern.charAt(patInd)) {
                // character match
                strInd++;
                patInd++;
            } else {
                // character mismatch without word match
                if (patInd == 0) {
                    strInd++;
                } else {
                    patInd = lps[patInd - 1];
                }
            }
        }
        // the condition on the while loop would skip the match at the very end of the str
        if (patInd == pattern.length()) {
            matcher.matchStartIndexes.add(strInd - pattern.length());
        }
        return matcher;
    }
}
