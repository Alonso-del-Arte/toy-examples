package reinventedwheels;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BasicCalculatorTest {

    private static final double TEST_DELTA = 0.00000001;

    private static BasicCalculator calculator;

    @BeforeEach
    void setUp() {
        System.out.println("Initializing calculator...");
        calculator = new BasicCalculator();
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

    @Test
    void testDivideByZero() {
        double someNumber = Math.random() + 1.0;
        calculator.add(someNumber);
        Throwable t = assertThrows(IllegalArgumentException.class, () -> {
            calculator.divide(0.0);
            double badResult = calculator.getCurrVal();
            System.out.println(someNumber + " divided by 0 is said to be "
                    + badResult);
        });
        String excMsg = t.getMessage();
        assert excMsg != null : "Exception message should not be null";
        System.out.println("\"" + excMsg + "\"");
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

    @Test
    void testSquareRootOnNegative() {
        double someNumber = Math.random() * 100 + 1.0;
        calculator.subtract(someNumber);
        Throwable t = assertThrows(IllegalArgumentException.class, () -> {
            calculator.squareRoot();
            double badResult = calculator.getCurrVal();
            System.out.println("sqrt(-" + someNumber + ") is said to be "
                    + badResult);
        });
        String excMsg = t.getMessage();
        assert excMsg != null : "Exception message should not be null";
        System.out.println("\"" + excMsg + "\"");
    }

    @AfterEach
    void tearDown() {
        try {
            double value = calculator.getCurrVal();
            System.out.println("Value for calculator to display is " + value);
        } catch (IllegalStateException ise) {
            System.out.println(ise.getMessage());
            System.out.println("\"" + ise.getCause().getMessage() + "\"");
            System.out.println();
        }
    }

}
