package measures.metric;

import java.math.BigDecimal;

public abstract class LengthMeasure implements Comparable<LengthMeasure> {

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
