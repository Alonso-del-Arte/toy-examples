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
    void testGiveLengthZero() {
        BinaryStringsCollector collector = new BinaryStringsCollector((byte) 0);
        Set<String> set = collector.give();
        String msg = "Set for length 0 should be empty, was given " + set.size()
                + " element(s)";
        assert set.isEmpty() : msg;
    }

    private static void assertSetsContainSame(Set<String> expected,
                                              Set<String> actual) {
        assertEquals(expected.size(), actual.size(),
                "Expected and actual should be of the same size");
        String msg = "Set " + expected + " should contain the same elements as "
                + actual;
        assert expected.containsAll(actual) : msg;
    }

    @Test
    void testGiveLengthOne() {
        BinaryStringsCollector collector = new BinaryStringsCollector((byte) 1);
        Set<String> expected = new HashSet<>();
        expected.add("0");
        expected.add("1");
        Set<String> actual = collector.give();
        assertSetsContainSame(expected, actual);
    }

    @Test
    void testGiveLengthTwo() {
        BinaryStringsCollector collector = new BinaryStringsCollector((byte) 2);
        Set<String> expected = new HashSet<>();
        expected.add("00");
        expected.add("01");
        expected.add("10");
        Set<String> actual = collector.give();
        assertSetsContainSame(expected, actual);
    }

    @Test
    void testGiveLengthThree() {
        BinaryStringsCollector collector = new BinaryStringsCollector((byte) 3);
        Set<String> expected = new HashSet<>();
        expected.add("000");
        expected.add("001");
        expected.add("010");
        expected.add("100");
        expected.add("101");
        Set<String> actual = collector.give();
        assertSetsContainSame(expected, actual);
    }

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
