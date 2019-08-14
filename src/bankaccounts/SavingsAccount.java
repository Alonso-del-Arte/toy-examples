package bankaccounts;

import entities.Entity;

public class SavingsAccount extends BankAccount {

    public SavingsAccount(Entity primary, Deposit initialDeposit) {
        this(primary, null, "Primary Savings", initialDeposit);
    }

    public SavingsAccount(Entity primary, Entity secondary, String label, Deposit initialDeposit) {
        this.accountBalance = BankAccount.INITIALIZATION_ACCOUNT_BALANCE;
        this.accountNumber = BankAccount.getNewAccountNumber();
        this.primaryAccountHolder = primary;
        this.noSecondaryAccountHolderFlag = (secondary == null);
        this.secondaryAccountHolder = secondary;
        this.accountLabel = label;
        this.processDeposit(initialDeposit);
        this.accountBeneficiary = null;
    }

}
