package textops;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import randomness.ExtendedRandom;

class Native2ASCIITest {

    @Test
    void testProcessLineLeavesASCIIOnlyInputUnchanged() {
        int length = ExtendedRandom.nextInt(4) + 16;
        String expected = ExtendedRandom.alphanumeric(length);
        String actual = Native2ASCII.processLine(expected);
        assertEquals(expected, actual);
    }

}
