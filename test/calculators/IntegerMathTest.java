package calculators;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import randomness.ExtendedRandom;

class IntegerMathTest {

    private static final List<Long> FIBONACCI_NUMBERS = new ArrayList<>();

    static {
        FIBONACCI_NUMBERS.add(0L);
        FIBONACCI_NUMBERS.add(1L);
        long nextFibonacci = 1L;
        int currIndex = 2;
        while (nextFibonacci > 0) {
            FIBONACCI_NUMBERS.add(nextFibonacci);
            nextFibonacci = FIBONACCI_NUMBERS.get(currIndex - 1)
                    + FIBONACCI_NUMBERS.get(currIndex);
            currIndex++;
        }
    }

    @Test
    void testNegativeNumberIsNotPowerOfTwo() {
        int bound = -((int) Short.MAX_VALUE);
        int n = ExtendedRandom.nextInt(bound);
        String msg = "Number " + n + " should not be considered power of 2";
        assert !IntegerMath.isPowerOfTwo(n) : msg;
    }

    @Test
    void testIsPowerOfTwo() {
        System.out.println("isPowerOfTwo");
        int power = 1;
        while (power > 0) {
            String msg = "Number " + power
                    + " should be considered a power of 2";
            assert IntegerMath.isPowerOfTwo(power) : msg;
            power <<= 1;
        }
    }

    @Test
    void testZeroIsNotPowerOfTwo() {
        assert !IntegerMath.isPowerOfTwo(0)
                : "0 should not be considered a power of 2";
    }

    @Test
    void testIntMinValueIsNotPowerOfTwo() {
        int n = Integer.MIN_VALUE;
        String msg = "Number " + n + " should not be considered a power of 2";
        assert !IntegerMath.isPowerOfTwo(n) : msg;
    }

    @Test
    void testIsNotPowerOfTwo() {
        int bound = Short.MAX_VALUE;
        for (int n = 3; n < bound; n += 2) {
            int m = n;
            while (m < bound) {
                String msg = "Number " + m
                        + " should not be considered a power of 2";
                assert !IntegerMath.isPowerOfTwo(m) : msg;
                m <<= 1;
            }
        }
    }

    @Test
    void testNeitherPrimeNorComposite() {
        assert !IntegerMath.isPrime(-1) : "-1 is not prime";
        assert !IntegerMath.isPrime(0) : "0 is not prime";
        assert !IntegerMath.isPrime(1) : "1 is not prime";
    }

    @Test
    void testNotPrime() {
        int start = ExtendedRandom.nextInt(32) + 8;
        int end = ExtendedRandom.nextInt(4096) + start;
        List<Integer> primes = EratosthenesSieve.listPrimes(start, end);
        String msgPart = " is not prime";
        String msg;
        String positivePrimeMsg
                = "Number said to be positive prime should be greater than 1";
        for (int p : primes) {
            assert p > 1 : positivePrimeMsg;
            int square = p * p;
            msg = square + msgPart;
            assert !IntegerMath.isPrime(square) : msg;
            msg = '-' + msg;
            assert !IntegerMath.isPrime(-square) : msg;
            int pronic = square + p;
            msg = pronic + msgPart;
            assert !IntegerMath.isPrime(pronic) : msg;
            msg = '-' + msg;
            assert !IntegerMath.isPrime(-pronic) : msg;
        }
    }

    @Test
    void testIsPrime() {
        System.out.println("isPrime");
        int start = (ExtendedRandom.nextInt(4096) + 64) | 1;
        int end = (start + ExtendedRandom.nextInt(128) + 2) | 1;
        double root = Math.sqrt(end);
        String msgPart = " should be recognized as prime";
        for (int i = start; i < end; i += 2) {
            int potentialDivisor = 3;
            boolean noDivisorFound = true;
            while (potentialDivisor <= root && noDivisorFound) {
                noDivisorFound = i % potentialDivisor != 0;
                potentialDivisor += 2;
            }
            if (noDivisorFound) {
                assert IntegerMath.isPrime(i) : (i + msgPart);
                assert IntegerMath.isPrime(-i) : (-i + msgPart);
            }
        }
    }

    @Test
    void testSmallPrimes() {
        List<Integer> smallPrimes = EratosthenesSieve.listPrimes(-128, 128);
        String msgPart = " should be recognized as prime";
        for (int p : smallPrimes) {
            assert IntegerMath.isPrime(p) : (p + msgPart);
        }
    }

    @Test
    void testBadBoundForRandomPrime() {
        int[] badBounds = {-1, 0, 1};
        for (int badBound : badBounds) {
            String msg = "Bad bound " + badBound
                    + " should have caused IllegalArgumentException";
            Throwable t = assertThrows(IllegalArgumentException.class, () -> {
                int badResult = IntegerMath.randomPrime(badBound);
                System.out.println("Bad bound " + badBound
                        + " somehow gave result " + badResult);
            }, msg);
            String excMsg = t.getMessage();
            assert excMsg != null : "Exception message should not be null";
            System.out.println("\"" + excMsg + "\"");
        }
    }

