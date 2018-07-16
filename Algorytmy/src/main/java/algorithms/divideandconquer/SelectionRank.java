package algorithms.divideandconquer;

import java.util.Optional;

public class SelectionRank {
    public static <T extends Comparable<T>> Optional<T> selectKth(final T[] input, final int k) {
        if (k >= input.length) {
            return Optional.empty();
        } else {
            return selectKth(input, k, 0, input.length);
        }
    }

    private static <T extends Comparable<T>> Optional<T> selectKth(final T[] input, final int k, final int start, final int end) {
        if (end - start == 1 && k == 0) {
            return Optional.of(input[start]);
        } else {
            int currentPivot = start;
            T pivotElement = input[end - 1]; // take last as pivot point
            // TODO modify pivot choosing strategy
            for (int i = start; i < end - 1; i++) {
                if (pivotElement.compareTo(input[i]) > 0) {
                    T temp = input[currentPivot];
                    input[currentPivot] = input[i];
                    input[i] = temp;
                    currentPivot++;
                }
            }
            input[end - 1] = input[currentPivot];
            input[currentPivot] = pivotElement; // put the pivot element in place
            if (currentPivot - start == k) {
                return Optional.of(pivotElement);
            } else if (k < currentPivot - start) { // it's on the left side
                return selectKth(input, k, start, currentPivot);
            } else { //it's on the right
                return selectKth(input, k - currentPivot + start - 1, currentPivot + 1, end);
            }
        }
    }
}
