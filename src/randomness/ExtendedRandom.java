package randomness;

import collections.immutable.Range;
import fractions.Fraction;

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

    // TODO: Write tests for this
    public static int nextInt(Range range) {
        return 0;
    }

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

    // TODO: Write tests for this
    public Character.UnicodeBlock chooseBMPBlock() {
        return Character.UnicodeBlock.AEGEAN_NUMBERS;
    }

    // TODO: Write tests for this
    public Character.UnicodeBlock
    chooseBMPBlockOtherThan(Character.UnicodeBlock block) {
        return block;
    }

    // TODO: Write tests for this
    public char chooseCharacterFromBlock(Character.UnicodeBlock block) {
        return '?';
    }

    // TODO: Write tests for this
    public String makeStringWithBlockCharacters(Character.UnicodeBlock block) {
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
