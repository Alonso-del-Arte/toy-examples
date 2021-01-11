package reinventedwheels;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BasicCalculatorTest {

    private static final double TEST_DELTA = 0.00000001;

    private BasicCalculator calculator;

    @BeforeAll
    public void setUp() {
        System.out.println("Initializing calculator...");
        calculator = new BasicCalculator();
    }

    @AfterAll
    public void tearDown() {
        try {
            double value = calculator.getCurrVal();
            System.out.println("Value for calculator to display is " + value);
        } catch (IllegalStateException ise) {
            System.out.println(ise.getMessage());
            System.out.println(ise.getCause().getMessage());
            System.out.println();
        }
    }

    /**
     * Test of getCurrVal method of class BasicCalculator. The current value of
     * a freshly instantiated instance of BasicCalculator should be 0.0, e.g.,
     * right after setUp().
     */
    @Test
    public void testGetCurrVal() {
        System.out.println("getCurrVal");
        double expected = 0.0;
        double actual = calculator.getCurrVal();
        assertEquals(expected, actual, TEST_DELTA);
    }

    @Test
    public void testClearError() {
        System.out.println("clearError");
        calculator.subtract(1.0);
        try {
            calculator.squareRoot();
            fail("Check results of square root tests");
        } catch (Exception e) {
            System.out.println("Calculator should be in error state after trying to computer sqrt(-1.0)");
            calculator.clearError();
        }
        double expected = 0.0;
        double actual = calculator.getCurrVal();
        assertEquals(expected, actual, TEST_DELTA);
        try {
            calculator.divide(0.0);
            fail("Check results of division tests");
        } catch (Exception e) {
            System.out.println("Calculator should be in error state after trying to divide by zero");
            calculator.clearError();
        }
        actual = calculator.getCurrVal();
        assertEquals(expected, actual, TEST_DELTA);
    }

    @Test
    public void testAdd() {
        System.out.println("add");
        double expected = 2.0;
        calculator.add(1.0);
        calculator.add(1.0);
        double actual = calculator.getCurrVal();
        assertEquals(expected, actual, TEST_DELTA);
    }

    @Test
    public void testSubtract() {
        System.out.println("subtract");
        double expected = -1.0;
        calculator.subtract(1.0);
        double actual = calculator.getCurrVal();
        assertEquals(expected, actual, TEST_DELTA);
    }

    @Test
    public void testMultiply() {
        System.out.println("multiply");
        calculator.add(9.659210963325183);
        calculator.multiply(1.0225);
        double expected = 9.87654321;
        double actual = calculator.getCurrVal();
        assertEquals(expected, actual, TEST_DELTA);
    }

    @Test
    public void testDivide() {
        System.out.println("divide");
        calculator.add(1.0);
        calculator.divide(7.0);
        double expected = 0.14285714;
        double actual = calculator.getCurrVal();
        assertEquals(expected, actual, TEST_DELTA);
    }

    @Test
    public void testDivideByZero() {
        calculator.add(1.0);
        try {
            calculator.divide(0.0);
            fail("Trying to divide by zero should have caused an exception");
        } catch (IllegalArgumentException iae) {
            System.out.println("Trying to divide by zero correctly caused IllegalArgumentException");
            System.out.println("\"" + iae.getMessage() + "\"");
        } catch (ArithmeticException ae) {
            System.out.println("ArithmeticException is adequate for division by zero");
            System.out.println("\"" + ae.getMessage() + "\"");
        } catch (Exception e) {
            String failMsg = e.getClass().getName() + " is wrong exception to throw for trying to divide by zero";
            fail(failMsg);
        }
    }

    @Test
    public void testSquareRoot() {
        System.out.println("squareRoot");
        calculator.add(163.0);
        calculator.squareRoot();
        double expected = 12.76714533;
        double actual = calculator.getCurrVal();
        assertEquals(expected, actual, TEST_DELTA);
    }

    @Test
    public void testSquareRootOnNegative() {
        calculator.subtract(163.0);
        try {
            calculator.squareRoot();
            fail("Since the square root of a negative number is imaginary, an exception should have occurred");
        } catch (IllegalArgumentException iae) {
            System.out.println("Trying to take square root of negative number correctly caused IllegalArgumentException");
            System.out.println("\"" + iae.getMessage() + "\"");
        } catch (ArithmeticException ae) {
            System.out.println("ArithmeticException is adequate for trying to take square root of negative number");
            System.out.println("\"" + ae.getMessage() + "\"");
        } catch (Exception e) {
            String failMsg = e.getClass().getName() + " is wrong exception to throw for trying to take square root of negative number";
            fail(failMsg);
        }
    }

}