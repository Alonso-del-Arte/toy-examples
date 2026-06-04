package textops;

import arithmetic.Range;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import randomness.ExtendedRandom;

class TextCalculatorTest {

    private static final int POST_ASCII_TEST_CHAR_BOUND = '\u4E00' - 128;

    private static final String[] ADJECTIVES = {"Associated", "Ballistic",
            "Consolidated", "Diversified", "Equestrian", "Federal", "Global",
            "Historical", "International", "Local", "National", "State"};

    private static final String[] SINGULAR_NOUNS = {"Academy", "Bureau",
            "Chamber", "Department"};

    public static final String[] PLURAL_NOUNS = {"Armadillos", "Bullfrogs",
            "Clerks", "Investigations", "Jokes", "Kangaroos", "Llamas"};

    @Test
    void testBlockRange() {
        System.out.println("blockRange");
        Character.UnicodeBlock block = Character.UnicodeBlock.BASIC_LATIN;
        Range expected = new Range(0x0020, 0x007E);
        Range actual = TextCalculator.blockRange(block);
        assertEquals(expected, actual);
    }

    @Test
    void testBlockRangeLatin1Supplement() {
        Character.UnicodeBlock block
                = Character.UnicodeBlock.LATIN_1_SUPPLEMENT;
        Range expected = new Range(0x00A0, 0x00FF);
        Range actual = TextCalculator.blockRange(block);
        assertEquals(expected, actual);
    }

    @Test
    void testBlockRangeLatinExtendedA() {
        Character.UnicodeBlock block
                = Character.UnicodeBlock.LATIN_EXTENDED_A;
        Range expected = new Range(0x0100, 0x017F);
        Range actual = TextCalculator.blockRange(block);
        assertEquals(expected, actual);
    }

    @Test
    void testBlockRangeLatinExtendedB() {
        Character.UnicodeBlock block
                = Character.UnicodeBlock.LATIN_EXTENDED_B;
        Range expected = new Range(0x0180, 0x024F);
        Range actual = TextCalculator.blockRange(block);
        assertEquals(expected, actual);
    }

    @Test
    void testBlockRangeIPAExtensions() {
        Character.UnicodeBlock block
                = Character.UnicodeBlock.IPA_EXTENSIONS;
        Range expected = new Range(0x0250, 0x02AF);
        Range actual = TextCalculator.blockRange(block);
        assertEquals(expected, actual);
    }

    @Test
    void testBlockRangeSpacingModifiers() {
        Character.UnicodeBlock block
                = Character.UnicodeBlock.SPACING_MODIFIER_LETTERS;
        Range expected = new Range(0x02B0, 0x02FF);
        Range actual = TextCalculator.blockRange(block);
        assertEquals(expected, actual);
    }

    @Test
    void testBlockRangeCombiningDiacritics() {
        Character.UnicodeBlock block
                = Character.UnicodeBlock.COMBINING_DIACRITICAL_MARKS;
        Range expected = new Range(0x0300, 0x036F);
        Range actual = TextCalculator.blockRange(block);
        assertEquals(expected, actual);
    }

    @Test
    void testBlockRangeGreek() {
        Character.UnicodeBlock block = Character.UnicodeBlock.GREEK;
        Range expected = new Range(0x0370, 0x03FF);
        Range actual = TextCalculator.blockRange(block);
        assertEquals(expected, actual);
    }

    @Test
    void testBlockRangeCyrillic() {
        Character.UnicodeBlock block = Character.UnicodeBlock.CYRILLIC;
        Range expected = new Range(0x0400, 0x04FF);
        Range actual = TextCalculator.blockRange(block);
        assertEquals(expected, actual);
    }

    @Test
    void testBlockRangeCyrillicSupplement() {
        Character.UnicodeBlock block
                = Character.UnicodeBlock.CYRILLIC_SUPPLEMENTARY;
        Range expected = new Range(0x0500, 0x052F);
        Range actual = TextCalculator.blockRange(block);
        assertEquals(expected, actual);
    }

