package randomness;

import fractions.Fraction;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

class ExtendedRandomTest {

    public static final Random RANDOM = new Random(-System.currentTimeMillis());

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
            System.out.println("Calling nextObject() on an empty array gave "
                    + badStatement.toString());
        });
        String excMsg = t.getMessage();
        assert excMsg != null : "Message should not be null";
        System.out.println("\"" + excMsg + "\"");
    }

    @Test
    void testNextObjectList() {
        System.out.println("nextObjectList");
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        testNextObjectList(list);
    }

    @Test
    void testNextObjectSet() {
        System.out.println("nextObjectSet");
        Set<String> set = new HashSet<>(Arrays.asList("apple", "banana", "cherry", "date"));
        testNextObjectSet(set);
    }

    @Test
    void testNextObjectEmptyListThrowsException() {
        List<String> emptyList = Collections.emptyList();
        Throwable t = assertThrows(NoSuchElementException.class, () -> {
            String badString = ExtendedRandom.nextObject(emptyList);
            System.out.println("Calling nextObject() on an empty list gave " + badString);
        });
        String excMsg = t.getMessage();
        assertNotNull(excMsg, "Message should not be null");
        System.out.println("\"" + excMsg + "\"");
    }

    @Test
    void testNextObjectEmptySetThrowsException() {
        Set<Double> emptySet = Collections.emptySet();
        Throwable t = assertThrows(NoSuchElementException.class, () -> {
            Double badDouble = ExtendedRandom.nextObject(emptySet);
            System.out.println("Calling nextObject() on an empty set gave " + badDouble);
        });
        String excMsg = t.getMessage();
        assertNotNull(excMsg, "Message should not be null");
        System.out.println("\"" + excMsg + "\"");
    }

    // Helper method to test nextObject() for lists
    private <T> void testNextObjectList(List<T> list) {
        int capacity = list.size();
        Set<T> expected = new HashSet<>(list);
        Set<T> actual = new HashSet<>(capacity);

        int numberOfCalls = 20 * capacity;
        for (int j = 0; j < numberOfCalls; j++) {
            actual.add(ExtendedRandom.nextObject(list));
        }

        String msg = "After " + numberOfCalls
                + " nextObject() calls for a list with " + capacity
                + " elements, all elements should have been given";
        assertEquals(expected, actual, msg);
    }

    // Helper method to test nextObject() for sets
    private <T> void testNextObjectSet(Set<T> set) {
        int capacity = set.size();
        Set<T> expected = new HashSet<>(set);
        Set<T> actual = new HashSet<>(capacity);

        int numberOfCalls = 20 * capacity;
        for (int j = 0; j < numberOfCalls; j++) {
            actual.add(ExtendedRandom.nextObject(set));
        }

        String msg = "After " + numberOfCalls
                + " nextObject() calls for a set with " + capacity
                + " elements, all elements should have been given";
        assertEquals(expected, actual, msg);
    }
}
