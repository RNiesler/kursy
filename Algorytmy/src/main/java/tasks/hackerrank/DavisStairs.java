package tasks.hackerrank;

public class DavisStairs {
    static int stepPermsRecursive(int n) {
        int[] cache = new int[n + 1];
        return stepPermsRecursive(n, cache);
    }

    private static int stepPermsRecursive(int n, int[] cache) {
        if (cache[n] != 0) {
            return cache[n];
        }
        int result;
        if (n <= 1) {
            result = 1;
        } else if (n == 2) {
            result = 2;
        } else { // n >= 3
            result = stepPerms(n - 1) + stepPerms(n - 2) + stepPerms(n - 3);
        }

        cache[n] = result;
        return result;
    }

    static int stepPerms(int n) {
        if (n <= 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        } else if (n == 3) {
            return 4;
        } else {
            int[] prevSteps = new int[]{1, 2, 4};
            for (int i = 3; i < n; i++) {
                int next = prevSteps[0] + prevSteps[1] + prevSteps[2];
                prevSteps[0] = prevSteps[1];
                prevSteps[1] = prevSteps[2];
                prevSteps[2] = next;
            }
            return prevSteps[2];
        }
    }
}
