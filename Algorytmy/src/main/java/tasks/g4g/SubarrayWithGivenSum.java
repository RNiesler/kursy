package tasks.g4g;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;
import java.util.Optional;

public class SubarrayWithGivenSum {
    @Data
    @AllArgsConstructor
    public static class Pair {
        private int start;
        private int end;
    }

    public static Optional<Pair> findSubarrayWithGivenSum(final int[] array, final int targetSum) {
        Objects.requireNonNull(array);
        if (array.length == 0) {
            throw new IllegalArgumentException();
        }
        int start = 0;
        int end = 0;
        int sum = array[0];
        while (end < array.length && start < array.length) {
            if (sum == targetSum) {
                return Optional.of(new Pair(start, end));
            } else if (sum > targetSum && start < end) {
                sum -= array[start];
                start++;
            } else {
                end++;
                if (end < array.length) {
                    sum += array[end];
                }

            }
        }
        return Optional.empty();
    }
}
