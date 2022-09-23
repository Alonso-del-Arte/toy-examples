package measures.imperial;

import fractions.Fraction;

import measures.UnitOfMeasure;

public abstract class LengthMeasure extends UnitOfMeasure
        implements Comparable<LengthMeasure> {

    private final Fraction quantity;

    private final Fraction scaling;

    // TODO: Write tests for this
    public final Fraction getNumber() {
        return new Fraction(0);
    }

    // TODO: Write tests for this
    @Override
    public boolean equals(Object o) {
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
