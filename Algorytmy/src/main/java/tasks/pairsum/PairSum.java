package tasks.pairsum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class PairSum {
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class PairSumPair {
        private int i1;
        private int i2;
    }

    /**
     * Find indexes of pairs in the array that sum up to the given sum.
     *
     * @param array The array must be sorted
     */
    public static List<PairSumPair> pairSumSorted(final int[] array, final int sum) {
        Objects.requireNonNull(array);
        List<PairSumPair> result = new LinkedList<>();
        int start = 0;
        int end = array.length - 1;
        while (start < end) {
            int tempSum = array[start] + array[end];
            if (tempSum == sum) {
                result.add(new PairSumPair(start, end));
                start++;
                end--;
            } else if (tempSum < sum) {
                start++;
            } else {
                end--;
            }
        }
        return result;
    }

    /**
     * Find indexes of pairs in the array that sum up to the given sum. Assuming no repetitions.
     *
     * @param array The array doesn't have to be sorted
     */
    public static List<PairSumPair> pairSumUnsorted(final int[] array, final int sum) {
        Objects.requireNonNull(array);
        Map<Integer, Integer> complements = new HashMap<>();
        List<PairSumPair> result = new LinkedList<>();
        for (int i = 0; i < array.length; i++) {
            if (complements.containsKey(array[i])) {
                result.add(new PairSumPair(complements.get(array[i]), i));
            }
            complements.put(sum - array[i], i);
        }
        return result;
    }
}
