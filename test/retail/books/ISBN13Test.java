package retail.books;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ISBN13Test {

    @Test
    void testOriginalPrefixConstant() {
        short expected = 978;
        short actual = ISBN13.ORIGINAL_ISBN_13_PREFIX;
        assertEquals(expected, actual);
    }

}
