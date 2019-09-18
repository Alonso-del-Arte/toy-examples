package bankaccounts;

import bankaccounts.transactions.Deposit;
import entities.Entity;

public class SavingsAccount extends BankAccount {

    public SavingsAccount(Entity primary, Deposit initialDeposit) {
        this(primary, null, "Primary Savings", initialDeposit);
    }

    public SavingsAccount(Entity primary, Entity secondary, String label, Deposit initialDeposit) {
        super(primary, secondary, label, initialDeposit);
    }

}
