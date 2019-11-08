import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private static final double THRESHOLD = 1.96d;

    private final double mean;
    private final double stddev;
    private final double confidenceLo;
    private final double confidenceHi;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0) throw new IllegalArgumentException("n must be greater than 0");
        if (trials <= 0) throw new IllegalArgumentException("trials must be greater than 0");

        double[] fractions = new double[trials];

        int max = n + 1;
        int nSquared = n * n;
        for (int i = 0; i < trials; i++) {
            Percolation model = new Percolation(n);
            do {
                int row = StdRandom.uniform(1, max);
                int col = StdRandom.uniform(1, max);

                model.open(row, col);
            } while (!model.percolates());

            fractions[i] = (double) model.numberOfOpenSites() / nSquared;
        }

        mean = StdStats.mean(fractions);
        stddev = StdStats.stddev(fractions);
        confidenceLo = mean - THRESHOLD * stddev / Math.sqrt(trials);
        confidenceHi = mean + THRESHOLD * stddev / Math.sqrt(trials);

    }

    // sample mean of percolation threshold
    public double mean() {
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return stddev;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return confidenceLo;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return confidenceHi;
    }

    // test client (see below)
    public static void main(String[] args) {
        /*
        mean                    = 0.5929934999999997
        stddev                  = 0.00876990421552567
        95% confidence interval = [0.5912745987737567, 0.5947124012262428]
         */
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);

        PercolationStats stats = new PercolationStats(n, trials);

        StdOut.println("mean                    = " + stats.mean());
        StdOut.println("stdev                   = " + stats.stddev());
        StdOut.println("95% confidence interval = [" + stats.confidenceLo() + ", " + stats.confidenceHi() + "]");
    }
}