package measures.metric;

import java.math.BigDecimal;

public final class Meter extends LengthMeasure {

    // TODO: Write tests for this
    @Override
    public String getSingularWord() {
        return "NOT IMPLEMENTED YET";
    }

    // TODO: Write tests for this
    @Override
    public String getPluralWord() {
        return "NOT IMPLEMENTED YET";
    }

    // TODO: Write tests for this
    @Override
    public String getAbbreviation() {
        return "NOT IMPLEMENTED YET";
    }

    public Meter(BigDecimal number) {
        super(number, BigDecimal.ONE);
    }

}
