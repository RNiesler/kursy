package tasks.hackerrank;

public class FlippingBits {
    final static long MASK = makeMask();

    private static long makeMask() {
        long n = 0;
        for (int i = 0; i < 32; i++) {
            n <<= 1;
            n++;
        }
        return n;
    }

    // Complete the flippingBits function below.
    static long flippingBits(long n) {
        return (~n) & MASK;
    }
}
