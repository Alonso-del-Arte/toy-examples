package viewers;

import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class MandelbrotJuliaViewerTest {

    @Test
    void testAppleMenuBarWhenApplicable() {
        String[] args = {};
        MandelbrotJuliaViewer.main(args);
        boolean isMacOS = System.getProperty("os.name").startsWith("Mac OS");
        String propKey = "apple.laf.useScreenMenuBar";
        String expected = isMacOS ? "true" : "false";
        String actual = System.getProperty(propKey);
        if (actual == null) {
            String msg = "Property \"" + propKey
                    + "\" should not be absent on Mac OS X system";
            assert !isMacOS : msg;
        } else {
            assertEquals(expected, actual);
        }
    }

}
