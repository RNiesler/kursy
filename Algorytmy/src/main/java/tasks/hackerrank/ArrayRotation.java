package tasks.hackerrank;

import java.util.Arrays;
import java.util.Objects;

public class ArrayRotation {
    static int[] rotLeft(int[] a, int d) {
        Objects.requireNonNull(a);
        final int n = a.length;
        int[] firstD = Arrays.copyOfRange(a, 0, d);
        for (int i = 0; i < d; i++) {
            int prev = firstD[i];
            int j = n - d + i;
            while (j >= 0) {
                int temp = a[j];
                a[j] = prev;
                prev = temp;
                j -= d;
            }
        }
        return a;
    }
}
