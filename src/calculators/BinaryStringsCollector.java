package calculators;

import java.util.HashSet;
import java.util.Set;

public class BinaryStringsCollector {

    private final boolean isLengthMoreThanZero;

    public Set<String> give() {
        Set<String> set = new HashSet<>();
        if (this.isLengthMoreThanZero) {
            set.add("0");
            set.add("1");
        }
        return set;
    }

    public BinaryStringsCollector(byte length) {
        if (length < 0) {
            String excMsg = "Length " + length
                    + " not valid, needs to be 0 or greater";
            throw new IllegalArgumentException(excMsg);
        }
        this.isLengthMoreThanZero = length > 0;
    }

}
