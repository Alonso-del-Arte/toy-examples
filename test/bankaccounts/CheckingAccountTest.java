package bankaccounts;

import bankaccounts.transactions.Deposit;
import bankaccounts.transactions.Withdrawal;
import currency.CurrencyAmount;
import entities.Address;
import entities.Person;

import java.time.LocalDateTime;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class CheckingAccountTest {

    private static CheckingAccount checking;
    private static SavingsAccount savings;

    private static final CurrencyAmount ASSURED_MINIMUM_BALANCE = new CurrencyAmount(10000L);

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

    @Before
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
        String assertionMessage = "Should be able to make withdrawal of " + withdrawalAmount.negate().toString();
        assertTrue(assertionMessage, checking.processWithdrawal(withdrawal));
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
        String assertionMessage = "Should be able to make withdrawal of " + withdrawalAmount.negate().toString() + " on " + withdrawalTime.toString();
        assertTrue(assertionMessage, checking.processWithdrawal(withdrawal));
        CurrencyAmount expected = initBal.plus(withdrawalAmount);
        assertionMessage = "Should not be able to repeat withdrawal of " + withdrawalAmount.negate().toString() + " on " + withdrawalTime.toString();
        assertFalse(assertionMessage, checking.processWithdrawal(withdrawal));
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
        String assertionMessage = "Overdraft withdrawal should process by transferring from savings";
        assertTrue(assertionMessage, checking.processWithdrawal(withdrawal));
        checkingBalance = checking.getAccountBalance();
        assertionMessage = "Checking account balance after overdraft transfer should be $0.00";
        assertEquals(assertionMessage, BankAccount.INITIALIZATION_ACCOUNT_BALANCE, checkingBalance);
    }

    @After
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