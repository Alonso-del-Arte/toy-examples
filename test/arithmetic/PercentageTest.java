package arithmetic;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import randomness.ExtendedRandom;

class PercentageTest {

    @Test
    void testToString() {
        System.out.println("toString");
        for (int i = 0; i < 101; i++) {
            Percentage instance = new Percentage(i);
            String expected = i + "%";
            String actual = instance.toString();
            assertEquals(expected, actual);
        }
    }

    @Test
    void testToStringIntegersThroughBigDecimal() {
        for (int i = 0; i < 101; i++) {
            BigDecimal value = BigDecimal.valueOf(i);
            Percentage instance = new Percentage(value);
            String expected = i + "%";
            String actual = instance.toString();
            assertEquals(expected, actual);
        }
    }

    @Test
    void testToStringSixteenthsThroughFloatingPoint() {
        int floor = ExtendedRandom.nextInt(100);
        double sixteenth = 1.0 / 16;
        int stop = floor + 1;
        for (double value = floor + sixteenth; value < stop;
             value += sixteenth) {
            Percentage instance = new Percentage(value);
            String expected = value + "%";
            String actual = instance.toString();
            assertEquals(expected, actual);
        }
    }

    @Test @org.junit.jupiter.api.Disabled
    void testToStringFromBigDecimalTenths() {
        BigDecimal tenth = BigDecimal.ONE.divide(BigDecimal.TEN,
                RoundingMode.HALF_DOWN);
        fail("FINISH WRITING THIS TEST");
    }

    @Test
    void testConstructorRejectsNaN() {
        double value = Double.NaN;
        String message = "Constructor should reject " + value;
        Throwable t = assertThrows(IllegalArgumentException.class, () -> {
            Percentage badInstance = new Percentage(value);
            System.out.println(message + ", not given " + badInstance);
        }, message);
        String excMsg = t.getMessage();
        assert excMsg != null : "Exception message should not be null";
        assert !excMsg.isBlank() : "Exception message should not be blank";
        System.out.println("\"" + excMsg + "\"");
    }

    @Test
    void testConstructorRejectsNegativeInfinity() {
        double value = Double.NEGATIVE_INFINITY;
        String message = "Constructor should reject " + value;
        Throwable t = assertThrows(IllegalArgumentException.class, () -> {
            Percentage badInstance = new Percentage(value);
            System.out.println(message + ", not given " + badInstance);
        }, message);
        String excMsg = t.getMessage();
        assert excMsg != null : "Exception message should not be null";
        assert !excMsg.isBlank() : "Exception message should not be blank";
        System.out.println("\"" + excMsg + "\"");
    }

    @Test
    void testConstructorRejectsPositiveInfinity() {
        double value = Double.POSITIVE_INFINITY;
        String message = "Constructor should reject " + value;
        Throwable t = assertThrows(IllegalArgumentException.class, () -> {
            Percentage badInstance = new Percentage(value);
            System.out.println(message + ", not given " + badInstance);
        }, message);
        String excMsg = t.getMessage();
        assert excMsg != null : "Exception message should not be null";
        assert !excMsg.isBlank() : "Exception message should not be blank";
        System.out.println("\"" + excMsg + "\"");
    }

}
