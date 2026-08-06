package numerics;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Tests of the UUIDVariant enumerated type. Since the focus here is on the
 * variant rather than the version, I could have the version chosen
 * pseudorandomly, but I think I'm only going to use Version 4 and Version 7
 * UUIDs in these tests.
 */
class UUIDVariantTest {

    private static final long DCE_VARIANT_MASK = -4611686018427387905L;

    private static final Random RANDOM = new Random(DCE_VARIANT_MASK);

    private static int chooseVersion4BitOrVersion7Bits() {
        if (RANDOM.nextBoolean()) {
            return UUIDType.Constants.VERSION_4_BIT;
        } else {
            return UUIDType.Constants.VERSION_7_BITS;
        }
    }

    private static UUID makeUUID() {
        long highBits = chooseVersion4BitOrVersion7Bits();
        long lowBits = RANDOM.nextLong();
        return new UUID(highBits, lowBits);
    }

}
