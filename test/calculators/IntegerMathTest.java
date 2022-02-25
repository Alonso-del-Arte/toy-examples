package calculators;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class IntegerMathTest {

    @Test
    void testEmptyPrimeList() {
        List<Integer> expected = new ArrayList<>();
        List<Integer> actual = IntegerMath.primes(0);
        assertEquals(expected, actual);
    }

    @Test
    void testListFirstFourPositivePrimes() {
        List<Integer> expected = new ArrayList<>();
        expected.add(2);
        expected.add(3);
        expected.add(5);
        expected.add(7);
        List<Integer> actual = IntegerMath.primes(10);
        assertEquals(expected, actual);
    }

}