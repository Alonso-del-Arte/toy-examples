package currency;

import java.time.LocalDateTime;
import java.util.Currency;
import java.util.Locale;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CurrencyConversionNeededExceptionTest {

    private static LocalDateTime testBeginDateTime = LocalDateTime.now();

    private static final Currency DOLLARS = Currency.getInstance(Locale.US);
    private static final Currency EUROS = Currency.getInstance("EUR");

    private static final CurrencyAmount dollarAmount
            = new CurrencyAmount(1899L, DOLLARS);
    private static final CurrencyAmount euroAmount
            = new CurrencyAmount(2472L, EUROS);

    private static CurrencyConversionNeededException testExc
            = new CurrencyConversionNeededException("test", dollarAmount, euroAmount);

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
        String msg = "Exception occurrence date/time " + occurDateTimeStr
                + " should be before now, " + nowDateTimeStr;
        assert LocalDateTime.now().compareTo(testExc.getOccurDateTime()) >= 1
                : msg;
        String testStartTimeStr = testBeginDateTime.toString();
        msg = "Exception occurrence date/time " + occurDateTimeStr
                + " should be after start of tests time " + testStartTimeStr;
        assert testExc.getOccurDateTime().compareTo(testBeginDateTime) >= 1
                : msg;
    }

    /**
     * This only checks that the currency is correct, e.g., a dollar to euros
     * exchange is in euros. Checking that the amount is correct belongs in
     * CurrencyConverterTest.
     */
    @Test
    public void testExchangeAToB() {
        System.out.println("exchangeAToB");
        Currency aToBCurrency = testExc.exchangeAToB().getCurrency();
        assertEquals(EUROS, aToBCurrency);
    }

    /**
     * This only checks that the currency is correct, e.g., a euros to dollars
     * exchange is in dollars. Checking that the amount is correct belongs in
     * CurrencyConverterTest.
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
            CurrencyConversionNeededException exc
                    = new CurrencyConversionNeededException(excMsg, amountA,
                    amountB);
            String msg = "There should be no exception for USD to USD";
            System.out.println(msg);
            System.out.println("\"" + exc.getMessage() + "\"");
            fail(msg);
        } catch (IllegalArgumentException iae) {
            System.out.println("USD to USD correctly triggered IllegalArgumentException");
            System.out.println("\"" + iae.getMessage() + "\"");
        } catch (Exception e) {
            String msg = e.getClass().getName()
                    + " is the wrong exception to throw for USD to USD";
            fail(msg);
        }
    }

}
