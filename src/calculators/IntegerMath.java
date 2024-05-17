package calculators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import randomness.ExtendedRandom;

/**
 * Utility class for integer arithmetic. Mostly concerning prime numbers and
 * divisors.
 * @author Alonso del Arte
 */
public class IntegerMath {

    private static final int[] POWERS_OF_TWO = {1, 2, 4, 8, 16, 32, 64,
            128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536,
            131072, 262144, 524288, 1048576, 2097152, 4194304, 8388608,
            16777216, 33554432, 67108864, 134217728, 268435456, 536870912,
            1073741824};

    public static boolean isPowerOfTwo(int n) {
        return Arrays.binarySearch(POWERS_OF_TWO, n) > -1;
    }

    /**
     * Determines whether a given number is prime or not in <b>Z</b>.
     * @param n The number to test for primality. Examples: 13, &minus;883, 49.
     * @return True if <code>n</code> is prime, false otherwise. For both 13 and
     * &minus;883 this will return true. For 49 it will return false because
     * that number is divisible by &minus;7 and 7.
     */
    public static boolean isPrime(int n) {
        int number = Math.abs(n);
        switch (number) {
            case 0:
            case 1:
                return false;
            case 2:
                return true;
            default:
                if (number % 2 == 0) return false;
                boolean noDivisorFound = true;
                double root = Math.sqrt(number);
                int potentialDivisor = 3;
                while (potentialDivisor <= root && noDivisorFound) {
                    noDivisorFound = number % potentialDivisor != 0;
                    potentialDivisor += 2;
                }
                return noDivisorFound;
        }
    }

    /**
     * Gives a pseudorandom prime number.
     * @param bound The bound for the prime number. May be negative. For
     *              example, &minus;1000.
     * @return A pseudorandom prime number between 0 and <code>bound</code>. The
     * sign of the prime number will match the sign of <code>bound</code>. For
     * example, &minus;709.
     * @throws IllegalArgumentException If <code>bound</code> is &minus;1, 0 or
     * 1.
     */
    public static int randomPrime(int bound) {
        List<Integer> primes = EratosthenesSieve.listPrimes(bound);
        int primePi = primes.size();
        if (primePi == 0) {
            String excMsg = "No primes in range 0 to " + bound;
            throw new IllegalArgumentException(excMsg);
        }
        int index = ExtendedRandom.nextInt(primePi);
        return primes.get(index);
    }

    /**
     * Gives a pseudorandom prime number within specified bounds. The current
     * implementation relies on having a complete list of primes between the
     * bounds and to or through 0. For the sake of performance, the ranges
     * should be small, but not too small. Keeping the bounds within the range
     * of <code>short</code> should deliver results reasonably quickly.
     * @param lowerBound The lower bound for the pseudorandom prime. For
     *                   example, &minus;2048. If this number is itself prime,
     *                   it may be given as a result, especially if the range is
     *                   small.
     * @param upperBound The upper bound for the pseudorandom prime, preferably
     *                   greater than <code>lowerBound</code> but not too much
     *                   greater. For example, 2047.  If this number is itself
     *                   prime, it may be given as a result, especially if the
     *                   range is small.
     * @return A prime number within the specified bounds. Given the examples
     * above, the result might &minus;1109.
     * @throws IllegalArgumentException If there are no primes within the
     * specified bounds. The exception message will say so, e.g., "There are no
     * primes between 888 and 906".
     */
    public static int randomPrime(int lowerBound, int upperBound) {
        List<Integer> primes = EratosthenesSieve.listPrimes(lowerBound,
                upperBound);
        int size = primes.size();
        if (size == 0) {
            String excMsg = "There are no primes between " + lowerBound
                    + " and " + upperBound;
            throw new IllegalArgumentException(excMsg);
        }
        int index = ExtendedRandom.nextInt(size);
        return primes.get(index);
    }

