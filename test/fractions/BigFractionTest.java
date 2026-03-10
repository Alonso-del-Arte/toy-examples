package fractions;

import java.math.BigInteger;
import java.util.Random;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests of the BigFraction class.
 * @author Alonso del Arte
 */
class BigFractionTest {

    private static final Random RANDOM = new Random();

    private static BigInteger getPositiveInteger() {
        return new BigInteger(72, RANDOM).add(BigInteger.ONE);
    }

    private static BigInteger nextCoprime(BigInteger number) {
        BigInteger propNum = number.add(BigInteger.TWO);
        while (number.gcd(propNum).compareTo(BigInteger.ONE) > 0) {
            propNum = propNum.add(BigInteger.ONE);
        }
        return propNum;
    }

    @Test
    void testToStringAlreadyInLowestTerms() {
        BigInteger numer = getPositiveInteger();
        BigInteger denom = nextCoprime(numer);
        BigFraction instance = new BigFraction(numer, denom);
        String expected = numer + "/" + denom;
        String actual = instance.toString();
        assertEquals(expected, actual);
    }

    @Test  @org.junit.jupiter.api.Disabled
    void testCompareTo() {
        fail("Haven't written test yet");
    }

    /**
     * Constructor test. If the numerator is null, a {@code
     * NullPointerException} should be thrown.
     */
    @Test
    void testConstructorRejectsNullNumerator() {
        BigInteger denominator = getPositiveInteger();
        Throwable t = assertThrows(NullPointerException.class, () -> {
            BigFraction badFraction = new BigFraction(null, denominator);
            System.out.println("Should not have been able to create "
                    + badFraction + " with null numerator");
        });
        System.out.println("Null numerator correctly caused NPE");
        String excMsg = t.getMessage();
        assert excMsg != null : "Message should not be null";
        System.out.println("\"" + excMsg + "\"");
    }

    /**
     * Constructor test. In addition to ensuring the denominator is not 0, the
     * constructor must also ensure the denominator is not null. If the
     * denominator is null, the constructor should throw a {@code
     * NullPointerException}.
     */
    @Test
    void testConstructorRejectsNullDenominator() {
        BigInteger numerator = getPositiveInteger();
        Throwable t = assertThrows(NullPointerException.class, () -> {
            BigFraction badFraction = new BigFraction(numerator, null);
            System.out.println("Should not have been able to create "
                    + badFraction + " with null denominator");
        });
        System.out.println("Null denominator correctly caused NPE");
        String excMsg = t.getMessage();
        assert excMsg != null : "Message should not be null";
        System.out.println("\"" + excMsg + "\"");
    }

}
