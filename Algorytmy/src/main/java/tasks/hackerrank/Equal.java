package tasks.hackerrank;

import java.util.Objects;

public class Equal {
    static int equal(int[] arr) {
        Objects.requireNonNull(arr);
        int count = 0;
        while (true) {
            int min = arr[0];
            int max = arr[0];
            for (int i = 1; i < arr.length; i++) {
                if (arr[i] < min) {
                    min = arr[i];
                } else if (arr[i] > max) {
                    max = arr[i];
                }
            }
            int diff = 1;
            if (max - min == 0) {
                break;
            } else if (max - min > 2 && max >= 5) {
                diff = 5;
            } else if (max - min >= 2) {
                diff = 2;
            }
            count++;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == max) {
                    arr[i] -= diff;
                    break;
                }
            }
        }
        return count;
    }
}
