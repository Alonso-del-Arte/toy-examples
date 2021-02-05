package bankaccounts.transactions;

import currency.CurrencyAmount;

import java.time.LocalDateTime;

public abstract class Transaction {

    private final CurrencyAmount transactionAmount;
    private final LocalDateTime transactionDate;
    private final String transactionDescription;

    public CurrencyAmount getTransactionAmount() {
        return this.transactionAmount;
    }

    public LocalDateTime getTransactionDate() {
        return this.transactionDate;
    }

    public String getTransactionDescription() {
        return this.transactionDescription;
    }

    @Override
    public String toString() {
        return this.transactionDate.toString() + " "
                + this.transactionDescription + " "
                + this.transactionAmount.toString();
    }

    Transaction(CurrencyAmount amount, LocalDateTime dateTime,
                String description) {
        this.transactionAmount = amount;
        this.transactionDate = dateTime;
        this.transactionDescription = description;
    }

}
