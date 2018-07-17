package algorithms.patternsearch;

import java.util.Iterator;
import java.util.stream.Collectors;

/**
 * https://en.wikipedia.org/wiki/Rabin%E2%80%93Karp_algorithm
 */
public class RabinKarpSubstringSearch implements SubstringSearch {
    private final String pattern;
    private final Hasher hasher;

    public RabinKarpSubstringSearch(final String pattern) {
        this(new RabinKarpHasher(pattern), pattern);
    }

    public RabinKarpSubstringSearch(final Hasher hasher, final String pattern) {
        this.pattern = pattern;
        this.hasher = hasher;

    }

    public interface Hasher {
        /**
         * @param start inclusive
         * @param end   exclusive
         */
        int hash(String str, int start, int end);

        int rehash(int previous, String str, int prevStart, int nextEnd);
    }

    public static class RabinKarpHasher implements Hasher {
        private final int PRIME = 101;
        private final int BASE = 256;
        private final int leftBaseOffset;

        public RabinKarpHasher(String pattern) {
            int temp = 1;
            for (int i = 0; i < pattern.length() - 1; i++) {
                temp = (temp * BASE) % PRIME;
            }
            leftBaseOffset = temp;
        }

        @Override
        public int hash(String str, int start, int end) {
            int hash = 0;
            for (int i = 0; i < end - start; i++) {
                hash = (hash * BASE + str.charAt(i)) % PRIME;
            }
            return hash;
        }

        @Override
        public int rehash(int previous, String str, int prevStart, int nextEnd) {
            int newHash = ((previous - leftBaseOffset * str.charAt(prevStart)) * BASE + str.charAt(nextEnd)) % PRIME;
            return newHash < 0 ? newHash + PRIME : newHash;
        }
    }

    private class Matcher extends DefaultMatcher {

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
        int patternHash = hasher.hash(pattern, 0, pattern.length());
        int currentHash = hasher.hash(str, 0, pattern.length());
        // check if the pattern is at the start
        if (currentHash == patternHash && literalMatch(str, 0)) {
            matcher.matchStartIndexes.add(0);
        }
        int matchInd = 1;
        while (matchInd <= str.length() - pattern.length()) {
            currentHash = hasher.rehash(currentHash, str, matchInd - 1, matchInd + pattern.length() - 1);
            if (currentHash == patternHash && literalMatch(str, matchInd)) {
                matcher.matchStartIndexes.add(matchInd);
            }
            matchInd++;
        }
        return matcher;
    }

    boolean literalMatch(final String str, final int startInd) {
        boolean match = true;
        int i = 0;
        while (match && i < pattern.length()) {
            if (pattern.charAt(i) != str.charAt(startInd + i)) {
                match = false;
            }
            i++;
        }
        return match;
    }
}
