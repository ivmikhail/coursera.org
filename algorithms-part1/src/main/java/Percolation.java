import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] grid;
    private int openSites;
    private final WeightedQuickUnionUF uf;
    private final int top;
    private final int bottom;
    private final int n;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("Grid must be NxN");

        this.n = n;
        int nSquared = n * n;
        uf = new WeightedQuickUnionUF(nSquared + 2);

        grid = new boolean[n][n];
        top = nSquared;
        bottom = nSquared + 1;
        openSites = 0;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (isOpen(row, col)) return;

        grid[row - 1][col - 1] = true;
        openSites++;

        // connect to top
        int value = getValue(row, col);

        if (row == 1) {
            uf.union(value, top);
        } else if (isOpen(row - 1, col)) {
            uf.union(value, getValue(row - 1, col));
        }

        // connect to left
        if (col != 1 && isOpen(row, col - 1)) {
            uf.union(value, getValue(row, col - 1));
        }

        // connect to right
        if (col != n && isOpen(row, col + 1)) {
            uf.union(value, getValue(row, col + 1));
        }

        // connect to bottom
        if (row == n) {
            uf.union(value, bottom);
        } else if (isOpen(row + 1, col)) {
            uf.union(value, getValue(row + 1, col));
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);

        return grid[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row, col);

        return uf.connected(getValue(row, col), top);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.connected(top, bottom);
    }

    private int getValue(int row, int col) {
        return (row - 1) * n + (col - 1);
    }

    private void validate(int row, int col) {
        if (row < 1 || row > n) {
            throw new IllegalArgumentException("row must be > 0 and <= " + n);
        }

        if (col < 1 || col > n) {
            throw new IllegalArgumentException("col must be > 0 and <= " + n);
        }
    }
}
