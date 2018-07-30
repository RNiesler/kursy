package tasks.hackerrank;

public class BubbleSort {
    static void countSwaps(int[] a) {
        int numSwaps = 0;
        boolean sorted = false;
        int iterCount = 0;
        while (!sorted) {
            sorted = true;
            for (int j = 0; j < a.length - 1 - iterCount; j++) {
                // Swap adjacent elements if they are in decreasing order
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                    numSwaps++;
                    sorted = false;
                }
            }
            iterCount++;

        }
        System.out.println("Array is sorted in " + numSwaps + " swaps.");
        System.out.println("First Element: " + a[0]);
        System.out.println("Last Element: " + a[a.length - 1]);

    }
}
