package bankaccounts;

import bankaccounts.transactions.*;
import bankaccounts.transactions.fees.Fee;
import currency.CurrencyAmount;
import entities.Entity;

import java.util.ArrayList;

public abstract class BankAccount {

    Entity primaryAccountHolder;
    Entity secondaryAccountHolder;
    boolean noSecondaryAccountHolderFlag;
    long accountNumber;
    String accountLabel;
    CurrencyAmount accountBalance;
    ArrayList<Transaction> accountHistory;
    Entity accountBeneficiary;

    private static long nextAccountNumber = 5550000000000000L;

    public static final CurrencyAmount INITIALIZATION_ACCOUNT_BALANCE = new CurrencyAmount(0L);

    /**
     * Gives a new account number. In a program handling real life account
     * data, this would probably involve some kind of database access.
     * @return An account number not associated with any other account.
     */
    static long getNewAccountNumber() {
        nextAccountNumber++;
        return nextAccountNumber;
    }

    public void setAccountBeneficiary(Entity entity) {
        this.accountBeneficiary = entity;
    }

    public Entity getAccountBeneficiary() {
        return this.accountBeneficiary;
    }

    public void removeAccountBeneficiary() {
        this.accountBeneficiary = null;
    }

    CurrencyAmount getAccountBalance() {
        return this.accountBalance;
    }

    public void processDeposit(Deposit deposit) {
        if (!this.accountHistory.contains(deposit)) {
            this.accountBalance = this.accountBalance.plus(deposit.getTransactionAmount());
            this.accountHistory.add(deposit);
        }
    }

    /**
     * Processes withdrawal of the specified amount.
     * @param withdrawal Transaction object with the withdrawal amount with date.
     * @return True if the withdrawal succeeds, false if the balance is insufficient.
     * @throws currency.CurrencyConversionNeededException If the withdrawal amount is in a different currency.
     */
    public boolean processWithdrawal(Withdrawal withdrawal) {
        if (this.accountHistory.contains(withdrawal)) {
            return false;
        } else {
            CurrencyAmount projectedBalance = this.accountBalance.plus(withdrawal.getTransactionAmount());
            if (projectedBalance.compareTo(INITIALIZATION_ACCOUNT_BALANCE) < 0) {
                return false;
            } else {
                this.accountBalance = projectedBalance;
                this.accountHistory.add(withdrawal);
                return true;
            }
        }
    }

    void processFee(Fee fee) {
        this.accountBalance = this.accountBalance.plus(fee.getTransactionAmount());
        this.accountHistory.add(fee);
    }

    public void processComment(Comment comment) {
        this.accountHistory.add(comment);
    }

    public BankAccount(Entity primary, Entity secondary, String label, Deposit initialDeposit) {
        this.accountBalance = INITIALIZATION_ACCOUNT_BALANCE;
        this.accountNumber = getNewAccountNumber();
        this.primaryAccountHolder = primary;
        this.noSecondaryAccountHolderFlag = (secondary == null);
        this.secondaryAccountHolder = secondary;
        this.accountLabel = label;
        this.accountHistory = new ArrayList<>();
        this.processDeposit(initialDeposit);
        this.accountBeneficiary = null;
    }

}
