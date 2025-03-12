package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PairTest {
    private Pair <Integer,String> pair;
    @BeforeEach
    void setUp() {
        pair = new Pair<>(1,"a");
    }

    @AfterEach
    void tearDown() {
        pair = null;
        System.gc();
    }

    @Test
    void getK() {
        assertEquals(1,pair.getK());
    }

    @Test
    void setK() {
        pair.setK(3);
        assertEquals(3,pair.getK());
    }

    @Test
    void getV() {
        assertEquals("a",pair.getV());
    }

    @Test
    void setV() {
        pair.setV("b");
        assertEquals("b",pair.getV());
    }

    @Test
    void getKV() {
        assertEquals("Pair{k=1, v=a}",pair.getKV());
    }

    @Test
    void setPair() {
        pair.setKV(2,"b");
        assertEquals("Pair{k=2, v=b}",pair.getKV());
    }

    @Test
    void testToString() {
        assertEquals("Pair{k=1, v=a}",pair.toString());
    }
}