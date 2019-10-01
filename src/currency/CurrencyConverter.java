package currency;

import java.util.Currency;

public class CurrencyConverter {

    // STUB TO FAIL THE TEST
    public static CurrencyAmount convert(CurrencyAmount source, Currency target) {
        return new CurrencyAmount(-100 * source.getUnitAmount(), target);
    }

}
