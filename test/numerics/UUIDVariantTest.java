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

    private static final long VAR_INCR = 1L << 60;

    private static final long LOW_60_BITS_MASK = VAR_INCR - 1;

    private static final Random RANDOM = new Random(DCE_VARIANT_MASK
            & System.currentTimeMillis());

    private static int chooseVersion4BitOrVersion7Bits() {
        if (RANDOM.nextBoolean()) {
            return UUIDType.Constants.VERSION_4_BIT;
        } else {
            return UUIDType.Constants.VERSION_7_BITS;
        }
    }

    private static UUID makeNonNCSUUID() {
        long highBits = chooseVersion4BitOrVersion7Bits();
        long lowBits = (RANDOM.nextLong() >>> 4) | Long.MIN_VALUE;
        return new UUID(highBits, lowBits);
    }

    @Test
    void testHighFourBitsMask() {
        long expected = -1152921504606846976L;
        long actual = UUIDVariant.Constants.HIGH_FOUR_BITS_MASK;
        assertEquals(expected, actual);
    }

    @Test
    void testVariantIncrementConstant() {
        long expected = 1L << 60;
        long actual = UUIDVariant.Constants.VARIANT_INCREMENT;
        assertEquals(expected, actual);
    }

    @Test
    void testIsOfVariantNCSBackwardCompatible() {
        long highBits = chooseVersion4BitOrVersion7Bits();
        long startingLowBits = RANDOM.nextLong() & LOW_60_BITS_MASK;
        for (long lowBits = startingLowBits; lowBits > 0; lowBits += VAR_INCR) {
            UUID uuid = new UUID(highBits, lowBits);
            String msg = "UUID " + uuid + " should be NCS backward compatible";
            assert UUIDVariant.NCS_BACKWARD_COMPATIBLE.isOfVariant(uuid) : msg;
        }
    }

    @Test
    void testIsNotOfVariantNCSBackwardCompatible() {
        long highBits = chooseVersion4BitOrVersion7Bits();
        long startingLowBits = (RANDOM.nextLong() >>> 4) | Long.MIN_VALUE;
        for (long lowBits = startingLowBits; lowBits < 0; lowBits += VAR_INCR) {
            UUID uuid = new UUID(highBits, lowBits);
            String msg = "UUID " + uuid
                    + " should not be NCS backward compatible";
            assert !UUIDVariant.NCS_BACKWARD_COMPATIBLE.isOfVariant(uuid) : msg;
        }
    }

}
