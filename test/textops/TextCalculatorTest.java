package textops;

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

}
