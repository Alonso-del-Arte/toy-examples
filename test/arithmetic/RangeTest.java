package arithmetic;

import java.time.LocalDateTime;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import randomness.ExtendedRandom;

class RangeTest {

    public static final Random RANDOM = new Random(~System.currentTimeMillis());

    private static Range makeRangeWithNegativeStep() {
        int start = RANDOM.nextInt(128) + 2;
        int step = -RANDOM.nextInt(1, 64);
        int end = start + step * RANDOM.nextInt(1,128);
        return new Range(start, end, step);
    }

    private static Range makeRangeWithImplicitStep1() {
        int start = RANDOM.nextInt(128) + 2;
        int end = start + RANDOM.nextInt(128) + 2;
        return new Range(start, end);
    }

    private static Range makeRangeWithPositiveStep() {
        int start = RANDOM.nextInt(128) + 2;
        int step = RANDOM.nextInt(2, 64);
        int end = start + RANDOM.nextInt(128) + 2;
        return new Range(start, end, step);
    }

    private static Range makeRange() {
        int selector = RANDOM.nextInt() & Integer.MAX_VALUE;
        return switch (selector % 3) {
            case 0 -> makeRangeWithPositiveStep();
            case 1 -> makeRangeWithImplicitStep1();
            default -> makeRangeWithNegativeStep();
        };
    }

    @Test
    void testToString() {
        System.out.println("toString");
        int start = RANDOM.nextInt(Byte.MAX_VALUE) + 1;
        int step = RANDOM.nextInt(2, 128);
        int multiplier = RANDOM.nextInt(2, 128);
        int end = start + multiplier * step;
        Range instance = new Range(start, end, step);
        String expected = start + " to " + end + " by " + step;
        String actual = instance.toString();
        assertEquals(expected, actual);
    }

    @Test
    void testGetStart() {
        System.out.println("getStart");
        int expected = RANDOM.nextInt(Byte.MAX_VALUE) + 1;
        int step = RANDOM.nextInt(2, 128);
        int multiplier = RANDOM.nextInt(2, 128);
        int end = expected + multiplier * step;
        Range instance = new Range(expected, end, step);
        int actual = instance.getStart();
        String message = "Getting start of " + instance;
        assertEquals(expected, actual, message);
    }

    @Test
    void testGetEnd() {
        System.out.println("getEnd");
        int start = RANDOM.nextInt(Byte.MAX_VALUE) + 1;
        int step = RANDOM.nextInt(2, 128);
        int multiplier = RANDOM.nextInt(2, 128);
        int expected = start + multiplier * step;
        Range instance = new Range(start, expected, step);
        int actual = instance.getEnd();
        String message = "Getting end of " + instance;
        assertEquals(expected, actual, message);
    }

    @Test
    void testGetStep() {
        System.out.println("getStep");
        int start = RANDOM.nextInt(Byte.MAX_VALUE) + 1;
        int expected = RANDOM.nextInt(2, 128);
        int multiplier = RANDOM.nextInt(2, 128);
        int end = start + multiplier * expected;
        Range instance = new Range(start, end, expected);
        int actual = instance.getStep();
        String message = "Getting step of " + instance;
        assertEquals(expected, actual, message);
    }

    @Test
    void testToStringAuxConstructor() {
        int start = RANDOM.nextInt(Byte.MAX_VALUE) + 1;
        int end = start + RANDOM.nextInt(1, 128);
        Range instance = new Range(start, end);
        String expected = start + " to " + end;
        String actual = instance.toString();
        assertEquals(expected, actual);
    }

    @Test
    void testGetStartAuxConstructor() {
        int expected = RANDOM.nextInt(Byte.MAX_VALUE) + 1;
        int end = expected + RANDOM.nextInt(1, 128);
        Range instance = new Range(expected, end);
        int actual = instance.getStart();
        String message = "Getting start of " + instance;
        assertEquals(expected, actual, message);
    }

    @Test
    void testGetEndAuxConstructor() {
        int start = RANDOM.nextInt(Byte.MAX_VALUE) + 1;
        int expected = start + RANDOM.nextInt(1, 128);
        Range instance = new Range(start, expected);
        int actual = instance.getEnd();
        String message = "Getting end of " + instance;
        assertEquals(expected, actual, message);
    }

