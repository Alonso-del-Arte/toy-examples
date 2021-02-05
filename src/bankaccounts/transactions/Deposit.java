package bankaccounts.transactions;

import bankaccounts.BankAccount;
import currency.CurrencyAmount;

import java.time.LocalDateTime;

public class Deposit extends Transaction {

    public Deposit(CurrencyAmount amount, LocalDateTime dateTime) {
        this(amount, dateTime, "Deposit");
    }

    public Deposit(CurrencyAmount amount, LocalDateTime dateTime,
                   String description) {
        super(amount, dateTime, description);
        if (amount.compareTo(BankAccount.INITIALIZATION_ACCOUNT_BALANCE) < 0) {
            String excMsg = "Negative deposit amount " + amount.toString()
                    + " is not valid";
            throw new IllegalArgumentException(excMsg);
        }
    }

}
