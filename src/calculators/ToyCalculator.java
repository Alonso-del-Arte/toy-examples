package calculators;

/**
 * A completely unnecessary utility class, duplicating arithmetic functions
 * already available in Java as operators.
 * @author Alonso del Arte
 */
public class ToyCalculator {

    /**
     * Adds two integers. This is the most popular toy example for test-driven
     * development (TDD), and, in my opinion, also the lamest.
     * @param addendA The first integer to add. For example, 1.
     * @param addendB The second integer to add. For example, also 1.
     * @return The sum of the two integers. For example, 1.
     */
    public static int plus(int addendA, int addendB) {
        return addendA + addendB;
    }

    // TODO: Write tests for this
    public static int minus(int minuend, int subtrahend) {
        return Integer.MAX_VALUE;
    }

    // TODO: Write tests for this
    public static int times(int multiplicandA, int multiplicandB) {
        return Integer.MIN_VALUE;
    }

    // TODO: Write tests for this
    public static int divides(int dividend, int divisor) {
        return Integer.MAX_VALUE;
    }

}
