package currency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Currency;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CurrencyAmountTest {

    static final Currency CANADA_DOLLAR_CURRENCY
            = Currency.getInstance(Locale.CANADA);
    static final Currency EAST_CARIBBEAN_DOLLARS = Currency.getInstance("XCD");
    static final Currency EURO_CURRENCY = Currency.getInstance("EUR");
    static final Currency US_DOLLAR_CURRENCY = Currency.getInstance(Locale.US);
    static final Currency YEN_CURRENCY = Currency.getInstance(Locale.JAPAN);

    @Test
    void testGetAmountInCents() {
        System.out.println("getAmountInCents");
        long expected = 43075L;
        CurrencyAmount testAmount = new CurrencyAmount(expected,
                US_DOLLAR_CURRENCY);
        long actual = testAmount.getAmountInCents();
        assertEquals(expected, actual);
    }

    @Test
    void testGetUnitAmount() {
        System.out.println("getUnitAmount");
        long amount = 10341L;
        long expected = amount / 100;
        CurrencyAmount testAmount = new CurrencyAmount(amount,
                CANADA_DOLLAR_CURRENCY);
        long actual = testAmount.getUnitAmount();
        assertEquals(expected, actual);
    }

    @Test
    void testGetChangeAmount() {
        System.out.println("getChangeAmount");
        long amount = 10341L;
        long expected = amount % 100;
        CurrencyAmount testAmount = new CurrencyAmount(amount,
                CANADA_DOLLAR_CURRENCY);
        long actual = testAmount.getChangeAmount();
        assertEquals(expected, actual);
    }

    @Test
    void testGetCurrency() {
        System.out.println("getCurrency");
        CurrencyAmount testAmount = new CurrencyAmount(1899L, EURO_CURRENCY);
        Currency actual = testAmount.getCurrency();
        assertEquals(EURO_CURRENCY, actual);
    }

    @Test
    void testPlus() {
        System.out.println("plus");
        CurrencyAmount testAddendA = new CurrencyAmount(4890398L,
                US_DOLLAR_CURRENCY);
        CurrencyAmount testAddendB = new CurrencyAmount(4899L,
                US_DOLLAR_CURRENCY);
        CurrencyAmount expected = new CurrencyAmount(4895297L,
                US_DOLLAR_CURRENCY);
        CurrencyAmount actual = testAddendA.plus(testAddendB);
        assertEquals(expected, actual);
    }

    @Test
    void testPlusOnDiffCurrencies() {
        CurrencyAmount testAddendA = new CurrencyAmount(100000,
                CANADA_DOLLAR_CURRENCY);
        CurrencyAmount testAddendB = new CurrencyAmount(25000000,
                YEN_CURRENCY);
        System.out.print("Trying to add " + testAddendA.toString()
                + " to " + testAddendB.toString() + "... ");
        Exception exception
                = assertThrows(CurrencyConversionNeededException.class, () -> {
            CurrencyAmount result = testAddendA.plus(testAddendB);
            System.out.println("should not have given result "
                    + result.toString());
        });
        System.out.println("caused CurrencyConversionNeededException");
        String excMsg = exception.getMessage();
        assert excMsg != null : "Exception message should not be null";
        System.out.println("\"" + excMsg + "\"");
    }

    @Test
    void testMinus() {
        System.out.println("minus");
        CurrencyAmount testMinuend = new CurrencyAmount(4890398L,
                US_DOLLAR_CURRENCY);
        CurrencyAmount testSubtrahend = new CurrencyAmount(4899L,
                US_DOLLAR_CURRENCY);
        CurrencyAmount expected = new CurrencyAmount(4885499L,
                US_DOLLAR_CURRENCY);
        CurrencyAmount actual = testMinuend.minus(testSubtrahend);
        assertEquals(expected, actual);
    }

    @Test
    void testMinusOnDiffCurrencies() {
        CurrencyAmount testMinuend = new CurrencyAmount(100000,
                CANADA_DOLLAR_CURRENCY);
        CurrencyAmount testSubtrahend = new CurrencyAmount(25000000,
                YEN_CURRENCY);
        System.out.print("Trying to subtract " + testSubtrahend.toString()
                + " from " + testMinuend.toString() + "... ");
        Exception exception
                = assertThrows(CurrencyConversionNeededException.class, () -> {
            CurrencyAmount result = testMinuend.minus(testSubtrahend);
            System.out.println("should not have given result "
                    + result.toString());
        });
        System.out.println("caused CurrencyConversionNeededException");
        String excMsg = exception.getMessage();
        assert excMsg != null : "Exception message should not be null";
        System.out.println("\"" + excMsg + "\"");
    }

    @Test
    void testTimes() {
        System.out.println("times");
        CurrencyAmount testMultiplicand = new CurrencyAmount(5399L,
                US_DOLLAR_CURRENCY);
        short testMultiplier = 3;
        CurrencyAmount expected = new CurrencyAmount(16197L,
                US_DOLLAR_CURRENCY);
        CurrencyAmount actual = testMultiplicand.times(testMultiplier);
        assertEquals(expected, actual);
    }

    @Test
    void testDivides() {
        System.out.println("divides");
        CurrencyAmount testDividend = new CurrencyAmount(32200L,
                CANADA_DOLLAR_CURRENCY);
        CurrencyAmount expected = new CurrencyAmount(10733,
                CANADA_DOLLAR_CURRENCY);
        CurrencyAmount actual = testDividend.divides((short) 3);
        assertEquals(expected, actual);
    }

    @Test
    void testDivideByZero() {
        CurrencyAmount testDividend = new CurrencyAmount(58347L,
                US_DOLLAR_CURRENCY);
        short testDivisor = 0;
        try {
            CurrencyAmount result = testDividend.divides(testDivisor);
            String msg = "Trying to divide " + testDividend.toString()
                    + " by 0 should've caused an exception, not given result "
                    + result.toString();
            fail(msg);
        } catch (IllegalArgumentException iae) {
            System.out.println("Trying to divide " + testDividend.toString()
                    + " by 0 correctly triggered IllegalArgumentException");
            System.out.println("\"" + iae.getMessage() + "\"");
        } catch (ArithmeticException ae) {
            System.out.println("ArithmeticException is adequate for dividing "
                    + testDividend.toString() + " by 0");
            System.out.println("\"" + ae.getMessage() + "\"");
        } catch (RuntimeException re) {
            String msg = re.getClass().getName()
                    + " is the wrong exception to throw for trying to divide "
                    + testDividend.toString() + " by 0";
            fail(msg);
        }
    }

    @Test
    void testToString() {
        System.out.println("toString");
        CurrencyAmount testAmount = new CurrencyAmount(4399L,
                US_DOLLAR_CURRENCY);
        String expected = "$43.99";
        String actual = testAmount.toString();
        assertEquals(expected, actual);
    }

    @Test
    void testToStringCentAmountsSingleDigit() {
        CurrencyAmount testAmount;
        String expected, actual;
        for (int i = 0; i < 10; i++) {
            testAmount = new CurrencyAmount(i, US_DOLLAR_CURRENCY);
            expected = "$0.0" + i;
            actual = testAmount.toString();
            assertEquals(expected, actual);
        }
    }

    @Test
    void testToStringCentAmountsTwoDigits() {
        CurrencyAmount testAmount;
        String expected, actual;
        for (int i = 10; i < 100; i++) {
            testAmount = new CurrencyAmount(i, US_DOLLAR_CURRENCY);
            expected = "$0." + i;
            actual = testAmount.toString();
            assertEquals(expected, actual);
        }
    }

    @Test
    void testToStringNegativeAmount() {
        CurrencyAmount testAmount = new CurrencyAmount(-380,
                US_DOLLAR_CURRENCY);
        String expected = "$-3.80";
        String actual = testAmount.toString();
        assertEquals(expected, actual);
    }

    @Test
    void testEquals() {
        System.out.println("equals");
        CurrencyAmount amountA = new CurrencyAmount(3272500L,
                US_DOLLAR_CURRENCY);
        CurrencyAmount amountB = new CurrencyAmount(3272500L,
                US_DOLLAR_CURRENCY);
        String msg = amountA.toString() + " should be found to be equal to "
                + amountB.toString();
        assertEquals(amountA, amountB, msg);
        assertEquals(amountB, amountA, msg);
        amountB = new CurrencyAmount(3272500L, EURO_CURRENCY);
        msg = amountA.toString() + " should not be found to be equal to "
                + amountB.toString();
        assertNotEquals(amountA, amountB, msg);
        amountB = new CurrencyAmount(3272499L, US_DOLLAR_CURRENCY);
        msg = amountA.toString() + " should not be found to be equal to "
                + amountB.toString();
        assertNotEquals(amountA, amountB, msg);
    }

    @Test
    void testHashCode() {
        System.out.println("hashCode");
        CurrencyAmount dollarAmount = new CurrencyAmount(8987L,
                US_DOLLAR_CURRENCY);
        CurrencyAmount oneDollarCent = new CurrencyAmount(1L,
                US_DOLLAR_CURRENCY);
        CurrencyAmount euroAmount = new CurrencyAmount(8987L, EURO_CURRENCY);
        CurrencyAmount oneEuroCent = new CurrencyAmount(1L, EURO_CURRENCY);
        CurrencyAmount yenAmount = new CurrencyAmount(8987L, YEN_CURRENCY);
        CurrencyAmount oneYen = new CurrencyAmount(1L, YEN_CURRENCY);
        HashSet<Integer> hashSet = new HashSet<>();
        int expectedSize = 0;
        for (int i = 0; i < 100; i++) {
            hashSet.add(dollarAmount.hashCode());
            dollarAmount = dollarAmount.plus(oneDollarCent);
            hashSet.add(euroAmount.hashCode());
            euroAmount = euroAmount.plus(oneEuroCent);
            hashSet.add(yenAmount.hashCode());
            yenAmount = yenAmount.plus(oneYen);
            expectedSize += 3;
        }
        String msg = "Set of hash codes should have " + expectedSize
                + " elements";
        assertEquals(expectedSize, hashSet.size(), msg);
    }

    @Test
    void testCompareTo() {
        System.out.println("compareTo");
        CurrencyAmount firstAmount = new CurrencyAmount(389L, EURO_CURRENCY);
        CurrencyAmount negativeAmount = new CurrencyAmount(-4500,
                EURO_CURRENCY);
        CurrencyAmount secondAmount = new CurrencyAmount(1899L, EURO_CURRENCY);
        CurrencyAmount thirdAmount = new CurrencyAmount(500044873L,
                EURO_CURRENCY);
        CurrencyAmount zeroethAmount = new CurrencyAmount(0L, EURO_CURRENCY);
        List<CurrencyAmount> toBeSorted = new ArrayList<>();
        toBeSorted.add(firstAmount);
        toBeSorted.add(negativeAmount);
        toBeSorted.add(secondAmount);
        toBeSorted.add(thirdAmount);
        toBeSorted.add(zeroethAmount);
        List<CurrencyAmount> expected = new ArrayList<>();
        expected.add(negativeAmount);
        expected.add(zeroethAmount);
        expected.add(firstAmount);
        expected.add(secondAmount);
        expected.add(thirdAmount);
        List<CurrencyAmount> actual = new ArrayList<>(toBeSorted);
        Collections.sort(actual);
        assertEquals(expected, actual);
    }

    @Test
    void testCompareToDiffCurrency() {
        CurrencyAmount dollarAmount = new CurrencyAmount(599L,
                US_DOLLAR_CURRENCY);
        CurrencyAmount yenAmount = new CurrencyAmount(599L, YEN_CURRENCY);
        try {
            int result = dollarAmount.compareTo(yenAmount);
            String msg = "Trying to compare " + dollarAmount.toString()
                    + " to " + yenAmount.toString()
                    + " should have caused an exception, not given result "
                    + result;
            fail(msg);
        } catch (CurrencyConversionNeededException currConvNeedExc) {
            System.out.println("Trying to compare " + dollarAmount.toString()
                    + " to " + yenAmount.toString()
                    + " correctly caused CurrencyConversionNeededException");
            System.out.println("\"" + currConvNeedExc.getMessage() + "\"");
        } catch (RuntimeException re) {
            String msg = re.getClass().getName()
                    + " is the wrong exception to throw for trying to compare "
                    + dollarAmount.toString() + " to " + yenAmount.toString();
            fail(msg);
        }
    }

    @Test
    void testConstructorRejectsPreciousMetals() {
        Currency platinum = Currency.getInstance("XPT");
        System.out.print("Trying to use " + platinum.getDisplayName() + "("
                + platinum.getSymbol() + ") for currency amount... ");
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> {
            CurrencyAmount badAmount = new CurrencyAmount(100, platinum);
                    System.out.println("somehow created " + badAmount);
        });
        System.out.println("correctly caused IllegalArgumentException");
        String excMsg = exception.getMessage();
        assert excMsg != null : "Exception message should not be null";
        System.out.println("\"" + excMsg + "\"");
    }

    @Test
    void testConstructorInfersUnitedStatesDollars() {
        long cents = 50389L;
        CurrencyAmount expected = new CurrencyAmount(cents);
        CurrencyAmount actual = new CurrencyAmount(cents, US_DOLLAR_CURRENCY);
        assertEquals(expected, actual);
    }

}
