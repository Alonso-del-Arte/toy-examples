package currency;

import java.util.Currency;
import java.util.Locale;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CurrencyConverterTest {

    private static final Currency UNITED_STATES_DOLLARS
            = Currency.getInstance(Locale.US);
    private static final Currency YEN = Currency.getInstance(Locale.JAPAN);

    /**
     * This test assumes $1 is at least 75 yen but no more than 125 yen.
     */
    @Test
    void testConvertDollarsToYen() {
        fail("Examine this test");
        int cents = 10000;
        CurrencyAmount dollarAmount = new CurrencyAmount(cents,
                UNITED_STATES_DOLLARS);
        int minCents = (int) Math.floor(0.75 * cents);
        CurrencyAmount minYen = new CurrencyAmount(minCents, YEN);
        CurrencyAmount toYen = CurrencyConverter.convert(dollarAmount, YEN);
        System.out.println(dollarAmount.toString() + " is said to exchange to "
                + toYen.toString());
        String minMsg = dollarAmount.toString() + " should be more than "
                + minYen.toString();
        assert toYen.compareTo(minYen) >= 0 : minMsg;
        int maxCents = (int) Math.ceil(1.25 * cents);
        CurrencyAmount maxYen = new CurrencyAmount(maxCents, YEN);
        String maxMsg = dollarAmount.toString() + " should be less than "
                + maxYen.toString();
        assert toYen.compareTo(maxYen) <= 0 : maxMsg;
    }

    /**
     * This test assumes 100 yen is no more than $1.
     */
    @Test
    void testConvertYenToDollars() {
        fail("Examine this test");
        int cents = 1000000;
        CurrencyAmount yenAmount = new CurrencyAmount(cents, YEN);
        CurrencyAmount maxDollars = new CurrencyAmount(cents + cents / 4,
                UNITED_STATES_DOLLARS);
        CurrencyAmount toDollars = CurrencyConverter.convert(yenAmount,
                UNITED_STATES_DOLLARS);
        System.out.println(yenAmount.toString() + " is said to exchange to "
                + toDollars.toString());
        String msg = yenAmount.toString() + " should not be more than "
                + maxDollars.toString();
        assert toDollars.compareTo(maxDollars) <= 0 : msg;
    }

    @Test
    void testConvertNegativeAmountStaysNegative() {
        fail("Examine this test");
        CurrencyAmount negAmount = new CurrencyAmount(-300L,
                UNITED_STATES_DOLLARS);
        CurrencyAmount zeroYen = new CurrencyAmount(0L, YEN);
        CurrencyAmount convertedAmount = CurrencyConverter.convert(negAmount,
                YEN);
        System.out.println(negAmount.toString() + " is said to exchange to "
                + convertedAmount.toString());
        String msg = negAmount.toString()
                + " should convert to negative amount of yen";
        assert zeroYen.compareTo(convertedAmount) >= 1 : msg;
    }

    @Test
    void testZeroAmountStaysZero() {
        fail("Examine this test");
        CurrencyAmount zeroDollars = new CurrencyAmount(0L,
                UNITED_STATES_DOLLARS);
        CurrencyAmount zeroYen = new CurrencyAmount(0L, YEN);
        CurrencyAmount convertedAmount = CurrencyConverter.convert(zeroDollars,
                YEN);
        System.out.println(zeroDollars.toString() + " is said to exchange to "
                + convertedAmount.toString());
        assertEquals(zeroYen, convertedAmount);
    }

    @Test
    void testConvertPositiveAmountStaysPositive() {
        fail("Examine this test");
        CurrencyAmount posAmount = new CurrencyAmount(5989,
                UNITED_STATES_DOLLARS);
        CurrencyAmount zeroYen = new CurrencyAmount(0L, YEN);
        CurrencyAmount convertedAmount = CurrencyConverter.convert(posAmount,
                YEN);
        System.out.println(posAmount.toString() + " is said to exchange to "
                + convertedAmount.toString());
        String msg = posAmount.toString()
                + " should convert to positive amount of yen";
        assert zeroYen.compareTo(convertedAmount) <= -1 : msg;
    }

    @Test
    void testNoChangeSameCurrencyDollars() {
        fail("Examine this test");
        CurrencyAmount someAmount = new CurrencyAmount(81052,
                UNITED_STATES_DOLLARS);
        CurrencyAmount convertedAmount = CurrencyConverter.convert(someAmount,
                UNITED_STATES_DOLLARS);
        assertEquals(someAmount, convertedAmount);
    }

    @Test
    void testNoChangeSameCurrencyYen() {
        fail("Examine this test");
        CurrencyAmount someAmount = new CurrencyAmount(87698264, YEN);
        CurrencyAmount convertedAmount = CurrencyConverter.convert(someAmount,
                YEN);
        assertEquals(someAmount, convertedAmount);
    }

}
