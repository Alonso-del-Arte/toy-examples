package calculators;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BinaryStringsCollectorTest {

//    @BeforeAll
//    static void setUpClass() {}

    @Test
    void testConstructorRejectsNegativeLength() {
        Random random = new Random();
        byte badLength = (byte) (-random.nextInt(128));
        Throwable t = assertThrows(IllegalArgumentException.class, () -> {
            BinaryStringsCollector badCollector
                    = new BinaryStringsCollector(badLength);
            System.out.println("Should not have created " + badCollector
                    + " with bad length " + badLength);
        });
        String excMsg = t.getMessage();
        assert excMsg != null : "Message should not be null";
        String numStr = Byte.toString(badLength);
        String msg = "Message should contain \"" + numStr + "\"";
        assert excMsg.contains(numStr) : msg;
        System.out.println("\"" + excMsg + "\"");
    }

}