    /**
     * Calculates the remainder of the division of <i>a</i> by <i>b</i>. This
     * function gives the same result as the <code>%</code> operator if <i>a</i>
     * and <i>b</i> are both positive. But if <i>a</i> is negative and <i>b</i>
     * is positive, this function will return either 0 or a positive integer,
     * whereas the <code>%</code> operator returns a negative number. Both
     * values, and infinitely many others, are mathematically correct, but our
     * expectation is for the return value to be at least 0 and less than the
     * modulo.
     * @param a The number to be divided by the modulo. For example, &minus;118.
     * @param b The modulo. For example, 30.
     * @return Given positive <code>b</code>, either 0 or a positive integer
     * less than <code>b</code> according to whether or not <code>a</code>
     * divides <code>b</code> evenly. For example, 2, since &minus;4 &times; 30
     * + 2 = &minus;118. This function has not yet been tested for negative
     * <code>b</code>.
     * @throws ArithmeticException If <code>b</code> equals 0, as the
     * calculation involves division by 0.
     */
    public static int mod(int a, int b) {
        if (b == 0) {
            String excMsg = "Taking " + a + " modulo " + b
                    + " involves division by 0";
            throw new ArithmeticException(excMsg);
        }
        int intermediate = a % b;
        return (intermediate < 0) ? b + intermediate : intermediate;
    }

    /**
     * Calculates the greatest common divisor (GCD) of two integers using the
     * Euclidean algorithm. The algorithm is implemented through recursion.
     * @param a One of the two integers. Need not be greater than or less than
     *          <code>b</code> in any sense. For example, 42.
     * @param b One of the two integers. Need not be greater than or less than
     *          <code>a</code> in any sense. For example, &minus;49.
     * @return The GCD. For example, 7. However, rather than throw an exception,
     * this function gives the wrong answer for certain pairs involving
     * <code>Long.MIN_VALUE</code>, e.g., gcd(&minus;9223372036854775808, 0) is
     * incorrectly given as &minus;9223372036854775808 &mdash; the correct
     * answer, 9223372036854775808, is just outside the range of
     * <code>long</code>.
     */
    public static long euclideanGCD(long a, long b) {
        if (b == 0) {
            return Math.abs(a);
        } else {
            return euclideanGCD(b, a % b);
        }
    }

    /**
     * Computes the divisors of a number. This algorithm has not been optimized
     * for larger numbers (e.g., primes with more than 16 significant bits).
     * @param n The number to compute the divisors of. For example, 496. The
     *          behavior of this function is undefined for 0 and negative
     *          numbers. This is subject to change in a later version.
     * @return A list of the divisors of <code>n</code>, starting with 1, ending
     * with <code>n</code>, and with any other divisors in between in strictly
     * ascending order. For example, 1, 2, 4, 8, 16, 31, 62, 124, 248, 496.
     */
    public static List<Integer> divisors(int n) {
        Set<Integer> set = new HashSet<>();
        int smallDivisor = 1;
        int bigDivisor = n;
        while (smallDivisor < bigDivisor) {
            set.add(smallDivisor);
            set.add(bigDivisor);
            int remainder;
            do {
                smallDivisor++;
                remainder = n % smallDivisor;
                if (remainder == 0) {
                    bigDivisor = n / smallDivisor;
                }
            } while (remainder != 0);
        }
        List<Integer> divisors = new ArrayList<>(set);
        Collections.sort(divisors);
        return divisors;
    }

    /**
     * Pseudorandomly chooses a partition of a number into a specified number of
     * parts. The algorithm may be described as "greedy."
     * @param n The number to partition. For example, 72. The behavior of this
     *          function is undefined if the number to be partitioned is 0 or
     *          negative. This will probably change in a later version.
     * @param numParts The number of desired parts. Should be at least 1. For
     *                 example, 17.
     * @return A list of positive integers adding up to <code>n</code>. The list
     * is not guaranteed to be in any particular order, though the smaller parts
     * are likelier to be towards the end of the list. For example, 19, 3, 22,
     * 5, 1, 1, 10, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1 (these seventeen numbers add up
     * to 72). However, if <code>numParts</code> is low enough, the largest part
     * might actually be last, e.g., partitioning 119 into four parts might give
     * 35, 12, 15, 57.
     * @throws IllegalArgumentException If <code>numParts</code> is 0 or
     * negative.
     */
    public static List<Integer> randomPartition(int n, int numParts) {
        if (numParts < 1) {
            String excMsg = "Number of parts " + numParts + " is not valid";
            throw new IllegalArgumentException(excMsg);
        }
        List<Integer> parts = new ArrayList<>(numParts);
        while (numParts > 1) {
            int available = n - numParts + 1;
            int part = ExtendedRandom.nextInt(available) + 1;
            parts.add(part);
            n -= part;
            numParts--;
        }
        parts.add(n);
        return parts;
    }

}
