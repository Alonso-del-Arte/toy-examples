package arithmetic;

import java.math.BigDecimal;

public class Percentage implements Comparable<Percentage> {

    private final double num;

    @Override
    public String toString() {
//        if (this.num == Math.floor(this.num)) {
//            return Double.toString(this.num).replace(".0", "") + "%";
//        }
        return this.num + "%";
    }

    // TODO: Write tests for test
    @Override
    public int compareTo(Percentage other) {
        return 0;
    }

    public Percentage(long value) {
        this.num = value;
    }

    public Percentage(double value) {
        if (Double.isInfinite(value) || Double.isNaN(value)) {
            String excMsg = "Value " + value + " is not valid";
            throw new IllegalArgumentException(excMsg);
        }
        this.num = value;
    }

    // TODO: Write tests for this
    public Percentage(BigDecimal value) {
        this.num = value.intValue();
    }

}