    @Test
    void testBlockRangeArmenian() {
        Character.UnicodeBlock block = Character.UnicodeBlock.ARMENIAN;
        Range expected = new Range(0x0531, 0x058F);
        Range actual = TextCalculator.blockRange(block);
        assertEquals(expected, actual);
    }

    @Test
    void testBlockRangeHebrew() {
        Character.UnicodeBlock block = Character.UnicodeBlock.HEBREW;
        Range expected = new Range(0x0591, 0x05F4);
        Range actual = TextCalculator.blockRange(block);
        assertEquals(expected, actual);
    }

    @Test
    void testBlockRangeArabic() {
        Character.UnicodeBlock block = Character.UnicodeBlock.ARABIC;
        Range expected = new Range(0x0600, 0x06FF);
        Range actual = TextCalculator.blockRange(block);
        assertEquals(expected, actual);
    }

    @Test
    void testBlockRangeSyriac() {
        Character.UnicodeBlock block = Character.UnicodeBlock.SYRIAC;
        Range expected = new Range(0x0700, 0x074F);
        Range actual = TextCalculator.blockRange(block);
        assertEquals(expected, actual);
    }

    @Test
    void testBlockRangeArabicSupplement() {
        Character.UnicodeBlock block = Character.UnicodeBlock.ARABIC_SUPPLEMENT;
        Range expected = new Range(0x0750, 0x077F);
        Range actual = TextCalculator.blockRange(block);
        assertEquals(expected, actual);
    }

    @Test
    void testBlockRangeThaana() {
        Character.UnicodeBlock block = Character.UnicodeBlock.THAANA;
        Range expected = new Range(0x0780, 0x07B1);
        Range actual = TextCalculator.blockRange(block);
        assertEquals(expected, actual);
    }

    @Test
    void testBlockRangeNKo() {
        Character.UnicodeBlock block = Character.UnicodeBlock.NKO;
        Range expected = new Range(0x07C0, 0x07FF);
        Range actual = TextCalculator.blockRange(block);
        assertEquals(expected, actual);
    }

    @Test
    void testBlockRangeSamaritan() {
        Character.UnicodeBlock block = Character.UnicodeBlock.SAMARITAN;
        Range expected = new Range(0x0800, 0x083E);
        Range actual = TextCalculator.blockRange(block);
        assertEquals(expected, actual);
    }

    @Test
    void testBlockRangeMandaic() {
        Character.UnicodeBlock block = Character.UnicodeBlock.MANDAIC;
        Range expected = new Range(0x0840, 0x085E);
        Range actual = TextCalculator.blockRange(block);
        assertEquals(expected, actual);
    }

    @Test
    void testBlockRangeSyriacSupplement() {
        Character.UnicodeBlock block = Character.UnicodeBlock.SYRIAC_SUPPLEMENT;
        Range expected = new Range(0x0860, 0x086A);
        Range actual = TextCalculator.blockRange(block);
        assertEquals(expected, actual);
    }

    @Test
    void testBlockRangeArabicExtendedB() {
        Character.UnicodeBlock block = Character.UnicodeBlock.ARABIC_EXTENDED_B;
        Range expected = new Range(0x0870, 0x089F);
        Range actual = TextCalculator.blockRange(block);
        assertEquals(expected, actual);
    }

    @Test
    void testBlockRangeArabicExtendedA() {
        Character.UnicodeBlock block = Character.UnicodeBlock.ARABIC_EXTENDED_A;
        Range expected = new Range(0x08A0, 0x08FF);
        Range actual = TextCalculator.blockRange(block);
        assertEquals(expected, actual);
    }

    @Test
    void testBlockRangeDevanagari() {
        Character.UnicodeBlock block = Character.UnicodeBlock.DEVANAGARI;
        Range expected = new Range(0x0900, 0x097F);
        Range actual = TextCalculator.blockRange(block);
        assertEquals(expected, actual);
    }

    @Test
    void testBlockRangeBengali() {
        Character.UnicodeBlock block = Character.UnicodeBlock.BENGALI;
        Range expected = new Range(0x0980, 0x09FE);
        Range actual = TextCalculator.blockRange(block);
        assertEquals(expected, actual);
    }

