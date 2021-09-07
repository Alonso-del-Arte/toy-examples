package collections;

import java.sql.Clob;
import java.time.LocalDateTime;
import java.util.Random;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ArrayBackedCollectionTest {

    private static final Random RANDOM = new Random();

    @Test
    void testAdd() {
        System.out.println("add");
        ArrayBackedCollectionImpl<String> collection
                = new ArrayBackedCollectionImpl<>();
        String msg = "Should be able to add this message to the collection";
        boolean opResult = collection.add(msg);
        assert opResult : msg;
    }

    @Test
    void testContains() {
        System.out.println("contains");
        String msg = "Collection should contain this message that was added";
        ArrayBackedCollectionImpl<String> collection = new ArrayBackedCollectionImpl<>();
        collection.add(msg);
        assert collection.contains(msg) : msg;
    }

    @Test
    void testIndexOf() {
        System.out.println("indexOf");
        int size = RANDOM.nextInt(64) + 1;
        ArrayBackedCollectionImpl<Integer> collection
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
        ArrayBackedCollectionImpl<Integer> collection
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
        ArrayBackedCollectionImpl<LocalDateTime> collection
                = new ArrayBackedCollectionImpl<>();
        String msg = "Empty collection should not contain any element";
        assert !collection.contains(LocalDateTime.now()) : msg;
    }

    @Test
    void testDoesNotContainNull() {
        ArrayBackedCollectionImpl<LocalDateTime> collection
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
        ArrayBackedCollectionImpl<Clob> collection
                = new ArrayBackedCollectionImpl<>();
        boolean opResult = collection.add(null);
        String msg = "Collection should not add null element";
        assert !opResult : msg;
    }

    @Test
    void testAddExpandsCapacity() {
        int size = RANDOM.nextInt(48) + 4;
        ArrayBackedCollectionImpl<LocalDateTime> collection
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
    void testConstructorRejectsNegativeCapacity() {
        int badCapacity = -RANDOM.nextInt(128) - 1;
        Throwable t = assertThrows(IllegalArgumentException.class, () -> {
            ArrayBackedCollectionImpl<Clob> badCollection
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
        ArrayBackedCollectionImpl<Clob> collection
                = new ArrayBackedCollectionImpl<>(expected);
        int actual = collection.elements.length;
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
