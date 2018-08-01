package algorithms.dp;

import lombok.AllArgsConstructor;
import lombok.Data;

public class Knapsack {

    @Data
    @AllArgsConstructor
    public static class Item {
        int value;
        int weight;
    }

    public static int solve(final int capacity, final Item[] items) {
        int[][] memo = new int[items.length][capacity + 1];
        return solveRecursive(capacity, items, 0, memo);
    }

    private static int solveRecursive(final int capacity, final Item[] items, final int item, final int[][] memo) {
        if (item >= items.length) {
            return 0;
        }
        if (memo[item][capacity] != 0) {
            return memo[item][capacity];
        } else {
            int result = solveRecursive(capacity, items, item + 1, memo);
            if (items[item].getWeight() <= capacity) {
                int itemValue = items[item].getValue();
                result = Math.max(result, itemValue + solveRecursive(capacity - items[item].getWeight(), items, item + 1, memo));
            }
            memo[item][capacity] = result;
            return result;
        }
    }
}
