package numerics;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static textops.TextCalculator.padLeft;

/**
 * Holds a universally unique identifier (UUID). This one really is a toy
 * example because Java has had {@code java.util.UUID} since Java 1.5.
 * <p>UUIDs are essentially 128-bit unsigned integers. The human-readable
 * presentation consists of 32 hexadecimal digits separated with dashes in the
 * pattern 8-4-4-4-12. For example, 1AF0C360-2FFE-471E-78B2-0436144CAE67.</p>
 * <p>To generate pseudorandom UUIDs, you can use {@link
 * randomness.ExtendedRandom#nextUUID()}.</p>
 * @author Alonso del Arte
 */
public class UUID implements Comparable<UUID> {

    private static final String REG_EXP_STR
            = "[0-9A-F]{8}-[0-9A-F]{4}-[0-9A-F]{4}-[0-9A-F]{4}-[0-9A-F]{12}";

    public static final Pattern REGULAR_EXPRESSION = Pattern.compile(REG_EXP_STR);

    private final long high, low;

    public long getHighBits() {
        return this.high;
    }

    public long getLowBits() {
        return this.low;
    }

    // TODO: Write tests for this
    public byte getVersionID() {
        return -1;
    }

    /**
     * Parses text for a UUID with the correct formatting.
     * @param s The text to parse. The hexadecimal digits A, B, C, D, E and F
     *          may be in uppercase or lowercase. Consistency should be
     *          preferred but is not required. Two examples:
     *          "E3D4FAE7-C6D0-6C92-05B9-3BB36E06D7D3",
     *          "e3d4fae7-c6d0-6c92-05b9-3bb36e06d7d3".
     * @return The UUID. For both examples, this would be
     * E3D4FAE7-C6D0-6C92-05B9-3BB36E06D7D3.
     * @throws NumberFormatException If {@code s} is not formatted in the
     * pattern 8-4-4-4-12, or if it contains characters not recognized as
     * hexadecimal digits. Both "e3d4fae7c6d06c9205b93bb36e06d7d3" and
     * "6q4k7A76-Y65p-0py8-65M8-74ifv64IBh9i" would cause this exception, the
     * former because it's missing the dashes, the latter because it contains
     * the characters 'q', 'k', 'Y', etc., which are not recognized as
     * hexadecimal digits.
     */
    public static UUID parse(String s) {
        if (s == null) {
            throw new NullPointerException("Null String is not valid");
        }
        if (s.isBlank()) {
            String excMsg = "Not a valid UUID";
            throw new NumberFormatException(excMsg);
        }
        String preprocessed = s.toUpperCase();
        Matcher matcher = REGULAR_EXPRESSION.matcher(preprocessed);
        if (matcher.matches()) {
            String intermediate = preprocessed.replace("-", "");
            long highBits
                    = Long.parseUnsignedLong(intermediate.substring(0, 16), 16);
            long lowBits
                    = Long.parseUnsignedLong(intermediate.substring(16), 16);
            return new UUID(highBits, lowBits);
        } else {
            String excMsg = "\"" + s + "\" is not valid";
            throw new NumberFormatException(excMsg);
        }
    }

    // TODO: Write tests for this
    @Override
    public int compareTo(UUID other) {
        return 0;
    }

    /**
     * Compares this object to another. For the examples, suppose this UUID is
     * 5428F444-9D49-B382-2E35-8F8FEAB646BD.
     * @param obj The object to compare. Examples: an object identified by UUID
     *            5428F444-9D49-B382-2E35-8F8FEAB646BD in a database;
     *            the UUID 5428F444-9D49-B382-2E35-8F8FEAB646BD; an instance of
     *            {@code java.util.UUID} for
     *            UUID 5428F444-9D49-B382-2E35-8F8FEAB646BD; UUID
     *            5428F444-9D49-B382-D1CA-70701549B942; and a null.
     * @return True if and only if {@code obj} is of the same runtime class as
     * this object and has the same bit pattern. In the examples, false for the
     * object identified by UUID 5428F444-9D49-B382-2E35-8F8FEAB646BD; true for
     * UUID 5428F444-9D49-B382-2E35-8F8FEAB646BD; false for the {@code
     * java.util.UUID} instance of 5428F444-9D49-B382-2E35-8F8FEAB646BD even
     * though the bit pattern is the same; false for UUID
     * 5428F444-9D49-B382-D1CA-70701549B942 even though the runtime class and
     * high bits are the same; and false for null. Note that comparing UUID
     * 00000000-0000-0000-0000-000000000000 to null also gives false even though
     * the all-zeroes UUID is often referred to as "the null UUID."
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!this.getClass().equals(obj.getClass())) {
            return false;
        }
        UUID other = (UUID) obj;
        if (this.high != other.high) {
            return false;
        }
        return this.low == other.low;
    }

    /**
     * Generates a 32-bit hash code for this 128-bith UUID. Obviously, it's
     * impossible to have unique hash codes for all possible instances. But
     * hopefully these are unique enough for most practical purposes.
     * @return A 32-bit hash code. For example, for
     * UUID 339E925A-2CA8-071A-422A-6C03831DAB8D, the hash code might be
     * 861553181. But 65,535 other UUIDs might get the same hash code.
     */
    @Override
    public int hashCode() {
        int octetA = (int) (this.high >> 32) & 0xFF000000;
        int octetB = (int) (this.high >> 16) & 0x00FF0000;
        int octetC = (int) (this.low >> 48) & 0x0000FF00;
        int octetD = (int) (this.low >> 16) & 0x000000FF;
        return octetA + octetB + octetC + octetD;
    }

    /**
     * A text representation of the UUID using dashes, and uppercase letters for
     * the hexadecimal digits A, B, C, D, E and F. If lowercase letters are
     * needed for those digits, just call {@code toLowerCase()} on the result.
     * @return A {@code String}. For example,
     * "ABD70BBB-62B6-4C7D-2E35-8F8FEAB646BD". If necessary, use {@code
     * toLowerCase()} to obtain "abd70bbb-62b6-4c7d-2e35-8f8feab646bd".
     */
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
