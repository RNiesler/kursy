package tasks.hackerrank;

import java.util.HashMap;

public class IceCreamParlor {
    static void whatFlavors(int[] cost, int money) {
        HashMap<Integer, Integer> complementToPositionMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < cost.length; i++) {
            if (complementToPositionMap.containsKey(cost[i])) {
                System.out.println((complementToPositionMap.get(cost[i]) + 1) + " " + (i + 1));
                return;
            } else {
                complementToPositionMap.putIfAbsent(money - cost[i], i);
            }
        }
    }
}
