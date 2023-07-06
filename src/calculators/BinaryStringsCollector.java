package calculators;

import java.util.HashSet;
import java.util.Set;

public class BinaryStringsCollector {

    private final Set<String> SET = new HashSet<>();

    public Set<String> give() {
        return this.SET;
    }

    public BinaryStringsCollector(byte length) {
        if (length < 0) {
            String excMsg = "Length " + length
                    + " not valid, needs to be 0 or greater";
            throw new IllegalArgumentException(excMsg);
        }
        int capacity = 1 << (length - 1);
        if (length > 0) {
            int threshold = capacity + (capacity / 2);
            if (length == 1) threshold = 2;
            for (int i = 0; i < threshold; i++) {
                String numStr = String.format("%1$" + length + "s",
                        Integer.toString(i, 2)).replace(' ', '0');
                if (!numStr.contains("11")) {
                    SET.add(numStr);
                }
            }
        }
    }

}