    @Test
    void testBlockRangeGurmukhi() {
        Character.UnicodeBlock block = Character.UnicodeBlock.GURMUKHI;
        Range expected = new Range(0x0A01, 0x0A76);
        Range actual = TextCalculator.blockRange(block);
        assertEquals(expected, actual);
    }

    @Test
    void testBlockRangeGujarati() {
        Character.UnicodeBlock block = Character.UnicodeBlock.GUJARATI;
        Range expected = new Range(0x0A81, 0x0AFF);
        Range actual = TextCalculator.blockRange(block);
        assertEquals(expected, actual);
    }

    @Test
    void testBlockRangeOriya() {
        Character.UnicodeBlock block = Character.UnicodeBlock.ORIYA;
        Range expected = new Range(0x0B01, 0x0B77);
        Range actual = TextCalculator.blockRange(block);
        assertEquals(expected, actual);
    }

    @Test
    void testBlockRangeTamil() {
        Character.UnicodeBlock block = Character.UnicodeBlock.TAMIL;
        Range expected = new Range(0x0B82, 0x0BFA);
        Range actual = TextCalculator.blockRange(block);
        assertEquals(expected, actual);
    }

    @Test
    void testBlockRangeTelugu() {
        Character.UnicodeBlock block = Character.UnicodeBlock.TELUGU;
        Range expected = new Range(0x0C00, 0x0C7F);
        Range actual = TextCalculator.blockRange(block);
        assertEquals(expected, actual);
    }

    @Test
    void testBlockRangeKannada() {
        Character.UnicodeBlock block = Character.UnicodeBlock.KANNADA;
        Range expected = new Range(0x0C80, 0x0CF3);
        Range actual = TextCalculator.blockRange(block);
        assertEquals(expected, actual);
    }

    @Test
    void testBlockRangeMalayalam() {
        Character.UnicodeBlock block = Character.UnicodeBlock.MALAYALAM;
        Range expected = new Range(0x0D00, 0x0D7F);
        Range actual = TextCalculator.blockRange(block);
        assertEquals(expected, actual);
    }

    @Test
    void testBlockRangeSinhala() {
        Character.UnicodeBlock block = Character.UnicodeBlock.SINHALA;
        Range expected = new Range(0x0D81, 0x0DF4);
        Range actual = TextCalculator.blockRange(block);
        assertEquals(expected, actual);
    }

    @Test
    void testBlockRangeThai() {
        Character.UnicodeBlock block = Character.UnicodeBlock.THAI;
        Range expected = new Range(0x0E01, 0x0E5B);
        Range actual = TextCalculator.blockRange(block);
        assertEquals(expected, actual);
    }

    @Test
    void testBlockRangeLao() {
        Character.UnicodeBlock block = Character.UnicodeBlock.LAO;
        Range expected = new Range(0x0E81, 0x0EDF);
        Range actual = TextCalculator.blockRange(block);
        assertEquals(expected, actual);
    }

    @Test
    void testBlockRangeTibetan() {
        Character.UnicodeBlock block = Character.UnicodeBlock.TIBETAN;
        Range expected = new Range(0x0F00, 0x0FDA);
        Range actual = TextCalculator.blockRange(block);
        assertEquals(expected, actual);
    }

    @Test
    void testBlockRangeMyanmar() {
        Character.UnicodeBlock block = Character.UnicodeBlock.MYANMAR;
        Range expected = new Range(0x1000, 0x109F);
        Range actual = TextCalculator.blockRange(block);
        assertEquals(expected, actual);
    }

    @Test
    void testBlockRangeGeorgian() {
        Character.UnicodeBlock block = Character.UnicodeBlock.MYANMAR;
        Range expected = new Range(0x10A0, 0x10FF);
        Range actual = TextCalculator.blockRange(block);
        assertEquals(expected, actual);
    }

    @Test
    void testIsHighSurrogate() {
        System.out.println("isHighSurrogate");
        int span = 0xDC00 - 0xD800;
        char ch = (char) (0xD800 + ExtendedRandom.nextInt(span));
        String msg = "Character " + Character.getName(ch)
                + " should be found to be a high surrogate";
        assert TextCalculator.isHighSurrogate(ch) : msg;
    }

