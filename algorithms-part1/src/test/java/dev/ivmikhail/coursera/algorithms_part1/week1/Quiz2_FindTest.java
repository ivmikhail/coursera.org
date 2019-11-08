package dev.ivmikhail.coursera.algorithms_part1.week1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Quiz2_FindTest {

    private Quiz2_Find wqupc;

    @BeforeEach
    void setUp() {
        wqupc = new Quiz2_Find(10);
    }

    @Test
    void connected() {
        wqupc.union(1, 0);
        wqupc.union(1, 9);
        wqupc.union(9, 5);

        assertTrue(wqupc.connected(0, 5));
    }

    @Test
    void find() {
        wqupc.union(1, 2);
        wqupc.union(2, 6);
        wqupc.union(6, 9);

        assertEquals(9, wqupc.find(1));
        assertEquals(9, wqupc.find(2));
        assertEquals(9, wqupc.find(6));
        assertEquals(9, wqupc.find(9));


        assertEquals(3, wqupc.find(3));
        assertEquals(4, wqupc.find(4));
        assertEquals(5, wqupc.find(5));
        assertEquals(7, wqupc.find(7));
        assertEquals(8, wqupc.find(8));
    }

    @Test
    void findOne() {
        wqupc.union(0, 1);

        assertEquals(1, wqupc.find(0));
        assertEquals(1, wqupc.find(1));
        assertEquals(2, wqupc.find(2));
        assertEquals(3, wqupc.find(3));
        assertEquals(4, wqupc.find(4));
        assertEquals(5, wqupc.find(5));
        assertEquals(6, wqupc.find(6));
        assertEquals(7, wqupc.find(7));
        assertEquals(8, wqupc.find(8));
        assertEquals(9, wqupc.find(9));
    }
}