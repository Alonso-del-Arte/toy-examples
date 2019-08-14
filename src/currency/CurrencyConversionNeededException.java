package currency;

public class CurrencyConversionNeededException extends RuntimeException {

    private final CurrencyAmount amtA;
    private final CurrencyAmount amtB;

    public CurrencyAmount getAmountA() {
        return this.amtA;
    }

    public CurrencyAmount getAmountB() {
        return this.amtB;
    }

    // PLACEHOLDER FOR EXCHANGE RATE FUNCTION
    // In a real life scenario, this exception would surely provide at least one way to convert one currency to another

    public CurrencyConversionNeededException(String message, CurrencyAmount amountA, CurrencyAmount amountB) {
        super(message);
        if (amountA.getCurrency().equals(amountB.getCurrency())) {
            String excMsg = "No conversion is needed for operation involving " + amountA.toString() + " and " + amountB.toString();
            throw new IllegalArgumentException(excMsg);
        }
        this.amtA = amountA;
        this.amtB = amountB;
    }

}
