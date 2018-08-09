package tasks.g4g;

import java.util.Objects;

public class MatrixFill {
    private final static char O = 'O';
    private final static char O_FILL = '-';
    private final static char X = 'X';

    public static void matrixFill(final char[][] matrix) {
        Objects.requireNonNull(matrix);
        for (int i = 0; i < matrix.length; i++) {
            fillRecursive(matrix, i, 0);
            fillRecursive(matrix, i, matrix[i].length - 1);
        }
        for (int i = 0; i < matrix[0].length; i++) {
            fillRecursive(matrix, 0, i);
            fillRecursive(matrix, matrix.length - 1, i);
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == O) {
                    matrix[i][j] = X;
                } else if (matrix[i][j] == O_FILL) {
                    matrix[i][j] = O;
                }
            }
        }
    }

    private static void fillRecursive(final char[][] matrix, int row, int col) {
        if (row >= 0 && row < matrix.length &&
                col >= 0 && col < matrix[row].length &&
                matrix[row][col] == O) {
            matrix[row][col] = O_FILL;
            fillRecursive(matrix, row - 1, col);
            fillRecursive(matrix, row - 1, col + 1);
            fillRecursive(matrix, row, col + 1);
            fillRecursive(matrix, row + 1, col);
            fillRecursive(matrix, row, col - 1);
        }
    }
}
