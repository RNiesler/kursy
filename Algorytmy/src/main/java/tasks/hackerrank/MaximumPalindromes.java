package tasks.hackerrank;

import java.util.Arrays;

// https://www.hackerrank.com/challenges/maximum-palindromes/problem
public class MaximumPalindromes {
    private static final int MODULO = 1000000007;
    private static final int ALPHABET_SIZE = 26;
    private static String str;
    private static int[] counts;

    // Complete the initialize function below.
    static void initialize(String s) {
        str = s;
        counts = new int[ALPHABET_SIZE];
        // This function is called once before all queries.

    }

    static int moduloFactorial(int n, int modulo) {
        int result = 1;
        for (int i = 2; i <= n; i++) {
            result = (result * i) % modulo;
        }
        return result;
    }

    static int answerQuery(int l, int r) {
        Arrays.fill(counts, 0);
        // count letters in the substring
        for (int i = l; i <= r; i++) {
            counts[str.charAt(i-1) - 'a']++;
        }

        int odds = 0;
        int maxLength = 0;
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            if (counts[i] % 2 == 1) {
                odds++;
            }
            counts[i] >>= 1; // divide by 2
            maxLength += counts[i];
        }

        int possibilities = odds == 0 ? 1 : odds; // different options for middle character
        // maxLength! / (counts[0]! *... * counts[n-2]! * counts[n-1]!)
        possibilities *= moduloFactorial(maxLength, MODULO) % MODULO;
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            if (counts[i] > 0) {
                possibilities /= moduloFactorial(counts[0], MODULO); //TODO this is wrong
            }
        }
        return possibilities;
    }
}
