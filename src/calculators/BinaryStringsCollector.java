package calculators;

import java.util.HashSet;
import java.util.Set;

public class BinaryStringsCollector {

    private final byte len;
    private final Set<String> set = new HashSet<>();

    public Set<String> give() {
        return this.set;
    }

    private void addZero(String numStr) {
        String doubled = numStr + '0';
        if (doubled.length() <= this.len) {
            this.set.add(String.format("%1$" + this.len + "s", doubled)
                    .replace(' ', '0'));
            this.addZero(doubled);
            this.addOne(numStr);
        }
    }

    private void addOne(String numStr) {
        String doubledPlusOne = numStr + '1';
        if (doubledPlusOne.length() <= this.len) {
            if (!doubledPlusOne.contains("11")) {
                this.set.add(String.format("%1$" + this.len + "s",
                        doubledPlusOne).replace(' ', '0'));
                this.addZero(doubledPlusOne);
                this.addOne(doubledPlusOne);
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
        int capacity = 1 << (length - 1);
        if (this.len > 0) {
            this.addZero("");
        }
    }

}
