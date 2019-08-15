package bankaccounts;

import bankaccounts.transactions.Deposit;
import bankaccounts.transactions.Withdrawal;
import currency.CurrencyAmount;
import entities.Address;
import entities.Person;

import java.time.LocalDateTime;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class CheckingAccountTest {

    private static CheckingAccount checking;
    private static SavingsAccount savings;

    @BeforeClass
    public static void setUpClass() {
        Address customerAddress = new Address("12345 Main Avenue", null, "Township Village", "Michigan", "United States", "48000-0555");
        Person customer = new Person("Joan Q. Public", customerAddress);
        CurrencyAmount initDepAmount = new CurrencyAmount(1000000L);
        LocalDateTime initDepDate = LocalDateTime.now().minusYears(1);
        Deposit initDep = new Deposit(initDepAmount, initDepDate);
        savings = new SavingsAccount(customer, initDep);
        initDepAmount = new CurrencyAmount(150000L);
        initDepDate = initDepDate.plusMinutes(5);
        initDep = new Deposit(initDepAmount, initDepDate);
        checking = new CheckingAccount(customer, initDep);
        checking.associateSavingsAccount(savings);
    }

    @Test
    public void testOverdraftTransfer() {
        System.out.println("Savings account balance: " + savings.getAccountBalance().toString());
        CurrencyAmount checkingBalance = checking.getAccountBalance();
        System.out.println("Checking account balance: " + checkingBalance.toString());
        System.out.println("Attempting to make overdraft withdrawal...");
        CurrencyAmount excess = new CurrencyAmount(899L);
        CurrencyAmount overdraftAmount = checkingBalance.plus(excess).negate();
        Withdrawal withdrawal = new Withdrawal(overdraftAmount, LocalDateTime.now());
        String assertionMessage = "Overdraft withdrawal should process by transferring from savings";
        assertTrue(assertionMessage, checking.processWithdrawal(withdrawal));
        checkingBalance = checking.getAccountBalance();
        assertionMessage = "Checking account balance after overdraft transfer should be $0.00";
        assertEquals(assertionMessage, BankAccount.INITIALIZATION_ACCOUNT_BALANCE, checkingBalance);
        System.out.println("Savings account balance: " + savings.getAccountBalance().toString());
        System.out.println("Checking account balance: " + checkingBalance.toString());
    }

}