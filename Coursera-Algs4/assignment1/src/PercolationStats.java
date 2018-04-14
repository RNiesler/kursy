import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] results;

    /**
     * perform trials independent experiments on an n-by-n grid
     */
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }
        results = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()) {
                int toOpenInd = StdRandom.uniform(n * n);
                int row = toOpenInd / n + 1;
                int col = toOpenInd % n + 1;
                while (percolation.isOpen(row, col)) {
                    toOpenInd = StdRandom.uniform(n * n);
                    row = toOpenInd / n + 1;
                    col = toOpenInd % n + 1;
                }
                percolation.open(row, col);
            }
            results[i] = (double) percolation.numberOfOpenSites() / (n*n);
        }
    }

    /**
     * sample mean of percolation threshold
     */
    public double mean() {
        return StdStats.mean(results);
    }

    /**
     * sample standard deviation of percolation threshold
     */
    public double stddev() {
        return StdStats.stddev(results);
    }

    /**
     * low  endpoint of 95% confidence interval
     */
    public double confidenceLo() {
        return mean() - 1.96 * stddev() / Math.sqrt(results.length);
    }

    /**
     * high endpoint of 95% confidence interval
     */
    public double confidenceHi() {
        return mean() + 1.96 * stddev() / Math.sqrt(results.length);
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Expecting 2 arguments: n - matrix size, T - trials number");
        }
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(n, trials);
        StdOut.printf("%-24s = %f\n", "mean", stats.mean());
        StdOut.printf("%-24s = %f\n", "stddev", stats.stddev());
        StdOut.printf("%-24s = [%f, %f]\n", "95%% confidence interval", stats.confidenceLo(), stats.confidenceHi());
    }
}