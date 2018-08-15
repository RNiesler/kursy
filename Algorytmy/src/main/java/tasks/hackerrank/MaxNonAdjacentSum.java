package tasks.hackerrank;

import java.util.Objects;

//Given an array of integers, find the subset of non-adjacent elements with the maximum sum. Calculate the sum of that subset.
public class MaxNonAdjacentSum {
    static int maxSubsetSum(int[] arr) {
        Objects.requireNonNull(arr);
        int[] memo = new int[arr.length];
        return maxNonAdjacentSumRecursive(arr, 0, memo);
    }

    private static int maxNonAdjacentSumRecursive(final int[] input, final int start,
                                                  final int[] memo) {
        if (start >= input.length) {
            return 0;
        } else if (memo[start] != 0) {
            return memo[start];
        } else {
            int sum = input[start] + maxNonAdjacentSumRecursive(input, start + 2, memo);
            int sumWithout = maxNonAdjacentSumRecursive(input, start + 1, memo);
            if (sumWithout > sum) {
                sum = sumWithout;
            }
            memo[start] = sum;
            return sum;
        }
    }

}
