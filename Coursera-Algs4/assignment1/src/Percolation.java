import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF weightedQuickUnionUF;

    private int n;
    private boolean[] matrix;
    private int openCounter = 0;
    private int openedInFirstRow = -1;

    /**
     * create n-by-n grid, with all sites blocked
     */
    public Percolation(int n) {
        this.weightedQuickUnionUF = new WeightedQuickUnionUF(n * n);
        this.n = n;
        matrix = new boolean[n * n];
    }

    /**
     * open site (row, col) if it is not open already
     */
    public void open(int row, int col) {
        if (!isOpen(row, col)) {
            int ind = getIndex(row, col);
            matrix[ind] = true;
            openCounter++;
            if (row == 1) {
                if (openedInFirstRow >= 0) {
                    weightedQuickUnionUF.union(openedInFirstRow, ind);
                } else {
                    openedInFirstRow = ind;
                }
            }
            // do unions
            int[] neighbours = {getTop(ind), getBottom(ind), getLeft(ind), getRight(ind)};
            for (int neighbour : neighbours) {
                if (neighbour >= 0 && matrix[neighbour]) {
                    weightedQuickUnionUF.union(ind, neighbour);
                }
            }
        }
    }

    /**
     * is site (row, col) open?
     */
    public boolean isOpen(int row, int col) {
        return matrix[getIndex(row, col)];
    }

    /**
     * is site (row, col) full?
     */
    public boolean isFull(int row, int col) {
        if (openedInFirstRow >= 0 && isOpen(row, col)) {
            return weightedQuickUnionUF.connected(openedInFirstRow, getIndex(row, col));
        } else {
            return false;
        }
    }

    /**
     * number of open sites
     */
    public int numberOfOpenSites() {
        return openCounter;
    }

    /**
     * does the system percolate?
     */
    public boolean percolates() {
        if (openedInFirstRow >= 0) {
            for (int i = 1; i <= n; i++) {
                if (isFull(n, i)) {
                    return true;
                }
            }
        }
        return false;
    }

    private int getIndex(int row, int col) {
        return (row - 1) * n + (col - 1);
    }

    private int getTop(int ind) {
        return ind - n;
    }

    private int getBottom(int ind) {
        int bottomInd = ind + n;
        if (bottomInd < n * n) {
            return bottomInd;
        } else {
            return -1;
        }
    }

    private int getLeft(int ind) {
        if (ind % n != 0) {
            return ind - 1;
        } else {
            return -1;
        }
    }

    private int getRight(int ind) {
        if (ind % n != n - 1) {
            return ind + 1;
        } else {
            return -1;
        }
    }


}