package tasks.hackerrank;

public class GreatXor {
    static long theGreatXor(long x) {
        long possibilities = 0;
        long setBit = Long.highestOneBit(x); // start with highest set bit

        while (setBit > 0) {
            // look for unset bit
            while ((x & setBit) != 0) {
                setBit >>= 1;
            }
            // if the bit is unset we can set bits to the right in any way and the condition will be met
            possibilities += setBit;
            setBit >>= 1;
        }
        return possibilities;
    }
}
