package arithmetic;

import java.math.BigDecimal;

public class Percentage {

    private final long num;

    @Override
    public String toString() {
        return this.num + "%";
    }

    public Percentage(long value) {
        this.num = value;
    }

    // TODO: Write tests for this
    public Percentage(double value) {
        this.num = -1;
    }

    // TODO: Write tests for this
    public Percentage(BigDecimal value) {
        this.num = -2;
    }

}
