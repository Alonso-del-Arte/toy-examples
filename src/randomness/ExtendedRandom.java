package randomness;

import fractions.Fraction;

import static java.lang.Character.UnicodeBlock;
import static java.lang.Character.UnicodeBlock.*;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Set;

import numerics.ComplexNumber;

public class ExtendedRandom {

    private static final Random RANDOM = new Random();

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

    /**
     * Gives a pseudorandomly chosen integer. May be positive or negative, or
     * it could even be 0. This function is essentially a static wrapper for
     * <code>java.util.Random</code>'s <code>nextInt()</code>.
     * @return A pseudorandomly chosen integer, at least
     * <code>Integer.MIN_VALUE</code>, at most <code>Integer.MAX_VALUE</code>.
     * For example, &minus;2038868420.
     */
    public static int nextInt() {
        return RANDOM.nextInt();
    }

    /**
     * Gives a pseudorandomly chosen integer as specified by the bound. This
     * function is essentially a static wrapper for
     * <code>java.util.Random</code>'s one-parameter <code>nextInt()</code>.
     * @param bound A lower or upper bound for the pseudorandomly chosen
     *              integer. The other bound is 0. The farther away from 0 that
     *              this parameter is, the likelier it is for repeated calls to
     *              give a decent distribution of values. Examples: 1024,
     *              &minus;1024.
     * @return A pseudorandomly chosen integer, may be 0 or any integer between
     * 0 and <code>bound</code>. For example, given a bound of 1024, this
     * function might return 743. Given a bound of &minus;1024, this function
     * might return &minus;692.
     */
    public static int nextInt(int bound) {
        int signAdjust = bound < 0 ? -1 : 1;
        bound /= signAdjust;
        return RANDOM.nextInt(bound) * signAdjust;
    }

//    public static int nextInt(Range range) {
//        return 0;
//    }

    /**
     * Gives a pseudorandomly chosen <code>BigInteger</code> instance
     * corresponding to 0 or a positive integer. This is a static wrapper for
     * one of the <code>BigInteger</code> constructors.
     * @param bitLength How many significant bits the integer should have at
     *                  most. For example, 72.
     * @return A pseudorandomly chosen <code>BigInteger</code> instance, at
     * least <code>BigInteger.ZERO</code>, at most
     * 2<sup><code>bitLength</code></sup> &minus; 1. But it's likelier to be
     * closer to the latter than to the former. For example, given a
     * <code>bitLength</code> of 72, the result might be 1617975039218359439262.
     */
    public static BigInteger nextBigInt(int bitLength) {
        return new BigInteger(bitLength, RANDOM);
    }

    // TODO: Write Javadoc
    public static double nextDouble() {
        return RANDOM.nextDouble();
    }

    /**
     * Gives a pseudorandomly chosen fraction. May be positive or negative, or
     * it could even be 0.
     * @return A fraction with a pseudorandom signed 32-bit integer for the
     * numerator and a denominator of at least 1 and at most
     * <code>Integer.MAX_VALUE</code>.
     */
    public static Fraction nextFraction() {
        int numer = RANDOM.nextInt();
        int denom = RANDOM.nextInt(Integer.MAX_VALUE) + 1;
        return new Fraction(numer, denom);
    }

    // TODO: Write tests for this
    public static ComplexNumber nextComplex() {
        return new ComplexNumber(0.0, 0.0);
    }

    public static String alphanumeric(int length) {
        if (length < 1) {
            String excMsg = "Length " + length + " is not valid";
            throw new IllegalArgumentException(excMsg);
        }
        char[] asciiChars = new char[length];
        for (int i = 0; i < length; i++) {
            char ch;
            if (RANDOM.nextBoolean()) {
                ch = (char) (RANDOM.nextInt(10) + '0');
            } else {
                if (RANDOM.nextBoolean()) {
                    ch = (char) (RANDOM.nextInt(26) + 'A');
                } else {
                    ch = (char) (RANDOM.nextInt(26) + 'a');
                }
            }
            asciiChars[i] = ch;
        }
        return new String(asciiChars);
    }

