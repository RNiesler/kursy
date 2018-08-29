package tasks.hackerrank;

public class SumVsXor {
    static long sumXor(long n) {
        long possibilities = 1; // zero is always possible
        long setBit = Long.highestOneBit(n);
        while (setBit > 0) {
            // get unset bit
            while (setBit > 0 && (setBit | n) == n) {
                setBit >>= 1;
            }
            if (setBit != 0) {
                possibilities <<= 1;
                setBit >>= 1;
            }
        }
        return possibilities;
    }
}
