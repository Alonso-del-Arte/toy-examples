package collections.immutable;

import java.util.Random;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import randomness.ExtendedRandom;

class RangeTest {

    @Test
    void testGetBegin() {
        System.out.println("getBegin");
        int expected = ExtendedRandom.nextInt();
        int finish = expected + 1;
        int step = 1;
        Range range = new Range(expected, finish, step);
        int actual = range.getStart();
        assertEquals(expected, actual);
    }

    @Test
    void testGetFinish() {
        System.out.println("getFinish");
        int begin = ExtendedRandom.nextInt();
        int expected = begin + 1;
        int step = 1;
        Range range = new Range(begin, expected, step);
        int actual = range.getFinish();
        assertEquals(expected, actual);
    }

    @Test
    void testGetStep() {
        System.out.println("getStep");
        int expected = ExtendedRandom.nextInt(128) + 2;
        int begin = -2 * ExtendedRandom.nextInt(65536);
        int end = 2 * ExtendedRandom.nextInt(65535);
        Range range = new Range(begin, end, expected);
        int actual = range.getStep();
        assertEquals(expected, actual);
    }

    @Test
    void testAuxiliaryConstructorInfersPositiveOneStep() {
        int begin = -ExtendedRandom.nextInt(256) - 4;
        int end = ExtendedRandom.nextInt(-begin) + 16;
        Range range = new Range(begin, end);
        int expected = 1;
        int actual = range.getStep();
        assertEquals(expected, actual);
    }

    @Test
    void testAuxiliaryConstructorInfersNegativeOneStep() {
        int begin = ExtendedRandom.nextInt(256) + 4;
        int end = -ExtendedRandom.nextInt(begin) - 16;
        Range range = new Range(begin, end);
        int expected = -1;
        int actual = range.getStep();
        assertEquals(expected, actual);
    }

}
