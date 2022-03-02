package calculators;

import java.util.HashSet;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class IntegerMathTest {

    private static final Random RANDOM = new Random();

    @Test
    void testNeitherPrimeNorComposite() {
        assert !IntegerMath.isPrime(-1) : "-1 is not prime";
        assert !IntegerMath.isPrime(0) : "0 is not prime";
        assert !IntegerMath.isPrime(1) : "1 is not prime";
    }

    @Test
    void testNotPrime() {
        int start = RANDOM.nextInt(32) + 8;
        int end = RANDOM.nextInt(4096) + start;
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
        int start = (RANDOM.nextInt(4096) + 64) | 1;
        int end = (start + RANDOM.nextInt(128) + 2) | 1;
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
    void testRandomPrimeCanGiveNegativePrime() {
        int p = IntegerMath.randomPrime(-128);
        assert IntegerMath.isPrime(p)
                : "Should have received a prime, not " + p;
        assert p < 0 : "Prime " + p + " should be negative";
    }

    @Test
    void testRandomPrime() {
        System.out.println("randomPrime");
        int size = RANDOM.nextInt(128) + 32;
        HashSet<Integer> set = new HashSet<>(size);
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

}
