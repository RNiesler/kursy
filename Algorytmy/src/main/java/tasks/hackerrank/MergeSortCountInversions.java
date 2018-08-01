package tasks.hackerrank;

import java.util.Objects;

public class MergeSortCountInversions {
    static long countInversions(int[] arr) {
        Objects.requireNonNull(arr);
        int[] temp = new int[arr.length];
        return countInversionsRecursive(arr, 0, arr.length - 1, temp);

    }

    static long countInversionsRecursive(int[] arr, int start, int end, int[] temp) {
        if (start >= end) {
            return 0l;
        }
        int mid = (start + ((end - start) / 2));
        long sum = countInversionsRecursive(arr, start, mid, temp);
        sum += countInversionsRecursive(arr, mid + 1, end, temp);
        sum += mergeAndCount(arr, start, mid, end, temp);
        return sum;
    }

    private static long mergeAndCount(int[] arr, int leftStart, int leftEnd, int rightEnd, int[] temp) {
        int tempIndex = 0;
        int left = leftStart;
        int right = leftEnd + 1;
        long inversions = 0l;
        while (left <= leftEnd && right <= rightEnd) {
            if (arr[left] <= arr[right]) {
                temp[tempIndex] = arr[left];
                left++;
            } else {
                inversions += leftEnd + 1 - left;
                temp[tempIndex] = arr[right];
                right++;
            }
            tempIndex++;
        }

        System.arraycopy(arr, left, temp, tempIndex, leftEnd - left + 1);
        System.arraycopy(arr, right, temp, tempIndex, rightEnd - right + 1);
        System.arraycopy(temp, 0, arr, leftStart, rightEnd - leftStart + 1);
        return inversions;
    }
}
