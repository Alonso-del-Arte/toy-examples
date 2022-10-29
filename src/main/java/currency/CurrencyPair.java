package currency;

import java.util.Currency;

public class CurrencyPair {

    private final Currency fromCurrency, toCurrency;

    public Currency getFrom() {
        return this.fromCurrency;
    }

    public Currency getTo() {
        return this.toCurrency;
    }

    public CurrencyPair(Currency from, Currency to) {
        if (from == null || to == null) {
            String excMsg = "from and to must both be non-null";
            throw new NullPointerException(excMsg);
        }
        this.fromCurrency = from;
        this.toCurrency = to;
    }

}
