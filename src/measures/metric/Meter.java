package measures.metric;

import java.math.BigDecimal;

public class Meter extends LengthMeasure {

    public Meter(BigDecimal number) {
        super(number, BigDecimal.ONE);
    }

}
