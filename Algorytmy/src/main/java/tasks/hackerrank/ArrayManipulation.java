package tasks.hackerrank;

import java.util.Objects;

public class ArrayManipulation {
    static long arrayManipulationSimple(int n, int[][] queries) {
        Objects.requireNonNull(queries);
        long[] array = new long[n];
        long globalMax = 0;
        for (int i = 0; i < queries.length; i++) {
            for (int j = queries[i][0] - 1; j < queries[i][1]; j++) {
                array[j] += queries[i][2];
                if (array[j] > globalMax) {
                    globalMax = array[j];
                }
            }

        }
        return globalMax;

    }

    // each start of the range notes how much the value increases, each end of the node decreases the same amount
    // To get the max we have to traverse the whole array and sum everything while keeping the max
    static long arrayManipulation(int n, int[][] queries) {
        Objects.requireNonNull(queries);
        long[] array = new long[n];
        for (int i = 0; i < queries.length; i++) {
            array[queries[i][0] - 1] += queries[i][2];
            if(queries[i][1] < array.length)
                array[queries[i][1]] -= queries[i][2];
        }

        long globalMax = 0;
        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += array[i];
            if (sum > globalMax) {
                globalMax = sum;
            }
        }
        return globalMax;

    }
}