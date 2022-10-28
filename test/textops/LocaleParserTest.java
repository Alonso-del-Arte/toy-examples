package textops;

import java.util.Locale;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LocaleParserTest {

    @Test
    void testParseDefaultLocale() {
        Locale expected = Locale.getDefault();
        Locale actual = LocaleParser.parse(expected.toString());
        assertEquals(expected, actual);
    }

}