    @Test
    void testIsNotHighSurrogatePrecede0xD800() {
        char ch = (char) (ExtendedRandom.nextInt(0xD800));
        String msg = "Character " + Character.getName(ch)
                + " should not be found to be a high surrogate";
        assert !TextCalculator.isHighSurrogate(ch) : msg;
    }

    @Test
    void testLowSurrogateIsNotHighSurrogate() {
        int span = 0xE000 - 0xDC00;
        char ch = (char) (0xDC00 + ExtendedRandom.nextInt(span));
        String msg = "Character " + Character.getName(ch)
                + " should not be found to be a high surrogate";
        assert !TextCalculator.isHighSurrogate(ch) : msg;
    }

    @Test
    void testIsNotHighSurrogateFollow0xDFFF() {
        int span = 0x10000 - 0xE000;
        char ch = (char) (0xE000 + ExtendedRandom.nextInt(span));
        String msg = "Character " + Character.getName(ch)
                + " should not be found to be a high surrogate";
        assert !TextCalculator.isHighSurrogate(ch) : msg;
    }

    @Test
    void testisLowSurrogate() {
        System.out.println("isLowSurrogate");
        int span = 0xE000 - 0xDC00;
        char ch = (char) (0xDC00 + ExtendedRandom.nextInt(span));
        String msg = "Character " + Character.getName(ch)
                + " should be found to be a low surrogate";
        assert TextCalculator.isLowSurrogate(ch) : msg;
    }

    @Test
    void testIsNotLowSurrogatePrecede0xD800() {
        char ch = (char) (ExtendedRandom.nextInt(0xD800));
        String msg = "Character " + Character.getName(ch)
                + " should not be found to be a low surrogate";
        assert !TextCalculator.isLowSurrogate(ch) : msg;
    }

    @Test
    void testHighSurrogateIsNotLowSurrogate() {
        int span = 0xDC00 - 0xD800;
        char ch = (char) (0xD800 + ExtendedRandom.nextInt(span));
        String msg = "Character " + Character.getName(ch)
                + " should not be found to be a low surrogate";
        assert !TextCalculator.isLowSurrogate(ch) : msg;
    }

    @Test
    void testIsNotLowSurrogateFollow0xDFFF() {
        int span = 0x10000 - 0xE000;
        char ch = (char) (0xE000 + ExtendedRandom.nextInt(span));
        String msg = "Character " + Character.getName(ch)
                + " should not be found to be a low surrogate";
        assert !TextCalculator.isLowSurrogate(ch) : msg;
    }

    @Test
    void testIsAllASCIIRejectsNullString() {
        String message = "Null String should cause NPE";
        Throwable t = assertThrows(NullPointerException.class, () -> {
            @SuppressWarnings("ConstantConditions")
            boolean result = TextCalculator.isAllASCII(null);
            System.out.println(message + ", not given result " + result);
        }, message);
        String excMsg = t.getMessage();
        assert excMsg != null : "Exception message should not be null";
        assert !excMsg.isBlank() : "Exception message should not be blank";
        System.out.println(message);
        System.out.println("\"" + excMsg + "\"");
    }

    @Test
    void testEmptyStringIsAllASCII() {
        String message = "Empty String should be considered all ASCII";
        assert TextCalculator.isAllASCII("") : message;
    }

    @Test
    void testIsAllASCII() {
        System.out.println("isAllASCII");
        int len = ExtendedRandom.nextInt(16) + 4;
        char[] chars = new char[len];
        for (int i = 0; i < len; i++) {
            chars[i] = (char) ExtendedRandom.nextInt(128);
        }
        String example = new String(chars);
        String msg = "String \"" + example
                + "\" should be found to be all ASCII";
        assert TextCalculator.isAllASCII(example) : msg;
    }

    private char choosePostASCIIChar() {
        int index = ExtendedRandom.nextInt(POST_ASCII_TEST_CHAR_BOUND) + 128;
        while (!Character.isDefined(index)) {
            index++;
        }
        return (char) index;
    }

