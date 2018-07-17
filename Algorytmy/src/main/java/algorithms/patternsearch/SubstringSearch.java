package algorithms.patternsearch;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public interface SubstringSearch {
    Matcher search(String str);

    interface Matcher {
        Iterator<String> matchIterator();

        Iterator<Integer> matchIndexIterator();

        boolean matched();
    }

    abstract class DefaultMatcher implements Matcher {
        protected List<Integer> matchStartIndexes = new LinkedList<>();
        protected final String str;

        protected DefaultMatcher(String str) {
            this.str = str;
        }

        @Override
        public Iterator<Integer> matchIndexIterator() {
            return matchStartIndexes.iterator();
        }

        @Override
        public boolean matched() {
            return !matchStartIndexes.isEmpty();
        }

    }


}
