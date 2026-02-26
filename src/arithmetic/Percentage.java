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
        if (Double.isNaN(value)) {
            String excMsg = "Value " + value + " is not valid";
            throw new IllegalArgumentException(excMsg);
        }
        this.num = -1;
    }

    // TODO: Write tests for this
    public Percentage(BigDecimal value) {
        this.num = -2;
    }

}
