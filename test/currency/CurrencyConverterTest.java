package currency;

import java.util.Currency;

import org.junit.Test;
import static org.junit.Assert.*;

public class CurrencyConverterTest {

    private static final Currency DOLLARS = Currency.getInstance("USD");
    private static final Currency YEN = Currency.getInstance("JPY");

    /**
     * This test assumes $1 is at least 100 yen.
     */
    @Test
    public void testConvertDollarsToYen() {
        CurrencyAmount dollarAmount = new CurrencyAmount(10000L, DOLLARS);
        CurrencyAmount minYen = new CurrencyAmount(1000000L, YEN);
        CurrencyAmount toYen = CurrencyConverter.convert(dollarAmount, YEN);
        System.out.println(dollarAmount.toString() + " is said to exchange to " + toYen.toString());
        String assertionMessage = dollarAmount.toString() + " should be more than " + minYen.toString();
        assertTrue(assertionMessage, toYen.compareTo(minYen) >= 0);
    }

    /**
     * This test assumes 100 yen is no more than $1.
     */
    @Test
    public void testConvertYenToDollars() {
        CurrencyAmount yenAmount = new CurrencyAmount(1000000L, YEN);
        CurrencyAmount maxDollars = new CurrencyAmount(10000L, DOLLARS);
        CurrencyAmount toDollars = CurrencyConverter.convert(yenAmount, DOLLARS);
        System.out.println(yenAmount.toString() + " is said to exchange to " + toDollars.toString());
        String assertionMessage = yenAmount.toString() + " should not be more than " + maxDollars.toString();
        assertTrue(assertionMessage, toDollars.compareTo(maxDollars) <= 0);
    }

    @Test
    public void testConvertNegativeAmountStaysNegative() {
        CurrencyAmount negAmount = new CurrencyAmount(-300L, DOLLARS);
        CurrencyAmount zeroYen = new CurrencyAmount(0L, YEN);
        CurrencyAmount convertedAmount = CurrencyConverter.convert(negAmount, YEN);
        System.out.println(negAmount.toString() + " is said to exchange to " + convertedAmount.toString());
        String assertionMessage = negAmount.toString() + " should convert to negative amount of yen";
        assertTrue(assertionMessage, zeroYen.compareTo(convertedAmount) >= 1);
    }

    @Test
    public void testZeroAmountStaysZero() {
        CurrencyAmount zeroDollars = new CurrencyAmount(0L, DOLLARS);
        CurrencyAmount zeroYen = new CurrencyAmount(0L, YEN);
        CurrencyAmount convertedAmount = CurrencyConverter.convert(zeroDollars, YEN);
        System.out.println(zeroDollars.toString() + " is said to exchange to " + convertedAmount.toString());
        assertEquals(zeroYen, convertedAmount);
    }

    @Test
    public void testConvertPositiveAmountStaysPositive() {
        CurrencyAmount posAmount = new CurrencyAmount(5989, DOLLARS);
        CurrencyAmount zeroYen = new CurrencyAmount(0L, YEN);
        CurrencyAmount convertedAmount = CurrencyConverter.convert(posAmount, YEN);
        System.out.println(posAmount.toString() + " is said to exchange to " + convertedAmount.toString());
        String assertionMessage = posAmount.toString() + " should convert to positive amount of yen";
        assertTrue(assertionMessage, zeroYen.compareTo(convertedAmount) <= -1);
    }

    @Test
    public void testNoChangeSameCurrencyDollars() {
        CurrencyAmount someAmount = new CurrencyAmount(81052, DOLLARS);
        CurrencyAmount convertedAmount = CurrencyConverter.convert(someAmount, DOLLARS);
        assertEquals(someAmount, convertedAmount);
    }

    @Test
    public void testNoChangeSameCurrencyYen() {
        CurrencyAmount someAmount = new CurrencyAmount(87698264, YEN);
        CurrencyAmount convertedAmount = CurrencyConverter.convert(someAmount, YEN);
        assertEquals(someAmount, convertedAmount);
    }

}