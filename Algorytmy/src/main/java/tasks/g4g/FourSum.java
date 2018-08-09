package tasks.g4g;

import java.util.HashMap;
import java.util.Optional;

public class FourSum {
    public static Optional<int[]> fourSum(int[] input, int targetSum) {
        class Pair {
            Pair(int x, int y) {
                this.x = x;
                this.y = y;
            }

            int x;
            int y;
        }

        HashMap<Integer, Pair> pairSums = new HashMap<>();
        for (int i = 0; i < input.length; i++) {
            for (int j = i + 1; j < input.length; j++) {
                int currentSum = input[i] + input[j];
                if (pairSums.containsKey(targetSum - currentSum)) {
                    Pair pair = pairSums.get(targetSum - currentSum);
                    if (pair.x != i && pair.x != j && pair.y != i && pair.y != j) {
                        return Optional.of(new int[]{input[pair.x], input[pair.y], input[i], input[j]});
                    }
                }
                Pair currentPair = new Pair(i, j);
                pairSums.put(currentSum, currentPair);
            }
        }
        return Optional.empty();
    }
}
