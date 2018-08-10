package tasks.g4g;

public class MaximumSubarrayDifference {
    public static int maxSubarrayDiff(final int[] input) {
        int[] maxLeft = makeMaxLeft(input);
        int[] minLeft = makeMinLeft(input);
        int[] maxRight = makeMaxRight(input);
        int[] minRight = makeMinRight(input);

        int maxSoFar = 0;

        for (int i = 1; i < input.length; i++) {
            int maxLeftMinRight = Math.abs(maxLeft[i - 1] - minRight[i]);
            if (maxLeftMinRight > maxSoFar) {
                maxSoFar = maxLeftMinRight;
            }
            int minLeftMaxRight = Math.abs(minLeft[i - 1] - maxRight[i]);
            if (minLeftMaxRight > maxSoFar) {
                maxSoFar = minLeftMaxRight;
            }
        }
        return maxSoFar;
    }

    private static int[] makeMinLeft(final int[] input) {
        int[] negatedInput = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            negatedInput[i] = -1 * input[i];
        }
        int[] minLeftNegated = makeMaxLeft(negatedInput);
        for (int i = 0; i < minLeftNegated.length; i++) {
            minLeftNegated[i] = -1 * minLeftNegated[i];
        }
        return minLeftNegated;
    }

    private static int[] makeMaxLeft(final int[] input) {
        int[] maxLeft = new int[input.length];
        maxLeft[0] = input[0];
        int max = maxLeft[0];
        int maxSingleElem = maxLeft[0];
        int maxEndingAtI = maxLeft[0];
        if (maxEndingAtI < 0) {
            maxEndingAtI = 0;
        }
        for (int i = 1; i < input.length; i++) {
            maxEndingAtI += input[i];
            if (maxEndingAtI < 0) {
                maxEndingAtI = 0;
            }
            if (maxEndingAtI > max) {
                max = maxEndingAtI;
            }
            if (input[i] > maxSingleElem) {
                maxSingleElem = input[i];
            }
            if (max == 0) { // when all elements are < 0
                maxLeft[i] = maxSingleElem;
            } else {
                maxLeft[i] = max;
            }
        }
        return maxLeft;
    }

    private static int[] makeMinRight(final int[] input) {
        int[] negatedInput = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            negatedInput[i] = -1 * input[i];
        }
        int[] minRightNegated = makeMaxRight(negatedInput);
        for (int i = 0; i < minRightNegated.length; i++) {
            minRightNegated[i] = -1 * minRightNegated[i];
        }
        return minRightNegated;
    }

    private static int[] makeMaxRight(final int[] input) {
        int[] maxRight = new int[input.length];
        maxRight[input.length - 1] = input[input.length - 1];
        int max = maxRight[input.length - 1];
        int maxSingleElement = maxRight[input.length - 1];
        int maxStartingAtI = maxRight[input.length - 1];
        if (maxStartingAtI < 0) {
            maxStartingAtI = 0;
        }
        for (int i = input.length - 2; i > 0; i--) {
            maxStartingAtI += input[i];
            if (maxStartingAtI < 0) {
                maxStartingAtI = 0;
            }
            if (maxStartingAtI > max) {
                max = maxStartingAtI;
            }
            if (maxSingleElement < input[i]) {
                maxSingleElement = input[i];
            }
            if (max == 0) {
                maxRight[i] = maxSingleElement;
            } else {
                maxRight[i] = max;
            }
        }
        return maxRight;
    }
}