    @Test
    void testIsNotAllASCII() {
        int len = ExtendedRandom.nextInt(16) + 4;
        char[] chars = new char[len];
        for (int i = 0; i < len; i++) {
            chars[i] = (char) ExtendedRandom.nextInt(128);
        }
        char changer = choosePostASCIIChar();
        int changeIndex = ExtendedRandom.nextInt(len);
        chars[changeIndex] = changer;
        String example = new String(chars);
        String msg = "String \"" + example + "\", which includes character "
                + Character.getName(changer)
                + " should not be found to be all ASCII";
        assert !TextCalculator.isAllASCII(example) : msg;
    }

    @Test
    void testIsOutsideBMPRejectsNullString() {
        String message = "Null String should cause NPE";
        Throwable t = assertThrows(NullPointerException.class, () -> {
            @SuppressWarnings("ConstantConditions")
            boolean result = TextCalculator.isOutsideBMP(null);
            System.out.println(message + ", not given result " + result);
        }, message);
        String excMsg = t.getMessage();
        assert excMsg != null : "Exception message should not be null";
        assert !excMsg.isBlank() : "Exception message should not be blank";
        System.out.println(message);
        System.out.println("\"" + excMsg + "\"");
    }

    @Test
    void testIsNotOutsideBMP() {
        int len = ExtendedRandom.nextInt(16) + 4;
        String s = ExtendedRandom.alphanumeric(len);
        String msg = "\"" + s + "\" should not be considered outside BMP";
        assert !TextCalculator.isOutsideBMP(s) : msg;
    }

    @Test
    void testEmptyStringIsNotOutsideBMP() {
        String msg = "Empty String should not be considered outside BMP";
        assert !TextCalculator.isOutsideBMP("") : msg;
    }

    private static int chooseSMPChar() {
        int propChar;
        do {
            propChar = 65536 + ExtendedRandom.nextInt(0x7B);
        } while (!Character.isDefined(propChar));
        return propChar;
    }

    @Test
    void testIsOutsideBMP() {
        System.out.println("isOutsideBMP");
        int bound = 5;
        int prefixLength = ExtendedRandom.nextInt(bound);
        String prefix = (prefixLength == 0) ? ""
                : ExtendedRandom.alphanumeric(prefixLength);
        int codePoint = chooseSMPChar();
        char[] surrogates = {Character.highSurrogate(codePoint),
                Character.lowSurrogate(codePoint)};
        String nonSMPChar = new String(surrogates);
        int suffixLength = ExtendedRandom.nextInt(bound);
        String suffix = (suffixLength == 0) ? ""
                : ExtendedRandom.alphanumeric(suffixLength);
        String s = prefix + nonSMPChar + suffix;
        String msg = "\"" + s
                + "\" should be found to be outside BMP on account of '"
                + nonSMPChar + "', " + Character.getName(codePoint);
        assert TextCalculator.isOutsideBMP(s) : msg;
    }

    @Test
    void testPadWithSpacesLeftRejectsNegativeLength() {
        int badLength = ExtendedRandom.nextInt() | Integer.MIN_VALUE;
        String message = "padWithSpacesLeft() should reject length "
                + badLength;
        Throwable t = assertThrows(IllegalArgumentException.class, () -> {
            String badResult = TextCalculator.padWithSpacesLeft(message,
                    badLength);
            System.out.println(message + ", not given result " + badResult);
        }, message);
        String excMsg = t.getMessage();
        assert excMsg != null : "Exception message should not be null";
        assert !excMsg.isBlank() : "Exception message should not be blank";
        String numStr = Integer.toString(badLength);
        String containsMsg = "Exception message should contain \"" + numStr
                + "\"";
        assert excMsg.contains(numStr) : containsMsg;
    }

    @Test
    void testPadWithSpacesLeftDoesNotChangeLongerString() {
        int strLen = ExtendedRandom.nextInt(16) + 4;
        String expected = ExtendedRandom.alphanumeric(strLen);
        int length = ExtendedRandom.nextInt(strLen);
        String actual = TextCalculator.padWithSpacesLeft(expected, length);
        String message = "Trying to pad String of " + strLen
                + " characters to length " + length + " should not change it";
        assertEquals(expected, actual, message);
    }

