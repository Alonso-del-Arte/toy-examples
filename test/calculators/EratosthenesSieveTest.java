package calculators;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void testEmptyPrimeList() {
        List<Integer> expected = new ArrayList<>();
        List<Integer> actual = EratosthenesSieve.listPrimes(0);
        assertEquals(expected, actual);
    }

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

}
