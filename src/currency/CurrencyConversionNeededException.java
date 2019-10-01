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

    public CurrencyAmount exchangeAToB() {
        return CurrencyConverter.convert(this.amtA, this.amtB.getCurrency());
    }

    public CurrencyAmount exchangeBToA() {
        return CurrencyConverter.convert(this.amtB, this.amtA.getCurrency());
    }

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
