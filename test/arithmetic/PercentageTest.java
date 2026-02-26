package arithmetic;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class PercentageTest {

    @Test
    void testToString() {
        System.out.println("toString");
        for (int i = 0; i < 101; i++) {
            Percentage instance = new Percentage(i);
            String expected = i + "%";
            String actual = instance.toString();
            assertEquals(expected, actual);
        }
    }

    @Test
    void testConstructorRejectsNaN() {
        double value = Double.NaN;
        String message = "Constructor should reject " + value;
        Throwable t = assertThrows(IllegalArgumentException.class, () -> {
            Percentage badInstance = new Percentage(value);
            System.out.println(message + ", not given " + badInstance);
        }, message);
        String excMsg = t.getMessage();
        assert excMsg != null : "Exception message should not be null";
        assert !excMsg.isBlank() : "Exception message should not be blank";
        System.out.println("\"" + excMsg + "\"");
    }

}
