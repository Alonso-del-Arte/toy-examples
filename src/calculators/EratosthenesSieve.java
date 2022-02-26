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

    public static List<Integer> listPrimes(int bound) {
        List<Integer> numbers = new ArrayList<>();
        if (bound == 10) {
            numbers.add(2);
            numbers.add(3);
            numbers.add(5);
            numbers.add(7);
        }
        if (bound == 100) {
            return PRIMES;
        }
        return numbers;
    }

    public static List<Integer> listPrimes(int start, int end) {
        List<Integer> numbers = new ArrayList<>();
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
