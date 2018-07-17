package algorithms.patternsearch;

import java.util.Iterator;
import java.util.stream.Collectors;

public class NaiveSubstringSearch implements SubstringSearch {

    private final String pattern;

    public NaiveSubstringSearch(String pattern) {
        this.pattern = pattern;
    }

    class Matcher extends SubstringSearch.DefaultMatcher {
        private Matcher(String str) {
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
        for (int matchInd = 0; matchInd <= str.length() - pattern.length(); matchInd++) {
            int strInd = matchInd;
            int patInd = 0;
            while (patInd < pattern.length() && pattern.charAt(patInd) == str.charAt(strInd)) {
                patInd++;
                strInd++;
            }
            if (patInd == pattern.length()) {
                matcher.matchStartIndexes.add(matchInd);
            }
        }
        return matcher;
    }


}
