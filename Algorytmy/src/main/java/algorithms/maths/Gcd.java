package algorithms.maths;

import lombok.AllArgsConstructor;

public class Gcd {
    static int euclideanIter(int a, int b) {
        while (b > 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    static int euclideanRecursive(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return euclideanRecursive(b, a % b);
        }
    }

    @AllArgsConstructor
    static class ExtendedResult {
        int gcd, eA, eB;
    }

    static ExtendedResult extendedEuclidean(int a, int b) {
        int sA = 1, sB = 0, tA = 0, tB = 1;
        while (b > 0) {
            int q = a / b;
            int temp = b;
            b = a % b;
            a = temp;

            temp = sB;
            sB = sA - q * sB;
            sA = temp;

            temp = tB;
            tB = tA - q * tB;
            tA = temp;
        }
        return new ExtendedResult(a, sA, tA);
    }
}
