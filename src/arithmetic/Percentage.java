package arithmetic;

import java.math.BigDecimal;

public class Percentage implements Comparable<Percentage> {

    private final BigDecimal num;

    @Override
    public String toString() {
        if (this.num.stripTrailingZeros().scale() < 1) {
            return this.num.toString().replace(".0", "") + "%";
        }
        return this.num + "%";
    }

    // TODO: Write tests for test
    @Override
    public int compareTo(Percentage other) {
        return 0;
    }

    public Percentage(long value) {
        this.num = BigDecimal.valueOf(value);
    }

    public Percentage(double value) {
        if (Double.isInfinite(value) || Double.isNaN(value)) {
            String excMsg = "Value " + value + " is not valid";
            throw new IllegalArgumentException(excMsg);
        }
        this.num = BigDecimal.valueOf(value);
    }

    // TODO: Write tests for this
    public Percentage(BigDecimal value) {
        this.num = value;
    }

}
