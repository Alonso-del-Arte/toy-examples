package bankaccounts;

import currency.CurrencyAmount;
import entities.Entity;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;

public class CheckingAccount extends BankAccount {

    private ArrayList<Check> checksList;

    private SavingsAccount assocSav;

    public void associateSavingsAccount(SavingsAccount savings) {
        this.assocSav = savings;
    }

    @Override
    public boolean processWithdrawal(Withdrawal withdrawal) {
        CurrencyAmount projectedBalance = this.accountBalance.plus(withdrawal.getTransactionAmount());
        if (projectedBalance.compareTo(INITIALIZATION_ACCOUNT_BALANCE) < 0) {
            if (this.assocSav != null) {
                LocalDateTime transferDateTime = withdrawal.getTransactionDate().minusMinutes(2);
                Withdrawal transferWithdrawal = new Withdrawal(projectedBalance, transferDateTime);
                if (this.assocSav.processWithdrawal(transferWithdrawal)) {
                    OverdraftTransferFee transferFee = new OverdraftTransferFee(transferDateTime);
                    this.assocSav.processFee(transferFee);
                    transferDateTime = transferDateTime.plusMinutes(1);
                    CurrencyAmount deficitCompensation = projectedBalance.negate();
                    Deposit transferDeposit = new Deposit(deficitCompensation, transferDateTime);
                    this.processDeposit(transferDeposit);
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        this.accountBalance = projectedBalance;
        this.accountHistory.add(withdrawal);
        return true;
    }

    public CheckingAccount(Entity primary, Deposit initialDeposit) {
        this(primary, null, "Primary Checking", initialDeposit);
    }

    public CheckingAccount(Entity primary, Entity secondary, String label, Deposit initialDeposit) {
        this.accountBalance = BankAccount.INITIALIZATION_ACCOUNT_BALANCE;
        this.accountNumber = BankAccount.getNewAccountNumber();
        this.primaryAccountHolder = primary;
        this.noSecondaryAccountHolderFlag = (secondary == null);
        this.secondaryAccountHolder = secondary;
        this.accountLabel = label;
        this.processDeposit(initialDeposit);
        this.accountBeneficiary = null;
        this.checksList = new ArrayList<>();
        this.assocSav = null;
    }

}
