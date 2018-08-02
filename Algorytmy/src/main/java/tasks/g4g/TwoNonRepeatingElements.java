package tasks.g4g;

import lombok.AllArgsConstructor;
import lombok.Data;

public class TwoNonRepeatingElements {
    @Data
    @AllArgsConstructor
    public static class Pair {
        private int x1;
        private int x2;
    }

    public static Pair findNonRepeatingPair(int[] input) {
        int xor = 0;
        for (int x : input) {
            xor ^= x;
        }
        if (xor == 0) {
            throw new IllegalStateException();
        }
        int n = 0; // position of the first set bit
        while (xor % 2 == 0) {
            xor >>>= 1;
            n++;
        }
        int mask = 1 << n;
        int xorWithMaskSet = 0;
        int xorWithMaskUnset = 0;
        for (int x : input) {
            if ((x & mask) > 0) {
                xorWithMaskSet ^= x;
            } else {
                xorWithMaskUnset ^= x;
            }
        }
        return new Pair(xorWithMaskSet, xorWithMaskUnset);
    }

}
