package tasks.g4g;

//Given a paper of size A x B.
// Task is to cut the paper into squares of any size.
// Find the minimum number of squares that can be cut from the paper.
public class MinimumSquares {
    public static int minimumSquares(int dimX, int dimY) {
        int[][] memo = new int[dimX + 1][dimY + 1];
        return minimumSquaresRecursive(dimX, dimY, memo);
    }

    private static int minimumSquaresRecursive(int dimX, int dimY, int[][] memo) {
        if (memo[dimX][dimY] != 0) {
            return memo[dimX][dimY];
        } else if (dimX == 0 || dimY == 0) {
            return 0;
        } else {
            int smallerDim = dimX;
            if (dimY < dimX) {
                smallerDim = dimY;
            }

            int currentMin = Integer.MAX_VALUE;
            for (int dim = smallerDim; dim >= 1; dim--) {
                int squaresHorizontal = dimX / dim;
                int rectangleRightWidth = dimX % dim;
                int squaresVertical = dimY / dim;
                int rectangleBottomHeight = dimY % dim;

                int squareCount = squaresHorizontal * squaresVertical;
                if (squareCount < currentMin) {
                    squareCount += minimumSquares(rectangleRightWidth, dimY);
                }
                if (squareCount < currentMin) {
                    squareCount += minimumSquares(dimX - rectangleRightWidth, rectangleBottomHeight);
                }

                if (squareCount < currentMin) {
                    currentMin = squareCount;
                }
            }
            return currentMin;
        }
    }

    public static int minimumSquaresIterative(int dimX, int dimY) {
        int[][] solution = new int[dimX + 1][dimY + 1];

        for (int i = 1; i <= dimX; i++) {
            solution[i][1] = i;
        }
        for (int i = 1; i <= dimY; i++) {
            solution[1][i] = i;
        }

        for (int x = 2; x <= dimX; x++) {
            for (int y = 2; y <= dimY; y++) {
                int smallerDim = x;
                if (y < x) {
                    smallerDim = y;
                }
                int currentMin = Integer.MAX_VALUE;
                for (int dim = smallerDim; dim >= 1; dim--) {
                    int squaresHorizontal = x / dim;
                    int rectangleRightWidth = x % dim;
                    int squaresVertical = y / dim;
                    int rectangleBottomHeight = y % dim;
                    int squareCount = squaresHorizontal * squaresVertical;
                    squareCount += solution[rectangleRightWidth][dimY];
                    squareCount += solution[x - rectangleRightWidth][rectangleBottomHeight];
                    if (squareCount < currentMin) {
                        currentMin = squareCount;
                    }
                }
                solution[x][y] = currentMin;
            }
        }

        return solution[dimX][dimY];
    }
}