    /**
     * Chooses a Unicode block from the Basic Multilingual Plane (BMP). The
     * blocks of high and low surrogates are deliberately excluded.
     * @return A block from the BMP. For example, Bengali. Since this is a Java
     * 8 project rather than a Java 21 project, seven blocks are missing from
     * the set of possible outputs for this function, but this was not
     * intentional. Namely:
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
    public static UnicodeBlock chooseBMPBlock() {
        int index = RANDOM.nextInt(NUMBER_OF_BLOCKS);
        return UNICODE_BLOCKS[index];
    }

    /**
     * Chooses a Unicode block from the Basic Multilingual Plane (BMP) other
     * than a specified block.
     * @param block The block not to choose. For example, Basic Latin. It should
     *              not be null, but other than that there are no requirements.
     *              Though if this block is not from the BMP, it would make more
     *              sense to call {@link #chooseBMPBlock()} instad.
     * @return A block other than {@code block}. For example, Glagolitic.
     * @throws NullPointerException If {@code block} is null.
     */
    public static UnicodeBlock chooseBMPBlockOtherThan(UnicodeBlock block) {
        if (block == null) {
            String excMsg = "Block should not be null";
            throw new NullPointerException(excMsg);
        }
        UnicodeBlock propBlock = block;
        while (propBlock.equals(block)) {
            propBlock = chooseBMPBlock();
        }
        return propBlock;
    }

    // TODO: Write tests for this
    public static char chooseCharacterFromBlock(UnicodeBlock block) {
        return '?';
    }

    // TODO: Write tests for this
    public static String makeStringWithBlockCharacters(UnicodeBlock block) {
        return "SORRY, NOT IMPLEMENTED YET";
    }

    /**
     * Pseudorandomly chooses an element from an array.
     * @param array The array of elements to choose from. For example, an array
     *              of weekdays. Should not be empty.
     * @param <E> The type of the elements in the array. This will be the return
     *           type.
     * @return An element from the array. For example, Thursday.
     * @throws NoSuchElementException If <code>array</code> is empty.
     */
    public static <E> E nextObject(E[] array) {
        if (array.length == 0) {
            String excMsg = "Array " + Arrays.toString(array)
                    + " has no elements to choose from";
            throw new NoSuchElementException(excMsg);
        }
        int index = RANDOM.nextInt(array.length);
        return array[index];
    }

    /**
     * Pseudorandomly chooses an element from a list.
     * @param list The list of elements to choose from. For example, a list of
     *             Web-safe colors.
     * @param <E> The type of the elements in the list. This will be the return
     *           type.
     * @return An element from the list. For example, cyan.
     * @throws NoSuchElementException If <code>list</code> is empty.
     */
    public static <E> E nextObject(List<E> list) {
        int size = list.size();
        if (size == 0) {
            String excMsg = "List " + list + " has no elements to choose from";
            throw new NoSuchElementException(excMsg);
        }
        int index = RANDOM.nextInt(size);
        return list.get(index);
    }

    /**
     * Pseudorandomly chooses an element from a set.
     * @param set The set of elements to choose from. For example, a set of
     *            currencies.
     * @param <E> The type of the elements in the set. This will be the return
     *           type.
     * @return An element from the set. For example, Canadian dollars.
     * @throws NoSuchElementException If <code>set</code> is empty.
     */
    public static <E> E nextObject(Set<E> set) {
        int size = set.size();
        if (size == 0) {
            String excMsg = "Set " + set + " has no elements to choose from";
            throw new NoSuchElementException(excMsg);
        }
        int index = RANDOM.nextInt(size);
        Iterator<E> iterator = set.iterator();
        int curr = 0;
        while (curr < index) {
            iterator.next();
            curr++;
        }
        return iterator.next();
    }

}
