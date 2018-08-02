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

    public static int unboundedKnapsack(final int capacity, final Item[] items) {
        int[] memo = new int[capacity + 1];
        return unboundedKnapsackRecursive(capacity, items, memo);
    }

    private static int unboundedKnapsackRecursive(final int capacity, final Item[] items, final int[] memo) {
        if (memo[capacity] != 0) {
            return memo[capacity];
        } else {
            int maxValue = 0;
            for (Item item : items) {
                if (item.weight <= capacity) {
                    int value = item.value + unboundedKnapsack(capacity - item.weight, items);
                    if (value > maxValue) {
                        maxValue = value;
                    }
                }
            }
            return maxValue;
        }
    }

    public static int unboundedBottomUp(final int capacity, final Item[] items) {
        int memo[] = new int[capacity + 1];
        for (int i = 1; i <= capacity; i++) {
            memo[i] = memo[i - 1];
            for (Item item : items) {
                if (item.weight <= i) {
                    int valueWithItem = item.value + memo[i - item.weight];
                    memo[i] = Math.max(valueWithItem, memo[i]);
                }
            }
        }
        return memo[capacity];
    }

    public static int solveBottomUp(final int capacity, final Item[] items) {
        int memo[][] = new int[items.length + 1][capacity + 1];
        for (int item = 0; item < items.length; item++) {
            for (int subCapacity = 1; subCapacity <= capacity; subCapacity++) {
                if (items[item].weight <= subCapacity) {
                    int valueWithItem = items[item].value + memo[item + 1][capacity - items[item].weight];
                    memo[item + 1][subCapacity] = Math.max(valueWithItem, memo[item][capacity]);
                }
            }
        }

        return memo[items.length][capacity];
    }
}