    @Test
    void testRandomPrimeCanGiveNegativePrime() {
        int p = IntegerMath.randomPrime(-128);
        assert IntegerMath.isPrime(p)
                : "Should have received a prime, not " + p;
        assert p < 0 : "Prime " + p + " should be negative";
    }

    @Test
    void testRandomPrime() {
        System.out.println("randomPrime");
        int size = ExtendedRandom.nextInt(128) + 32;
        Set<Integer> set = new HashSet<>(size);
        int start = size * size;
        int end = start + size;
        for (int bound = start; bound < end; bound++) {
            int p = IntegerMath.randomPrime(bound);
            assert IntegerMath.isPrime(p)
                    : "Number " + p + " expected to be prime";
            assert p > 0 : "Prime " + p + " expected to be positive";
            set.add(p);
        }
        int expected = (int) Math.floor(0.9 * size);
        int actual = set.size();
        System.out.println("Successfully got " + size + " primes, " + actual
                + " distinct");
        String msg = "Expected at least " + expected + " distinct primes, got "
                + actual;
        assert actual >= expected : msg;
    }

    @Test
    void testRandomPrimeFromArbitraryRange() {
        int start = ExtendedRandom.nextInt(4096) + 4;
        int end = 2 * start + ExtendedRandom.nextInt(512) + 8;
        int prime = IntegerMath.randomPrime(start, end);
        String primeMsg = "Number " + prime + " should be prime";
        assert IntegerMath.isPrime(prime) : primeMsg;
        String rangeMsg = "Prime " + prime + " should be at least " + start
                + " but not more than " + end;
        assert (start <= prime) && (prime <= end) : rangeMsg;
    }

    @Test
    void testRandomPrimeFromPrimeEmptyRangeCausesException() {
        int start = 524;
        int end = 540;
        Throwable exc = assertThrows(IllegalArgumentException.class, () -> {
            int badResult = IntegerMath.randomPrime(start, end);
            System.out.println("Trying to get prime between " + start + " and "
                    + end + " should not have given result " + badResult);
        });
        String excMsg = exc.getMessage();
        assert excMsg != null : "Message should not be null";
        System.out.println("\"" + excMsg + "\"");
    }

    @Test
    void testEuclideanGCDSamePositiveNumber() {
        int expected = ExtendedRandom.nextInt(1024) + 256;
        long actual = IntegerMath.euclideanGCD(expected, expected);
        assertEquals(expected, actual);
    }

    @Test
    void testMod() {
        System.out.println("mod");
        int m = ExtendedRandom.nextInt(256) + 4;
        int numberOfSpans = 12;
        int stop = m * numberOfSpans;
        for (int i = -stop; i < stop; i += m) {
            for (int expected = 0; expected < m; expected++) {
                int n = i + expected;
                int actual = IntegerMath.mod(n, m);
                String msg= "Expecting " + n + " modulo " + m + " to be "
                        + expected;
                assertEquals(expected, actual, msg);
            }
        }
    }

    @Test
    void testModZero() {
        int n = ExtendedRandom.nextInt() ^ 16;
        Throwable t = assertThrows(ArithmeticException.class, () -> {
            int badResidue = IntegerMath.mod(n, 0);
            System.out.println("Trying to calculate " + n
                    + " mod 0 should've caused an exception, not given result "
                    + badResidue);
        });
        String excMsg = t.getMessage();
        assert excMsg != null : "Exception message should not be null";
        assert !excMsg.isEmpty() : "Exception message should not be empty";
        String msg = "Exception message should contain parameter n = " + n;
        assert excMsg.contains(Integer.toString(n)) : msg;
    }

    @Test
    void testEuclideanGCDSameNegativeNumber() {
        int a = -ExtendedRandom.nextInt(1024) - 256;
        int expected = -a;
        long actual = IntegerMath.euclideanGCD(a, a);
        assertEquals(expected, actual);
    }

    @Test
    void testEuclideanGCD() {
        System.out.println("euclideanGCD");
        int expected = ExtendedRandom.nextInt(4096) + 4;
        for (long i = -256; i < 256; i++) {
            long a = i * expected;
            long b = a + expected;
            long actual = IntegerMath.euclideanGCD(a, b);
            assertEquals(expected, actual);
        }
    }

    @Test
    void testEuclideanGCDTwoConsecutiveFibonacciNumbers() {
        int lastIndex = FIBONACCI_NUMBERS.size() - 1;
        for (int i = 0; i < lastIndex; i++) {
            long a = FIBONACCI_NUMBERS.get(i);
            long b = FIBONACCI_NUMBERS.get(i + 1);
            String msg = "gcd(" + a + ", " + b + ") should be 1";
            assertEquals(1, IntegerMath.euclideanGCD(a, b), msg);
        }
    }

