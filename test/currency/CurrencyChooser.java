package currency;

import java.util.ArrayList;
import java.util.Currency;
import java.util.HashSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import randomness.ExtendedRandom;

public class CurrencyChooser {

    // TODO: Write tests for this
    public static Currency chooseCurrency() {
        return Currency.getInstance("XTS");
    }

    // TODO: Write tests for this
    public static Currency chooseCurrency(int fractionDigits) {
        return Currency.getInstance("XTS");
    }

    // TODO: Write tests for this
    public static Currency chooseCurrencyOtherThan(Currency currency) {
        return Currency.getInstance("USD");
    }

}
