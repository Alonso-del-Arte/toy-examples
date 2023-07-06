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
            default:
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
