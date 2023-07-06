package calculators;

import java.util.HashSet;
import java.util.Set;

public class BinaryStringsCollector {

    private final byte len;
    private final Set<String> set = new HashSet<>();

    public Set<String> give() {
        return this.set;
    }

    private String leftZeroPad(String s) {
        return String.format("%1$" + this.len + "s", s).replace(' ', '0');
    }

    private void addZero(String numStr) {
        String doubled = numStr + '0';
        if (doubled.length() <= this.len) {
            this.set.add(leftZeroPad(doubled));
            this.addZero(doubled);
            this.addOne(numStr);
        }
    }

    private void addOne(String numStr) {
        String doubledPlusOne = numStr + '1';
        if (doubledPlusOne.length() <= this.len) {
            if (!doubledPlusOne.contains("11")) {
                this.set.add(leftZeroPad(doubledPlusOne));
                this.addZero(doubledPlusOne);
            }
        }
    }

    public BinaryStringsCollector(byte length) {
        if (length < 0) {
            String excMsg = "Length " + length
                    + " not valid, needs to be 0 or greater";
            throw new IllegalArgumentException(excMsg);
        }
        this.len = length;
        if (this.len > 0) {
            this.addZero("");
        }
    }

}
