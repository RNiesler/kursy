package tasks.hackerrank;

import java.util.Objects;

public class DfcConnectedCells {
    private static final int VISITED = 2;

    static int maxRegion(int[][] grid) {
        Objects.requireNonNull(grid);
        int n = grid.length;
        int m = grid[0].length;
        int maxRegion = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    int region = dfsRegion(i, j, grid);
                    if (region > maxRegion) {
                        maxRegion = region;
                    }
                }
            }
        }
        return maxRegion;
    }

    private static int dfsRegion(int row, int column, int[][] grid) {
        if (row < 0 || column < 0 || row >= grid.length || column >= grid[row].length ||
                grid[row][column] != 1) {
            return 0;
        } else {
            int regionSize = 1;
            grid[row][column] = VISITED;
            // bottom-left-diagonal
            regionSize += dfsRegion(row + 1, column - 1, grid);
            // bottom
            regionSize += dfsRegion(row + 1, column, grid);
            // bottom-right-diagonal
            regionSize += dfsRegion(row + 1, column + 1, grid);
            // right
            regionSize += dfsRegion(row, column + 1, grid);
            // left
            regionSize += dfsRegion(row, column - 1, grid);
            // upper-left-diagonal
            regionSize += dfsRegion(row - 1, column - 1, grid);
            // top
            regionSize += dfsRegion(row - 1, column, grid);
            // upper-right-diagonal
            regionSize += dfsRegion(row - 1, column + 1, grid);
            return regionSize;
        }
    }
}
