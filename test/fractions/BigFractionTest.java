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

    // TODO: Write test for toString() not in lowest terms

    // TODO: Write test for toString() with negative denominator

    @Test
    void testToHTMLStringAlreadyInLowestTerms() {
        BigInteger numer = getPositiveInteger();
        BigInteger denom = nextCoprime(numer);
        BigFraction instance = new BigFraction(numer, denom);
        String expected = "<sup>" + numer + "</sup>&frasl;<sub>" + denom
                + "</sub>";
        String actual = instance.toHTMLString();
        assertEquals(expected, actual);
    }

    // TODO: Write test for toHTMLString() not in lowest terms

    // TODO: Write test for toHTMLString() with negative denominator

    // TODO: Write test for toTeXString()

    // TODO: Write test for toTeXString() not in lowest terms

    // TODO: Write test for toTeXString() with negative denominator

    @Test
    public void testToStringOmitsDenomOne() {
        BigInteger numerator = new BigInteger(72, RANDOM);
        BigFraction instance = new BigFraction(numerator, BigInteger.ONE);
        String expected = numerator.toString();
        String actual = instance.toString();
        assertEquals(expected, actual);
    }

    @Test
    void testGetNumeratorAlreadyInLowestTerms() {
        BigInteger expected = getPositiveInteger();
        BigInteger denom = nextCoprime(expected);
        BigFraction instance = new BigFraction(expected, denom);
        BigInteger actual = instance.getNumerator();
        String message = "Getting numerator of " + instance;
        assertEquals(expected, actual, message);
    }

    // TODO: Write test for getNumerator() not in lowest terms

    // TODO: Write test for getNumerator() with negative denominator

    @Test
    void testGetDenominatorAlreadyInLowestTerms() {
        BigInteger numer = getPositiveInteger();
        BigInteger expected = nextCoprime(numer);
        BigFraction instance = new BigFraction(numer, expected);
        BigInteger actual = instance.getDenominator();
        String message = "Getting denominator of " + instance;
        assertEquals(expected, actual, message);
    }

    // TODO: Write test for getDenominator() not in lowest terms

    // TODO: Write test for getDenominator() with negative denominator

    @Test  @org.junit.jupiter.api.Disabled
    void testCompareTo() {
        fail("HAVEN'T WRITTEN TEST YET");
    }

    @Test
    void testConstructorRejectsDenomZero() {
        BigInteger numer = getPositiveInteger();
        String message = "Numerator " + numer
                + " and denominator 0 should've caused exception";
        Throwable t = assertThrows(ArithmeticException.class, () -> {
            BigFraction badResult = new BigFraction(numer, BigInteger.ZERO);
            System.out.println(message + ", not given " + badResult);
        }, message);
        String excMsg = t.getMessage();
        assert excMsg != null : "Exception message should not be null";
        assert !excMsg.isBlank() : "Exception message should not be blank";
        System.out.println("\"" + excMsg + "\"");
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
