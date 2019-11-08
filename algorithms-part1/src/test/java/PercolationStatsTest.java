import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PercolationStatsTest {
    private static final double TOLERANCE = 0.01;

    @Test
    void test_200_100() {
        PercolationStats stats = new PercolationStats(200, 100);

        assertEquals(0.5929934999999997, stats.mean(), TOLERANCE);
        assertEquals(0.00876990421552567, stats.stddev(), TOLERANCE);
        assertEquals(0.5912745987737567, stats.confidenceLo(), TOLERANCE);
        assertEquals(0.5947124012262428, stats.confidenceHi(), TOLERANCE);
    }

    @Test
    void test_2_10000() {
        PercolationStats stats = new PercolationStats(2, 10_000);

        assertEquals(0.666925, stats.mean(), TOLERANCE);
        assertEquals(0.11776536521033558, stats.stddev(), TOLERANCE);
        assertEquals(0.6646167988418774, stats.confidenceLo(), TOLERANCE);
        assertEquals(0.6692332011581226, stats.confidenceHi(), TOLERANCE);
    }

    @Test
    void test_2_100000() {
        PercolationStats stats = new PercolationStats(2, 10_000);

        assertEquals(0.6669475, stats.mean(), TOLERANCE);
        assertEquals(0.11775205263262094, stats.stddev(), TOLERANCE);
        assertEquals(0.666217665216461, stats.confidenceLo(), TOLERANCE);
        assertEquals(0.6676773347835391, stats.confidenceHi(), TOLERANCE);
    }
}