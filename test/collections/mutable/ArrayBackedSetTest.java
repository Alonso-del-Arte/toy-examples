package collections.mutable;

import java.math.BigInteger;
import java.sql.Clob;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Random;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import randomness.ExtendedRandom;

class ArrayBackedSetTest {

    private static final Random RANDOM = new Random();

    @Test
    void testAdd() {
        System.out.println("add");
        ArrayBackedSet<LocalDateTime> set = new ArrayBackedSet<>();
        LocalDateTime dateTime = LocalDateTime.now();
        String msg = "Should be able to add " + dateTime + " to set of times";
        boolean opResult = set.add(dateTime);
        assert opResult : msg;
    }

    @Test
    void testAddRejectsNull() {
        ArrayBackedSet<Clob> set = new ArrayBackedSet<>();
        String msg = "Should not be able to add null to a set";
        boolean opResult = set.add(null);
        assert !opResult : msg;
    }

    @Test
    void testContains() {
        System.out.println("contains");
        ArrayBackedSet<LocalDateTime> set = new ArrayBackedSet<>();
        int size = ExtendedRandom.nextInt(13) + 2;
        LocalDateTime dateTime = LocalDateTime.now();
        LocalDateTime[] dateTimes = new LocalDateTime[size];
        for (int i = 0; i < size; i++) {
            dateTime = dateTime.minusDays(i);
            dateTimes[i] = dateTime;
            set.add(dateTime);
        }
        for (LocalDateTime element : dateTimes) {
            String msg = "Set should contain " + element.toString();
            assert set.contains(element) : msg;
        }
    }

    @Test
    void testDoesNotContain() {
        ArrayBackedSet<LocalDateTime> set = new ArrayBackedSet<>();
        int size = ExtendedRandom.nextInt(13) + 2;
        LocalDateTime dateTime = LocalDateTime.now();
        for (int i = 0; i < size; i++) {
            dateTime = dateTime.plusDays(i);
            set.add(dateTime);
        }
        String msg = "Set of upcoming times should not contain "
                + LocalDateTime.MIN;
        assert !set.contains(LocalDateTime.MIN) : msg;
    }

    @Test
    void testSize() {
        System.out.println("size");
        int bound = 24;
        ArrayBackedSet<LocalDateTime> set = new ArrayBackedSet<>(bound);
        int expected = ExtendedRandom.nextInt(bound);
        LocalDateTime dateTime = LocalDateTime.now();
        for (int i = 0; i < expected; i++) {
            dateTime = dateTime.plusHours(i);
            set.add(dateTime);
        }
        String msg = "Array-backed set should have " + expected + " elements";
        int actual = set.size();
        assertEquals(expected, actual, msg);
    }

    @Test
    void testNoDuplicateElements() {
        ArrayBackedSet<String> set = new ArrayBackedSet<>();
        String msg = "Should not be able to add same element twice";
        set.add(msg);
        int expected = set.size();
        boolean reAddFlag = set.add(msg);
        assert !reAddFlag : msg;
        int actual = set.size();
        assertEquals(expected, actual);
    }

    @Test
    void testCapacityExpandsAsNeeded() {
        int size = ExtendedRandom.nextInt(20) + 25;
        ArrayBackedSet<LocalDateTime> set = new ArrayBackedSet<>(size);
        LocalDateTime dateTime = LocalDateTime.now();
        for (int i = 0; i < size; i++) {
            dateTime = dateTime.plusMinutes(i);
            set.add(dateTime);
        }
        try {
            set.add(LocalDateTime.MAX);
            System.out.println("Set successfully expanded capacity to hold "
                    + set.size() + " elements");
        } catch (ArrayIndexOutOfBoundsException aioobe) {
            String msg = "Set failed to expand to add after first " + size
                    + " elements";
            System.out.println(msg);
            System.out.println("\"" + aioobe.getMessage() + "\"");
            fail(msg);
        }
    }

    @Test
    void testNoRemovalIfNotInSet() {
        ArrayBackedSet<LocalDateTime> set = new ArrayBackedSet<>();
        int expected = 0;
        LocalDateTime element = LocalDateTime.now();
        String msg = "Should not be able to remove " + element
                + " from set since it wasn't added in the first place";
        boolean opResult = set.remove(element);
        int actual = set.size();
        assert !opResult : msg;
        assertEquals(expected, actual);
    }

    @Test
    void testRemove() {
        System.out.println("remove");
        int originalSize = 20;
        ArrayBackedSet<String> set = new ArrayBackedSet<>(originalSize);
        int placementIndex = ExtendedRandom.nextInt(20);
        String element = "Element to be removed";
        for (int i = 0; i < originalSize; i++) {
            if (i == placementIndex) {
                set.add(element);
            } else {
                set.add(Integer.toString(ExtendedRandom.nextInt()));
            }
        }
        boolean opResult = set.remove(element);
        String msg = "Should have been able to remove \"" + element
                + "\" from set";
        assert opResult : msg;
        msg = "Set should no longer contain removed element";
        assert !set.contains(element) : msg;
        int expected = originalSize - 1;
        int actual = set.size();
        assertEquals(expected, actual);
    }

    @Test
    void testIsEmpty() {
        System.out.println("isEmpty");
        ArrayBackedSet<Clob> set = new ArrayBackedSet<>();
        String msg = "Newly created set should be empty";
        assert set.isEmpty() : msg;
    }

