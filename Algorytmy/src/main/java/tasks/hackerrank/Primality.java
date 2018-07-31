package tasks.hackerrank;

public class Primality {
    static String primalitySieve(int n) {
        if (n <= 1) {
            return "Not prime";
        }
        int sqN = (int) Math.floor(Math.sqrt(n));
        boolean[] sieve = new boolean[n];
        // reverse logic to avoid array initialization - false means that index+1 is a prime
        for (int i = 1; i < sqN; i++) {
            if (!sieve[i]) { // i+1 is a prime
                int x = 2 * (i + 1);
                while (x <= n) {
                    sieve[x - 1] = true;    // not prime
                    x += i + 1;
                }
                if (sieve[n - 1]) {
                    return "Not prime";
                }
            }
        }
        return "Prime";
    }

    static String primality(int n) {
        if (n <= 1) {
            return "Not prime";
        }
        int sqN = (int) Math.floor(Math.sqrt(n));
        for (int i = 2; i <= sqN; i++) {
            if (n % i == 0)
                return "Not prime";
        }
        return "Prime";
    }
}
