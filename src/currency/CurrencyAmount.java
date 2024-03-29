package currency;

import arithmetic.IntegerMultipliable;
import arithmetic.RingSummable;

import java.util.Currency;
import java.util.Locale;

public class CurrencyAmount implements Comparable<CurrencyAmount>,
        IntegerMultipliable<CurrencyAmount>, RingSummable<CurrencyAmount> {

    private final long amountInCents;
    private final Currency currencyID;

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

    public CurrencyAmount plus(CurrencyAmount addend) {
        if (this.currencyID.equals(addend.currencyID)) {
            long sumCents = this.amountInCents + addend.amountInCents;
            return new CurrencyAmount(sumCents, this.currencyID);
        } else {
            String excMsg = "Currency conversion needed to add " + addend
                    + " to " + this;
            throw new CurrencyConversionNeededException(excMsg, this, addend);
        }
    }

    public CurrencyAmount negate() {
        return new CurrencyAmount(-this.amountInCents, this.currencyID);
    }

    public CurrencyAmount minus(CurrencyAmount subtrahend) {
        return this.plus(subtrahend.negate());
    }

    @Override
    public CurrencyAmount times(int multiplicand) {
        long multCents = this.amountInCents * multiplicand;
        return new CurrencyAmount(multCents, this.currencyID);
    }

    @Override
    public CurrencyAmount divides(int divisor) {
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
        if (currency == null) {
            String excMsg = "currency must not be null";
            throw new NullPointerException(excMsg);
        }
        if (currency.getDefaultFractionDigits() == -1) {
            String excMsg = "Can't use currency " + currency.getDisplayName()
                    + " (" + currency.getCurrencyCode() + ")";
            throw new IllegalArgumentException(excMsg);
        }
        this.amountInCents = centsAmount;
        this.currencyID = currency;
    }

}
