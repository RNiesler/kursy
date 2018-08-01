package algorithms.divideandconquer;

import java.util.Objects;

public class QuickSort {
    public static <T extends Comparable<? super T>> void sort(final T[] arr) {
        Objects.requireNonNull(arr);
        sort(arr, 0, arr.length - 1);
    }

    private static <T extends Comparable<? super T>> void sort(final T[] arr, int start, int end) {
        if (start >= end) {
            return;
        } else {
            int mid = (start + end) / 2;
            T midVal = arr[mid];
            swap(arr, mid, end);
            int left = 0;
            int right = end - 1;
            while (left <= right) {
                if (arr[left].compareTo(midVal) <= 0) {
                    left++;
                } else if (arr[right].compareTo(midVal) >= 0) {
                    right--;
                } else if (left < right) {
                    swap(arr, left, right);
                }
            }

            swap(arr, left, end);
            sort(arr, start, left - 1);
            sort(arr, left + 1, end);
        }
    }

    private static <T extends Comparable<? super T>> void swap(final T[] arr, int pos1, int pos2) {
        T temp = arr[pos1];
        arr[pos1] = arr[pos2];
        arr[pos2] = temp;
    }
}