    @Test
    void testGetStepAuxConstructor() {
        int start = RANDOM.nextInt(Byte.MAX_VALUE) + 1;
        int end = start + RANDOM.nextInt(1, 128);
        Range instance = new Range(start, end);
        int expected = 1;
        int actual = instance.getStep();
        String message = "Getting step of " + instance
                + " (implicitly step of 1)";
        assertEquals(expected, actual, message);
    }

    @Test
    void testToStringStep1MainConstructor() {
        int start = RANDOM.nextInt(Byte.MAX_VALUE) + 1;
        int end = start + RANDOM.nextInt(2, 128);
        Range instance = new Range(start, end, 1);
        String expected = start + " to " + end;
        String actual = instance.toString();
        assertEquals(expected, actual);
    }

    @Test
    void testGet() {
        System.out.println("get");
        int start = RANDOM.nextInt(Byte.MAX_VALUE) + 1;
        int end = start + RANDOM.nextInt(1, 128);
        Range instance = new Range(start, end);
        int stop = end - start + 2;
        for (int index = 0; index < stop; index++) {
            int expected = start + index;
            int actual = instance.get(index);
            String message = "Getting element " + index + " of " + instance;
            assertEquals(expected, actual, message);
        }
    }

    @Test
    void testGetExplicitStep1() {
        int start = RANDOM.nextInt(Byte.MAX_VALUE) + 1;
        int end = start + RANDOM.nextInt(1, 128);
        Range instance = new Range(start, end, 1);
        int stop = end - start + 2;
        for (int index = 0; index < stop; index++) {
            int expected = start + index;
            int actual = instance.get(index);
            String message = "Getting element " + index + " of " + instance;
            assertEquals(expected, actual, message);
        }
    }

    @Test
    void testGetNegativeStep() {
        int end = RANDOM.nextInt(Byte.MAX_VALUE) + 1;
        int step = -RANDOM.nextInt(2, 8);
        int start = end - step * RANDOM.nextInt(1, 128);
        Range instance = new Range(start, end, step);
        int stop = (end - start) / step + 2;
        for (int index = 0; index < stop; index++) {
            int expected = start + step * index;
            int actual = instance.get(index);
            String message = "Getting element " + index + " of " + instance;
            assertEquals(expected, actual, message);
        }
    }

    @Test
    void testGetPositiveStepMoreThan1() {
        int start = RANDOM.nextInt(Byte.MAX_VALUE) + 1;
        int step = RANDOM.nextInt(2, 8);
        int end = start + step * RANDOM.nextInt(1, 8);
        Range instance = new Range(start, end, step);
        int stop = (end - start) / step + 2;
        for (int index = 0; index < stop; index++) {
            int expected = start + step * index;
            int actual = instance.get(index);
            String message = "Getting element " + index + " of " + instance;
            assertEquals(expected, actual, message);
        }
    }

    @Test
    void testGetRangeNegativeStepRejectsNegativeIndex() {
        Range instance = makeRangeWithNegativeStep();
        int badIndex = -RANDOM.nextInt(1, 64);
        String message = "Trying to get element " + badIndex + " of " + instance
                + " should cause exception";
        Throwable t = assertThrows(IndexOutOfBoundsException.class, () -> {
            int badResult = instance.get(badIndex);
            System.out.println(message + " not given result " + badResult);
        }, message);
        String excMsg = t.getMessage();
        assert excMsg != null : "Exception message should not be null";
        assert !excMsg.isBlank() : "Exception message should not be blank";
        String numStr = Integer.toString(badIndex);
        String containsMsg = "Exception message should contain \"" + numStr
                + "\"";
        assert excMsg.contains(numStr) : containsMsg;
    }

    @Test
    void testGetRangeImplicitStep1RejectsNegativeIndex() {
        Range instance = makeRangeWithImplicitStep1();
        int badIndex = -RANDOM.nextInt(1, 64);
        String message = "Trying to get element " + badIndex + " of " + instance
                + " should cause exception";
        Throwable t = assertThrows(IndexOutOfBoundsException.class, () -> {
            int badResult = instance.get(badIndex);
            System.out.println(message + " not given result " + badResult);
        }, message);
        String excMsg = t.getMessage();
        assert excMsg != null : "Exception message should not be null";
        assert !excMsg.isBlank() : "Exception message should not be blank";
        String numStr = Integer.toString(badIndex);
        String containsMsg = "Exception message should contain \"" + numStr
                + "\"";
        assert excMsg.contains(numStr) : containsMsg;
    }