    @Test
    void testPadWithSpacesLeftDoesNotChangeEqualLengthString() {
        int length = ExtendedRandom.nextInt(16) + 4;
        String expected = ExtendedRandom.alphanumeric(length);
        String actual = TextCalculator.padWithSpacesLeft(expected, length);
        String message = "Trying to pad String of " + length
                + " characters to length " + length + " should not change it";
        assertEquals(expected, actual, message);
    }

    @Test
    void testPadWithSpacesRightRejectsNegativeLength() {
        int badLength = ExtendedRandom.nextInt() | Integer.MIN_VALUE;
        String message = "padWithSpacesRight() should reject length "
                + badLength;
        Throwable t = assertThrows(IllegalArgumentException.class, () -> {
            String badResult = TextCalculator.padWithSpacesRight(message,
                    badLength);
            System.out.println(message + ", not given result " + badResult);
        }, message);
        String excMsg = t.getMessage();
        assert excMsg != null : "Exception message should not be null";
        assert !excMsg.isBlank() : "Exception message should not be blank";
        String numStr = Integer.toString(badLength);
        String containsMsg = "Exception message should contain \"" + numStr
                + "\"";
        assert excMsg.contains(numStr) : containsMsg;
    }

    @Test
    void testPadWithSpacesRightDoesNotChangeLongerString() {
        int strLen = ExtendedRandom.nextInt(16) + 4;
        String expected = ExtendedRandom.alphanumeric(strLen);
        int length = ExtendedRandom.nextInt(strLen);
        String actual = TextCalculator.padWithSpacesRight(expected, length);
        String message = "Trying to pad String of " + strLen
                + " characters to length " + length + " should not change it";
        assertEquals(expected, actual, message);
    }

    @Test
    void testPadWithSpacesRightDoesNotChangeEqualLengthString() {
        int length = ExtendedRandom.nextInt(16) + 4;
        String expected = ExtendedRandom.alphanumeric(length);
        String actual = TextCalculator.padWithSpacesRight(expected, length);
        String message = "Trying to pad String of " + length
                + " characters to length " + length + " should not change it";
        assertEquals(expected, actual, message);
    }

    @Test
    void testPadLeftRejectsNullString() {
        int length = ExtendedRandom.nextInt(16) + 4;
        Character.UnicodeBlock block = ExtendedRandom.chooseBMPBlock();
        char ch = ExtendedRandom.chooseCharacterFromBlock(block);
        String message = "Trying to pad null String with '" + ch
                + "' to length " + length + " should cause NPE";
        Throwable t = assertThrows(NullPointerException.class, () -> {
            String badResult = TextCalculator.padLeft(null, length, ch);
            System.out.println(message + " not given result \"" + badResult
                    + "\"");
        }, message);
        String excMsg = t.getMessage();
        assert excMsg != null : "Exception message should not be null";
        assert !excMsg.isBlank() : "Exception message should not be blank";
    }

    @Test
    void testPadLeftRejectsNegativeLength() {
        int badLength = ExtendedRandom.nextInt() | Integer.MIN_VALUE;
        Character.UnicodeBlock block = ExtendedRandom.chooseBMPBlock();
        char c = ExtendedRandom.chooseCharacterFromBlock(block);
        String message = "padLeft() should reject length " + badLength;
        Throwable t = assertThrows(IllegalArgumentException.class, () -> {
            String badResult = TextCalculator.padLeft(message, badLength, c);
            System.out.println(message + ", not given result " + badResult);
        }, message);
        String excMsg = t.getMessage();
        assert excMsg != null : "Exception message should not be null";
        assert !excMsg.isBlank() : "Exception message should not be blank";
        String numStr = Integer.toString(badLength);
        String containsMsg = "Exception message should contain \"" + numStr
                + "\"";
        assert excMsg.contains(numStr) : containsMsg;
    }

