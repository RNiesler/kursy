package tasks.hackerrank;

import java.util.Objects;

public class NewYearChaos {
    static void minimumBribes(int[] q) {
        Objects.requireNonNull(q);
        int swaps = 0;
        for (int i = q.length - 1; i > 0; i--) {
            int consecutiveSwaps = 0;
            int j = i;
            while (j < q.length && q[j - 1] > q[j]) {
                int temp = q[j - 1];
                q[j - 1] = q[j];
                q[j] = temp;
                consecutiveSwaps++;
                j++;
            }
            if (consecutiveSwaps > 2) {
                System.out.println("Too chaotic");
                return;
            }
            swaps += consecutiveSwaps;
        }

        System.out.println(swaps);
    }
}
