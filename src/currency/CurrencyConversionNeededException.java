package currency;

import java.time.LocalDateTime;

public class CurrencyConversionNeededException extends RuntimeException {

    private final CurrencyAmount amtA;
    private final CurrencyAmount amtB;

    private final CurrencyAmount amtAToCurrB;
    private final CurrencyAmount amtBToCurrA;

    private final LocalDateTime occurDateTime;

    public CurrencyAmount getAmountA() {
        return this.amtA;
    }

    public CurrencyAmount getAmountB() {
        return this.amtB;
    }

    public LocalDateTime getOccurDateTime() {
        return this.occurDateTime;
    }

    /**
     * Exchange amount A to currency B
     * @return Amount exchanged at time exception was thrown
     */
    public CurrencyAmount exchangeAToB() {
        return this.amtAToCurrB;
    }

    /**
     * Exchange amount B to currency A
     * @return Amount exchanged at time exception was thrown
     */
    public CurrencyAmount exchangeBToA() {
        return this.amtBToCurrA;
    }

    public CurrencyConversionNeededException(String message,
                                             CurrencyAmount amountA,
                                             CurrencyAmount amountB) {
        super(message);
        if (amountA.getCurrency().equals(amountB.getCurrency())) {
            String excMsg = "No conversion is needed for operation involving "
                    + amountA.toString() + " and " + amountB.toString();
            throw new IllegalArgumentException(excMsg);
        }
        this.amtA = amountA;
        this.amtB = amountB;
        this.amtAToCurrB = CurrencyConverter.convert(this.amtA,
                this.amtB.getCurrency());
        this.amtBToCurrA = CurrencyConverter.convert(this.amtB,
                this.amtA.getCurrency());
        this.occurDateTime = LocalDateTime.now();
    }

}
