package retail.books;

import java.util.Random;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ISBNTest {

    static final Random RANDOM = new Random(System.currentTimeMillis() >>> 3);

    @Test
    void testToString() {
        System.out.println("toString");
        final long num = 978_0_00000_000L + RANDOM.nextInt(10_00000_000);
        ISBN isbn = new ISBN(num) {};
        StringBuilder intermediate = new StringBuilder(Long.toString(num));
        intermediate.insert(9, '-');
        intermediate.insert(4, '-');
        intermediate.insert(3, '-');
        fail("REWRITE THIS TEST");
//        String expected = intermediate.toString() + '-' + isbn.getCheckDigit();
//        String actual = isbn.toString();
//        assertEquals(expected, actual);
    }

}