    private List<Long> sumsOfConsecutiveFibonacciNumbers(int n) {
        int capacity = FIBONACCI_NUMBERS.size() - n;
        List<Long> sums = new ArrayList<>(capacity);
        int index = 0;
        long sum = 1L;
        while (sum > 0) {
            sums.add(sum);
            sum = 0L;
            int stop = index + n;
            for (int i = index; i < stop; i++) {
                sum += FIBONACCI_NUMBERS.get(i);
            }
            index++;
        }
        sums.remove(0);
        return sums;
    }

    @Test
    void testEuclideanGCDConsecutiveSumsOfFibonacciNumbers() {
        int n = ExtendedRandom.nextInt(7) + 3;
        List<Long> sums = sumsOfConsecutiveFibonacciNumbers(n);
        int lastIndex = sums.size() - 1;
        Set<Long> sumGCDs = new HashSet<>();
        long gcd = 1L;
        for (int i = 0; i < lastIndex; i++) {
            long a = sums.get(i);
            long b = sums.get(i + 1);
            gcd = IntegerMath.euclideanGCD(a, b);
            sumGCDs.add(gcd);
        }
        String msg = "GCD of two consecutive sums of " + n
                + " consecutive Fibonacci numbers is expected to be " + gcd
                + "?";
        assertEquals(1, sumGCDs.size(), msg);
    }

    /**
     * Test of the divisors function, of the IntegerMath class. The divisors of
     * 12 are 1, 2, 3, 4, 6 and 12 itself. The function should return these
     * numbers, and only these numbers, in a list, but in whatever order is most
     * convenient for the implementer.
     */
    @Test
    void testDivisorsOf12() {
        Set<Integer> expected = new HashSet<>();
        expected.add(1);
        expected.add(2);
        expected.add(3);
        expected.add(4);
        expected.add(6);
        expected.add(12);
        Set<Integer> actual = new HashSet<>(IntegerMath.divisors(12));
        assertEquals(expected, actual);
    }

    @Test
    void testDivisors() {
        System.out.println("divisors");
        int number = 36 * (ExtendedRandom.nextInt(512) + 8);
        Set<Integer> expected = new HashSet<>();
        expected.add(1);
        expected.add(number);
        int half = number / 2;
        for (int i = 2; i <= half; i++) {
            if (number % i == 0) {
                expected.add(i);
            }
        }
        Set<Integer> actual = new HashSet<>(IntegerMath.divisors(number));
        assertEquals(expected, actual);
    }

    @Test
    void testRandomPartitionRejectsNegativeSize() {
        int n = ExtendedRandom.nextInt(256) + 1;
        int badSize = -ExtendedRandom.nextInt(262144) - 1;
        Throwable t = assertThrows(IllegalArgumentException.class, () -> {
            List<Integer> badPartition
                    = IntegerMath.randomPartition(n, badSize);
            System.out.println(n + " said to be partitioned into " + badSize
                    + " parts as " + badPartition);
        });
        String excMsg = t.getMessage();
        assert excMsg != null : "Exception message should not be null";
        String msg = "Exception message should contain bad size " + badSize;
        assert excMsg.contains(Integer.toString(badSize)) : msg;
        System.out.println("\"" + excMsg + "\"");
    }

    @Test
    void testRandomPartitionRejectsSizeZero() {
        int n = ExtendedRandom.nextInt(256) + 1;
        int badSize = 0;
        Throwable t = assertThrows(IllegalArgumentException.class, () -> {
            List<Integer> badPartition
                    = IntegerMath.randomPartition(n, badSize);
            System.out.println(n + " said to be partitioned into " + badSize
                    + " parts as " + badPartition);
        });
        String excMsg = t.getMessage();
        assert excMsg != null : "Exception message should not be null";
        String msg = "Exception message should contain bad size 0";
        assert excMsg.contains("0") : msg;
        System.out.println("\"" + excMsg + "\"");
    }

    @Test
    void testRandomPartitionOfJustOne() {
        int expected = ExtendedRandom.nextInt(256) + 4;
        List<Integer> partition = IntegerMath.randomPartition(expected, 1);
        assertEquals(1, partition.size(), "Partition of length 1 requested");
        int actual = partition.get(0);
        assertEquals(expected, actual);
    }

    @Test
    void testRandomPartition() {
        System.out.println("randomPartition");
        int expected = ExtendedRandom.nextInt(128) + 32;
        int numParts = ExtendedRandom.nextInt(16) + 4;
        List<Integer> partition = IntegerMath.randomPartition(expected, numParts);
        System.out.println("Partition of " + expected + " into " + numParts
                + " parts said to be " + partition);
        String msgA = "Partition was requested to be in " + numParts + " parts";
        assertEquals(numParts, partition.size(), msgA);
        int actual = partition.stream().reduce(0, Integer::sum);
        String msgB = "Numbers " + partition + " expected to add up to "
                + expected;
        assertEquals(expected, actual, msgB);
    }

    @Test
    void testRandomPartitionTooManyParts() { // e.g., partition 8 into ten parts
        fail("RESUME WORKING HERE");
    }

}
