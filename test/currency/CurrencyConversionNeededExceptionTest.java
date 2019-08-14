package currency;

import java.util.Currency;

import org.junit.Test;

import static org.junit.Assert.*;

public class CurrencyConversionNeededExceptionTest {

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