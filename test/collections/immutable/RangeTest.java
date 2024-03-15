package collections.immutable;

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
    void testToString() {
        System.out.println("toString");
        int begin = ExtendedRandom.nextInt(1024) + 16;
        int step = ExtendedRandom.nextInt(16) + 4;
        int end = begin + step * ExtendedRandom.nextInt(256) + step;
        Range range = new Range(begin, end, step);
        String expected = begin + " to " + end + " by " + step;
        String actual = range.toString();
        assertEquals(expected, actual);
    }

    @Test
    void testToStringStepOneIsTacit() {
        int begin = ExtendedRandom.nextInt(256) + 4;
        int end = begin + ExtendedRandom.nextInt(32) + 8;
        Range range = new Range(begin, end);
        String expected = begin + " to " + end;
        String actual = range.toString();
        assertEquals(expected, actual);
    }

    @Test
    void testReferentialEquality() {
        int begin = ExtendedRandom.nextInt(1024) + 16;
        int step = ExtendedRandom.nextInt(4) + 1;
        int end = begin + step * ExtendedRandom.nextInt(256) + step;
        Range range = new Range(begin, end, step);
        assertEquals(range, range);
    }

    void testEquals() {
        System.out.println("equals");fail("WRITE THIS TEST");
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
