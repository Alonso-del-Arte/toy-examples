package calculators;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Utility class for integer arithmetic. Mostly concerning prime numbers and
 * divisors.
 * @author Alonso del Arte
 */
public class IntegerMath {

    private static final Random RANDOM = new Random();

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
        int index = RANDOM.nextInt(primePi);
        return primes.get(index);
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

    // TODO: Write tests for this
    public List<Integer> divisors(int n) {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(Integer.MIN_VALUE);
        return numbers;
    }

}
