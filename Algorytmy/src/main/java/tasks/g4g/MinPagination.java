package tasks.g4g;

//Given number of pages in n different books and m students. The books are arranged in ascending order of number of pages.
// Every student is assigned to read some consecutive books.
// The task is to assign books in such a way that the maximum number of pages assigned to a student is minimum.
public class MinPagination {
    public static int allocateMinPages(final int[] pages, final int readers) {
        int[][] memo = new int[readers + 1][pages.length + 1];
        return minPagesRecursive(pages, readers, 0, memo);
    }

    private static int minPagesRecursive(final int[] pages, final int readers, final int subsetStart, int[][] memo) {
        if (memo[readers][subsetStart] != 0) {
            return memo[readers][subsetStart];
        } else if (readers == 1) {
            int sum = 0;
            for (int i = subsetStart; i < pages.length; i++) {
                sum += pages[i];
            }
            memo[readers][subsetStart] = sum;
            return sum;
        } else if (subsetStart == pages.length) {
            return 0;
        } else {
            int prefixSum = 0;
            int minSum = Integer.MAX_VALUE;

            int i = subsetStart;
            while (i < pages.length && prefixSum < minSum) {
                prefixSum += pages[i];

                int currentSum = minPagesRecursive(pages, readers - 1, i + 1, memo);
                if (prefixSum > currentSum) {
                    currentSum = prefixSum;
                }

                if (currentSum < minSum) {
                    minSum = currentSum;
                }
                i++;
            }
            memo[readers][subsetStart] = minSum;
            return minSum;
        }
    }

    public static int allocateMinPagesIterative(final int[] pages, final int readers) {
        int[][] solution = new int[readers + 1][pages.length];
        solution[1][0] = pages[0];
        for (int i = 1; i < pages.length; i++) {
            solution[1][i] = solution[1][i - 1] + pages[i];
        }

        int[] rightCumulativeSum = new int[pages.length];
        rightCumulativeSum[pages.length - 1] = pages[pages.length - 1];
        for (int i = pages.length - 2; i >= 0; i--) {
            rightCumulativeSum[i] = rightCumulativeSum[i + 1] + pages[i];
        }

        for (int r = 2; r <= readers; r++) {
            for (int end = 1; end < pages.length; end++) {
                int minSum = Integer.MAX_VALUE;
                int rightSum = 0;
                for (int j = end; j > 0; j--) {
                    int sum = solution[r - 1][j];
                    if (sum < rightSum) {
                        sum = rightSum;
                    }
                    if (minSum > sum) {
                        minSum = sum;
                    }
                    rightSum += pages[j];
                }
                solution[r][end] = minSum;
            }

        }
        return solution[readers][pages.length-1];
    }
}
