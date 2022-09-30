package measures.imperial;

import arithmetic.IntegerMultipliable;
import arithmetic.RingSummable;
import fractions.Fraction;
import measures.UnitOfMeasure;

public abstract class LengthMeasure extends UnitOfMeasure
        implements Comparable<LengthMeasure>,
        IntegerMultipliable<LengthMeasure>, RingSummable<LengthMeasure> {

    final Fraction quantity;

    final Fraction scaling;

    public final Fraction getNumber() {
        return this.quantity;
    }

    // TODO: Write tests for this
    @Override
    public boolean equals(Object obj) {
        return false;
    }

    // TODO: Write tests for this
    @Override
    public int hashCode() {
        return 0;
    }

    // TODO: Write tests for this
    @Override
    public int compareTo(LengthMeasure other) {
        return 0;
    }

    LengthMeasure(Fraction number, Fraction scale) {
        this.quantity = number;
        this.scaling = scale;
    }

}
