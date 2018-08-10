package tasks.g4g;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class MergeOverlappingIntervals {
    public static List<int[]> mergeIntervals(int[][] intervals) {
        Objects.requireNonNull(intervals);
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int[] current = intervals[0];
        int i = 1;
        LinkedList<int[]> result = new LinkedList<>();
        result.add(current);
        while (i < intervals.length) {
            if (current[1] > intervals[i][0]) {
                if (current[1] < intervals[i][1]) {
                    current[1] = intervals[i][1];
                }
            } else {
                current = intervals[i];
                result.add(current);
            }
            i++;
        }
        return result;
    }
}
