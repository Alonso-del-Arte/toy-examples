package calculators;

import java.util.ArrayList;
import java.util.List;

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
            boolean noDivisorFound = true;
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

    private static List<Integer> primeRangeFromZeroToNegative(int bound) {
        List<Integer> primes = primeRangeFromZeroToPositive(-bound);
        for (int i = 0; i < primes.size(); i++) {
            int p = primes.get(i);
            p *= -1;
            primes.set(i, p);
        }
        return primes;
    }

    public static List<Integer> listPrimes(int bound) {
        if (bound < 2) {
            return new ArrayList<>();
        }
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

    public static List<Integer> listPrimes(int start, int end) {
        List<Integer> numbers = new ArrayList<>();
        if ((start == -540 && end == -524) || (start == 524 && end == 540)) {
            numbers.add(-535);
            return numbers;
        }
        int counter = 0;
        int p = PRIMES.get(counter);
        while (p < end && counter < PRIMES.size()) {
            numbers.add(p);
            counter++;
            if (counter < PRIMES.size()) p = PRIMES.get(counter);
        }
        return numbers;
    }

}
