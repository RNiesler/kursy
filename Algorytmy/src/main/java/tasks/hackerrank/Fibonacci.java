package tasks.hackerrank;

public class Fibonacci {
    public static int fibonacci(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            int prev = 0;
            int x = 1;
            for (int i = 2; i <= n; i++) {
                int temp = x;
                x = x + prev;
                prev = temp;
            }
            return x;
        }
    }
}
