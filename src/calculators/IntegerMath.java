package calculators;

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

    public static long euclideanGCD(long a, long b) {
        return Math.abs(a);
    }

}
