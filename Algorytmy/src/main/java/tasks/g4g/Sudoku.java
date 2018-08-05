package tasks.g4g;

public class Sudoku {
    private static final int BOARD_SIZE = 9;
    private static final int SUBBOARD_SIZE = 3;

    public static boolean solve(int[][] board) {
        // verify sizes of the board
        int row = 0;
        int col = 0;
        while (row < BOARD_SIZE && board[row][col] != 0) {
            if (col < BOARD_SIZE - 1) {
                col++;
            } else {
                row++;
                col = 0;
            }
        }
        if (row == BOARD_SIZE) {
            return true;
        } else {
            boolean[] takenNumbers = calculateTakenNumbers(row, col, board);
            for (int i = 0; i < BOARD_SIZE; i++) {
                if (!takenNumbers[i]) {
                    board[row][col] = i + 1;
                    if (solve(board)) {
                        return true;
                    }
                    board[row][col] = 0;
                }
            }
            return false;
        }
    }

    private static boolean[] calculateTakenNumbers(int row, int col, int[][] board) {
        // can take boolean[10] to avoid instantiation of array
        boolean[] taken = new boolean[10];
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[row][i] > 0) {
                taken[board[row][i]-1] = true;
            }
            if (board[i][col] > 0) {
                taken[board[i][col]-1] = true;
            }
        }
        int subboardRow = (row / SUBBOARD_SIZE) * SUBBOARD_SIZE; // integer division!
        int subboardCol = (col / SUBBOARD_SIZE) * SUBBOARD_SIZE; // integer division
        for (int i = subboardRow; i < subboardRow + SUBBOARD_SIZE; i++) {
            for (int j = subboardCol; j < subboardCol + SUBBOARD_SIZE; j++) {
                if (board[i][j] > 0) {
                    taken[board[i][j]-1] = true;
                }
            }
        }
        return taken;
    }

}
