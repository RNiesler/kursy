package algorithms.binarysearch;

import java.util.Comparator;
import java.util.Objects;

public class BinarySearch {
    public static <T> boolean contains(final T[] ts, final T x, final Comparator<T> comparator) {
        Objects.requireNonNull(ts);
        Objects.requireNonNull(comparator);
        return contains(ts, x, comparator, 0, ts.length - 1);
    }

    private static <T> boolean contains(final T[] ts, final T x, final Comparator<T> comparator, int start, int end) {
        if (end < start) {
            return false;
        } else {
            int mid = (start + end) / 2;
            int comparation = comparator.compare(ts[mid], x);
            if (comparation == 0) {
                return true;
            } else if (comparation > 0) { // x < mid
                return contains(ts, x, comparator, start, mid - 1);
            } else {
                return contains(ts, x, comparator, mid + 1, end);
            }
        }
    }

    public static <T> boolean containsIt(final T[] ts, final T x, final Comparator<T> comparator) {
        Objects.requireNonNull(ts);
        Objects.requireNonNull(comparator);
        int start = 0;
        int end = ts.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            int comparation = comparator.compare(ts[mid], x);
            if (comparation == 0) {
                return true;
            } else if (comparation > 0) { // x < mid
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return false;
    }
}
