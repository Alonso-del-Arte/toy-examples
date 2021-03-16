package currency;

import sysops.EnvironmentVariableStore;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Currency;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ExchangeRateProviderTest {

    private static final String API_KEY
            = EnvironmentVariableStore.getVariable("FOREX_API_KEY");

    @Test
    void testSetupQueryURL() {
        System.out.println("setupQueryURL");
        Currency fromCurrency = CurrencyAmountTest.EURO_CURRENCY;
        Currency toCurrency = CurrencyAmountTest.US_DOLLAR_CURRENCY;
        String urlText = "https://free.currconv.com/api/v7/convert?q="
                + fromCurrency.getCurrencyCode() + "_"
                + toCurrency.getCurrencyCode() + "&compact=ultra&apiKey="
                + API_KEY;
        try {
            URL expected = new URL(urlText);
            URL actual = ExchangeRateProvider.setupQueryURL(fromCurrency,
                    toCurrency);
            assertEquals(expected, actual);
        } catch (MalformedURLException mfurle) {
            System.out.println("Test caused MalformedURLException");
            System.out.println("\"" + mfurle.getMessage() + "\"");
            fail("Test should not have caused MalformedURLException");
        }
    }

    @Test
    void testGetNumberText() {
        System.out.println("getNumberText");
        Currency currency = CurrencyAmountTest.US_DOLLAR_CURRENCY;
        String urlText = "https://free.currconv.com/api/v7/convert?q="
                + currency.getCurrencyCode() + "_" + currency.getCurrencyCode()
                + "&compact=ultra&apiKey=" + API_KEY;
        try {
            URL url = new URL(urlText);
            String expected = "1";
            String actual = ExchangeRateProvider.getNumberText(url);
            assertEquals(expected, actual);
        } catch (IOException ioe) {
            String excName = ioe.getClass().getName();
            System.out.println("Test caused " + excName);
            System.out.println("\"" + ioe.getMessage() + "\"");
            fail("Test should not have caused " + excName);
        }
    }

    @Test
    void testRate() {
        System.out.println("rate");
        Currency fromCurrency = CurrencyAmountTest.EAST_CARIBBEAN_DOLLARS;
        Currency toCurrency = CurrencyAmountTest.US_DOLLAR_CURRENCY;
        try {
            double expected = 0.37037037;
            double actual = ExchangeRateProvider.rate(fromCurrency, toCurrency);
            double delta = 0.001;
            assertEquals(expected, actual, delta);
        } catch (IOException ioe) {
            String excName = ioe.getClass().getName();
            System.out.println("Test caused " + excName);
            System.out.println("\"" + ioe.getMessage() + "\"");
            fail("Test should not have caused " + excName);
        }
    }

}
