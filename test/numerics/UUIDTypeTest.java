package numerics;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class UUIDTypeTest {

    private static final long HIGH_BITS_VERSION_INCREMENT = 4096L;

    private static final long HIGH_BITS_VERSION_THRESHOLD = 65536L;

    private static final long HIGH_BITS_VERSION_MASK_OUT = -61441L;

    private static final long DCE_VARIANT_MASK = -4611686018427387905L;

    private static final Random RANDOM = new Random(DCE_VARIANT_MASK
            + System.currentTimeMillis());

    private static UUID nextUUIDv4() {
        long highBits = (((long) RANDOM.nextInt()) << 32)
                + (RANDOM.nextInt(32768) << 16) + 16384 + RANDOM.nextInt(2048);
        long lowBits = (RANDOM.nextLong() | Long.MIN_VALUE) & DCE_VARIANT_MASK;
        return new UUID(highBits, lowBits);
    }

    private static long version0HighBits() {
        return RANDOM.nextLong() & HIGH_BITS_VERSION_MASK_OUT;
    }

    @Test
    void testConstantVersionMask() {
        int expected = 61440;
        int actual = UUIDType.Constants.VERSION_MASK;
        assertEquals(expected, actual);
    }

    @Test
    void testConstantVersion1Bit() {
        int expected = 4096;
        int actual = UUIDType.Constants.VERSION_1_BIT;
        assertEquals(expected, actual);
    }

    @Test
    void testConstantVersion2Bit() {
        int expected = 8192;
        int actual = UUIDType.Constants.VERSION_2_BIT;
        assertEquals(expected, actual);
    }

    @Test
    void testConstantVersion3Bits() {
        int expected = (UUIDType.Constants.VERSION_1_BIT << 1)
                + UUIDType.Constants.VERSION_1_BIT;
        int actual = UUIDType.Constants.VERSION_3_BITS;
        assertEquals(expected, actual);
    }

    @Test
    void testConstantVersion4Bit() {
        int expected = UUIDType.Constants.VERSION_1_BIT << 2;
        int actual = UUIDType.Constants.VERSION_4_BIT;
        assertEquals(expected, actual);
    }

    @Test
    void testConstantVersion5Bits() {
        int expected = (UUIDType.Constants.VERSION_1_BIT << 2)
                + UUIDType.Constants.VERSION_1_BIT;
        int actual = UUIDType.Constants.VERSION_5_BITS;
        assertEquals(expected, actual);
    }

    @Test
    void testConstantVersion6Bits() {
        int expected = UUIDType.Constants.VERSION_4_BIT
                + UUIDType.Constants.VERSION_2_BIT;
        int actual = UUIDType.Constants.VERSION_6_BITS;
        assertEquals(expected, actual);
    }

    @Test
    void testConstantVersion7Bits() {
        int expected = 7 * UUIDType.Constants.VERSION_1_BIT;
        int actual = UUIDType.Constants.VERSION_7_BITS;
        assertEquals(expected, actual);
    }

    @Test
    void testConstantVersion8Bit() {
        int expected = UUIDType.Constants.VERSION_1_BIT << 3;
        int actual = UUIDType.Constants.VERSION_8_BIT;
        assertEquals(expected, actual);
    }

    @Test
    void testIsOfTypeMACVersion1() {
        long highBits = version0HighBits() + HIGH_BITS_VERSION_INCREMENT;
        long lowBits = RANDOM.nextLong();
        UUID uuid = new UUID(highBits, lowBits);
        String msg = "UUID " + uuid + " should be MAC, version 1";
        assert UUIDType.MAC.isOfType(uuid) : msg;
    }

    @Test
    void testVersion0IsNotVersion1() {
        long highBits = version0HighBits();
        long lowBits = RANDOM.nextLong();
        UUID uuid = new UUID(highBits, lowBits);
        String msg = "UUID " + uuid + " should not be MAC, version 1";
        assert !UUIDType.MAC.isOfType(uuid) : msg;
    }

    @Test
    void testVersions2To15AreNotVersion1Either() {
        long propHighBits = version0HighBits();
        long lowBits = RANDOM.nextLong();
        long start = 2 * HIGH_BITS_VERSION_INCREMENT;
        for (long versionBits = start;
             versionBits < HIGH_BITS_VERSION_THRESHOLD;
             versionBits += HIGH_BITS_VERSION_INCREMENT) {
            long highBits = propHighBits + versionBits;
            UUID uuid = new UUID(highBits, lowBits);
            String msg = uuid + " should not be Version 1";
            assert !UUIDType.MAC.isOfType(uuid) : msg;
        }
    }

    @Test
    void testIsOfTypeSecurity() {
        long highBits = version0HighBits() + 2 * HIGH_BITS_VERSION_INCREMENT;
        long lowBits = RANDOM.nextLong();
        UUID uuid = new UUID(highBits, lowBits);
        String msg = "UUID " + uuid + " should be Security";
        assert UUIDType.SECURITY.isOfType(uuid) : msg;
    }

    @Test
    void testVersion0IsNotVersion2() {
        long highBits = version0HighBits();
        long lowBits = RANDOM.nextLong();
        UUID uuid = new UUID(highBits, lowBits);
        String msg = "UUID " + uuid + " should not be Security";
        assert !UUIDType.SECURITY.isOfType(uuid) : msg;
    }

    @Test
    void testVersion1IsNotVersion2() {
        long highBits = version0HighBits() + HIGH_BITS_VERSION_INCREMENT;
        long lowBits = RANDOM.nextLong();
        UUID uuid = new UUID(highBits, lowBits);
        String msg = "UUID " + uuid + " should not be Security";
        assert !UUIDType.SECURITY.isOfType(uuid) : msg;
    }

    @Test
    void testVersions3To15AreNotVersion2Either() {
        long propHighBits = version0HighBits();
        long lowBits = RANDOM.nextLong();
        long start = 3 * HIGH_BITS_VERSION_INCREMENT;
        for (long versionBits = start;
             versionBits < HIGH_BITS_VERSION_THRESHOLD;
             versionBits += HIGH_BITS_VERSION_INCREMENT) {
            long highBits = propHighBits + versionBits;
            UUID uuid = new UUID(highBits, lowBits);
            String msg = uuid + " should not be Version 2";
            assert !UUIDType.SECURITY.isOfType(uuid) : msg;
        }
    }

    @Test
    void testIsOfTypeMD5() {
        long highBits = version0HighBits() + 2 * HIGH_BITS_VERSION_INCREMENT
                + HIGH_BITS_VERSION_INCREMENT;
        long lowBits = RANDOM.nextLong();
        UUID uuid = new UUID(highBits, lowBits);
        String msg = "UUID " + uuid + " should be MD5";
        assert UUIDType.MD5.isOfType(uuid) : msg;
    }

    @Test
    void testVersions0To2AreNotVersion3() {
        long propHighBits = version0HighBits();
        long lowBits = RANDOM.nextLong();
        long stop = 3 * HIGH_BITS_VERSION_INCREMENT;
        for (long versionBits = 0L; versionBits < stop;
             versionBits += HIGH_BITS_VERSION_INCREMENT) {
            long highBits = propHighBits + versionBits;
            UUID uuid = new UUID(highBits, lowBits);
            String msg = uuid + " should not be Version 3";
            assert !UUIDType.MD5.isOfType(uuid) : msg;
        }
    }

    @Test
    void testVersions4To15AreNotVersion3Either() {
        long propHighBits = version0HighBits();
        long lowBits = RANDOM.nextLong();
        long start = 4 * HIGH_BITS_VERSION_INCREMENT;
        for (long versionBits = start;
             versionBits < HIGH_BITS_VERSION_THRESHOLD;
             versionBits += HIGH_BITS_VERSION_INCREMENT) {
            long highBits = propHighBits + versionBits;
            UUID uuid = new UUID(highBits, lowBits);
            String msg = uuid + " should not be Version 3";
            assert !UUIDType.MD5.isOfType(uuid) : msg;
        }
    }

    @Test
    void testIsOfTypeRandomVersion4() {
        UUID uuid = nextUUIDv4();
        String msg = "UUID " + uuid + " should be RANDOM, version 4";
        assert UUIDType.RANDOM.isOfType(uuid) : msg;
    }

    @Test
    void testVersions0To3AreNotVersion4() {
        long propHighBits = version0HighBits();
        long lowBits = RANDOM.nextLong();
        long threshold = 4 * HIGH_BITS_VERSION_INCREMENT;
        for (long versionBits = 0; versionBits < threshold;
             versionBits += HIGH_BITS_VERSION_INCREMENT) {
            long highBits = propHighBits + versionBits;
            UUID uuid = new UUID(highBits, lowBits);
            String msg = uuid + " should not be Version 4";
            assert !UUIDType.RANDOM.isOfType(uuid) : msg;
        }
    }

    @Test
    void testVersions5To15AreNotVersion4Either() {
        long propHighBits = version0HighBits();
        long lowBits = RANDOM.nextLong();
        long start = 5 * HIGH_BITS_VERSION_INCREMENT;
        for (long versionBits = start;
             versionBits < HIGH_BITS_VERSION_THRESHOLD;
             versionBits += HIGH_BITS_VERSION_INCREMENT) {
            long highBits = propHighBits + versionBits;
            UUID uuid = new UUID(highBits, lowBits);
            String msg = uuid + " should not be Version 4";
            assert !UUIDType.RANDOM.isOfType(uuid) : msg;
        }
    }

    @Test
    void testIsOfTypeSHA1() {
        long highBits = version0HighBits() + 4 * HIGH_BITS_VERSION_INCREMENT
                + HIGH_BITS_VERSION_INCREMENT;
        long lowBits = RANDOM.nextLong();
        UUID uuid = new UUID(highBits, lowBits);
        String msg = "UUID " + uuid + " should be SHA-1";
        assert UUIDType.SHA1.isOfType(uuid) : msg;
    }

    @Test
    void testVersions0To4AreNotVersion5() {
        long propHighBits = version0HighBits();
        long lowBits = RANDOM.nextLong();
        long stop = 5 * HIGH_BITS_VERSION_INCREMENT;
        for (long versionBits = 0L; versionBits < stop;
             versionBits += HIGH_BITS_VERSION_INCREMENT) {
            long highBits = propHighBits + versionBits;
            UUID uuid = new UUID(highBits, lowBits);
            String msg = uuid + " should not be Version 5";
            assert !UUIDType.SHA1.isOfType(uuid) : msg;
        }
    }

    // TODO: Test MAC_SORTABLE

    // TODO: Test RANDOM_SORTABLE

    // TODO: Test UNKNOWN last

}
