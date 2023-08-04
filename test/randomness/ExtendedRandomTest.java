package randomness;

import fractions.Fraction;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

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
    void testNextIntBoundedByZeroThrowsException() {
        Throwable t = assertThrows(IllegalArgumentException.class, () -> {
            int badResult = ExtendedRandom.nextInt(0);
            System.out.println("Calling nextInt with bound 0 gave "
                    + badResult);
        });
        String excMsg = t.getMessage();
        assert excMsg != null : "Message should not be null";
        assert !excMsg.isEmpty() : "Message should not be empty";
        System.out.println("\"" + excMsg + "\"");
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
    void testNextIntBoundedByNegative() {
        int capacity = 2048;
        int bound = -(capacity - RANDOM.nextInt(128));
        Set<Integer> numbers = new HashSet<>(capacity);
        for (int i = 0; i < capacity; i++) {
            try {
                int number = ExtendedRandom.nextInt(bound);
                String msg = "Pseudorandom number " + number
                        + " should be at most 0 but also more than " + bound;
                assert number <= 0 : msg;
                assert number > bound : msg;
                numbers.add(number);
            } catch (IllegalArgumentException iae) {
                String errMsg = "Bound " + bound
                        + " shouldn't've caused IllegalArgumentException";
                throw new AssertionError(errMsg, iae);
            }
        }
        int expected = capacity / 2;
        int actual = numbers.size();
        String msg = "Expected at least " + expected
                + " distinct integers in the range " + (bound - 1)
                + " to 0 out of " + capacity + ", got " + actual;
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

    @Test
    void testAlphanumericRejectsNegativeLength() {
        int badLength = -RANDOM.nextInt(1024) - 1;
        Throwable t = assertThrows(IllegalArgumentException.class, () -> {
            String badString = ExtendedRandom.alphanumeric(badLength);
            System.out.println("Trying to get random alphanumeric of length "
                    + badLength + " should not have given \"" + badString
                    + "\"");
        });
        String excMsg = t.getMessage();
        assert excMsg != null : "Exception message should not be null";
        System.out.println("\"" + excMsg + "\"");
        String badLengthStr = Integer.toString(badLength);
        String msg = "Exception message should include \"" + badLengthStr
                + "\"";
        assert excMsg.contains(badLengthStr) : msg;
    }

    @Test
    void testAlphanumericRejectsLengthZero() {
        int badLength = 0;
        Throwable t = assertThrows(IllegalArgumentException.class, () -> {
            String badString = ExtendedRandom.alphanumeric(badLength);
            System.out.println("Trying to get random alphanumeric of length "
                    + badLength + " should not have given \"" + badString
                    + "\"");
        });
        String excMsg = t.getMessage();
        assert excMsg != null : "Exception message should not be null";
        System.out.println("\"" + excMsg + "\"");
        String badLengthStr = Integer.toString(badLength);
        String msg = "Exception message should include \"" + badLengthStr
                + "\"";
        assert excMsg.contains(badLengthStr) : msg;
    }

    @Test
    void testAlphanumericGivesStringOfSpecifiedLength() {
        for (int expected = 1; expected < 21; expected++) {
            String s = ExtendedRandom.alphanumeric(expected);
            int actual = s.length();
            assertEquals(expected, actual);
        }
    }

    private static boolean isASCIILetterOrDigit(char ch) {
        boolean isDigit = ch >= '0' && ch <= '9';
        boolean isUppercaseLetter = ch >= 'A' && ch <= 'Z';
        boolean isLowercaseLetter = ch >= 'a' && ch <= 'z';
        return isDigit || isUppercaseLetter || isLowercaseLetter;
    }

    @Test
    void testAlphanumericGivesStringOfASCIIAlphanumerics() {
        int length = RANDOM.nextInt(16) + 4;
        String s = ExtendedRandom.alphanumeric(length);
        String msgPartA = "Character '";
        String msgPartB = "' should be ASCII letter or digit";
        char[] characters = s.toCharArray();
        for (char ch : characters) {
            String msg = msgPartA + ch + msgPartB;
            assert isASCIILetterOrDigit(ch) : msg;
        }
    }

    @Test
    void testAlphanumeric() {
        System.out.println("alphanumeric");
        int capacity = RANDOM.nextInt(64) + 36;
        Set<String> strings = new HashSet<>(capacity);
        int length = RANDOM.nextInt(16) + 4;
        for (int i = 0; i < capacity; i++) {
            String s = ExtendedRandom.alphanumeric(length);
            strings.add(s);
        }
        int expected = 9 * capacity / 10;
        int actual = strings.size();
        String msg = "alphanumeric should've given at least " + expected
                + " distinct, gave " + actual + " distinct";
        System.out.println(msg);
        assert actual >= expected : msg;
    }

}