    @Test
    void testIsNotEmpty() {
        int expected = ExtendedRandom.nextInt(16) + 20;
        ArrayBackedSet<Integer> set = new ArrayBackedSet<>(expected);
        int start = ExtendedRandom.nextInt();
        for (int i = 0; i < expected; i++) {
            set.add(start + i);
        }
        int actual = set.size();
        assertEquals(expected, actual);
        String msg = "Set with " + actual
                + " elements should not be considered empty";
        assert !set.isEmpty() : msg;
    }

    @Test
    void testClear() {
        System.out.println("clear");
        int size = ExtendedRandom.nextInt(100) + 20;
        ArrayBackedSet<LocalDateTime> set = new ArrayBackedSet<>(size);
        for (int i = 0; i < size; i++) {
            set.add(LocalDateTime.now());
        }
        String msg = "Set should not be empty";
        assert set.size() > 0 : msg;
        assert !set.isEmpty() : msg;
        set.clear();
        assertEquals(0, set.size());
        assert set.isEmpty() : "Set should be empty now";
    }

    @Test
    void testReferentialEquality() {
        ArrayBackedSet<Clob> someSet = new ArrayBackedSet<>();
        assertEquals(someSet, someSet);
    }

    @Test
    void testNotEqualsNull() {
        ArrayBackedSet<Clob> set = new ArrayBackedSet<>();
        assertNotEquals(set, null);
    }

    @Test
    void testNotEqualsDiffClass() {
        ArrayBackedSet<LocalDateTime> arrayBackedSet = new ArrayBackedSet<>();
        HashSet<LocalDateTime> hashSet = new HashSet<>();
        assertNotEquals(arrayBackedSet, hashSet);
    }

    @Test
    void testNotEqualsDiffSize() {
        ArrayBackedSet<LocalDateTime> someSet = new ArrayBackedSet<>();
        ArrayBackedSet<LocalDateTime> diffSet = new ArrayBackedSet<>();
        LocalDateTime now = LocalDateTime.now();
        someSet.add(now);
        diffSet.add(now);
        diffSet.add(LocalDateTime.MIN);
        diffSet.add(LocalDateTime.MAX);
        assertNotEquals(someSet.size(), diffSet.size());
        assertNotEquals(someSet, diffSet);
    }

    @Test
    void testNotEqualsDiffElems() {
        int size = ExtendedRandom.nextInt(20) + 16;
        ArrayBackedSet<Integer> someSet = new ArrayBackedSet<>(size);
        ArrayBackedSet<Integer> diffSet = new ArrayBackedSet<>(size);
        for (int i = 0; i < size; i++) {
            someSet.add(i);
            diffSet.add(-i);
        }
        assertNotEquals(someSet, diffSet);
    }

    @Test
    void testEquals() {
        System.out.println("equals");
        ArrayBackedSet<Character> alphabetSet = new ArrayBackedSet<>();
        ArrayBackedSet<Character> pangramSet = new ArrayBackedSet<>();
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        for (char letter : alphabet) {
            alphabetSet.add(letter);
        }
        char[] pangram = "The quick brown fox jumps over the lazy dog"
                .replace(" ", "").toLowerCase().toCharArray();
        for (char gram : pangram) {
            pangramSet.add(gram);
        }
        assertEquals(alphabetSet, pangramSet);
    }

    @Test
    void testHashCode() {
        System.out.println("hashCode");
        HashSet<Integer> hashes = new HashSet<>();
        int expected = ExtendedRandom.nextInt(24);
        LocalDateTime dateTime;
        ArrayBackedSet<LocalDateTime> set = new ArrayBackedSet<>();
        for (int i = 0; i < expected; i++) {
            hashes.add(set.hashCode());
            dateTime = LocalDateTime.now().plusHours(i);
            set.add(dateTime);
        }
        int actual = hashes.size();
        assertEquals(expected, actual);
    }

    @Test
    void testHashCodeUnaffectedByElementAddOrder() {
        String first = "First Element";
        String second = "Second Element";
        String third = "Third Element";
        ArrayBackedSet<String> someSet = new ArrayBackedSet<>();
        someSet.add(first);
        someSet.add(second);
        someSet.add(third);
        ArrayBackedSet<String> sameSet = new ArrayBackedSet<>();
        sameSet.add(third);
        sameSet.add(first);
        sameSet.add(second);
        int expected = someSet.hashCode();
        int actual = sameSet.hashCode();
        assertEquals(expected, actual);
    }

    @Test
    void testIterator() {
        System.out.println("iterator");
        int size = ExtendedRandom.nextInt(48) + 16;
        ArrayBackedSet<BigInteger> expected = new ArrayBackedSet<>(size);
        BigInteger number;
        for (int i = 0; i < size; i++) {
            number = new BigInteger(i + 32, RANDOM);
            expected.add(number);
        }
        ArrayBackedSet<BigInteger> actual = new ArrayBackedSet<>(size);
        for (BigInteger n : expected) {
            actual.add(n);
        }
        assertEquals(expected, actual);
    }

    @Test
    void testConstructorRejectsNegativeCapacity() {
        int badSize = -ExtendedRandom.nextInt(128) - 1;
        Throwable t = assertThrows(IllegalArgumentException.class, () -> {
            ArrayBackedSet<LocalDateTime> badSet
                    = new ArrayBackedSet<>(badSize);
            System.out.println("Should not have been able to create " + badSet
                    + " with initial capacity " + badSize);
        });
        String excMsg = t.getMessage();
        assert excMsg != null : "Exception message should not be null";
        System.out.println("\"" + excMsg + "\"");
    }

}
