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

}
