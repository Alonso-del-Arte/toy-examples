package numerics;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class UUIDTypeTest {

    private static final long DCE_VARIANT_MASK = -4611686018427387905L;

    private static final Random RANDOM = new Random(DCE_VARIANT_MASK);

    private static UUID nextUUIDv4() {
        long highBits = (((long) RANDOM.nextInt()) << 32)
                + (RANDOM.nextInt(32768) << 16) + 16384 + RANDOM.nextInt(2048);
        long lowBits = (RANDOM.nextLong() | Long.MIN_VALUE) & DCE_VARIANT_MASK;
        return new UUID(highBits, lowBits);
    }

    // TODO: Test MAC

    // TODO: Test SECURITY

    // TODO: Test MD5

    // TODO: Test RANDOM

    // TODO: Test SHA1

    // TODO: Test MAC_SORTABLE

    // TODO: Test RANDOM_SORTABLE

    // TODO: Test UNKNOWN last

}
