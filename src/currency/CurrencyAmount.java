package currency;

import java.util.Currency;
import java.util.Locale;

public class CurrencyAmount implements Comparable<CurrencyAmount> {

    private long amountInCents;
    private Currency currencyID;

    public long getAmountInCents() {
        return this.amountInCents;
    }

    public long getUnitAmount() {
        long dollars = this.amountInCents;
        for (int i = 0; i < this.currencyID.getDefaultFractionDigits(); i++) {
            dollars /= 10;
        }
        return dollars;
    }

    public short getChangeAmount() {
        long maxCents = 1;
        for (int i = 0; i < this.currencyID.getDefaultFractionDigits(); i++) {
            maxCents *= 10;
        }
        return (short) (this.amountInCents % maxCents);
    }

    public Currency getCurrency() {
        return this.currencyID;
    }

    public CurrencyAmount plus(CurrencyAmount summand) {
        if (this.currencyID.equals(summand.currencyID)) {
            long sumCents = this.amountInCents + summand.amountInCents;
            return new CurrencyAmount(sumCents, this.currencyID);
        } else {
            String excMsg = "Currency conversion needed to add "
                    + summand.toString() + " to " + this.toString();
            throw new CurrencyConversionNeededException(excMsg, this, summand);
        }
    }

    public CurrencyAmount negate() {
        return new CurrencyAmount(-this.amountInCents, this.currencyID);
    }

    public CurrencyAmount minus(CurrencyAmount subtrahend) {
        return this.plus(subtrahend.negate());
    }

    public CurrencyAmount times(short multiplicand) {
        long multCents = this.amountInCents * multiplicand;
        return new CurrencyAmount(multCents, this.currencyID);
    }

    public CurrencyAmount divides(short divisor) {
        long divCents = this.amountInCents / divisor;
        return new CurrencyAmount(divCents, this.currencyID);
    }

    @Override
    public String toString() {
        StringBuilder amount = new StringBuilder(String.valueOf(this.amountInCents));
        int defaultFracDigits = this.currencyID.getDefaultFractionDigits();
        while (amount.length() <= defaultFracDigits) {
            amount.insert(0, '0');
        }
        StringBuilder amtStr = new StringBuilder(this.currencyID.getSymbol()
                + amount);
        if (defaultFracDigits > 0) {
            amtStr.insert(amtStr.length() - defaultFracDigits, '.');
        }
        return amtStr.toString();
    }

    @Override
    public int hashCode() {
        return (int) this.amountInCents * this.currencyID.getNumericCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CurrencyAmount that = (CurrencyAmount) obj;
        return this.amountInCents == that.amountInCents
                && this.currencyID.equals(that.currencyID);
    }

    @Override
    public int compareTo(CurrencyAmount other) {
        CurrencyAmount diff = this.minus(other);
        if (diff.amountInCents < 0) {
            return -1;
        }
        if (diff.amountInCents > 0) {
            return 1;
        }
        return 0;
    }

    public CurrencyAmount(long centsAmount) {
        this(centsAmount, Currency.getInstance(Locale.US));
    }

    public CurrencyAmount(long centsAmount, Currency currency) {
        if (currency.getDefaultFractionDigits() == -1) {
            String excMsg = "Can't use currency " + currency.getDisplayName()
                    + " (" + currency.getCurrencyCode() + ")";
            throw new IllegalArgumentException(excMsg);
        }
        this.amountInCents = centsAmount;
        this.currencyID = currency;
    }

}
