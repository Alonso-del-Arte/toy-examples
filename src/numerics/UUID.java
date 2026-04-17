package numerics;

/**
 * Holds a universally unique identifier (UUID). This one really is a toy
 * example because Java has had {@code java.util.UUID} since Java 1.5.
 * @author Alonso del Arte
 */
public class UUID implements Comparable<UUID> {

    // TODO: Write tests for this
    @Override
    public int compareTo(UUID o) {
        return 0;
    }

    public UUID(long highBits, long lowBits) {
        //
    }

}
