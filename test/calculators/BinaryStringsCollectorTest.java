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

    private static final byte MINIMUM_N_TO_CALCULATE = 5;

    private static final byte MAXIMUM_N_TO_CALCULATE = 12;

    private static final Map<Byte, Set<String>> BINARY_STRINGS_MAP
            = new HashMap<>(MAXIMUM_N_TO_CALCULATE
                    - MINIMUM_N_TO_CALCULATE + 1);

    @BeforeAll
    static void setUpClass() {
        for (byte i = MINIMUM_N_TO_CALCULATE; i <= MAXIMUM_N_TO_CALCULATE; i++) {
            int capacity = 1 << (i - 1);
            Set<String> set = new HashSet<>(capacity);
            int threshold = 2 * capacity;
            for (int j = 0; j < threshold; j++) {
                String numStr = String.format("%1$" + i + "s",
                        Integer.toString(j, 2)).replace(' ', '0');
                if (!numStr.contains("11")) {
                    set.add(numStr);
                }
            }
            BINARY_STRINGS_MAP.put(i, set);
        }
    }

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
    void testGiveLengthFour() {
        BinaryStringsCollector collector = new BinaryStringsCollector((byte) 4);
        Set<String> expected = new HashSet<>();
        expected.add("0000");
        expected.add("0001");
        expected.add("0010");
        expected.add("0100");
        expected.add("0101");
        expected.add("1000");
        expected.add("1001");
        expected.add("1010");
        Set<String> actual = collector.give();
        assertSetsContainSame(expected, actual);
    }

    @Test
    void testGiveOtherLengths() {
        for (byte n = MINIMUM_N_TO_CALCULATE; n <= MAXIMUM_N_TO_CALCULATE;
                n++) {
            BinaryStringsCollector collector = new BinaryStringsCollector(n);
            Set<String> expected = BINARY_STRINGS_MAP.get(n);
            Set<String> actual = collector.give();
            assertSetsContainSame(expected, actual);
        }
    }

    @Test
    void testGiveSecondTimeDoesNotRecalculate() {
        long firstCallStart = System.currentTimeMillis();
        BinaryStringsCollector collector
                = new BinaryStringsCollector(MAXIMUM_N_TO_CALCULATE);
        collector.give();
        long firstCallEnd = System.currentTimeMillis();
        collector.give();
        long secondCallEnd = System.currentTimeMillis();
        long firstCallDuration = firstCallEnd - firstCallStart;
        long secondCallDuration = secondCallEnd - firstCallEnd;
        long expected = firstCallDuration / 4;
        String msg = "First call took " + firstCallDuration
                + " ms, second call took " + secondCallDuration
                + " ms (should not have taken more than " + expected + " ms)";
        assert secondCallDuration <= expected : msg;
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
