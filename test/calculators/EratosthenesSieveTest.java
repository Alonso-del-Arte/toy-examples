package calculators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests of the EratosthenesSieve class.
 * @author Alonso del Arte
 */
class EratosthenesSieveTest {

    /**
     * Checks the primality of an integer according to an old definition that
     * considers 1 to be prime. This procedure should not be used for 0 nor for
     * negative integers.
     * @param n The positive integer to be tested for primality. Examples: 73,
     *          74, 75.
     * @throws AssertionError If <code>n</code> is actually a positive composite
     * integer.
     */
    private static void checkPrimality(int n) {
        double squareRoot = Math.sqrt(n);
        String msgPart = "Said to be prime, " + n
                + " should not be divisible by ";
        for (int i = 3; i <= squareRoot; i += 2) {
            String msg = msgPart + i;
            assert n % i != 0 : msg;
        }
    }

    /**
     * Another test of the listPrimes function, of the EratosthenesSieve class.
     * If the bound is 0, the returned list should be empty.
     */
    @Test
    void testEmptyPrimeList() {
        List<Integer> expected = new ArrayList<>();
        List<Integer> actual = EratosthenesSieve.listPrimes(0);
        assertEquals(expected, actual);
    }

    /**
     * Another test of the listPrimes function, of the EratosthenesSieve class.
     * The first four positive primes should be given in order.
     */
    @Test
    void testListFirstFourPositivePrimes() {
        List<Integer> expected = new ArrayList<>();
        expected.add(2);
        expected.add(3);
        expected.add(5);
        expected.add(7);
        List<Integer> actual = EratosthenesSieve.listPrimes(10);
        assertEquals(expected, actual);
    }

