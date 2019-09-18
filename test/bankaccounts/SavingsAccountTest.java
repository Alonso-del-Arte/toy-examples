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

public class SavingsAccountTest {

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
    }

    @Before
    public void setUp() {
        System.out.println("Savings account balance: " + savings.getAccountBalance().toString() + " prior to test");
    }

    @Test
    public void testDeposit() {
        System.out.println("processDeposit");
        CurrencyAmount initBal = savings.getAccountBalance();
        CurrencyAmount depositAmount = new CurrencyAmount(512L);
        LocalDateTime depositTime = LocalDateTime.now().minusDays(2);
        Deposit deposit = new Deposit(depositAmount, depositTime);
        savings.processDeposit(deposit);
        CurrencyAmount expected = initBal.plus(depositAmount);
        CurrencyAmount actual = savings.getAccountBalance();
        assertEquals(expected, actual);
    }

    @Test
    public void testDoubleDeposit() {
        System.out.println("Verifying the same deposit can't be made twice...");
        CurrencyAmount initBal = savings.getAccountBalance();
        CurrencyAmount depositAmount = new CurrencyAmount(4096L);
        LocalDateTime depositTime = LocalDateTime.now().minusHours(8);
        Deposit deposit = new Deposit(depositAmount, depositTime);
        savings.processDeposit(deposit);
        CurrencyAmount expected = initBal.plus(depositAmount);
        savings.processDeposit(deposit);
        CurrencyAmount actual = savings.getAccountBalance();
        assertEquals(expected, actual);
    }

    @Test
    public void testWithdrawal() {
        System.out.println("processWithdrawal");
        CurrencyAmount initBal = savings.getAccountBalance();
        CurrencyAmount withdrawalAmount = new CurrencyAmount(-16384L);
        LocalDateTime withdrawalTime = LocalDateTime.now().minusDays(1);
        Withdrawal withdrawal = new Withdrawal(withdrawalAmount, withdrawalTime);
        String assertionMessage = "Should be able to make withdrawal of " + withdrawalAmount.negate().toString();
        assertTrue(assertionMessage, savings.processWithdrawal(withdrawal));
        CurrencyAmount expected = initBal.plus(withdrawalAmount);
        CurrencyAmount actual = savings.getAccountBalance();
        assertEquals(expected, actual);
    }

    @Test
    public void testDoubleWithdrawal() {
        System.out.println("Verifying the same withdrawal can't be made twice...");
        CurrencyAmount initBal = savings.getAccountBalance();
        CurrencyAmount withdrawalAmount = new CurrencyAmount(-8192L);
        LocalDateTime withdrawalTime = LocalDateTime.now().minusHours(4);
        Withdrawal withdrawal = new Withdrawal(withdrawalAmount, withdrawalTime);
        String assertionMessage = "Should be able to make withdrawal of " + withdrawalAmount.negate().toString() + " on " + withdrawalTime.toString();
        assertTrue(assertionMessage, savings.processWithdrawal(withdrawal));
        CurrencyAmount expected = initBal.plus(withdrawalAmount);
        assertionMessage = "Should not be able to repeat withdrawal of " + withdrawalAmount.negate().toString() + " on " + withdrawalTime.toString();
        assertFalse(assertionMessage, savings.processWithdrawal(withdrawal));
        CurrencyAmount actual = savings.getAccountBalance();
        assertEquals(expected, actual);
    }

    @After
    public void tearDown() {
        CurrencyAmount currBal = savings.getAccountBalance();
        System.out.println("Savings account balance: " + currBal.toString() + " after test");
        System.out.println();
        if (currBal.compareTo(ASSURED_MINIMUM_BALANCE) < 0) {
            CurrencyAmount deficit = ASSURED_MINIMUM_BALANCE.minus(currBal);
            Deposit assureMinBalDep = new Deposit(deficit, LocalDateTime.now());
            savings.processDeposit(assureMinBalDep);
        }
    }

}