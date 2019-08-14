package bankaccounts;

import currency.CurrencyAmount;

import java.time.LocalDateTime;

public class Deposit extends Transaction {

    public Deposit(CurrencyAmount amount, LocalDateTime dateTime) {
        super(amount, dateTime);
        if (amount.compareTo(BankAccount.INITIALIZATION_ACCOUNT_BALANCE) < 0) {
            String excMsg = "Negative deposit amount " + amount.toString() + " is not valid";
            throw new IllegalArgumentException(excMsg);
        }
    }

}
