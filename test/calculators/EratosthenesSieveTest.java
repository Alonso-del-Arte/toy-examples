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
     * The primes cache should be updated after a query that requires raising
     * the threshold, but then a query for primes with a lower threshold should
     * not give primes in excess of the threshold.
     */
    @Test
    void testEratosthenesSieveCanTrim() {
        int smallThreshold = 20;
        int largerThreshold = 4 * smallThreshold + 25;
        List<Integer> list = EratosthenesSieve.listPrimes(largerThreshold);
        System.out.println("EratosthenesSieve reports " + list.size()
                + " primes between 1 and " + largerThreshold);
        Integer[] smallPrimes = {2, 3, 5, 7, 11, 13, 17, 19};
        ArrayList<Integer> expected
                = new ArrayList<>(Arrays.asList(smallPrimes));
        List<Integer> actual = EratosthenesSieve.listPrimes(smallThreshold);
        assertEquals(expected, actual);
    }

}
