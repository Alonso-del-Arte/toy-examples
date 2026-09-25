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

    private static final long GUID_C = Long.MIN_VALUE + (VAR_INCR << 2);

    private static final long GUID_D = GUID_C + VAR_INCR;

    private static final long LOW_60_BITS_MASK = VAR_INCR - 1;

    private static final Random RANDOM = new Random(DCE_VARIANT_MASK
            & System.currentTimeMillis());

    private static long chooseVersion4BitOrVersion7Bits() {
        long randomBits = RANDOM.nextLong()
                & UUIDType.Constants.ALL_BUT_VERSION_BITS_MASK;
        int versionBits = (RANDOM.nextBoolean())
                ? UUIDType.Constants.VERSION_4_BIT
                : UUIDType.Constants.VERSION_7_BITS;
        return randomBits + versionBits;
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
    void testMicrosoftGUIDCLevelConstant() {
        long actual = UUIDVariant.Constants.MICROSOFT_GUID_C_LEVEL;
        assertEquals(GUID_C, actual);
    }

    @Test
    void testMicrosoftGUIDDLevelConstant() {
        long actual = UUIDVariant.Constants.MICROSOFT_GUID_D_LEVEL;
        assertEquals(GUID_D, actual);
    }

    @Test
    void testIsOfVariantNCSBackwardCompatible() {
        long highBits = chooseVersion4BitOrVersion7Bits();
        long startingLowBits = RANDOM.nextLong() & LOW_60_BITS_MASK;
        // noinspection OverflowingLoopIndex
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

    @Test
    void testIsOfVariantDCE() {
        long highBits = chooseVersion4BitOrVersion7Bits();
        long startingLowBits = (RANDOM.nextLong() >>> 4) | Long.MIN_VALUE;
        long stop = Long.MIN_VALUE + (VAR_INCR << 2);
        for (long lowBits = startingLowBits; lowBits < stop; lowBits += VAR_INCR) {
            UUID uuid = new UUID(highBits, lowBits);
            String msg = "UUID " + uuid + " should be DCE 1.1";
            assert UUIDVariant.DISTRIBUTED_COMPUTING_ENVIRONMENT
                    .isOfVariant(uuid) : msg;
        }
    }

    @Test
    void testNCSBackwardCompatibleIsNotDCE() {
        long highBits = chooseVersion4BitOrVersion7Bits();
        long startingLowBits = RANDOM.nextLong() & LOW_60_BITS_MASK;
        // noinspection OverflowingLoopIndex
        for (long lowBits = startingLowBits; lowBits > 0; lowBits += VAR_INCR) {
            UUID uuid = new UUID(highBits, lowBits);
            String msg = "UUID " + uuid + " should not be DCE 1.1";
            assert !UUIDVariant.DISTRIBUTED_COMPUTING_ENVIRONMENT
                    .isOfVariant(uuid) : msg;
        }
    }

    @Test
    void testMicrosoftGUID_C_IsNotDCE() {
        long highBits = chooseVersion4BitOrVersion7Bits();
        long lowBits = (RANDOM.nextLong() >>> 4) | GUID_C;
        UUID uuid = new UUID(highBits, lowBits);
        String msg = "UUID " + uuid + " should not be DCE 1.1";
        assert !UUIDVariant.DISTRIBUTED_COMPUTING_ENVIRONMENT.isOfVariant(uuid)
                : msg;
    }

    @Test
    void testMicrosoftGUID_D_IsNotDCE() {
        long highBits = chooseVersion4BitOrVersion7Bits();
        long lowBits = (RANDOM.nextLong() >>> 4) | GUID_D;
        UUID uuid = new UUID(highBits, lowBits);
        String msg = "UUID " + uuid + " should not be DCE 1.1";
        assert !UUIDVariant.DISTRIBUTED_COMPUTING_ENVIRONMENT.isOfVariant(uuid)
                : msg;
    }

    @Test
    void testReservedForFutureUseIsNotDCE() {
        long highBits = chooseVersion4BitOrVersion7Bits();
        long lowBits = (RANDOM.nextLong() >>> 4) | (-2 * VAR_INCR);
        UUID uuid = new UUID(highBits, lowBits);
        String msg = "UUID " + uuid + " should not be DCE 1.1";
        assert !UUIDVariant.DISTRIBUTED_COMPUTING_ENVIRONMENT.isOfVariant(uuid)
                : msg;
    }

    @Test
    void testUnknownIsNotDCE() {
        long highBits = chooseVersion4BitOrVersion7Bits();
        long lowBits = (RANDOM.nextLong() >>> 4) | (-VAR_INCR);
        UUID uuid = new UUID(highBits, lowBits);
        String msg = "UUID " + uuid + " should not be DCE 1.1";
        assert !UUIDVariant.DISTRIBUTED_COMPUTING_ENVIRONMENT.isOfVariant(uuid)
                : msg;
    }

    @Test
    void testMicrosoftGUID_C_IsMicrosoftGUID() {
        long highBits = chooseVersion4BitOrVersion7Bits();
        long lowBits = (RANDOM.nextLong() >>> 4) | GUID_C;
        UUID uuid = new UUID(highBits, lowBits);
        String msg = "UUID " + uuid + " should be Microsoft GUID";
        assert UUIDVariant.MICROSOFT_GUID.isOfVariant(uuid) : msg;
    }

}
