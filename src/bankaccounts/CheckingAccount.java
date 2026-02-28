package bankaccounts;

import bankaccounts.transactions.Deposit;
import currency.CurrencyAmount;
import entities.Entity;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class CheckingAccount extends BankAccount {

    private ArrayList<Check> checksList;

    private SavingsAccount assocSav;

    public void associateSavingsAccount(SavingsAccount savings) {
        this.assocSav = savings;
    }

//    @Override
//    public boolean processWithdrawal(Withdrawal withdrawal) {
//        return true;
////        if (this.accountHistory.contains(withdrawal)) {return false;}else{
////            CurrencyAmount projectedBalance = this.accountBalance.plus(withdrawal.getTransactionAmount());
////            if (projectedBalance.compareTo(BankAccount.INITIALIZATION_ACCOUNT_BALANCE) < 0) {
////                if (this.assocSav != null) {
////                    LocalDateTime transferDateTime = withdrawal.getTransactionDate().minusMinutes(2);
////                    Withdrawal transferWithdrawal = new Withdrawal(projectedBalance, transferDateTime);
////                    if (this.assocSav.processWithdrawal(transferWithdrawal)) {
////                        OverdraftTransferFee transferFee = new OverdraftTransferFee(transferDateTime);
////                        this.assocSav.processFee(transferFee);
////                        transferDateTime = transferDateTime.plusMinutes(1);
////                        CurrencyAmount deficitCompensation = projectedBalance.negate();
////                        Deposit transferDeposit = new Deposit(deficitCompensation, transferDateTime);
////                        this.processDeposit(transferDeposit);
////                        projectedBalance = BankAccount.INITIALIZATION_ACCOUNT_BALANCE;
////                    } else {
////                        return false;
////                    }
////                } else {
////                    return false;
////                }
////            }
////            this.accountBalance = projectedBalance;
////            this.accountHistory.add(withdrawal);
////            return true;
////        }
//    }

    public CheckingAccount(Entity primary, Deposit initialDeposit) {
        this(primary, null, "Primary Checking", initialDeposit);
    }

    public CheckingAccount(Entity primary, Entity secondary, String label, Deposit initialDeposit) {
        super(primary, secondary, label, initialDeposit);
        this.checksList = new ArrayList<>();
        this.assocSav = null;
    }

}
