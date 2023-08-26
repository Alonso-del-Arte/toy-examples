package trading;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import randomness.ExtendedRandom;

class StockMarketTest {

    private static final Map<String, String> MARKET_NAMES_AND_ABBREVIATIONS
            = new HashMap<>();

    private static final String NASDAQ_NAME
            = "National Association of Securities Dealers Automated Quotations";

    static {
        MARKET_NAMES_AND_ABBREVIATIONS.put("New York Stock Exchange", "NYSE");
        MARKET_NAMES_AND_ABBREVIATIONS.put("NYSE Chicago", "CHX");
        MARKET_NAMES_AND_ABBREVIATIONS.put(NASDAQ_NAME, "NASDAQ");
    }

    @Test
    void testGetName() {
        System.out.println("getName");
        for (Map.Entry<String, String> nameAndAbbrev
                : MARKET_NAMES_AND_ABBREVIATIONS.entrySet()) {
            String expected = nameAndAbbrev.getKey();
            StockMarket exchange = new StockMarket(expected,
                    nameAndAbbrev.getValue());
            String actual = exchange.getName();
            assertEquals(expected, actual);
        }
    }

    @Test
    void testGetNameRandom() {
        String expected = ExtendedRandom.alphanumeric(20);
        StockMarket exchange = new StockMarket(expected,
                expected.substring(0, 5));
        String actual = exchange.getName();
        assertEquals(expected, actual);
    }

    @Test
    void testGetAbbreviation() {
        System.out.println("getAbbreviation");
        for (Map.Entry<String, String> nameAndAbbrev
                : MARKET_NAMES_AND_ABBREVIATIONS.entrySet()) {
            String expected = nameAndAbbrev.getValue();
            StockMarket exchange = new StockMarket(nameAndAbbrev.getKey(),
                    expected);
            String actual = exchange.getAbbreviation();
            assertEquals(expected, actual);
        }
    }

    @Test
    void testGetAbbreviationRandom() {
        String expected = ExtendedRandom.alphanumeric(5);
        StockMarket exchange = new StockMarket("Fictional Exchange", expected);
        String actual = exchange.getAbbreviation();
        assertEquals(expected, actual);
    }

//    @Test 1-param constructor should be able to determine acronym, at least
//    for the simpler cases, e.g., "SFE" for "San
    void testAuxiliaryConstructor() {
        fail("Haven't written test yet");
    }

    @Test
    void testConstructorRejectsNullName() {
        Throwable t = assertThrows(NullPointerException.class, () -> {
            StockMarket exchange = new StockMarket(null, "???");
            System.out.println("Should not have been able to create " + exchange
                    + " with null name");
        });
        String excMsg = t.getMessage();
        assert excMsg != null : "Message should not be null";
        assert !excMsg.isEmpty() : "Message should not be empty";
        System.out.println("\"" + excMsg + "\"");
    }

    @Test
    void testConstructorRejectsEmptyName() {
        Throwable t = assertThrows(IllegalArgumentException.class, () -> {
            StockMarket exchange = new StockMarket("", "???");
            System.out.println("Should not have been able to create " + exchange
                    + " with empty name");
        });
        String excMsg = t.getMessage();
        assert excMsg != null : "Message should not be null";
        assert !excMsg.isEmpty() : "Message should not be empty";
        System.out.println("\"" + excMsg + "\"");
    }

    @Test
    void testConstructorRejectsNullAbbreviation() {
        Throwable t = assertThrows(NullPointerException.class, () -> {
            StockMarket exchange = new StockMarket("Some Exchange", null);
            System.out.println("Should not have been able to create " + exchange
                    + " with null abbreviation");
        });
        String excMsg = t.getMessage();
        assert excMsg != null : "Message should not be null";
        assert !excMsg.isEmpty() : "Message should not be empty";
        System.out.println("\"" + excMsg + "\"");
    }

    @Test
    void testConstructorRejectsEmptyAbbreviation() {
        Throwable t = assertThrows(IllegalArgumentException.class, () -> {
            StockMarket exchange = new StockMarket("Some Exchange", "");
            System.out.println("Should not have been able to create " + exchange
                    + " with empty abbreviation");
        });
        String excMsg = t.getMessage();
        assert excMsg != null : "Message should not be null";
        assert !excMsg.isEmpty() : "Message should not be empty";
        System.out.println("\"" + excMsg + "\"");
    }

    @Test
    void testConstructorRejectsAbbreviationLongerThanName() {
        int nameLen = ExtendedRandom.nextInt(64) + 16;
        String name = ExtendedRandom.alphanumeric(nameLen);
        int abbrevLen = nameLen + ExtendedRandom.nextInt(9) + 1;
        String abbrev = ExtendedRandom.alphanumeric(abbrevLen);
        Throwable t = assertThrows(IllegalArgumentException.class, () -> {
            StockMarket exchange = new StockMarket(name, abbrev);
            System.out.println("Should not have been able to create " + exchange
                    + " with name " + name + " and abbreviation " + abbrev);
        });
        String excMsg = t.getMessage();
        assert excMsg != null : "Message should not be null";
        assert !excMsg.isEmpty() : "Message should not be empty";
        System.out.println("\"" + excMsg + "\"");
    }

}
