package numerics;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.example.NullProvider.provideNull;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import static randomness.ExtendedRandom.nextInt;
import static randomness.ExtendedRandom.nextLong;
import static randomness.ExtendedRandom.nextPowerOfTwo;
import static textops.TextCalculator.padLeft;

class UUIDTest {

    public static final String UUID_REG_EXP
            = "[0-9A-F]{8}-[0-9A-F]{4}-[0-9A-F]{4}-[0-9A-F]{4}-[0-9A-F]{12}";

    @Test
    void testRegularExpressionConstant() {
        assertEquals(UUID_REG_EXP, UUID.REGULAR_EXPRESSION.toString());
    }

    @Test
    void testToStringNullUUID() {
        UUID instance = new UUID(0L, 0L);
        String expected = "00000000-0000-0000-0000-000000000000";
        String actual = instance.toString();
        assertEquals(expected, actual);
    }

    @Test
    void testToStringFullUUID() {
        UUID instance = new UUID(-1L, -1L);
        String expected = "FFFFFFFF-FFFF-FFFF-FFFF-FFFFFFFFFFFF";
        String actual = instance.toString();
        assertEquals(expected, actual);
    }

    @Test
    void testToString() {
        System.out.println("toString");
        long mask = nextPowerOfTwo() + nextPowerOfTwo();
        long highBits = nextLong() ^ mask;
        long lowBits = nextLong() ^ mask;
        UUID instance = new UUID(highBits, lowBits);
        String highStr = padLeft(Long.toHexString(highBits).toUpperCase(), 16,
                '0');
        String lowStr = padLeft(Long.toHexString(lowBits).toUpperCase(), 16,
                '0');
        String expected = highStr.substring(0, 8) + "-"
                + highStr.substring(8, 12) + "-" + highStr.substring(12, 16)
                + "-" + lowStr.substring(0, 4) + "-" + lowStr.substring(4);
        String actual = instance.toString();
        assertEquals(expected, actual);
    }

    @Test
    void testReferentialEquality() {
        long highBits = nextLong();
        long lowBits = nextLong();
        UUID instance = new UUID(highBits, lowBits);
        assertEquals(instance, instance);
    }

    @Test
    void testNotEqualsNull() {
        long highBits = nextLong();
        long lowBits = nextLong();
        UUID instance = new UUID(highBits, lowBits);
        Object obj = provideNull();
        String msg = instance + " should not equal null";
        assert !instance.equals(obj) : msg;
    }

    @Test
    void testNotEqualsDiffClass() {
        long highBits = nextLong();
        long lowBits = nextLong();
        UUID instance = new UUID(highBits, lowBits);
        UUID instanceDiffClass = new UUID(highBits, lowBits) {};
        String msg = instance + " of class " + instance.getClass().getName()
                + " should not equal " + instanceDiffClass + " of class "
                + instanceDiffClass.getClass().getName();
        assert !instance.equals(instanceDiffClass) : msg;
    }

    @Test
    void testEquals() {
        System.out.println("equals");
        long highBits = nextLong();
        long lowBits = nextLong();
        UUID someUUID = new UUID(highBits, lowBits);
        UUID sameUUID = new UUID(highBits, lowBits);
        assertEquals(someUUID, sameUUID);
    }

    @Test
    void testNotEqualDiffHighBits() {
        long highBitsA = nextLong();
        long highBitsB = (highBitsA >> 32) + (highBitsA << 32);
        long lowBits = nextLong();
        UUID uuidA = new UUID(highBitsA, lowBits);
        UUID uuidB = new UUID(highBitsB, lowBits);
        String msg = uuidA + " should not equal " + uuidB;
        assert !uuidA.equals(uuidB) : msg;
    }

    @Test
    void testNotEqualDiffLowBits() {
        long highBits = nextLong();
        long lowBitsA = nextLong();
        long lowBitsB = (lowBitsA >> 32) + (lowBitsA << 32);
        UUID uuidA = new UUID(highBits, lowBitsA);
        UUID uuidB = new UUID(highBits, lowBitsB);
        String msg = uuidA + " should not equal " + uuidB;
        assert !uuidA.equals(uuidB) : msg;
    }

    @Test
    void testHashCode() {
        System.out.println("hashCode");
        int capacity = nextInt(256, 4096);
        Set<UUID> uuids = new HashSet<>(capacity);
        Set<Integer> hashes = new HashSet<>(capacity);
        for (int i = 0; i < capacity; i++) {
            long highBits = nextLong();
            long lowBits = nextLong();
            UUID instance = new UUID(highBits, lowBits);
            uuids.add(instance);
            hashes.add(instance.hashCode());
        }
        int size = uuids.size();
        int minimum = 11 * size / 20;
        int actual = hashes.size();
        String msg = "Having generated " + size + " UUIDs, expected at least "
                + minimum + " distinct hash codes; got " + actual;
        assert actual >= minimum : msg;
        System.out.println(msg);
    }

    @Test
    void testParseRejectsEmptyString() {
        String message = "String \"\" should cause an exception";
        Throwable t = assertThrows(NumberFormatException.class, () -> {
            UUID badResult = UUID.parse("");
            System.out.println(message + ", not given result " + badResult);
        }, message);
        String excMsg = t.getMessage();
        assert excMsg != null : "Exception message should not be null";
        assert !excMsg.isBlank() : "Exception message should not be blank";
    }

    @Test
    void testParseRejectsBlankString() {
        int len = nextInt(4, 16);
        char[] spaces = new char[len];
        Arrays.fill(spaces, ' ');
        String s = new String(spaces);
        String message = "String \"" + s + "\" should cause an exception";
        Throwable t = assertThrows(NumberFormatException.class, () -> {
            UUID badResult = UUID.parse(s);
            System.out.println(message + ", not given result " + badResult);
        }, message);
        String excMsg = t.getMessage();
        assert excMsg != null : "Exception message should not be null";
        assert !excMsg.isBlank() : "Exception message should not be blank";
    }

    @Test
    void testParseNegativeHigh() {
        long highBits = nextLong() | Long.MIN_VALUE;
        long lowBits = nextLong() & Long.MAX_VALUE;
        UUID expected = new UUID(highBits, lowBits);
        String s = expected.toString();
        UUID actual = UUID.parse(s);
        assertEquals(expected, actual);
    }

    @Test
    void testParseNegativeLow() {
        long highBits = nextLong() & Long.MAX_VALUE;
        long lowBits = nextLong() | Long.MIN_VALUE;
        UUID expected = new UUID(highBits, lowBits);
        String s = expected.toString();
        UUID actual = UUID.parse(s);
        assertEquals(expected, actual);
    }

    @Test
    void testParse() {
        System.out.println("parse");
        long highBits = nextLong() & Long.MAX_VALUE;
        long lowBits = nextLong() & Long.MAX_VALUE;
        UUID expected = new UUID(highBits, lowBits);
        String s = expected.toString();
        UUID actual = UUID.parse(s);
        assertEquals(expected, actual);
    }

    @Test
    void testParseLowercase() {
        long highBits = nextLong();
        long lowBits = nextLong();
        UUID expected = new UUID(highBits, lowBits);
        String s = expected.toString().toLowerCase();
        UUID actual = UUID.parse(s);
        assertEquals(expected, actual);
    }

}
