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
            = new CurrencyConversionNeededException("test", dollarAmount,
                    euroAmount);

    @Test
    void testGetAmountA() {
        System.out.println("getAmountA");
        assertEquals(dollarAmount, testExc.getAmountA());
    }

    @Test
    void testGetAmountB() {
        System.out.println("getAmountB");
        assertEquals(euroAmount, testExc.getAmountB());
    }

    @Test
    void testGetOccurDateTime() {
        System.out.println("getOccurDateTime");
        String occurDateTimeStr = testExc.getOccurDateTime().toString();
        String nowDateTimeStr = LocalDateTime.now().toString();
        String beforeMsg = "Exception occurrence date/time " + occurDateTimeStr
                + " should be before now, " + nowDateTimeStr;
        assert LocalDateTime.now().compareTo(testExc.getOccurDateTime()) >= 1
                : beforeMsg;
        String testStartTimeStr = testBeginDateTime.toString();
        String afterMsg = "Exception occurrence date/time " + occurDateTimeStr
                + " should be after start of tests time " + testStartTimeStr;
        assert testExc.getOccurDateTime().compareTo(testBeginDateTime) >= 1
                : afterMsg;
    }

    /**
     * This only checks that the currency is correct, e.g., a dollar to euros
     * exchange is in euros. Checking that the amount is correct belongs in
     * CurrencyConverterTest.
     */
    @Test
    void testExchangeAToB() {
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
    void testExchangeBToA() {
        System.out.println("exchangeBToA");
        Currency bToACurrency = testExc.exchangeBToA().getCurrency();
        assertEquals(DOLLARS, bToACurrency);
    }

    @Test
    void testConstructor() {
        Currency dollarCurrency = Currency.getInstance("USD");
        CurrencyAmount amountA = new CurrencyAmount(100000L, dollarCurrency);
        CurrencyAmount amountB = new CurrencyAmount(5000L, dollarCurrency);
        Throwable t = assertThrows(IllegalArgumentException.class, () -> {
            String excMsg = "This exception should not have been constructed";
            CurrencyConversionNeededException exc
                    = new CurrencyConversionNeededException(excMsg, amountA,
                    amountB);
            System.out.println(exc.toString());
        });
        String excMsg = t.getMessage();
        assert excMsg != null : "Exception message should not be null";
        System.out.println("\"" + excMsg + "\"");
    }

}
