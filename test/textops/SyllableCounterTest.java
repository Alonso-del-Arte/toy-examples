package textops;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import randomness.ExtendedRandom;

class SyllableCounterTest {

    @Test
    void testGetText() {
        System.out.println("getText");
        int len = ExtendedRandom.nextInt(8) + 2;
        String expected = WordCounterTest.makePhrase(len);
        SyllableCounter instance = new SyllableCounter(expected);
        String actual = instance.getText();
        assertEquals(expected, actual);
    }

}
