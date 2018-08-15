package tasks.hackerrank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Candies {
    public static long candies(int n, int[] arr) {
        Objects.requireNonNull(arr);
        List<long[]> memo = new ArrayList<>();
        memo.add(new long[arr.length]);
        return candiesRecursive(arr, 0, 1, memo);
    }

    private static boolean hasMemo(List<long[]> memo, int start, int candies) {
        return memo.size() > candies && memo.get(candies)[start] > 0;
    }

    private static long getMemo(List<long[]> memo, int start, int candies) {
        return memo.get(candies)[start];
    }

    private static void setMemo(List<long[]> memo, int start, int candies, long value) {
        if(candies >= memo.size()) {
            int inputSize = memo.get(0).length;
            while(memo.size() <= candies) {
                memo.add(new long[inputSize]);
            }
        }
        memo.get(candies)[start] = value;
    }

    private static long candiesRecursive(final int[] grades, final int start, final int candies, final List<long[]> memo) {
        if (start == grades.length - 1) {
            return candies;
        } else if (hasMemo(memo, start, candies)) {
            return getMemo(memo, start,candies);
        } else {
            long result = -1;
            if (grades[start] > grades[start + 1]) {
                int candiesNo = 1;
                while (candiesNo < candies && result < 0) {
                    result = candiesRecursive(grades, start + 1, candiesNo++, memo);
                }
                result = result > 0 ? result + candies : result;
            } else {
                int candiesNo = grades[start] == grades[start + 1] ? 0 : candies;
                while (result < 0) {
                    result = candiesRecursive(grades, start + 1, ++candiesNo, memo);
                }
                result = result + candies;
            }
            setMemo(memo, start, candies, result);
            return result;
        }
    }

    public static long candies2(final int n, final int[] input) {
        long[] candies = new long[input.length];
        candies[0] = 1;
        int i = 0;
        while (true) {
            i = goRight(input, candies, i);
            if (i < input.length-1) {
                i = goLeft(input, candies, i);
            } else {
                break;
            }
        }
        return Arrays.stream(candies).sum();
    }

    private static int goRight(int[] input, long[] candies, int i) {
        while (i < candies.length - 1) {
            if(input[i] < input[i+1]) {
                candies[i+1] = candies[i]+1;
            } else {
                candies[i+1] = 1;
                if(candies[i] == 1) {
                    return i;
                }
            }
            i++;
        }
        return i;
    }

    private static int goLeft(int[] input, long[] candies, int i) {
        while(i > 0) {
            candies[i] += 1;
            if(input[i-1] > input[i] && candies[i] == candies[i-1]) {
                i--;
            } else {
                return i;
            }
        }
        return i;
    }

    public static long candies3(int n, int[] input) {
        long[] candies = new long[input.length];
        candies[0] = 1;
        for(int i=1; i<input.length; i++) {
            if(input[i-1] < input[i]) {
                if(candies[i-1] < 1) {
                    candies[i] = 2;
                } else {
                    candies[i] = candies[i-1] + 1;
                }
            } else if(candies[i-1] > 1) {
                candies[i] = 1;
            } else {
                candies[i] = candies[i-1]-1;
            }
        }

        for(int i=input.length-1; i>0; i--) {
            if(candies[i] < 1) {
                candies[i] = 1;
            }
            if(input[i-1] > input[i] && candies[i-1] <= candies[i]) {
                candies[i-1] = candies[i]+1;
            }
        }

        return Arrays.stream(candies).sum();
    }
}
