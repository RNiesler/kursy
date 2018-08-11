package tasks.g4g;

import java.util.Objects;

//Given an array arr[], find the maximum j – i such that arr[j] > arr[i].
public class MaxIndexDiff {
    public static int maxIndexDiff(final int[] input) {
        Objects.requireNonNull(input);
        int[] leftMin = new int[input.length];
        int[] rightMax = new int[input.length];
        leftMin[0] = input[0];
        rightMax[input.length - 1] = input[input.length - 1];
        for (int i = 1; i < input.length; i++) {
            leftMin[i] = input[i] < leftMin[i - 1] ? input[i] : leftMin[i - 1];
        }
        for (int i = input.length - 2; i >= 0; i--) {
            rightMax[i] = input[i] > rightMax[i + 1] ? input[i] : rightMax[i + 1];
        }

        int maxDiff = -1;
        int left = 0;
        int right = 1;
        while (left < right && right < input.length) {
            if (leftMin[left] > rightMax[right]) {
                left++;
            } else {
                if (right - left > maxDiff) {
                    maxDiff = right - left;
                }
                right++;
            }
        }
        return maxDiff;
    }
}
