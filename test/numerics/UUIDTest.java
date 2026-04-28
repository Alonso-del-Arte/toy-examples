package numerics;

import static org.example.NullProvider.provideNull;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import static randomness.ExtendedRandom.nextLong;
import static randomness.ExtendedRandom.nextPowerOfTwo;
import static textops.TextCalculator.padLeft;

class UUIDTest {

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
        //                 01234567 8901 2345 6789 012345678901
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

}
