package bankaccounts;

import bankaccounts.transactions.Deposit;
import bankaccounts.transactions.Withdrawal;
import currency.CurrencyAmount;
import entities.Address;
import entities.Person;

import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CheckingAccountTest {

    private static CheckingAccount checking;
    private static SavingsAccount savings;

    private static final CurrencyAmount ASSURED_MINIMUM_BALANCE = new CurrencyAmount(10000L);

    @BeforeAll
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

    @BeforeEach
    public void setUp() {
        System.out.println("Savings account balance: " + savings.getAccountBalance().toString() + " prior to test");
        System.out.println("Checking account balance: " + checking.getAccountBalance().toString() + " prior to test");
    }

    @Test
    public void testDeposit() {
        System.out.println("processDeposit");
        CurrencyAmount initBal = checking.getAccountBalance();
        CurrencyAmount depositAmount = new CurrencyAmount(32768L);
        LocalDateTime depositTime = LocalDateTime.now().minusDays(2);
        Deposit deposit = new Deposit(depositAmount, depositTime);
        checking.processDeposit(deposit);
        CurrencyAmount expected = initBal.plus(depositAmount);
        CurrencyAmount actual = checking.getAccountBalance();
        assertEquals(expected, actual);
    }

    @Test
    public void testDoubleDeposit() {
        System.out.println("Verifying the same deposit can't be made twice...");
        CurrencyAmount initBal = checking.getAccountBalance();
        CurrencyAmount depositAmount = new CurrencyAmount(65536L);
        LocalDateTime depositTime = LocalDateTime.now().minusHours(8);
        Deposit deposit = new Deposit(depositAmount, depositTime);
        checking.processDeposit(deposit);
        CurrencyAmount expected = initBal.plus(depositAmount);
        checking.processDeposit(deposit);
        CurrencyAmount actual = checking.getAccountBalance();
        assertEquals(expected, actual);
    }

    @Test
    public void testWithdrawal() {
        System.out.println("processWithdrawal");
        CurrencyAmount initBal = checking.getAccountBalance();
        CurrencyAmount withdrawalAmount = new CurrencyAmount(-1024L);
        LocalDateTime withdrawalTime = LocalDateTime.now().minusDays(1);
        Withdrawal withdrawal = new Withdrawal(withdrawalAmount, withdrawalTime);
        String msg = "Should be able to make withdrawal of " + withdrawalAmount.negate().toString();
        assertTrue(checking.processWithdrawal(withdrawal), msg);
        CurrencyAmount expected = initBal.plus(withdrawalAmount);
        CurrencyAmount actual = checking.getAccountBalance();
        assertEquals(expected, actual);
    }

    @Test
    public void testDoubleWithdrawal() {
        System.out.println("Verifying the same withdrawal can't be made twice...");
        CurrencyAmount initBal = checking.getAccountBalance();
        CurrencyAmount withdrawalAmount = new CurrencyAmount(-2048L);
        LocalDateTime withdrawalTime = LocalDateTime.now().minusHours(4);
        Withdrawal withdrawal = new Withdrawal(withdrawalAmount, withdrawalTime);
        String msg = "Should be able to make withdrawal of " + withdrawalAmount.negate().toString() + " on " + withdrawalTime.toString();
        assertTrue(checking.processWithdrawal(withdrawal), msg);
        CurrencyAmount expected = initBal.plus(withdrawalAmount);
        msg = "Should not be able to repeat withdrawal of " + withdrawalAmount.negate().toString() + " on " + withdrawalTime.toString();
        assertFalse(checking.processWithdrawal(withdrawal), msg);
        CurrencyAmount actual = checking.getAccountBalance();
        assertEquals(expected, actual);
    }

    @Test
    public void testOverdraftTransfer() {
        System.out.println("Attempting to make overdraft withdrawal...");
        CurrencyAmount checkingBalance = checking.getAccountBalance();
        CurrencyAmount excess = new CurrencyAmount(899L);
        CurrencyAmount overdraftAmount = checkingBalance.plus(excess).negate();
        Withdrawal withdrawal = new Withdrawal(overdraftAmount, LocalDateTime.now());
        String msg = "Overdraft withdrawal should process by transferring from savings";
        assertTrue(checking.processWithdrawal(withdrawal), msg);
        checkingBalance = checking.getAccountBalance();
        msg = "Checking account balance after overdraft transfer should be $0.00";
        assertEquals(BankAccount.INITIALIZATION_ACCOUNT_BALANCE, checkingBalance, msg);
    }

    @AfterEach
    public void tearDown() {
        System.out.println("Savings account balance: " + savings.getAccountBalance().toString() + " after test");
        CurrencyAmount currBal = checking.getAccountBalance();
        System.out.println("Checking account balance: " + currBal.toString() + " after test");
        System.out.println();
        if (currBal.compareTo(ASSURED_MINIMUM_BALANCE) < 0) {
            CurrencyAmount deficit = ASSURED_MINIMUM_BALANCE.minus(currBal);
            Deposit assureMinBalDep = new Deposit(deficit, LocalDateTime.now());
            checking.processDeposit(assureMinBalDep);
        }
    }

}