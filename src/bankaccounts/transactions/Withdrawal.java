package bankaccounts.transactions;

import bankaccounts.BankAccount;
import currency.CurrencyAmount;

import java.time.LocalDateTime;

public class Withdrawal extends Transaction {

    public Withdrawal(CurrencyAmount amount, LocalDateTime dateTime) {
        this(amount, dateTime, "Withdrawal");
    }

    public Withdrawal(CurrencyAmount amount, LocalDateTime dateTime, String description) {
        super(amount, dateTime, description);
        if (amount.compareTo(BankAccount.INITIALIZATION_ACCOUNT_BALANCE) > 0) {
            String excMsg = "Withdrawal amount " + amount.toString() + " ought to be negative";
            throw new IllegalArgumentException(excMsg);
        }
    }

}
