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

}
