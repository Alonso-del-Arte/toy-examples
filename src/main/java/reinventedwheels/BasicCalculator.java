package reinventedwheels;

public class BasicCalculator {

    public static final String CLEARABLE_ERROR_NOTIFICATION
            = "Calculator currently in error state, use clearError() to clear";

    private double currVal = 0.0;
    private boolean errorFlag = false;
    private RuntimeException errorCause;

    public double getCurrVal() {
        if (this.errorFlag) {
            throw new IllegalStateException(CLEARABLE_ERROR_NOTIFICATION,
                    this.errorCause);
        }
        return this.currVal;
    }

    public void clearError() {
        this.currVal = 0.0;
        this.errorFlag = false;
    }

    public void add(double summand) {
        this.currVal += summand;
    }

    public void subtract(double subtrahend) {
        this.add(-subtrahend);
    }

    public void multiply(double multiplicand) {
        this.currVal *= multiplicand;
    }

    public void divide(double divisor) {
        if (divisor == 0.0) {
            String msg = "Can't divide " + this.currVal + " by zero";
            IllegalArgumentException iae = new IllegalArgumentException(msg);
            this.errorFlag = true;
            this.errorCause = iae;
            throw iae;
        }
        this.currVal /= divisor;
    }

    public void squareRoot() {
        if (this.currVal < 0.0) {
            String excMsg = "Square root of " + this.currVal
                    + " is an imaginary number";
            IllegalArgumentException iae = new IllegalArgumentException(excMsg);
            this.errorFlag = true;
            this.errorCause = iae;
            throw iae;
        }
        this.currVal = Math.sqrt(this.currVal);
    }

}
