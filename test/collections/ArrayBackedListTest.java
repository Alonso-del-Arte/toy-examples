package collections;

import java.math.BigInteger;
import java.sql.CallableStatement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;
import java.util.Random;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ArrayBackedListTest {

    private static final Random RANDOM = new Random();

    @Test
    void testAdd() {
        System.out.println("add");
        ArrayBackedList<Locale> list = new ArrayBackedList<>();
        Locale locale = Locale.getDefault();
        String msg = "Should be able to add " + locale.toString()
                + " to list of locales";
        boolean opResult = list.add(locale);
        assert opResult : msg;
    }

    @Test
    void testContains() {
        System.out.println("contains");
        ArrayBackedList<String> list = new ArrayBackedList<>();
        String msg = "List should contain this message after it's added";
        list.add(msg);
        assert list.contains(msg) : msg;
    }

    @Test
    void testDoesNotContain() {
        ArrayBackedList<String> list = new ArrayBackedList<>();
        String msg = "Since this message wasn't added, list shouldn't have it";
        assert !list.contains(msg) : msg;
    }

    private ArrayBackedList<BigInteger> makeBigIntList(int size) {
        BigInteger number;
        ArrayBackedList<BigInteger> list = new ArrayBackedList<>(size);
        for (int i = 0; i < size; i++) {
            number = new BigInteger(64 + i, RANDOM);
            list.add(number);
        }
        return list;
    }

    @Test
    void testSize() {
        System.out.println("size");
        int expected = RANDOM.nextInt(32);
        ArrayBackedList<BigInteger> list = this.makeBigIntList(expected);
        String msg = "Array-backed list should have " + expected + " elements";
        int actual = list.size();
        assertEquals(expected, actual, msg);
    }

    @Test
    void testCapacityExpandsAsNeeded() {
        int size = RANDOM.nextInt(20) + 25;
        ArrayBackedList<LocalDateTime> list = new ArrayBackedList<>(size);
        LocalDateTime dateTime = LocalDateTime.now();
        for (int i = 0; i < size; i++) {
            dateTime = dateTime.plusHours(i);
            list.add(dateTime);
        }
        try {
            list.add(LocalDateTime.MAX);
            System.out.println("List successfully expanded capacity to hold "
                    + list.size() + " elements");
        } catch (ArrayIndexOutOfBoundsException aioobe) {
            String msg = "List failed to expand to add after first " + size
                    + " elements";
            System.out.println(msg);
            System.out.println("\"" + aioobe.getMessage() + "\"");
            fail(msg);
        }
    }

    @Test
    void testGetRejectsNegativeIndex() {
        ArrayBackedList<CallableStatement> list = new ArrayBackedList<>();
        int badIndex = -RANDOM.nextInt(256) - 1;
        Throwable t = assertThrows(IndexOutOfBoundsException.class, () -> {
            CallableStatement badStatement = list.get(badIndex);
            System.out.println("Using index " + badIndex
                    + " should not have given element "
                    + badStatement.toString());
        });
        String excMsg = t.getMessage();
        assert excMsg != null : "Exception message should not be null";
        System.out.println("\"" + excMsg + "\"");
    }

    @Test
    void testGetRejectsExcessiveIndex() {
        int size = RANDOM.nextInt(32) + 1;
        ArrayBackedList<BigInteger> list = this.makeBigIntList(size);
        int badIndex = size + RANDOM.nextInt(32);
        Throwable t = assertThrows(IndexOutOfBoundsException.class, () -> {
            BigInteger badNumber = list.get(badIndex);
            System.out.println("Using index " + badIndex
                    + " for list that only has " + size
                    + " elements should not have given element "
                    + badNumber.toString());
        });
        String excMsg = t.getMessage();
        assert excMsg != null : "Exception message should not be null";
        System.out.println("\"" + excMsg + "\"");
    }

    @Test
    void testGet() {
        System.out.println("get");
        int size = RANDOM.nextInt(24) + 12;
        LocalDateTime[] array = new LocalDateTime[size];
        ArrayBackedList<LocalDateTime> list = new ArrayBackedList<>();
        LocalDateTime time = LocalDateTime.now();
        for (int i = 0; i < size; i++) {
            array[i] = time;
            list.add(time);
            time = time.plusHours(7);
        }
        for (int j = 0; j < size; j++) {
            assertEquals(array[j], list.get(j));
        }
    }

    @Test
    void testNoRemovalIfNotInList() {
        ArrayBackedList<String> set = new ArrayBackedList<>();
        int expected = 0;
        String msg = "Shouldn't remove this message that wasn't added";
        boolean opResult = set.remove(msg);
        int actual = set.size();
        assert !opResult : msg;
        assertEquals(expected, actual);
    }

    @Test
    void testRemove() {
        System.out.println("remove");
        int originalSize = 20;
        ArrayBackedList<String> list = new ArrayBackedList<>(originalSize);
        int placementIndex = RANDOM.nextInt(20);
        String element = "Element to be removed";
        for (int i = 0; i < originalSize; i++) {
            if (i == placementIndex) {
                list.add(element);
            } else {
                list.add(Integer.toString(RANDOM.nextInt()));
            }
        }
        boolean opResult = list.remove(element);
        String msg = "Should have been able to remove \"" + element
                + "\" from list";
        assert opResult : msg;
        int expected = originalSize - 1;
        int actual = list.size();
        assertEquals(expected, actual);
    }

    @Test
    void testRemoveByIndexRejectsNegativeIndex() {
        ArrayBackedList<CallableStatement> list = new ArrayBackedList<>();
        int badIndex = -RANDOM.nextInt(256) - 1;
        Throwable t = assertThrows(IndexOutOfBoundsException.class, () -> {
            CallableStatement badStatement = list.remove(badIndex);
            System.out.println("Using index " + badIndex
                    + " for removal should not have given element "
                    + badStatement.toString());
        });
        String excMsg = t.getMessage();
        assert excMsg != null : "Exception message should not be null";
        System.out.println("\"" + excMsg + "\"");
    }

    @Test
    void testRemoveByIndexRejectsExcessiveIndex() {
        int size = RANDOM.nextInt(32) + 1;
        ArrayBackedList<BigInteger> list = this.makeBigIntList(size);
        int badIndex = size + RANDOM.nextInt(32);
        Throwable t = assertThrows(IndexOutOfBoundsException.class, () -> {
            BigInteger badNumber = list.remove(badIndex);
            System.out.println("Using index " + badIndex
                    + " for removal from list that only has " + size
                    + " elements should not have given element "
                    + badNumber.toString());
        });
        String excMsg = t.getMessage();
        assert excMsg != null : "Exception message should not be null";
        System.out.println("\"" + excMsg + "\"");
    }

    @Test
    void testRemoveByIndex() {
        int size = RANDOM.nextInt(40) + 10;
        ArrayBackedList<BigInteger> list = this.makeBigIntList(size);
        int index = RANDOM.nextInt(size);
        BigInteger expected = list.get(index);
        BigInteger actual = list.remove(index);
        assertEquals(expected, actual);
        assertEquals(size - 1, list.size());
    }

    @Test
    void testRemoveShiftsRemainingElementsLeft() {
        int size = RANDOM.nextInt(48) + 12;
        ArrayBackedList<LocalDateTime> list = new ArrayBackedList<>(size);
        int removalIndex = RANDOM.nextInt(size - 5);
        LocalDateTime currTime = LocalDateTime.now();
        LocalDateTime otherTime;
        LocalDateTime[] expected = new LocalDateTime[size - 1];
        for (int i = 0; i < removalIndex; i++) {
            otherTime = currTime.minusHours(7 + 12 * i);
            expected[i] = otherTime;
            list.add(otherTime);
        }
        list.add(currTime);
        int reducedSize = size - 1;
        for (int j = removalIndex; j < reducedSize; j++) {
            otherTime = currTime.plusHours(7 + 12 * j);
            expected[j] = otherTime;
            list.add(otherTime);
        }
        boolean opResult = list.remove(currTime);
        String msg = "Should have been able to remove " + currTime.toString()
                + " from list of times";
        assert opResult : msg;
        for (int index = 0; index < reducedSize; index++) {
            assertEquals(expected[index], list.get(index));
        }
    }

    @Test
    void testRemoveByIndexShiftsRemainingElementsLeft() {
        int size = RANDOM.nextInt(48) + 12;
        ArrayBackedList<LocalDateTime> list = new ArrayBackedList<>(size);
        int removalIndex = RANDOM.nextInt(size - 5);
        LocalDateTime currTime = LocalDateTime.now();
        LocalDateTime otherTime;
        LocalDateTime[] expected = new LocalDateTime[size - 1];
        for (int i = 0; i < removalIndex; i++) {
            otherTime = currTime.minusHours(7 + 12 * i);
            expected[i] = otherTime;
            list.add(otherTime);
        }
        list.add(currTime);
        int reducedSize = size - 1;
        for (int j = removalIndex; j < reducedSize; j++) {
            otherTime = currTime.plusHours(7 + 12 * j);
            expected[j] = otherTime;
            list.add(otherTime);
        }
        assertEquals(currTime, list.remove(removalIndex));
        for (int index = 0; index < reducedSize; index++) {
            assertEquals(expected[index], list.get(index));
        }
    }

    @Test
    void testIsEmpty() {
        System.out.println("isEmpty");
        ArrayBackedList<CallableStatement> list = new ArrayBackedList<>();
        String msg = "Newly created list should be empty";
        assert list.isEmpty() : msg;
    }

    @Test
    void testIsNotEmpty() {
        int expected = RANDOM.nextInt(16) + 20;
        ArrayBackedList<Integer> list = new ArrayBackedList<>(expected);
        int start = RANDOM.nextInt();
        for (int i = 0; i < expected; i++) {
            list.add(start + i);
        }
        int actual = list.size();
        assertEquals(expected, actual);
        String msg = "List with " + actual
                + " elements should not be considered empty";
        assert !list.isEmpty() : msg;
    }

    @Test
    void testClear() {
        System.out.println("clear");
        int size = RANDOM.nextInt(32) + 16;
        ArrayBackedList<BigInteger> list = this.makeBigIntList(size);
        String preMsg = "List with " + size
                + " elements should not be considered empty";
        assert !list.isEmpty() : preMsg;
        list.clear();
        String msg = "List should be empty after calling cleear()";
        assert list.isEmpty() : msg;
    }

    @Test
    void testReferentialEquality() {
        ArrayBackedList<CallableStatement> someList = new ArrayBackedList<>();
        assertEquals(someList, someList);
    }

    @Test
    void testNotEqualsNull() {
        ArrayBackedList<Locale> list = new ArrayBackedList<>();
        assertNotEquals(list, null);
    }

    @Test
    void testNotEqualsDiffClass() {
        LocalDateTime currentTime = LocalDateTime.now();
        ArrayBackedList<LocalDateTime> arrayBackedList
                = new ArrayBackedList<>();
        arrayBackedList.add(currentTime);
        ArrayList<LocalDateTime> arrayList = new ArrayList<>();
        arrayList.add(currentTime);
        assertNotEquals(arrayBackedList, arrayList);
    }

    @Test
    void testNotEqualsDiffSize() {
        int sizeA = RANDOM.nextInt(48);
        int sizeB = 2 * sizeA + 1;
        ArrayBackedList<BigInteger> listA = this.makeBigIntList(sizeA);
        ArrayBackedList<BigInteger> listB = this.makeBigIntList(sizeB);
        assertNotEquals(listA.size(), listB.size());
        assertNotEquals(listA, listB);
    }

    @Test
    void testNotEqualsDiffElems() {
        int size = RANDOM.nextInt(40);
        ArrayBackedList<BigInteger> listA = this.makeBigIntList(size);
        ArrayBackedList<BigInteger> listB = this.makeBigIntList(size);
        assertNotEquals(listA, listB);
    }

    @Test
    void testEquals() {
        System.out.println("equals");
        int size = RANDOM.nextInt(128);
        ArrayBackedList<BigInteger> someList = new ArrayBackedList<>(size);
        ArrayBackedList<BigInteger> sameList = new ArrayBackedList<>(size);
        BigInteger number;
        for (int i = 0; i < size; i++) {
            number = new BigInteger(32 + i, RANDOM);
            someList.add(number);
            sameList.add(number);
        }
        assertEquals(someList, sameList);
    }

    @Test
    void testHashCode() {
        System.out.println("hashCode");
        HashSet<Integer> hashes = new HashSet<>();
        int expected = RANDOM.nextInt(24);
        LocalDateTime dateTime;
        ArrayBackedList<LocalDateTime> list = new ArrayBackedList<>();
        for (int i = 0; i < expected; i++) {
            hashes.add(list.hashCode());
            dateTime = LocalDateTime.now().plusSeconds(i);
            list.add(dateTime);
        }
        int actual = hashes.size();
        assertEquals(expected, actual);
    }

    @Test
    void testIterator() {
        System.out.println("iterator");
        int size = RANDOM.nextInt(48) + 16;
        ArrayBackedList<BigInteger> expected = new ArrayBackedList<>(size);
        BigInteger number;
        for (int i = 0; i < size; i++) {
            number = new BigInteger(i + 32, RANDOM);
            expected.add(number);
        }
        ArrayBackedList<BigInteger> actual = new ArrayBackedList<>(size);
        for (BigInteger n : expected) {
            actual.add(n);
        }
        assertEquals(expected, actual);
    }

    @Test
    void testConstructorRejectsNegativeCapacity() {
        int badSize = -RANDOM.nextInt(128) - 1;
        Throwable t = assertThrows(IllegalArgumentException.class, () -> {
            ArrayBackedList<LocalDateTime> badList
                    = new ArrayBackedList<>(badSize);
            System.out.println("Should not have been able to create "
                    + badList.toString() + " with initial capacity " + badSize);
        });
        String excMsg = t.getMessage();
        assert excMsg != null : "Exception message should not be null";
        System.out.println("\"" + excMsg + "\"");
    }

}
