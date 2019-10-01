package currency;

import java.time.LocalDateTime;
import java.util.Currency;

import org.junit.Test;

import static org.junit.Assert.*;

public class CurrencyConversionNeededExceptionTest {

    private static LocalDateTime testBeginDateTime = LocalDateTime.now();

    private static final Currency DOLLARS = Currency.getInstance("USD");
    private static final Currency EUROS = Currency.getInstance("EUR");

    private static final CurrencyAmount dollarAmount = new CurrencyAmount(1899L, DOLLARS);
    private static final CurrencyAmount euroAmount = new CurrencyAmount(2472L, EUROS);

    private static CurrencyConversionNeededException testExc = new CurrencyConversionNeededException("test", dollarAmount, euroAmount);

    @Test
    public void testGetAmountA() {
        System.out.println("getAmountA");
        assertEquals(dollarAmount, testExc.getAmountA());
    }

    @Test
    public void testGetAmountB() {
        System.out.println("getAmountB");
        assertEquals(euroAmount, testExc.getAmountB());
    }

    @Test
    public void testGetOccurDateTime() {
        System.out.println("getOccurDateTime");
        String occurDateTimeStr = testExc.getOccurDateTime().toString();
        String nowDateTimeStr = LocalDateTime.now().toString();
        String assertionMessage = "Exception occurrence date/time " + occurDateTimeStr + " should be before now, " + nowDateTimeStr;
        assertTrue(assertionMessage, LocalDateTime.now().compareTo(testExc.getOccurDateTime()) >= 1);
        String testStartTimeStr = testBeginDateTime.toString();
        assertionMessage = "Exception occurrence date/time " + occurDateTimeStr + " should be after start of tests time " + testStartTimeStr;
        assertTrue(assertionMessage, testExc.getOccurDateTime().compareTo(testBeginDateTime) >= 1);
    }

    /**
     * This only checks that the currency is correct, e.g., a dollar to euros exchange is in euros.
     * Checking that the amount is correct belongs in CurrencyConverterTest.
     */
    @Test
    public void testExchangeAToB() {
        System.out.println("exchangeAToB");
        Currency aToBCurrency = testExc.exchangeAToB().getCurrency();
        assertEquals(EUROS, aToBCurrency);
    }

    /**
     * This only checks that the currency is correct, e.g., a euros to dollars exchange is in dollars.
     * Checking that the amount is correct belongs in CurrencyConverterTest.
     */
    @Test
    public void testExchangeBToA() {
        System.out.println("exchangeBToA");
        Currency bToACurrency = testExc.exchangeBToA().getCurrency();
        assertEquals(DOLLARS, bToACurrency);
    }

    @Test
    public void testConstructor() {
        Currency dollarCurrency = Currency.getInstance("USD");
        CurrencyAmount amountA = new CurrencyAmount(100000L, dollarCurrency);
        CurrencyAmount amountB = new CurrencyAmount(5000L, dollarCurrency);
        try {
            String excMsg = "This exception should not have been constructed";
            CurrencyConversionNeededException exc = new CurrencyConversionNeededException(excMsg, amountA, amountB);
            String failMsg = "CurrencyConversionNeededException wrongly constructed when no conversion is needed (U. S. dollars to U. S. dollars)";
            System.out.println(failMsg);
            System.out.println("\"" + exc.getMessage() + "\"");
            fail(failMsg);
        } catch (IllegalArgumentException iae) {
            System.out.println("Trying to construct CurrencyConversionNeededException object when no conversion is needed correctly triggered IllegalArgumentException");
            System.out.println("\"" + iae.getMessage() + "\"");
        } catch (Exception e) {
            String failMsg = e.getClass().getName() + " is the wrong exception to throw for trying to construct CurrencyConversionNeededException object when no conversion is needed";
            fail(failMsg);
        }
    }

}