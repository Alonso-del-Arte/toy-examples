package randomness;

import currency.CurrencyAmount;
import fractions.Fraction;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import numerics.ComplexNumber;
import numerics.RomanNumeralsNumber;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ExtendedRandomTest {

    public static final Random RANDOM = new Random(-System.currentTimeMillis());

    @Test
    void testNextInt() {
        System.out.println("nextInt");
        int capacity = 2048;
        Set<Integer> numbers = new HashSet<>(capacity);
        for (int i = 0; i < capacity; i++) {
            numbers.add(ExtendedRandom.nextInt());
        }
        int expected = 15 * capacity / 16;
        int actual = numbers.size();
        String msg = "Expected at least " + expected
                + " distinct integers out of " + capacity + ", got " + actual;
        System.out.println(msg);
        assert actual >= expected : msg;
    }

    @Test
    void testNextIntBounded() {
        int capacity = 2048;
        int bound = capacity - RANDOM.nextInt(128);
        Set<Integer> numbers = new HashSet<>(capacity);
        for (int i = 0; i < capacity; i++) {
            int number = ExtendedRandom.nextInt(bound);
            String msg = "Pseudorandom number " + number
                    + " should be at least 0 but less than " + bound;
            assert number >= 0 : msg;
            assert number < bound : msg;
            numbers.add(number);
        }
        int expected = capacity / 2;
        int actual = numbers.size();
        String msg = "Expected at least " + expected
                + " distinct integers in the range 0 to " + (bound - 1)
                + " out of " + capacity + ", got " + actual;
        System.out.println(msg);
        assert actual >= expected : msg;
    }

    @Test
    void testNextBigInt() {
        System.out.println("nextBigInt");
        int capacity = 64;
        int boundExponent = capacity + RANDOM.nextInt(64);
        BigInteger two = BigInteger.valueOf(2);
        BigInteger bound = two.pow(boundExponent);
        Set<BigInteger> numbers = new HashSet<>(capacity);
        for (int i = 0; i < capacity; i++) {
            BigInteger number = ExtendedRandom.nextBigInt(boundExponent);
            String msg = "Pseudorandom number " + number
                    + " should be at least 0 but less than " + bound;
            assert number.compareTo(BigInteger.ZERO) >= 0 : msg;
            assert number.compareTo(bound) < 0 : msg;
            numbers.add(number);
        }
        int expected = 3 * capacity / 4;
        int actual = numbers.size();
        String msg = "Expected at least " + expected
                + " distinct integers in the range 0 to "
                + (bound.subtract(BigInteger.ONE)) + " out of " + capacity
                + ", got " + actual;
        System.out.println(msg);
        assert actual >= expected : msg;
    }

    @Test
    void testNextDouble() {
        System.out.println("nextDouble");
        int capacity = RANDOM.nextInt(32) + 128;
        Set<Double> set = new HashSet<>(capacity);
        int curr = 0;
        while (curr < capacity) {
            set.add(ExtendedRandom.nextDouble());
            curr++;
        }
        int expected = 9 * capacity / 10;
        int actual = set.size();
        String msg = "nextDouble() should have given at least " + expected
                + " distinct numbers, gave " + actual + " distinct";
        assert expected <= actual : msg;
    }

    @Test
    void testNextFraction() {
        System.out.println("nextFraction");
        int capacity = 1000;
        Set<Fraction> fractions = new HashSet<>(capacity);
        for (int i = 0; i < capacity; i++) {
            fractions.add(ExtendedRandom.nextFraction());
        }
        int expected = 3 * capacity / 4;
        int actual = fractions.size();
        String msg = "Expected at least " + expected
                + " distinct fractions out of " + capacity + ", got " + actual;
        System.out.println(msg);
        assert actual >= expected : msg;
    }

}