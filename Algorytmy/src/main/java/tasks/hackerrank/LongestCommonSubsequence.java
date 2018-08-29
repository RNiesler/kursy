package tasks.hackerrank;

import java.util.LinkedList;
import java.util.List;

public class LongestCommonSubsequence {
    static List<Integer> longestCommonSubsequenceRecursive(int[] a, int[] b) {
        return longestCommonSubsequenceRec(a, 0, b, 0);
    }

    static LinkedList<Integer> longestCommonSubsequenceRec(int[] a, int startA, int[] b, int startB) {
        if (startA == a.length || startB == b.length) {
            return new LinkedList<>();
        } else if (a[startA] == b[startB]) {
            LinkedList<Integer> list = longestCommonSubsequenceRec(a, startA + 1, b, startB + 1);
            list.addFirst(a[startA]);
            return list;
        } else {
            LinkedList<Integer> listA = longestCommonSubsequenceRec(a, startA + 1, b, startB);
            LinkedList<Integer> listB = longestCommonSubsequenceRec(a, startA, b, startB + 1);
            return listB.size() > listA.size() ? listB : listA;
        }
    }

    static int longestCommonSubsequenceLength(int[] a, int[] b) {
        int[][] subsequenceLength = new int[a.length + 1][b.length + 1];
        for (int iA = 1; iA <= a.length; iA++) {
            for (int iB = 1; iB <= b.length; iB++) {
                if (a[iA - 1] == b[iB - 1]) {
                    subsequenceLength[iA][iB] = subsequenceLength[iA - 1][iB - 1] + 1;
                } else {
                    int aa = subsequenceLength[iA - 1][iB];
                    int bb = subsequenceLength[iA][iB - 1];
                    if (aa < bb) {
                        subsequenceLength[iA][iB] = bb;
                    } else {
                        subsequenceLength[iA][iB] = aa;
                    }
                }
            }
        }
        return subsequenceLength[a.length][b.length];
    }

    enum RowCol {
        UP,
        LEFT,
        MATCH
    }

    static int[] longestCommonSubsequence(int[] a, int[] b) {
        int[][] subsequenceLength = new int[a.length + 1][b.length + 1];
        RowCol[][] predecessors = new RowCol[a.length + 1][b.length + 1];
        for (int iA = 1; iA <= a.length; iA++) {
            for (int iB = 1; iB <= b.length; iB++) {
                if (a[iA - 1] == b[iB - 1]) {
                    subsequenceLength[iA][iB] = subsequenceLength[iA - 1][iB - 1] + 1;
                    predecessors[iA][iB] = RowCol.MATCH;
                } else {
                    int aa = subsequenceLength[iA - 1][iB];
                    int bb = subsequenceLength[iA][iB - 1];
                    if (aa < bb) {
                        subsequenceLength[iA][iB] = bb;
                        predecessors[iA][iB] = RowCol.LEFT;
                    } else {
                        subsequenceLength[iA][iB] = aa;
                        predecessors[iA][iB] = RowCol.UP;
                    }
                }
            }
        }
        int iA = a.length;
        int iB = b.length;
        int length = subsequenceLength[a.length][b.length];
        int[] result = new int[length];
        while (iA > 0 && iB > 0) {
            if(predecessors[iA][iB] == RowCol.MATCH) {
                result[--length] = a[iA-1];
                iA--;
                iB--;
            } else if(predecessors[iA][iB] == RowCol.UP) {
                iA--;
            } else {
                iB--;
            }
        }
        return result;
    }
}
