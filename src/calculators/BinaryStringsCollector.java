package calculators;

import java.util.HashSet;
import java.util.Set;

public class BinaryStringsCollector {

    // TODO: Write tests for this
    public Set<String> give() {
        return new HashSet<>();
    }

    public BinaryStringsCollector(byte length) {
        if (length < 0) {
            String excMsg = "Length " + length
                    + " not valid, needs to be 0 or greater";
            throw new IllegalArgumentException(excMsg);
        }
    }

}
