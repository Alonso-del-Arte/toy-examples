package reinventedwheels;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BasicCalculatorTest {

    private static final double TEST_DELTA = 0.00000001;

    private static BasicCalculator calculator;

    @BeforeAll
    static void setUp() {
        System.out.println("Initializing calculator...");
        calculator = new BasicCalculator();
    }

    @AfterAll
    static void tearDown() {
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
    void testGetCurrVal() {
        System.out.println("getCurrVal");
        double expected = 0.0;
        double actual = calculator.getCurrVal();
        assertEquals(expected, actual, TEST_DELTA);
    }

    @Test
    void testClearError() {
        System.out.println("clearError");
        calculator.subtract(1.0);
        try {
            calculator.squareRoot();
            fail("Check results of square root tests");
        } catch (Exception e) {
            System.out.println("Calculator should show error for sqrt(-1.0)");
            calculator.clearError();
        }
        double expected = 0.0;
        double actual = calculator.getCurrVal();
        assertEquals(expected, actual, TEST_DELTA);
        try {
            calculator.divide(0.0);
            fail("Check results of division tests");
        } catch (Exception e) {
            System.out.println("Calculator should show error after x/0");
            calculator.clearError();
        }
        actual = calculator.getCurrVal();
        assertEquals(expected, actual, TEST_DELTA);
    }

    @Test
    void testAdd() {
        System.out.println("add");
        double expected = 2.0;
        calculator.add(1.0);
        calculator.add(1.0);
        double actual = calculator.getCurrVal();
        assertEquals(expected, actual, TEST_DELTA);
    }

    @Test
    void testSubtract() {
        System.out.println("subtract");
        double expected = -1.0;
        calculator.subtract(1.0);
        double actual = calculator.getCurrVal();
        assertEquals(expected, actual, TEST_DELTA);
    }

    @Test
    void testMultiply() {
        System.out.println("multiply");
        calculator.add(9.659210963325183);
        calculator.multiply(1.0225);
        double expected = 9.87654321;
        double actual = calculator.getCurrVal();
        assertEquals(expected, actual, TEST_DELTA);
    }

    @Test
    void testDivide() {
        System.out.println("divide");
        calculator.add(1.0);
        calculator.divide(7.0);
        double expected = 0.14285714;
        double actual = calculator.getCurrVal();
        assertEquals(expected, actual, TEST_DELTA);
    }

    // TODO: Change this test to use assertThrows()
    @Test
    void testDivideByZero() {
        calculator.add(1.0);
        try {
            calculator.divide(0.0);
            fail("Trying to divide by zero should have caused an exception");
        } catch (IllegalArgumentException iae) {
            System.out.println("Divide by 0 caused IllegalArgumentException");
            System.out.println("\"" + iae.getMessage() + "\"");
        } catch (ArithmeticException ae) {
            System.out.println("ArithmeticException adequate for divide by 0");
            System.out.println("\"" + ae.getMessage() + "\"");
        } catch (RuntimeException re) {
            String msg = re.getClass().getName()
                    + " is wrong exception to throw for divide by zero";
            fail(msg);
        }
    }

    @Test
    void testSquareRoot() {
        System.out.println("squareRoot");
        calculator.add(163.0);
        calculator.squareRoot();
        double expected = 12.76714533;
        double actual = calculator.getCurrVal();
        assertEquals(expected, actual, TEST_DELTA);
    }

    // TODO: Change this test to use assertThrows()
    @Test
    void testSquareRootOnNegative() {
        calculator.subtract(163.0);
        try {
            calculator.squareRoot();
            String msg = "Imaginary number sqrt(-163) should cause exception";
            fail(msg);
        } catch (IllegalArgumentException iae) {
            System.out.println("sqrt(-163) caused IllegalArgumentException");
            System.out.println("\"" + iae.getMessage() + "\"");
        } catch (ArithmeticException ae) {
            System.out.println("ArithmeticException adequate for sqrt(-163)");
            System.out.println("\"" + ae.getMessage() + "\"");
        } catch (Exception e) {
            String msg = e.getClass().getName()
                    + " is wrong exception to throw for sqrt(-163)";
            fail(msg);
        }
    }

}
