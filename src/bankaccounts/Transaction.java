package bankaccounts;

import currency.CurrencyAmount;

import java.time.LocalDateTime;

public abstract class Transaction {

    private final CurrencyAmount transactionAmount;
    private final LocalDateTime transactionDate;

    public CurrencyAmount getTransactionAmount() {
        return this.transactionAmount;
    }

    public LocalDateTime getTransactionDate() {
        return this.transactionDate;
    }

    public Transaction(CurrencyAmount amount, LocalDateTime dateTime) {
        this.transactionAmount = amount;
        this.transactionDate = dateTime;
    }

}
