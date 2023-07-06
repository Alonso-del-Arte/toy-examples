package calculators;

import java.util.HashSet;
import java.util.Set;

public class BinaryStringsCollector {

    private final int len;

    public Set<String> give() {
        Set<String> set = new HashSet<>();
        switch (this.len) {
            case 1:
                set.add("0");
                set.add("1");
                break;
            case 2:
                set.add("00");
                set.add("01");
                set.add("10");
                break;
            case 3:
                set.add("000");
                set.add("001");
                set.add("010");
                set.add("100");
                set.add("101");
            default:
                set.add("0000");
                set.add("0001");
                set.add("0010");
                set.add("0100");
                set.add("0101");
                set.add("1000");
                set.add("1001");
                set.add("1010");
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