    @Test
    void testGetRangePositiveStepRejectsNegativeIndex() {
        Range instance = makeRangeWithPositiveStep();
        int badIndex = -RANDOM.nextInt(1, 64);
        String message = "Trying to get element " + badIndex + " of " + instance
                + " should cause exception";
        Throwable t = assertThrows(IndexOutOfBoundsException.class, () -> {
            int badResult = instance.get(badIndex);
            System.out.println(message + " not given result " + badResult);
        }, message);
        String excMsg = t.getMessage();
        assert excMsg != null : "Exception message should not be null";
        assert !excMsg.isBlank() : "Exception message should not be blank";
        String numStr = Integer.toString(badIndex);
        String containsMsg = "Exception message should contain \"" + numStr
                + "\"";
        assert excMsg.contains(numStr) : containsMsg;
    }

    // TODO: Test get() rejects excessive index

    private static Object passThrough(Object obj) {
        return obj;
    }

    private static Object provideNull() {
        return null;
    }

    @Test
    void testReferentialEquality() {
        Range instance = makeRange();
        Object obj = passThrough(instance);
        String message = instance.toString() + " should be equal to itself";
        assertEquals(instance, obj, message);
    }

    @Test
    void testNotEqualsNull() {
        Range instance = makeRange();
        Object obj = provideNull();
        String msg = instance.toString() + " should not equal null";
        assert !instance.equals(obj) : msg;
    }

    @Test
    void testNotEqualsDiffClass() {
        Range instance = makeRange();
        Object[] objects = {ExtendedRandom.chooseBMPBlock(),
                ExtendedRandom.nextBigInt(72), ExtendedRandom.nextFraction(),
                ExtendedRandom.nextColor(), ExtendedRandom.nextComplex(),
                LocalDateTime.now()};
        for (Object obj : objects) {
            String msg = instance.toString() + " should not equal "
                    + obj.toString();
            assert !instance.equals(obj) : msg;
        }
    }

    @Test
    void testNotEqualsDiffStartBothImplicitStep1() {
        int startA = RANDOM.nextInt(256) - 128;
        int startB = startA + RANDOM.nextInt(256) + 4;
        int end = startB + RANDOM.nextInt(128) + 2;
        Range rangeA = new Range(startA, end);
        Range rangeB = new Range(startB, end);
        String message = rangeA + " should not equal " + rangeB;
        assertNotEquals(rangeA, rangeB, message);
    }

    @Test
    void testNotEqualsDiffStartBothExplicitStep1() {
        int startA = RANDOM.nextInt(256) - 128;
        int startB = startA + RANDOM.nextInt(256) + 4;
        int end = startB + RANDOM.nextInt(128) + 2;
        Range rangeA = new Range(startA, end, 1);
        Range rangeB = new Range(startB, end, 1);
        String message = rangeA + " should not equal " + rangeB;
        assertNotEquals(rangeA, rangeB, message);
    }

    @Test
    void testNotEqualsDiffStartOneImplicitStep1() {
        int startA = RANDOM.nextInt(256) - 128;
        int startB = startA + RANDOM.nextInt(256) + 4;
        int end = startB + RANDOM.nextInt(128) + 2;
        Range rangeA = new Range(startA, end);
        Range rangeB = new Range(startB, end, 1);
        String message = rangeA + " should not equal " + rangeB;
        assertNotEquals(rangeA, rangeB, message);
    }

    @Test
    void testNotEqualsDiffStart() {
        int startA = RANDOM.nextInt(256) - 128;
        int step = RANDOM.nextInt(2, 10);
        int startB = startA + step * (RANDOM.nextInt(32) + 8);
        int end = startB + step * (RANDOM.nextInt(16) + 4);
        Range rangeA = new Range(startA, end, step);
        Range rangeB = new Range(startB, end, step);
        String message = rangeA + " should not equal " + rangeB;
        assertNotEquals(rangeA, rangeB, message);
    }

    // TODO: Test hashCode()

    // TODO: Test constructor rejects wrong direction (negative) step

    // TODO: Test constructor rejects wrong direction (positive) step

}
