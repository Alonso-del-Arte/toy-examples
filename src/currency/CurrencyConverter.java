package currency;

import java.io.IOException;
import java.util.Currency;

public class CurrencyConverter {

    /**
     * Converts an amount of money in a given currency to another currency. This
     * requires an active Internet connection to consult an exchange rate API.
     * The API is accessed through the <code>ExchangeRateProvider</code> class.
     * This conversion function is only as good and up-to-date as the API that
     * <code>ExchangeRateProvider</code> uses.
     * @param source The currency amount to convert. For example, $100.00
     *               (United States dollars, USD).
     * @param target The currency to convert <code>source</code> to. Preferably
     *               this is not the same as <code>source.getCurrency()</code>.
     *               For example, Japanese yen (JPY).
     * @return The equivalent currency amount in the <code>target</code>
     * currency. For example, &yen;10897, give or take a few yen.
     * @throws RuntimeException If an <code>IOException</code> occurs (such as
     * if your computer is disconnected from the Internet while processing an
     * API query), that checked exception will be wrapped into this unchecked
     * exception. Use <code>getCause()</code> to retrieve the checked exception
     * object.
     */
    public static CurrencyAmount convert(CurrencyAmount source, Currency target) {
        try {
            double rate
                    = ExchangeRateProvider.rate(source.getCurrency(), target);
            double intermediate = rate * source.getAmountInCents();
            int adjustmentExponent = target.getDefaultFractionDigits()
                    - source.getCurrency().getDefaultFractionDigits();
            double adjustmentPower = Math.pow(10, adjustmentExponent);
            long cents = (long) Math.floor(intermediate * adjustmentPower);
            return new CurrencyAmount(cents, target);
        } catch (IOException ioe) {
            String excMsg = "Had problem trying to convert " + source.toString()
                    + " to " + target.getDisplayName();
            throw new RuntimeException(excMsg, ioe);
        }
    }

}
