package tasks.g4g;

import java.util.Objects;

public class FloodFill {
    public static void floodFill(int[][] matrix, int row, int col, int newColor) {
        Objects.requireNonNull(matrix);
        int prevColor = matrix[row][col];
        if (newColor != prevColor) {
            floodFillRecursive(matrix, row, col, prevColor, newColor);
        }
    }

    private static void floodFillRecursive(int[][] matrix, int row, int col, int prevColor, int newColor) {
        if (row >= 0 && row < matrix.length &&
                col >= 0 && col < matrix[row].length &&
                matrix[row][col] == prevColor) {
            matrix[row][col] = newColor;
            floodFillRecursive(matrix, row - 1, col, prevColor, newColor);
            floodFillRecursive(matrix, row + 1, col, prevColor, newColor);
            floodFillRecursive(matrix, row, col - 1, prevColor, newColor);
            floodFillRecursive(matrix, row, col + 1, prevColor, newColor);

        }
    }

}
