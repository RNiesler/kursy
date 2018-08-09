package tasks.g4g;

import java.util.Objects;

public class InPlaceRotate {
    public static void inPlaceRotate(int[][] matrix) {
        Objects.requireNonNull(matrix);
        int mid = (matrix.length - 1) / 2;
        for (int i = 0; i <= mid; i++) {
            int end = matrix.length - i - 1; // -1 to avoid double rotation of corners
            for (int j = i; j < end; j++) {
                fourWaySwap(matrix, i, j);
            }
        }
    }


    private static void fourWaySwap(int[][] matrix, int first, int i) {
        int last = matrix.length - first - 1;
        int start = first + i;
        int end = last - i;
        int temp = matrix[end][first];
        matrix[end][first] = matrix[first][start]; // top row to left column
        int temp2 = matrix[last][end];
        matrix[last][end] = temp; // left column to bottom row

        temp = matrix[start][last];
        matrix[start][last] = temp2; // bottom row to right column
        matrix[first][start] = temp;    // right column to top row
    }

    public static void inPlaceRotateArithmetic(int[] input) {
        int n = (int) Math.sqrt(input.length);
        if (n * n != input.length) {
            throw new IllegalArgumentException("The matrix must be square");
        }
        int mid = (n - 1) / 2;
        for (int i = 0; i <= mid; i++) {
            int end = n - i - 1; // - 1 to avoid double rotation of corners
            for (int j = i; j < end; j++) {
                int rangeStart = i;
                int rangeEnd = n - i - 1;
                int topRow = n * rangeStart + j;
                int leftColumn = (rangeEnd - j) * n + rangeStart;
                int bottomRow = rangeEnd * n + rangeEnd - j;
                int rightColumn = n * (rangeStart + j) + rangeEnd;
                int temp = input[leftColumn];
                input[leftColumn] = input[topRow];
                int temp2 = input[bottomRow];
                input[bottomRow] = temp;
                temp = input[rightColumn];
                input[rightColumn] = temp2;
                input[topRow] = temp;
            }
        }
    }
}
