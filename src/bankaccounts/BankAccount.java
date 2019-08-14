package bankaccounts;

import currency.CurrencyAmount;
import entities.Entity;

import java.util.ArrayList;

public abstract class BankAccount {

    protected Entity primaryAccountHolder;
    protected Entity secondaryAccountHolder;
    protected boolean noSecondaryAccountHolderFlag;
    protected long accountNumber;
    protected String accountLabel;
    protected CurrencyAmount accountBalance;
    protected ArrayList<Transaction> accountHistory;
    protected Entity accountBeneficiary;

    private static long nextAccountNumber = 5550000000000000L;

    public static final CurrencyAmount INITIALIZATION_ACCOUNT_BALANCE = new CurrencyAmount(0L);

    public static long getNewAccountNumber() {
        nextAccountNumber++;
        return nextAccountNumber;
    }

    public void processDeposit(Deposit deposit) {
        this.accountBalance = this.accountBalance.plus(deposit.getTransactionAmount());
        this.accountHistory.add(deposit);
    }

    /**
     * Processes withdrawal of the specified amount.
     * @param withdrawal Transaction object with the withdrawal amount with date.
     * @return True if the withdrawal succeeds, false if the balance is insufficient.
     * @throws currency.CurrencyConversionNeededException If the withdrawal amount is in a different currency.
     */
    public boolean processWithdrawal(Withdrawal withdrawal) {
        CurrencyAmount projectedBalance = this.accountBalance.plus(withdrawal.getTransactionAmount());
        if (projectedBalance.compareTo(INITIALIZATION_ACCOUNT_BALANCE) < 0) {
            return false;
        } else {
            this.accountBalance = projectedBalance;
            this.accountHistory.add(withdrawal);
            return true;
        }
    }

    public void processFee(Fee fee) {
        this.accountBalance = this.accountBalance.plus(fee.getTransactionAmount());
        this.accountHistory.add(fee);
    }

}
