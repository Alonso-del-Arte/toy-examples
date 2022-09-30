package measures.metric;

import arithmetic.IntegerMultipliable;
import arithmetic.RingSummable;

import java.math.BigDecimal;

import measures.UnitOfMeasure;

public abstract class LengthMeasure extends UnitOfMeasure
        implements Comparable<LengthMeasure>,
        IntegerMultipliable<LengthMeasure>, RingSummable<LengthMeasure> {

    private final BigDecimal quantity;

    private final BigDecimal scaling;

    // TODO: Write tests for this
    @Override
    public int compareTo(LengthMeasure other) {
        return 0;
    }

    LengthMeasure(BigDecimal number, BigDecimal scale) {
        this.quantity = number;
        this.scaling = scale;
    }

}
