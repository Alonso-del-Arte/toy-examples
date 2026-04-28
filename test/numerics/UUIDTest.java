package numerics;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import static randomness.ExtendedRandom.nextInt;
import static textops.TextCalculator.padLeft;

class UUIDTest {

    @Test
    void testToStringNullUUID() {
        UUID instance = new UUID(0L, 0L);
        String expected = "00000000-0000-0000-0000-000000000000";
        String actual = instance.toString();
        assertEquals(expected, actual);
    }

}
