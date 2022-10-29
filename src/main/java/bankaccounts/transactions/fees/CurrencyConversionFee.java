package bankaccounts.transactions.fees;

import bankaccounts.transactions.Transaction;
import currency.CurrencyAmount;
import currency.CurrencyConversionNeededException;
import currency.CurrencyConverter;

import java.time.LocalDateTime;

public class CurrencyConversionFee extends Fee {

    private Transaction assocTransact;

    private CurrencyConversionNeededException precipExc;

    public CurrencyConversionFee(CurrencyAmount amount, LocalDateTime dateTime,
                                 Transaction associatedTransaction) {
        this(amount, dateTime, associatedTransaction, null);
    }

    public CurrencyConversionFee(CurrencyAmount amount, LocalDateTime dateTime,
                                 Transaction associatedTransaction,
                                 CurrencyConversionNeededException precipitatingException) {
        super(amount, dateTime, "Currency Conversion Fee");
        this.assocTransact = associatedTransaction;
        this.precipExc = precipitatingException;
    }

}
