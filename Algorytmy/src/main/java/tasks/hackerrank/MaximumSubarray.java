package tasks.hackerrank;

public class MaximumSubarray {
    static int[] maxSubarray(int[] arr) {
        int[] result = new int[2];
        result[0] = calcMaxSubarray(arr);
        result[1] = maxSubsequence(arr);
        return result;
    }

    private static int calcMaxSubarray(int[] arr) {
        int currentSum = 0;
        int maxSum = Integer.MIN_VALUE;
        int end = 0;
        while (end < arr.length) {
            currentSum += arr[end];
            if (currentSum > maxSum) {
                maxSum = currentSum;
            }
            if (currentSum < 0) {
                currentSum = 0;
            }
            end++;
        }
        return maxSum;
    }

    private static int maxSubsequence(int[] arr) {
        int sum = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 0) {
                sum += arr[i];
            }
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        return sum == 0 ? max : sum;
    }
}
