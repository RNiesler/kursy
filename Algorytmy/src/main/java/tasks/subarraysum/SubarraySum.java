package tasks.subarraysum;

import java.util.Arrays;
import java.util.Optional;

public class SubarraySum {
    public static Optional<int[]> solveFor(final int[] input, final Integer sum) {
        int start = 0;
        int end = 0;
        int currentSum = 0;
        while (end < input.length && currentSum != sum) {
            if (currentSum < sum) {
                currentSum += input[end++];
            } else if (currentSum > sum) {
                currentSum -= input[start++];
            }
        }
        if (currentSum == sum) {
            return Optional.of(Arrays.copyOfRange(input, start, end));
        } else {
            return Optional.empty();
        }
    }
}
