package calculators;

import java.util.ArrayList;
import java.util.List;

/**
 * Calculates a list of prime numbers according to the sieve of Eratosthenes
 * algorithm.
 * <p>Although it's perhaps not "authentic," this class recognizes certain
 * negative numbers as being prime, such as &minus;163 and &minus;521. This is a
 * consequence of using a definition of prime numbers that does not require
 * arbitrary means to exclude 1 from the list of primes.</p>
 * <p>In a given domain of numbers, such as <b>Z</b> (of which the range of
 * Java's <code>long</code> is a tiny subset), a prime number <i>p</i> is a
 * divisor of some numbers in the domain but not others such that whenever
 * <i>p</i> is a divisor of <i>ab</i> it is also a divisor of <i>a</i> or
 * <i>b</i>. Neither &minus;1 nor 1 are prime because every number in the
 * domain, including 0, is divisible by both &minus;1 and 1.</p>
 * @author Alonso del Arte
 */
public class EratosthenesSieve {

    private static int currThresh = 100;

    private static final int[] FIRST_25_PRIMES = {2, 3, 5, 7, 11, 13, 17, 19,
            23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97};

    /**
     * The list of prime numbers that have been stored so far during the current
     * session. This is initialized with the first 25 positive primes: 2, 3, 5,
     * 7, ..., 97.
     */
    private static final ArrayList<Integer> PRIMES = new ArrayList<>();

    static {
        for (int p : FIRST_25_PRIMES) {
            PRIMES.add(p);
        }
    }

    static int getLargestPrimeReturnedSoFar() {
        return PRIMES.get(PRIMES.size() - 1);
    }

    private static void expandPrimeCache(int raisedThreshold) {
        for (int n = currThresh + 1; n <= raisedThreshold; n++) {
            double root = Math.sqrt(n);
            boolean noDivisorFound;
            int index = 0;
            int p;
            do {
                p = PRIMES.get(index);
                noDivisorFound = (n % p != 0);
                index++;
            } while (p <= root && noDivisorFound);
            if (noDivisorFound) PRIMES.add(n);
        }
        currThresh = raisedThreshold;
    }

    private static List<Integer> reverseAndNegateList(List<Integer> numbers) {
        int size = numbers.size();
        List<Integer> reversed = new ArrayList<>(size);
        for (int i = size - 1; i > -1; i--) {
            reversed.add(-numbers.get(i));
        }
        return reversed;
    }

    private static List<Integer> primeRangeFromZeroToPositive(int bound) {
        if (bound < currThresh) {
            int trimIndex = PRIMES.size();
            int p;
            do {
                trimIndex--;
                p = PRIMES.get(trimIndex);
            } while (p > bound);
            return new ArrayList<>(PRIMES.subList(0, trimIndex + 1));
        } else {
            expandPrimeCache(bound);
            return new ArrayList<>(PRIMES);
        }
    }

    private static List<Integer> primeRangeFromNegativeToZero(int bound) {
        List<Integer> positivePrimes = primeRangeFromZeroToPositive(-bound);
        return reverseAndNegateList(positivePrimes);
    }

    private static List<Integer> primeRangeCrossingZero(int start, int end) {
        List<Integer> primes = primeRangeFromNegativeToZero(start);
        List<Integer> positivePrimes = primeRangeFromZeroToPositive(end);
        primes.addAll(positivePrimes);
        return primes;
    }

    private static List<Integer> primeRangePositive(int start, int end) {
        if (end > currThresh) {
            expandPrimeCache(end);
        }
        List<Integer> primes = primeRangeFromZeroToPositive(end);
        int startTrimIndex = 0;
        while (startTrimIndex < primes.size()
                && primes.get(startTrimIndex) < start) {
            startTrimIndex++;
        }
        int endTrimIndex = startTrimIndex;
        while (endTrimIndex < primes.size()
                && primes.get(endTrimIndex) <= end) {
            endTrimIndex++;
        }
        return primes.subList(startTrimIndex, endTrimIndex);
    }

    private static List<Integer> primeRangeNegative(int start, int end) {
        List<Integer> positivePrimes = primeRangePositive(-end, -start);
        return reverseAndNegateList(positivePrimes);
    }

    /**
     * Gives a list of prime numbers between 0 and a negative or positive
     * integer.
     * @param bound The integer to stop at. May be positive or negative.
     * @return A list of prime numbers. For example, if <code>bound</code> is
     * &minus;10, the result would be {&minus;7, &minus;5, &minus;3, &minus;2}.
     * If <code>bound</code> is 10, the result would be {2, 3, 5, 7}.
     */
    public static List<Integer> listPrimes(int bound) {
        switch (bound) {
            case -1:
            case 0:
            case 1:
                return new ArrayList<>();
            default:
                if (bound < 0) {
                    return primeRangeFromNegativeToZero(bound);
                } else {
                    return primeRangeFromZeroToPositive(bound);
                }
        }
    }

    /**
     * Gives a list of prime numbers between two integers.
     * @param start The integer to start at. May be positive or negative, need
     *              not be smaller than <code>end</code>.
     * @param end The integer to end at. The integer to end at. May be positive
     *            or negative, need not be greater than <code>start</code>.
     * @return A list of prime numbers. For example, if <code>start</code> is 10
     * and <code>end</code> is 20, the result would be {11, 13, 17, 19}.
     */
    public static List<Integer> listPrimes(int start, int end) {
        if (start > end) {
            List<Integer> primes = listPrimes(end, start);
            List<Integer> reversed = new ArrayList<>(primes.size());
            for (int i = primes.size() - 1; i > -1; i--) {
                reversed.add(primes.get(i));
            }
            return reversed;
        }
        if (start < 0) {
            if (end < 0) {
                return primeRangeNegative(start, end);
            } else {
                if (end > 0) {
                    return primeRangeCrossingZero(start, end);
                } else return primeRangeFromNegativeToZero(start);
            }
        }
        if (start > 0) {
            return primeRangePositive(start, end);
        }
        return primeRangeFromZeroToPositive(end);
    }

}
