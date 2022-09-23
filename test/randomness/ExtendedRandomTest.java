package randomness;

import currency.CurrencyAmount;
import fractions.Fraction;

import java.util.HashSet;
import java.util.Set;

import numerics.ComplexNumber;
import numerics.RomanNumeralsNumber;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ExtendedRandomTest {

    @Test
    void testNextFraction() {
        System.out.println("nextFraction");
        int capacity = 1000;
        Set<Fraction> fractions = new HashSet<>(capacity);
        for (int i = 0; i < capacity; i++) {
            fractions.add(ExtendedRandom.nextFraction());
        }
        int expected = 3 * capacity / 4;
        int actual = fractions.size();
        String msg = "Expected at least " + expected
                + " distinct fractions out of " + capacity + ", got " + actual;
        System.out.println(msg);
        assert actual >= expected : msg;
    }

}