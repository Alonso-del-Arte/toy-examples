package calculators;

import java.util.HashSet;
import java.util.Set;

public class BinaryStringsCollector {

    private final int len;

    public Set<String> give() {
        int capacity = 1 << (this.len - 1);
        Set<String> set = new HashSet<>(capacity);
        int threshold = capacity + (capacity / 2);
        for (int i = 0; i < threshold; i++) {
            String numStr = String.format("%1$" + this.len + "s",
                    Integer.toString(i, 2)).replace(' ', '0');
            if (!numStr.contains("11")) {
                set.add(numStr);
            }
        }
        return set;
    }

    public BinaryStringsCollector(byte length) {
        if (length < 0) {
            String excMsg = "Length " + length
                    + " not valid, needs to be 0 or greater";
            throw new IllegalArgumentException(excMsg);
        }
        this.len = length;
    }

}