    /**
     * Test of the listPrimes function, of the EratosthenesSieve class. The
     * first 25 positive primes should be given in order.
     */
    @Test
    void testListPrimes() {
        System.out.println("listPrimes");
        int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47,
                53, 59, 61, 67, 71, 73, 79, 83, 89, 97};
        List<Integer> expected = new ArrayList<>();
        for (int p : primes) expected.add(p);
        List<Integer> actual = EratosthenesSieve.listPrimes(100);
        assertEquals(expected, actual);
    }

    /**
     * Another test of the listPrimes function, of the EratosthenesSieve class.
     * The cache of prime numbers should be expanded when called upon with
     * larger thresholds. For example, if it's been called upon to give primes
     * up to 500, and then it's called upon to give primes up to 1000, the cache
     * should expand accordingly.
     */
    @Test
    void testEratosthenesSieveCanExpandCache() {
        int prevLargestPrime = EratosthenesSieve.getLargestPrimeReturnedSoFar();
        int threshold = prevLargestPrime * prevLargestPrime;
        List<Integer> primes = EratosthenesSieve.listPrimes(threshold);
        String msg = "There should be more primes between " + prevLargestPrime
                + " and " + threshold;
        int currLargestPrime = EratosthenesSieve.getLargestPrimeReturnedSoFar();
        assert currLargestPrime > prevLargestPrime : msg;
        for (int p : primes) checkPrimality(p);
    }

    /**
     * Another test of the listPrimes function, of the EratosthenesSieve class.
     * The cache of prime numbers should be expanded when called upon with
     * larger thresholds. And if the new threshold happens to be a prime number
     * itself,
     */
    @Test
    void testEratosthenesSieveCanExpandCacheToPrimeThreshold() {
        int threshold = EratosthenesSieve.getLargestPrimeReturnedSoFar();
        boolean foundNextPrime;
        do {
            threshold += 2;
            boolean foundPrimeDivisor = false;
            int p = 3;
            double squareRoot = Math.sqrt(threshold);
            while (!foundPrimeDivisor && p <= squareRoot) {
                foundPrimeDivisor = (threshold % p == 0);
                p += 2;
            }
            foundNextPrime = !foundPrimeDivisor;
        } while (!foundNextPrime);
        List<Integer> primes = EratosthenesSieve.listPrimes(threshold);
        String msg = "List of primes between 2 and " + threshold
                + " should include " + threshold;
        assert primes.contains(threshold) : msg;
    }

    /**
     * Another test of the listPrimes function, of the EratosthenesSieve class.
     * Modifications to a received list of primes should not affect the list of
     * primes held by EratosthenesSieve.
     */
    @Test
    void testModifyPrimeSubsetCopyNotOriginal() {
        int firstThreshold = 20;
        List<Integer> subset = EratosthenesSieve.listPrimes(firstThreshold);
        for (int i = 0; i < subset.size(); i++) {
            int p = -subset.get(i);
            subset.set(i, p);
        }
        int secondThreshold = 40;
        Integer[] smallPrimes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37};
        ArrayList<Integer> expected
                = new ArrayList<>(Arrays.asList(smallPrimes));
        List<Integer> actual = EratosthenesSieve.listPrimes(secondThreshold);
        assertEquals(expected, actual);
    }

    /**
     * Another test of the listPrimes function, of the EratosthenesSieve class.
     * The primes cache should be updated after a query that requires raising
     * the threshold, but then a query for primes with a lower threshold should
     * not give primes in excess of the threshold.
     */
    @Test
    void testEratosthenesSieveCanTrim() {
        int smallThreshold = 20;
        int largerThreshold = 4 * smallThreshold + 25;
        EratosthenesSieve.listPrimes(largerThreshold);
        Integer[] smallPrimes = {2, 3, 5, 7, 11, 13, 17, 19};
        ArrayList<Integer> expected
                = new ArrayList<>(Arrays.asList(smallPrimes));
        List<Integer> actual = EratosthenesSieve.listPrimes(smallThreshold);
        assertEquals(expected, actual);
    }

    @Test
    void testListPrimesCanTakeNegativeBound() {
        int bound = -20;
        Integer[] primes = {-19, -17, -13, -11, -7, -5, -3, -2};
        List<Integer> expected
                = new ArrayList<>(Arrays.asList(primes));
        List<Integer> actual = EratosthenesSieve.listPrimes(bound);
        assertEquals(expected, actual);
    }

    @Test
    void testListPrimesEmptyRangeNegative() {
        int start = -540;
        int end = -524;
        List<Integer> expected = new ArrayList<>();
        List<Integer> actual = EratosthenesSieve.listPrimes(start, end);
        assertEquals(expected, actual);
    }

    @Test
    void testListPrimesEmptyPositiveRange() {
        int start = 524;
        int end = 540;
        List<Integer> expected = new ArrayList<>();
        List<Integer> actual = EratosthenesSieve.listPrimes(start, end);
        assertEquals(expected, actual);
    }

    @Test
    void testListPrimesNegativeRange() {
        int start = -110;
        int end = -100;
        Integer[] primeQuadruplet = {-109, -107, -103, -101};
        List<Integer> expected
                = new ArrayList<>(Arrays.asList(primeQuadruplet));
        List<Integer> actual = EratosthenesSieve.listPrimes(start, end);
        assertEquals(expected, actual);
    }

    @Test
    void testListPrimesPositiveRange() {
        int start = 100;
        int end = 110;
        Integer[] primeQuadruplet = {101, 103, 107, 109};
        List<Integer> expected
                = new ArrayList<>(Arrays.asList(primeQuadruplet));
        List<Integer> actual = EratosthenesSieve.listPrimes(start, end);
        assertEquals(expected, actual);
    }

    @Test
    void testListPrimesNegativeToPositiveRange() {
        int start = -20;
        int end = 20;
        Integer[] primes = {-19, -17, -13, -11, -7, -5, -3, -2, 2, 3, 5, 7, 11,
                13, 17, 19};
        List<Integer> expected
                = new ArrayList<>(Arrays.asList(primes));
        List<Integer> actual = EratosthenesSieve.listPrimes(start, end);
        assertEquals(expected, actual);
    }

    @Test
    void testListPrimesCanFlipRangeCrossingZero() {
        int start = 20;
        int end = -20;
        Integer[] primes = {19, 17, 13, 11, 7, 5, 3, 2, -2, -3, -5, -7, -11,
                -13, -17, -19};
        List<Integer> expected
                = new ArrayList<>(Arrays.asList(primes));
        List<Integer> actual = EratosthenesSieve.listPrimes(start, end);
        assertEquals(expected, actual);
    }

    @Test
    void testListPrimesCanFlipNegativeRange() {
        int start = -10;
        int end = -20;
        Integer[] primes = {-11, -13, -17, -19};
        List<Integer> expected
                = new ArrayList<>(Arrays.asList(primes));
        List<Integer> actual = EratosthenesSieve.listPrimes(start, end);
        assertEquals(expected, actual);
    }

    @Test
    void testListPrimesCanFlipPositiveRange() {
        int start = 20;
        int end = 10;
        Integer[] primes = {19, 17, 13, 11};
        List<Integer> expected
                = new ArrayList<>(Arrays.asList(primes));
        List<Integer> actual = EratosthenesSieve.listPrimes(start, end);
        assertEquals(expected, actual);
    }

    @Test
    void testListPrimesRangeFromExplicitZeroStartToNegativeEnd() {
        int end = -20;
        Integer[] primes = {-2, -3, -5, -7, -11, -13, -17, -19};
        List<Integer> expected
                = new ArrayList<>(Arrays.asList(primes));
        List<Integer> actual = EratosthenesSieve.listPrimes(0, end);
        assertEquals(expected, actual);
    }

    @Test
    void testListPrimesRangeFromExplicitZeroStartToPositiveEnd() {
        int end = 20;
        Integer[] primes = {2, 3, 5, 7, 11, 13, 17, 19};
        List<Integer> expected
                = new ArrayList<>(Arrays.asList(primes));
        List<Integer> actual = EratosthenesSieve.listPrimes(0, end);
        assertEquals(expected, actual);
    }

    @Test
    void testListPrimesRangeFromNegativeStartToExplicitZeroEnd() {
        int start = -20;
        Integer[] primes = {-19, -17, -13, -11, -7, -5, -3, -2};
        List<Integer> expected
                = new ArrayList<>(Arrays.asList(primes));
        List<Integer> actual = EratosthenesSieve.listPrimes(start, 0);
        assertEquals(expected, actual);
    }

    @Test
    void testListPrimesRangeFromPositiveStartToExplicitZeroEnd() {
        int start = 20;
        Integer[] primes = {19, 17, 13, 11, 7, 5, 3, 2};
        List<Integer> expected
                = new ArrayList<>(Arrays.asList(primes));
        List<Integer> actual = EratosthenesSieve.listPrimes(start, 0);
        assertEquals(expected, actual);
    }

}
