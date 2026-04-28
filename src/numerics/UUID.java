package numerics;

import static textops.TextCalculator.padLeft;

/**
 * Holds a universally unique identifier (UUID). This one really is a toy
 * example because Java has had {@code java.util.UUID} since Java 1.5.
 * @author Alonso del Arte
 */
public class UUID implements Comparable<UUID> {

    private final long high, low;

    // TODO: Write tests for this
    @Override
    public int compareTo(UUID other) {
        return 0;
    }

    // TODO: Write tests for this
    @Override
    public boolean equals(Object obj) {
        return this == obj;
    }

    // TODO: Write tests for this
    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        String highStr = padLeft(Long.toHexString(this.high).toUpperCase(), 16,
                '0');
        String lowStr = padLeft(Long.toHexString(this.low).toUpperCase(), 16,
                '0');
        return highStr.substring(0, 8) + "-" + highStr.substring(8, 12) + "-"
                + highStr.substring(12, 16) + "-" + lowStr.substring(0, 4) + "-"
                + lowStr.substring(4);
    }

    public UUID(long highBits, long lowBits) {
        this.high = highBits;
        this.low = lowBits;
    }

}
