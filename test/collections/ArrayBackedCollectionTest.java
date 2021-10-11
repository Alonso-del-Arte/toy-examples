package collections;

import java.math.BigInteger;
import java.sql.Clob;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

import javax.naming.ldap.Rdn;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ArrayBackedCollectionTest {

    private static final Random RANDOM = new Random();

    @Test
    void testAdd() {
        System.out.println("add");
        ArrayBackedCollection<String> collection
                = new ArrayBackedCollectionImpl<>();
        String msg = "Should be able to add this message to the collection";
        boolean opResult = collection.add(msg);
        assert opResult : msg;
    }

    @Test
    void testContains() {
        System.out.println("contains");
        String msg = "Collection should contain this message that was added";
        ArrayBackedCollection<String> collection
                = new ArrayBackedCollectionImpl<>();
        collection.add(msg);
        assert collection.contains(msg) : msg;
    }

    @Test
    void testIndexOf() {
        System.out.println("indexOf");
        int size = RANDOM.nextInt(64) + 1;
        ArrayBackedCollection<Integer> collection
                = new ArrayBackedCollectionImpl<>(size);
        for (int i = 0; i < size; i++) {
            collection.add(i);
        }
        int actual;
        for (int expected = 0; expected < size; expected++) {
            actual = collection.indexOf(expected);
            assertEquals(expected, actual);
        }
    }

    @Test
    void testNoIndexOf() {
        int size = RANDOM.nextInt(64) + 1;
        ArrayBackedCollection<Integer> collection
                = new ArrayBackedCollectionImpl<>(size);
        for (int i = 0; i < size; i++) {
            collection.add(2 * i * RANDOM.nextInt());
        }
        int expected = -1;
        int actual = collection.indexOf(2 * RANDOM.nextInt() - 1);
        assertEquals(expected, actual);
    }

    @Test
    void testDoesNotContain() {
        ArrayBackedCollection<LocalDateTime> collection
                = new ArrayBackedCollectionImpl<>();
        String msg = "Empty collection should not contain any element";
        assert !collection.contains(LocalDateTime.now()) : msg;
    }

    @Test
    void testDoesNotContainNull() {
        ArrayBackedCollection<LocalDateTime> collection
                = new ArrayBackedCollectionImpl<>();
        collection.add(LocalDateTime.now());
        try {
            String msg = "Collection should not contain null";
            assert !collection.contains(null) : msg;
        } catch (NullPointerException npe) {
            String msg = "Contains null query shouldn't have caused NPE";
            System.out.println(msg);
            System.out.println("\"" + npe.getMessage() + "\"");
            fail(msg);
        }
    }

    @Test
    void testAddRejectsNull() {
        ArrayBackedCollection<Clob> collection
                = new ArrayBackedCollectionImpl<>();
        boolean opResult = collection.add(null);
        String msg = "Collection should not add null element";
        assert !opResult : msg;
    }

    @Test
    void testAddExpandsCapacity() {
        int size = RANDOM.nextInt(48) + 4;
        ArrayBackedCollection<LocalDateTime> collection
                = new ArrayBackedCollectionImpl<>(size);
        LocalDateTime dateTime;
        for (int i = 0; i < size; i++) {
            dateTime = LocalDateTime.now().plusHours(i);
            collection.add(dateTime);
        }
        String msg = "Collection with " + size
                + " elements should have expanded for one more element";
        try {
            boolean opResult = collection.add(LocalDateTime.now());
            assert opResult : msg;
            System.out.println(msg.replace("should have", "successfully"));
        } catch (ArrayIndexOutOfBoundsException aioobe) {
            System.out.println("\"" + aioobe.getMessage() + "\"");
            fail(msg);
        }
    }

    @Test
    void testNoRemovalIfNotAlreadyInCollection() {
        int size = RANDOM.nextInt(64) + 1;
        ArrayBackedCollection<Integer> collection
                = new ArrayBackedCollectionImpl<>(size);
        for (int i = 0; i < size; i++) {
            collection.add(2 * i * RANDOM.nextInt() + 1);
        }
        int notInCollection = collection.indexOf(2 * RANDOM.nextInt());
        boolean opResult = collection.remove(notInCollection);
        String msg = "Should not be able to remove element not in collection";
        assert !opResult : msg;
    }

    @Test
    void testRemove() {
        System.out.println("remove");
        int size = RANDOM.nextInt(64) + 16;
        ArrayBackedCollection<LocalDateTime> collection
                = new ArrayBackedCollectionImpl<>(size);
        int index = RANDOM.nextInt(size);
        LocalDateTime currTime = LocalDateTime.now();
        int adjustment = RANDOM.nextInt(60) + 1;
        LocalDateTime time = currTime.plusMinutes(adjustment);
        for (int i = 0; i < size; i++) {
            if (i == index) {
                collection.add(currTime);
            } else {
                time = time.plusHours(i);
                collection.add(time);
            }
        }
        boolean opResult = collection.remove(currTime);
        assert opResult : "Should be able to remove specified item";
        String msg = "Collection should no longer contain removed item";
        assert !collection.contains(currTime) : msg;
    }

    @Test
    void testSize() {
        System.out.println("size");
        int capacity = RANDOM.nextInt(128) + 32;
        ArrayBackedCollection<BigInteger> collection
                = new ArrayBackedCollectionImpl<>(capacity);
        int fillLine = RANDOM.nextInt(capacity - 16) + 4;
        BigInteger number;
        BigInteger[] numbers = new BigInteger[fillLine];
        for (int i = 0; i < fillLine; i++) {
            number = new BigInteger(8 + i, RANDOM);
            collection.add(number);
            numbers[i] = number;
        }
        int removalLine = RANDOM.nextInt(fillLine);
        for (int j = 0; j < removalLine; j++) {
            collection.remove(numbers[j]);
        }
        int expected = fillLine - removalLine;
        int actual = collection.size();
        assertEquals(expected, actual);
    }

    @Test
    void testIsEmpty() {
        System.out.println("isEmpty");
        ArrayBackedCollection<Clob> collection
                = new ArrayBackedCollectionImpl<>();
        String msg = "Newly created collection should be empty";
        assert collection.isEmpty() : msg;
    }

    @Test
    void testIsNotEmpty() {
        ArrayBackedCollection<LocalDateTime> collection
                = new ArrayBackedCollectionImpl<>();
        collection.add(LocalDateTime.now());
        String msg = "Collection with one element should not be empty";
        assert !collection.isEmpty() : msg;
    }

    @Test
    void testClear() {
        System.out.println("clear");
        ArrayBackedCollection<String> collection
                = new ArrayBackedCollectionImpl<>();
        String elementPart = "Test element No. ";
        int size = RANDOM.nextInt(32) + 1;
        String element;
        for (int i = 0; i < size; i++) {
            element = elementPart + (i + 1);
            collection.add(element);
        }
        assert !collection.isEmpty();
        collection.clear();
        String msg = "Collection should be empty after calling clear()";
        assert collection.isEmpty() : msg;
    }

    @Test
    void testClearNullsOutBackingArraySlots() {
        ArrayBackedCollection<String> collection
                = new ArrayBackedCollectionImpl<>();
        String elementPart = "Test element No. ";
        int size = RANDOM.nextInt(32) + 1;
        String element;
        for (int i = 0; i < size; i++) {
            element = elementPart + (i + 1);
            collection.add(element);
        }
        collection.clear();
        for (int j = 0; j < collection.elements.length; j++) {
            String msg = "Backing array slot " + j + " should be null";
            assert collection.elements[j] == null : msg;
        }
    }

    @Test
    void testReferentialEquality() {
        ArrayBackedCollection<Rdn> collection
                = new ArrayBackedCollectionImpl<>();
        assertEquals(collection, collection);
    }

    @Test
    void testNotEqualsNull() {
        ArrayBackedCollection<Rdn> collection
                = new ArrayBackedCollectionImpl<>();
        assertNotEquals(collection, null);
    }

    @Test
    void testNotEqualsDiffClass() {
        String element = "Just for testing purposes";
        ArrayBackedCollection<String> collectionA
                = new ArrayBackedCollectionImpl<>();
        ArrayBackedCollection<String> collectionB
                = new ArrayBackedCollection<String>(ArrayBackedCollection
                        .DEFAULT_INITIAL_CAPACITY) {};
        collectionA.add(element);
        collectionB.add(element);
        assertNotEquals(collectionA, collectionB);
    }

    @Test
    void testNotEqualsDiffSize() {
        BigInteger element = new BigInteger(72, RANDOM);
        ArrayBackedCollection<BigInteger> collectionA
                = new ArrayBackedCollectionImpl<>();
        ArrayBackedCollection<BigInteger> collectionB
                = new ArrayBackedCollectionImpl<>();
        do {
            element = element.add(BigInteger.ONE);
            collectionA.add(element);
            collectionB.add(element);
        } while (RANDOM.nextBoolean());
        collectionA.add(element.add(BigInteger.ONE));
        String msg = "Since collection A has " + collectionA.size()
                + " elements and collection B has " + collectionB.size()
                + " elements, they should not be equal";
        assertNotEquals(collectionA, collectionB, msg);
    }

    @Test
    void testNotEqualsDiffOrder() {
        ArrayBackedCollection<Integer> collectionA
                = new ArrayBackedCollectionImpl<>();
        ArrayBackedCollection<Integer> collectionB
                = new ArrayBackedCollectionImpl<>();
        collectionA.add(1);
        collectionA.add(2);
        collectionA.add(3);
        collectionB.add(3);
        collectionB.add(1);
        collectionB.add(2);
        assertNotEquals(collectionA, collectionB);
    }

    @Test
    void testEquals() {
        System.out.println("equals");
        int size = RANDOM.nextInt(64) + 32;
        ArrayBackedCollection<LocalDateTime> someCollection
                = new ArrayBackedCollectionImpl<>(size);
        ArrayBackedCollection<LocalDateTime> sameCollection
                = new ArrayBackedCollectionImpl<>(size);
        LocalDateTime curr = LocalDateTime.now();
        for (int i = 0; i < size; i++) {
            curr = curr.plusMinutes(i);
            someCollection.add(curr);
            sameCollection.add(curr);
        }
        assertEquals(someCollection, sameCollection);
    }

    @Test
    void testHashCode() {
        System.out.println("hashCode");
        int size = RANDOM.nextInt(128) + 32;
        ArrayBackedCollection<LocalDateTime> collection
                = new ArrayBackedCollectionImpl<>(size);
        LocalDateTime curr = LocalDateTime.now();
        collection.add(curr);
        int prevHash = collection.hashCode();
        int currHash;
        for (int i = 1; i < size; i++) {
            curr = curr.plusHours(1);
            collection.add(curr);
            currHash = collection.hashCode();
            assertNotEquals(currHash, prevHash);
            prevHash = currHash;
        }
    }

    @Test
    void testIteratorHasNext() {
        ArrayBackedCollection<LocalDateTime> collection
                = new ArrayBackedCollectionImpl<>();
        collection.add(LocalDateTime.now());
        Iterator<LocalDateTime> iterator = collection.iterator();
        String msg = "Iterator for non-empty collection should have next";
        assert iterator.hasNext() : msg;
    }

    @Test
    void testIteratorDoesNotHaveNext() {
        ArrayBackedCollection<Rdn> collection
                = new ArrayBackedCollectionImpl<>();
        Iterator<Rdn> iterator = collection.iterator();
        String msg = "Iterator for empty collection should not have next";
        assert !iterator.hasNext() : msg;
    }

    @Test
    void testIteratorNextJustOneElement() {
        ArrayBackedCollection<LocalDateTime> collection
                = new ArrayBackedCollectionImpl<>();
        LocalDateTime expected = LocalDateTime.now();
        collection.add(expected);
        Iterator<LocalDateTime> iterator = collection.iterator();
        LocalDateTime actual = iterator.next();
        assertEquals(expected, actual);
    }

    @Test
    void testIteratorNext() {
        int size = RANDOM.nextInt(128) + 2;
        BigInteger[] expected = new BigInteger[size];
        ArrayBackedCollection<BigInteger> collection
                = new ArrayBackedCollectionImpl<>(size);
        for (int i = 0; i < size; i++) {
            BigInteger number = new BigInteger(64 + i, RANDOM);
            expected[i] = number;
            collection.add(number);
        }
        Iterator<BigInteger> iterator = collection.iterator();
        for (int j = 0; j < size; j++) {
            BigInteger actual = iterator.next();
            assertEquals(expected[j], actual);
        }
    }

    @Test
    void testIteratorNextThrowsExceptionAfterRunningOut() {
        ArrayBackedCollection<LocalDateTime> collection
                = new ArrayBackedCollectionImpl<>();
        collection.add(LocalDateTime.now());
        Iterator<LocalDateTime> iterator = collection.iterator();
        LocalDateTime element = iterator.next();
        System.out.println("Iterator has element " + element.toString()
                + " and no others");
        String msg = "Iterator should not have next";
        assert !iterator.hasNext() : msg;
        Throwable t = assertThrows(NoSuchElementException.class, () -> {
            LocalDateTime badElement = iterator.next();
            System.out.println("After giving its only element, "
                    + element.toString() + " iterator should not have given "
                    + badElement);
        });
        String excMsg = t.getMessage();
        assert excMsg != null : "Message should not be null";
        System.out.println("After running out, " + iterator.toString()
                + " correctly threw " + t.getClass().getName());
        System.out.println("\"" + excMsg + "\"");
    }

    @Test
    void testConstructorRejectsNegativeCapacity() {
        int badCapacity = -RANDOM.nextInt(128) - 1;
        Throwable t = assertThrows(IllegalArgumentException.class, () -> {
            ArrayBackedCollection<Clob> badCollection
                    = new ArrayBackedCollectionImpl<>(badCapacity);
            System.out.println("Should not have been able to create "
                    + badCollection.toString() + " with capacity "
                    + badCapacity);
        });
        String excMsg = t.getMessage();
        assert excMsg != null : "Exception message should not be null";
        System.out.println("\"" + excMsg + "\"");
    }

    @Test
    void testConstructorBackingArrayAllocation() {
        int expected = 2 * ArrayBackedCollection.DEFAULT_INITIAL_CAPACITY
                + RANDOM.nextInt(20) + 1;
        ArrayBackedCollection<Clob> collection
                = new ArrayBackedCollectionImpl<>(expected);
        int actual = collection.elements.length;
        assertEquals(expected, actual);
    }

    @Test
    void testCopyConstructor() {
        int size = RANDOM.nextInt(256) + 4;
        ArrayBackedCollection<LocalDateTime> expected
                = new ArrayBackedCollectionImpl<>(size);
        for (int i = 0; i < size; i++) {
            expected.add(LocalDateTime.now());
        }
        ArrayBackedCollection<LocalDateTime> actual
                = new ArrayBackedCollectionImpl<>(expected);
        assertEquals(expected, actual);
    }

    private static class ArrayBackedCollectionImpl<E>
            extends ArrayBackedCollection<E> {

        ArrayBackedCollectionImpl(ArrayBackedCollection<?
                extends E> collection) {
            super(collection);
        }

        ArrayBackedCollectionImpl() {
            this(ArrayBackedCollection.DEFAULT_INITIAL_CAPACITY);
        }

        ArrayBackedCollectionImpl(int initialCapacity) {
            super(initialCapacity);
        }

    }

}
