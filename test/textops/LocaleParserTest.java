package textops;

import java.util.Locale;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LocaleParserTest {

    @Test
    void testParseDefaultLocale() {
        Locale expected = Locale.getDefault();
        Locale actual = LocaleParser.parse(expected.toString());
        assertEquals(expected, actual);
    }

    @Test
    void testParse() {
        System.out.println("parse");
        Locale[] locales = Locale.getAvailableLocales();
        for (Locale expected : locales) {
            Locale actual = LocaleParser.parse(expected.toString());
            assertEquals(expected, actual);
        }
    }

    @Test
    void testParseUnavailableLocale() {
        Throwable t = assertThrows(NoSuchElementException.class, () -> {
            String s = "tlh-Q'Onos";
            Locale badLocale = LocaleParser.parse(s);
            System.out.println("Somehow obtained " + badLocale.toString()
                    + " for locale " + s);
        });
        String excMsg = t.getMessage();
        assert excMsg != null : "Message should not be null";
        System.out.println("\"" + excMsg + "\"");
    }

}
