package measures.imperial;

import fractions.Fraction;

public abstract class LengthMeasure implements Comparable<LengthMeasure> {

    private final Fraction quantity;

    private final Fraction scaling;

    LengthMeasure(Fraction number, Fraction scale) {
        this.quantity = number;
        this.scaling = scale;
    }

}
