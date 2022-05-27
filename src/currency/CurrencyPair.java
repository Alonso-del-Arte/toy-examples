package currency;

import java.util.Currency;

public class CurrencyPair {

    // TODO: Write tests for this
    public Currency getFrom() {
        return Currency.getInstance("XTS");
    }

    // TODO: Write tests for this
    public Currency getTo() {
        return Currency.getInstance("XXX");
    }

    public CurrencyPair(Currency from, Currency to) {}

}
