package randomness;

import fractions.Fraction;

import java.awt.Color;
import static java.lang.Character.UnicodeBlock.*;
import java.math.BigInteger;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Set;

import javax.naming.ldap.Rdn;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ExtendedRandomTest {

    public static final Random RANDOM = new Random(-System.currentTimeMillis());

    /**
     * A selection of Unicode blocks from the Basic Multilingual Plane. Since
     * this project uses Java 8 rather than the latest LTS, the following
     * Unicode blocks are missing:
     * <ul>
     *     <li>Syriac Supplement</li>
     *     <li>Arabic Extended B</li>
     *     <li>Cyrillic Extended C</li>
     *     <li>Georgian Extended</li>
     *     <li>Myanmar Extended B</li>
     *     <li>Latin Extended E</li>
     *     <li>Cherokee Supplement</li>
     * </ul>
     */
    private static final Character.UnicodeBlock[] UNICODE_BLOCKS
            = {BASIC_LATIN, LATIN_1_SUPPLEMENT, LATIN_EXTENDED_A,
            LATIN_EXTENDED_B, IPA_EXTENSIONS, GREEK, CYRILLIC,
            CYRILLIC_SUPPLEMENTARY, ARMENIAN, HEBREW, ARABIC, SYRIAC,
            ARABIC_SUPPLEMENT, THAANA, NKO, SAMARITAN, MANDAIC,
            ARABIC_EXTENDED_A, DEVANAGARI, BENGALI, GURMUKHI, GUJARATI, ORIYA,
            TAMIL, TELUGU, KANNADA, MALAYALAM, SINHALA, THAI, LAO, TIBETAN,
            MYANMAR, GEORGIAN, HANGUL_JAMO, ETHIOPIC, ETHIOPIC_SUPPLEMENT,
            CHEROKEE, UNIFIED_CANADIAN_ABORIGINAL_SYLLABICS, OGHAM, RUNIC,
            TAGALOG, HANUNOO, BUHID, TAGBANWA, KHMER, MONGOLIAN,
            UNIFIED_CANADIAN_ABORIGINAL_SYLLABICS_EXTENDED, LIMBU, TAI_LE,
            NEW_TAI_LUE, BUGINESE, TAI_THAM, BALINESE, SUNDANESE, BATAK,
            LEPCHA, OL_CHIKI, SUNDANESE_SUPPLEMENT, VEDIC_EXTENSIONS,
            LATIN_EXTENDED_ADDITIONAL, GREEK_EXTENDED, GLAGOLITIC,
            LATIN_EXTENDED_C, COPTIC, GEORGIAN_SUPPLEMENT, TIFINAGH,
            ETHIOPIC_EXTENDED, CYRILLIC_EXTENDED_A, HIRAGANA, KATAKANA,
            BOPOMOFO, KANBUN, BOPOMOFO_EXTENDED,
            CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A, CJK_UNIFIED_IDEOGRAPHS,
            YI_SYLLABLES, LISU, VAI, CYRILLIC_EXTENDED_B, BAMUM,
            LATIN_EXTENDED_D, SYLOTI_NAGRI, PHAGS_PA, SAURASHTRA,
            DEVANAGARI_EXTENDED, KAYAH_LI, REJANG, HANGUL_JAMO_EXTENDED_A,
            JAVANESE, CHAM, MYANMAR_EXTENDED_A, TAI_VIET,
            MEETEI_MAYEK_EXTENSIONS, ETHIOPIC_EXTENDED_A, MEETEI_MAYEK,
            HANGUL_SYLLABLES, HANGUL_JAMO_EXTENDED_B};

    private static final int NUMBER_OF_BLOCKS = UNICODE_BLOCKS.length;

    @Test
    void testNextInt() {
        System.out.println("nextInt");
        int capacity = 2048;
        Set<Integer> numbers = new HashSet<>(capacity);
        for (int i = 0; i < capacity; i++) {
            numbers.add(ExtendedRandom.nextInt());
        }
        int expected = 15 * capacity / 16;
        int actual = numbers.size();
        String msg = "Expected at least " + expected
                + " distinct integers out of " + capacity + ", got " + actual;
        System.out.println(msg);
        assert actual >= expected : msg;
    }

    @Test
    void testNextIntBoundedByZeroThrowsException() {
        Throwable t = assertThrows(IllegalArgumentException.class, () -> {
            int badResult = ExtendedRandom.nextInt(0);
            System.out.println("Calling nextInt with bound 0 gave "
                    + badResult);
        });
        String excMsg = t.getMessage();
        assert excMsg != null : "Message should not be null";
        assert !excMsg.isEmpty() : "Message should not be empty";
        System.out.println("\"" + excMsg + "\"");
    }

    @Test
    void testNextIntBounded() {
        int capacity = 2048;
        int bound = capacity - RANDOM.nextInt(128);
        Set<Integer> numbers = new HashSet<>(capacity);
        for (int i = 0; i < capacity; i++) {
            int number = ExtendedRandom.nextInt(bound);
            String msg = "Pseudorandom number " + number
                    + " should be at least 0 but less than " + bound;
            assert number >= 0 : msg;
            assert number < bound : msg;
            numbers.add(number);
        }
        int expected = capacity / 2;
        int actual = numbers.size();
        String msg = "Expected at least " + expected
                + " distinct integers in the range 0 to " + (bound - 1)
                + " out of " + capacity + ", got " + actual;
        System.out.println(msg);
        assert actual >= expected : msg;
    }

    @Test
    void testNextIntBoundedByNegative() {
        int capacity = 2048;
        int bound = -(capacity - RANDOM.nextInt(128));
        Set<Integer> numbers = new HashSet<>(capacity);
        for (int i = 0; i < capacity; i++) {
            try {
                int number = ExtendedRandom.nextInt(bound);
                String msg = "Pseudorandom number " + number
                        + " should be at most 0 but also more than " + bound;
                assert number <= 0 : msg;
                assert number > bound : msg;
                numbers.add(number);
            } catch (IllegalArgumentException iae) {
                String errMsg = "Bound " + bound
                        + " shouldn't've caused IllegalArgumentException";
                throw new AssertionError(errMsg, iae);
            }
        }
        int expected = capacity / 2;
        int actual = numbers.size();
        String msg = "Expected at least " + expected
                + " distinct integers in the range " + (bound - 1)
                + " to 0 out of " + capacity + ", got " + actual;
        System.out.println(msg);
        assert actual >= expected : msg;
    }

    @Test
    void testNextIntFromRange() {
        fail("RESUME WORK HERE");
    }

    @Test
    void testNextBigInt() {
        System.out.println("nextBigInt");
        int capacity = 64;
        int boundExponent = capacity + RANDOM.nextInt(64);
        BigInteger two = BigInteger.valueOf(2);
        BigInteger bound = two.pow(boundExponent);
        Set<BigInteger> numbers = new HashSet<>(capacity);
        for (int i = 0; i < capacity; i++) {
            BigInteger number = ExtendedRandom.nextBigInt(boundExponent);
            String msg = "Pseudorandom number " + number
                    + " should be at least 0 but less than " + bound;
            assert number.compareTo(BigInteger.ZERO) >= 0 : msg;
            assert number.compareTo(bound) < 0 : msg;
            numbers.add(number);
        }
        int expected = 3 * capacity / 4;
        int actual = numbers.size();
        String msg = "Expected at least " + expected
                + " distinct integers in the range 0 to "
                + (bound.subtract(BigInteger.ONE)) + " out of " + capacity
                + ", got " + actual;
        System.out.println(msg);
        assert actual >= expected : msg;
    }

    @Test
    void testNextDouble() {
        System.out.println("nextDouble");
        int capacity = RANDOM.nextInt(32) + 128;
        Set<Double> set = new HashSet<>(capacity);
        int curr = 0;
        while (curr < capacity) {
            set.add(ExtendedRandom.nextDouble());
            curr++;
        }
        int expected = 9 * capacity / 10;
        int actual = set.size();
        String msg = "nextDouble() should have given at least " + expected
                + " distinct numbers, gave " + actual + " distinct";
        assert expected <= actual : msg;
    }

    @Test
    void testNextFraction() {
        System.out.println("nextFraction");
        int capacity = 1000;
        Set<Fraction> fractions = new HashSet<>(capacity);
        for (int i = 0; i < capacity; i++) {
            fractions.add(ExtendedRandom.nextFraction());
        }
        int expected = 3 * capacity / 4;
        int actual = fractions.size();
        String msg = "Expected at least " + expected
                + " distinct fractions out of " + capacity + ", got " + actual;
        System.out.println(msg);
        assert actual >= expected : msg;
    }

    @Test
    void testAlphanumericRejectsNegativeLength() {
        int badLength = -RANDOM.nextInt(1024) - 1;
        Throwable t = assertThrows(IllegalArgumentException.class, () -> {
            String badString = ExtendedRandom.alphanumeric(badLength);
            System.out.println("Trying to get random alphanumeric of length "
                    + badLength + " should not have given \"" + badString
                    + "\"");
        });
        String excMsg = t.getMessage();
        assert excMsg != null : "Exception message should not be null";
        System.out.println("\"" + excMsg + "\"");
        String badLengthStr = Integer.toString(badLength);
        String msg = "Exception message should include \"" + badLengthStr
                + "\"";
        assert excMsg.contains(badLengthStr) : msg;
    }

    @Test
    void testAlphanumericRejectsLengthZero() {
        int badLength = 0;
        Throwable t = assertThrows(IllegalArgumentException.class, () -> {
            String badString = ExtendedRandom.alphanumeric(badLength);
            System.out.println("Trying to get random alphanumeric of length "
                    + badLength + " should not have given \"" + badString
                    + "\"");
        });
        String excMsg = t.getMessage();
        assert excMsg != null : "Exception message should not be null";
        System.out.println("\"" + excMsg + "\"");
        String badLengthStr = Integer.toString(badLength);
        String msg = "Exception message should include \"" + badLengthStr
                + "\"";
        assert excMsg.contains(badLengthStr) : msg;
    }

    @Test
    void testAlphanumericGivesStringOfSpecifiedLength() {
        for (int expected = 1; expected < 21; expected++) {
            String s = ExtendedRandom.alphanumeric(expected);
            int actual = s.length();
            assertEquals(expected, actual);
        }
    }

    private static boolean isASCIILetterOrDigit(char ch) {
        boolean isDigit = ch >= '0' && ch <= '9';
        boolean isUppercaseLetter = ch >= 'A' && ch <= 'Z';
        boolean isLowercaseLetter = ch >= 'a' && ch <= 'z';
        return isDigit || isUppercaseLetter || isLowercaseLetter;
    }

    @Test
    void testAlphanumericGivesStringOfASCIIAlphanumerics() {
        int length = RANDOM.nextInt(16) + 4;
        String s = ExtendedRandom.alphanumeric(length);
        String msgPartA = "Character '";
        String msgPartB = "' should be ASCII letter or digit";
        char[] characters = s.toCharArray();
        for (char ch : characters) {
            String msg = msgPartA + ch + msgPartB;
            assert isASCIILetterOrDigit(ch) : msg;
        }
    }

    @Test
    void testAlphanumeric() {
        System.out.println("alphanumeric");
        int capacity = RANDOM.nextInt(64) + 36;
        Set<String> strings = new HashSet<>(capacity);
        int length = RANDOM.nextInt(16) + 4;
        for (int i = 0; i < capacity; i++) {
            String s = ExtendedRandom.alphanumeric(length);
            strings.add(s);
        }
        int expected = 9 * capacity / 10;
        int actual = strings.size();
        String msg = "alphanumeric should've given at least " + expected
                + " distinct, gave " + actual + " distinct";
        System.out.println(msg);
        assert actual >= expected : msg;
    }

    private static void assertBlockIsFromBMP(Character.UnicodeBlock block) {
        boolean found = false;
        int index = 0;
        while (!found && index < NUMBER_OF_BLOCKS) {
            found = block.equals(UNICODE_BLOCKS[index]);
            index++;
        }
        String msg = "Block " + block.toString() + " should come from BMP";
        assert found : msg;
    }

    @Test
    void testChooseBMPBlock() {
        System.out.println("chooseBMPBlock");
        int totalNumberOfCalls = 16 * NUMBER_OF_BLOCKS;
        Set<Character.UnicodeBlock> blocks = new HashSet<>(NUMBER_OF_BLOCKS);
        for (int i = 0; i < totalNumberOfCalls; i++) {
            Character.UnicodeBlock block = ExtendedRandom.chooseBMPBlock();
            assertBlockIsFromBMP(block);
            blocks.add(block);
        }
        int minimum = 3 * NUMBER_OF_BLOCKS / 5;
        int actual = blocks.size();
        String msg = "After " + totalNumberOfCalls + " calls to choose from "
                + NUMBER_OF_BLOCKS
                + " Unicode blocks, set should have at least " + minimum
                + ", got " + actual;
        assert actual >= minimum : msg;
    }

    @Test
    void testChooseBMPBlockOtherThan() {
        System.out.println("chooseBMPBlockOtherThan");
        for (Character.UnicodeBlock block : UNICODE_BLOCKS) {
            Character.UnicodeBlock other
                    = ExtendedRandom.chooseBMPBlockOtherThan(block);
            String msg = other.toString() + " should not be " + block.toString();
            assert !block.equals(other) : msg;
        }
    }

    @Test
    void testChooseBMPBlockOtherThanNullCausesException() {
        String message = "Choosing block other than null should cause NPE";
        Throwable t = assertThrows(NullPointerException.class, () -> {
            Character.UnicodeBlock badBlock
                    = ExtendedRandom.chooseBMPBlockOtherThan(null);
            System.out.println(message + ", not given " + badBlock.toString());
        }, message);
        String excMsg = t.getMessage();
        assert excMsg != null : "Exception message should not be null";
        assert !excMsg.isEmpty() : "Exception message should not be empty";
        System.out.println("\"" + excMsg + "\"");
    }

    @Test
    void testNextObject() {
        System.out.println("nextObject");
        int capacity = RANDOM.nextInt(32) + 8;
        LocalDateTime[] array = new LocalDateTime[capacity];
        Set<LocalDateTime> expected = new HashSet<>(capacity);
        LocalDateTime curr = LocalDateTime.now();
        for (int i = 0; i < capacity; i++) {
            curr = curr.minusHours((long) i * RANDOM.nextInt(24));
            array[i] = curr;
            expected.add(curr);
        }
        Set<LocalDateTime> actual = new HashSet<>(capacity);
        int numberOfCalls = 20 * capacity;
        for (int j = 0; j < numberOfCalls; j++) {
            actual.add(ExtendedRandom.nextObject(array));
        }
        String msg = "After " + numberOfCalls
                + " nextObject() calls for array with " + capacity
                + " elements, all elements should have been given";
        assertEquals(expected, actual, msg);
    }

    @Test
    void testNextObjectEmptyArrayThrowsException() {
        Statement[] array = {};
        Throwable t = assertThrows(NoSuchElementException.class, () -> {
            Statement badStatement = ExtendedRandom.nextObject(array);
            System.out.println("Calling nextObject() on empty array gave "
                    + badStatement.toString());
        });
        String excMsg = t.getMessage();
        assert excMsg != null : "Message should not be null";
        System.out.println("\"" + excMsg + "\"");
    }

    @Test
    void testNextObjectList() {
        int capacity = RANDOM.nextInt(32) + 8;
        List<Color> list = new ArrayList<>(capacity);
        Set<Color> expected = new HashSet<>(capacity);
        for (int i = 0; i < capacity; i++) {
            Color color = new Color(RANDOM.nextInt() + i);
            list.add(color);
            expected.add(color);
        }
        Set<Color> actual = new HashSet<>(capacity);
        int numberOfCalls = 20 * capacity;
        for (int j = 0; j < numberOfCalls; j++) {
            actual.add(ExtendedRandom.nextObject(list));
        }
        String msg = "After " + numberOfCalls
                + " nextObject() calls for list with " + capacity
                + " elements, all elements should have been given";
        assertEquals(expected, actual, msg);
    }

    @Test
    void testNextObjectEmptyListThrowsException() {
        List<Double> list = new ArrayList<>();
        double someNumber = RANDOM.nextDouble();
        list.add(someNumber);
        list.remove(0);
        Throwable t = assertThrows(NoSuchElementException.class, () -> {
            double badNumber = ExtendedRandom.nextObject(list);
            System.out.println("Calling nextObject() on empty list gave "
                    + badNumber);
        });
        String excMsg = t.getMessage();
        assert excMsg != null : "Message should not be null";
        System.out.println("\"" + excMsg + "\"");
    }

    @Test
    void testNextObjectSet() {
        int capacity = RANDOM.nextInt(32) + 8;
        Set<String> expected = new HashSet<>(capacity);
        LocalDateTime curr = LocalDateTime.now();
        for (int i = 0; i < capacity; i++) {
            curr = curr.minusHours((long) i * RANDOM.nextInt(24));
            expected.add(curr.toString());
        }
        Set<String> actual = new HashSet<>(capacity);
        int numberOfCalls = 20 * capacity;
        for (int j = 0; j < numberOfCalls; j++) {
            actual.add(ExtendedRandom.nextObject(expected));
        }
        String msg = "After " + numberOfCalls
                + " nextObject() calls for set with " + capacity
                + " elements, all elements should have been given";
        assertEquals(expected, actual, msg);
    }

    @Test
    void testNextObjectEmptySetThrowsException() {
        Set<Rdn> set = new HashSet<>();
        Throwable t = assertThrows(NoSuchElementException.class, () -> {
            Rdn badRDN = ExtendedRandom.nextObject(set);
            System.out.println("Calling nextObject() on empty set gave "
                     + badRDN.toString());
        });
        String excMsg = t.getMessage();
        assert excMsg != null : "Message should not be null";
        System.out.println("\"" + excMsg + "\"");
    }

}
