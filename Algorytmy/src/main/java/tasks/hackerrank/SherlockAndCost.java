package tasks.hackerrank;

import java.util.Arrays;
import java.util.Objects;

public class SherlockAndCost {
    static int cost(int[] b) {
        Objects.requireNonNull(b);
        int maxB = Arrays.stream(b).max().getAsInt();
        int[][] result = new int[b.length][maxB + 1];
        for (int i = 1; i < b.length; i++) {
            for (int j = 1; j <= b[i]; j++) {
                int maxSum = 0;
                for (int k = 1; k <= b[i - 1]; k++) {
                    int sum = result[i - 1][k] + Math.abs(k - j);
                    if (sum > maxSum) {
                        maxSum = sum;
                    }
                }
                result[i][j] = maxSum;
            }

        }
        int maxSumGlobal = 0;
        for (int i = 1; i <= maxB; i++) {
            if (result[b.length - 1][i] > maxSumGlobal) {
                maxSumGlobal = result[b.length - 1][i];
            }
        }
        return maxSumGlobal;
    }

    static int costEfficient(int[] b) {
        Objects.requireNonNull(b);
        int maxB = Arrays.stream(b).max().getAsInt();
        int[][] result = new int[b.length][2];
        for (int i = 1; i < b.length; i++) {
            // when position == b[i] while previous 1
            int prevOne = result[i - 1][0] + b[i] - 1;
            // when position == b[i] while previous was b[i-1]
            int prevB = result[i - 1][1] + Math.abs(b[i] - b[i - 1]);
            result[i][1] = prevOne > prevB ? prevOne : prevB;

            // when position == 1 while previous b[i-1]
            prevB = result[i - 1][1] + b[i - 1] - 1;
            result[i][0] = prevB > result[i - 1][0] ? prevB : result[i - 1][0];
        }
        int maxSumGlobal = 0;
        for (int i = 0; i < 2; i++) {
            if (result[b.length - 1][i] > maxSumGlobal) {
                maxSumGlobal = result[b.length - 1][i];
            }
        }
        return maxSumGlobal;
    }
}
