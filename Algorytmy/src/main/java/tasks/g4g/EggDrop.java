package tasks.g4g;

public class EggDrop {
    public static int minEggDrops(int eggCount, int floors) {
        int[][] memo = new int[eggCount][floors];
        return minEggDropsRecursive(eggCount, floors, memo);
    }

    private static int minEggDropsRecursive(int eggCount, int floors, int[][] memo) {
        if (eggCount <= 0 || floors <= 0) {
            return 0;
        } else if (eggCount == 1) {
            return floors;
        } else if (memo[eggCount - 1][floors - 1] != 0) {
            return memo[eggCount - 1][floors - 1];
        } else {
            int minDrops = Integer.MAX_VALUE;
            for (int i = 0; i < floors; i++) {
                int minDropsOnIth = Math.max(
                        // the egg breaks on the ith floor
                        minEggDropsRecursive(eggCount - 1, i, memo),
                        // the egg doesn't break on the ith floor
                        minEggDropsRecursive(eggCount, floors - i - 1, memo)
                );
                minDropsOnIth += 1; // we need to count the drop
                if (minDropsOnIth < minDrops) {
                    minDrops = minDropsOnIth;
                }
            }
            memo[eggCount - 1][floors - 1] = minDrops;
            return minDrops;
        }
    }

    public static int minEggDropIterative(int eggCount, int totalFloors) {
        int[][] solution = new int[eggCount + 1][totalFloors + 1];
        for (int i = 0; i <= totalFloors; i++) {
            solution[0][i] = 0; // it's always 0 if we have no eggs
            solution[1][i] = i; // i floors need i drops of a single egg
        }
        for (int i = 0; i <= eggCount; i++) {
            solution[i][0] = 0; // 0 floors need 0 drops
        }
        for (int floors = 1; floors <= totalFloors; floors++) {
            for (int eggs = 2; eggs <= eggCount; eggs++) {
                int minDrops = Integer.MAX_VALUE;
                for (int floorDroppedOn = 1; floorDroppedOn <= floors; floorDroppedOn++) {
                    int drops = Math.max(
                            // egg breaks
                            solution[eggs - 1][floorDroppedOn - 1],
                            // egg doesn't break
                            solution[eggs][floors - floorDroppedOn]
                    );
                    drops += 1; // count the drop
                    if (drops < minDrops) {
                        minDrops = drops;
                    }
                }
                solution[eggs][floors] = minDrops;
            }
        }
        return solution[eggCount][totalFloors];
    }
}
