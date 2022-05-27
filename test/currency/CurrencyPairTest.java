package currency;

import java.util.Currency;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CurrencyPairTest {

    @Test
    void testGetFrom() {
        System.out.println("getFrom");
        Currency expected = Currency.getInstance("XTS");
        Currency to = Currency.getInstance("XXX");
        CurrencyPair pair = new CurrencyPair(expected, to);
        Currency actual = pair.getFrom();
        assertEquals(expected, actual);
    }

    @Test
    void testGetFromDiffPair() {
        Currency expected = CurrencyAmountTest.US_DOLLAR_CURRENCY;
        Currency to = CurrencyAmountTest.EURO_CURRENCY;
        CurrencyPair pair = new CurrencyPair(expected, to);
        Currency actual = pair.getFrom();
        assertEquals(expected, actual);
    }

    @Test
    void testGetTo() {
        System.out.println("getTo");
        Currency from = Currency.getInstance("XTS");
        Currency expected = Currency.getInstance("XXX");
        CurrencyPair pair = new CurrencyPair(from, expected);
        Currency actual = pair.getTo();
        assertEquals(expected, actual);
    }

    @Test
    void testGetToDiffPair() {
        Currency from = CurrencyAmountTest.CANADA_DOLLAR_CURRENCY;
        Currency expected = CurrencyAmountTest.YEN_CURRENCY;
        CurrencyPair pair = new CurrencyPair(from, expected);
        Currency actual = pair.getTo();
        assertEquals(expected, actual);
    }

    @Test
    void testConstructorRejectsNullFrom() {
        Currency to = CurrencyAmountTest.EAST_CARIBBEAN_DOLLARS;
        Throwable t = assertThrows(NullPointerException.class, () -> {
            CurrencyPair badPair = new CurrencyPair(null, to);
            System.out.println("Should not have been able to create " + badPair
                    + " with null From");
        });
        String excMsg = t.getMessage();
        assert excMsg != null : "Message should not be null";
        System.out.println("\"" + excMsg + "\"");
    }

    @Test
    void testConstructorRejectsNullTo() {
        Currency from = CurrencyAmountTest.US_DOLLAR_CURRENCY;
        Throwable t = assertThrows(NullPointerException.class, () -> {
            CurrencyPair badPair = new CurrencyPair(from, null);
            System.out.println("Should not have been able to create " + badPair
                    + " with null To");
        });
        String excMsg = t.getMessage();
        assert excMsg != null : "Message should not be null";
        System.out.println("\"" + excMsg + "\"");
    }

}
