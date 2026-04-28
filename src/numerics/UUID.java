package numerics;

/**
 * Holds a universally unique identifier (UUID). This one really is a toy
 * example because Java has had {@code java.util.UUID} since Java 1.5.
 * @author Alonso del Arte
 */
public class UUID implements Comparable<UUID> {

    // TODO: Write tests for this
    @Override
    public int compareTo(UUID other) {
        return 0;
    }

    @Override
    public String toString() {
        return "00000000-0000-0000-0000-000000000000";
    }

    public UUID(long highBits, long lowBits) {
        //
    }

}
