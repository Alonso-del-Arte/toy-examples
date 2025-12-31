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

    @Test
    void testConstructorRejectsNullText() {
        String message = "Constructor should reject null text";
        Throwable t = assertThrows(NullPointerException.class, () -> {
            SyllableCounter badInstance = new SyllableCounter(null);
            System.out.println(message + ", not created instance "
                    + badInstance);
        }, message);
        String excMsg = t.getMessage();
        assert excMsg != null : "Exception message should not be null";
        assert !excMsg.isBlank() : "Exception message should not be blank";
        System.out.println("\"" + excMsg + "\"");
    }

}
