package calculators;

import java.util.HashSet;
import java.util.Set;

public class BinaryStringsCollector {

    private final byte len;

    private final int max;

    private final Set<String> set = new HashSet<>();

    public Set<String> give() {
        return new HashSet<>(this.set);
    }

    private String leftZeroPad(String s) {
        return String.format("%1$" + this.len + "s", s).replace(' ', '0');
    }

    private void traverseOdd(int n) {
        if (n < this.max) {
            this.set.add(this.leftZeroPad(Integer.toString(n, 2)));
            this.traverseEven(2 * n);
        }
    }

    private void traverseEven(int n) {
        if (n < this.max) {
            this.set.add(this.leftZeroPad(Integer.toString(n, 2)));
            int twice = 2 * n;
            this.traverseEven(twice);
            this.traverseOdd(twice + 1);
        }
    }

    public BinaryStringsCollector(byte length) {
        if (length < 0) {
            String excMsg = "Length " + length
                    + " not valid, needs to be 0 or greater";
            throw new IllegalArgumentException(excMsg);
        }
        this.len = length;
        this.max = 1 << this.len;
        if (this.len > 0) {
            this.set.add(this.leftZeroPad("0"));
            this.traverseOdd(1);
        }
    }

}