    @Test
    void testPadLeftDoesNotChangeLongerString() {
        int strLen = ExtendedRandom.nextInt(16) + 4;
        String expected = ExtendedRandom.alphanumeric(strLen);
        int length = ExtendedRandom.nextInt(strLen);
        Character.UnicodeBlock block = ExtendedRandom.chooseBMPBlock();
        char c = ExtendedRandom.chooseCharacterFromBlock(block);
        String actual = TextCalculator.padLeft(expected, length, c);
        String message = "Trying to pad String of " + strLen
                + " characters to length " + length + " should not change it";
        assertEquals(expected, actual, message);
    }

    @Test
    void testPadLeftDoesNotChangeEqualLengthString() {
        int length = ExtendedRandom.nextInt(16) + 4;
        Character.UnicodeBlock block = ExtendedRandom.chooseBMPBlock();
        char c = ExtendedRandom.chooseCharacterFromBlock(block);
        String expected = ExtendedRandom.alphanumeric(length);
        String actual = TextCalculator.padLeft(expected, length, c);
        String message = "Trying to pad String of " + length
                + " characters to length " + length + " should not change it";
        assertEquals(expected, actual, message);
    }

    @Test
    void testPadLeft() {
        System.out.println("padLeft");
        int originalLength = ExtendedRandom.nextInt(16) + 4;
        int paddingLength = ExtendedRandom.nextInt(9) + 1;
        int length = originalLength + paddingLength;
        String s = ExtendedRandom.alphanumeric(originalLength);
        Character.UnicodeBlock block = ExtendedRandom.chooseBMPBlock();
        char ch = ExtendedRandom.chooseCharacterFromBlock(block);
        char[] paddingChars = new char[paddingLength];
        Arrays.fill(paddingChars, ch);
        String padding = new String(paddingChars);
        String expected = padding + s;
        String actual = TextCalculator.padLeft(s, length, ch);
        String message = "Padding \"" + s + "\" to length " + length + " with '"
                + ch + "'";
        assertEquals(expected, actual, message);
    }

    @Test
    void testPadRightRejectsNegativeLength() {
        int badLength = ExtendedRandom.nextInt() | Integer.MIN_VALUE;
        Character.UnicodeBlock block = ExtendedRandom.chooseBMPBlock();
        char c = ExtendedRandom.chooseCharacterFromBlock(block);
        String message = "padRight() should reject length " + badLength;
        Throwable t = assertThrows(IllegalArgumentException.class, () -> {
            String badResult = TextCalculator.padRight(message, badLength, c);
            System.out.println(message + ", not given result " + badResult);
        }, message);
        String excMsg = t.getMessage();
        assert excMsg != null : "Exception message should not be null";
        assert !excMsg.isBlank() : "Exception message should not be blank";
        String numStr = Integer.toString(badLength);
        String containsMsg = "Exception message should contain \"" + numStr
                + "\"";
        assert excMsg.contains(numStr) : containsMsg;
    }

    @Test
    void testPadRightDoesNotChangeLongerString() {
        int strLen = ExtendedRandom.nextInt(16) + 4;
        String expected = ExtendedRandom.alphanumeric(strLen);
        int length = ExtendedRandom.nextInt(strLen);
        Character.UnicodeBlock block = ExtendedRandom.chooseBMPBlock();
        char c = ExtendedRandom.chooseCharacterFromBlock(block);
        String actual = TextCalculator.padRight(expected, length, c);
        String message = "Trying to pad String of " + strLen
                + " characters to length " + length + " should not change it";
        assertEquals(expected, actual, message);
    }

    @Test
    void testPadRightDoesNotChangeEqualLengthString() {
        int length = ExtendedRandom.nextInt(16) + 4;
        Character.UnicodeBlock block = ExtendedRandom.chooseBMPBlock();
        char c = ExtendedRandom.chooseCharacterFromBlock(block);
        String expected = ExtendedRandom.alphanumeric(length);
        String actual = TextCalculator.padRight(expected, length, c);
        String message = "Trying to pad String of " + length
                + " characters to length " + length + " should not change it";
        assertEquals(expected, actual, message);
    }

}
