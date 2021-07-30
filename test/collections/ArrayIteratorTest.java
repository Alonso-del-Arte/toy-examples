package collections;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Random;

import javax.naming.ldap.Rdn;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ArrayIteratorTest {

    private static final Random RANDOM = new Random();

    @Test
    void testHasNext() {
        System.out.println("hasNext");
        String msg = "Iterator with three elements should have next element";
        String[] array = {"Example element 1", "Example element 2", msg};
        ArrayIterator<String> iterator = new ArrayIterator<>(array, 3);
        assert iterator.hasNext() : msg;
    }

    @Test
    void testDoesNotHaveNext() {
        int size = RANDOM.nextInt(256) + 1;
        Rdn[] array = new Rdn[size];
        ArrayIterator<Rdn> iterator = new ArrayIterator<>(array, 0);
        String msg = "Even though passed array has " + size
                + " slots, zero-capped iterator should not have next element";
        assert !iterator.hasNext() : msg;
    }

    @Test
    void testHasNextChangesToDoesNotHaveNext() {
        String[] array = {"Just one element for this one"};
        ArrayIterator<String> iterator = new ArrayIterator<>(array, 1);
        String preRetrievalMsg = "Iterator should have next element";
        assert iterator.hasNext() : preRetrievalMsg;
        String element = iterator.next();
        String postRetrievalMsg = "After giving single element \"" + element
                + "\", iterator should not have next anymore";
        assert !iterator.hasNext() : postRetrievalMsg;
    }

    @Test
    void testConstructorRejectsNullArray() {
        Throwable t = assertThrows(NullPointerException.class, () -> {
            ArrayIterator<String> badIterator
                    = new ArrayIterator<>(null, 0);
            System.out.println("Should not have been able to create "
                    + badIterator.toString());
        });
        String excMsg = t.getMessage();
        assert excMsg != null : "Exception message should not be null";
        System.out.println("\"" + excMsg + "\"");
    }

    @Test
    void testConstructorRejectsNullElements() {
        LocalDate[] badArray = {null, null, null};
        Throwable t = assertThrows(NullPointerException.class, () -> {
            ArrayIterator<LocalDate> badIterator = new ArrayIterator<>(badArray,
                    badArray.length);
            System.out.println("Should not have been able to create "
                    + badIterator.toString());
        });
        String excMsg = t.getMessage();
        assert excMsg != null : "Exception message should not be null";
        System.out.println("\"" + excMsg + "\"");
    }

    @Test
    void testConstructorRejectsNegativeLastFreeIndex() {
        String[] array = {"Hello, world!", "Test 1, 2, 3"};
        int badLastFreeIndex = -RANDOM.nextInt(128) - 1;
        Throwable t = assertThrows(IllegalArgumentException.class, () -> {
            ArrayIterator<String> badIterator = new ArrayIterator<>(array,
                    badLastFreeIndex);
            System.out.println("Should not have been able to create "
                    + badIterator.toString() + " with bad last free index "
                    + badLastFreeIndex);
        });
        String excMsg = t.getMessage();
        assert excMsg != null : "Exception message should not be null";
        System.out.println("\"" + excMsg + "\"");
    }

    @Test
    void testConstructorRejectsExcessiveLastFreeIndex() {
        int size = RANDOM.nextInt(32) + 1;
        LocalDate[] array = new LocalDate[size];
        LocalDate today = LocalDate.now();
        for (int i = 0; i < size; i++) {
            array[i] = today.plusDays(i);
        }
        int badLastFreeIndex = size + RANDOM.nextInt(32) + 1;
        Throwable t = assertThrows(IllegalArgumentException.class, () -> {
            ArrayIterator<String> badIterator = new ArrayIterator<>(array,
                    badLastFreeIndex);
            System.out.println("Should not have been able to create "
                    + badIterator.toString() + " with bad last free index "
                    + badLastFreeIndex + " for array with only " + size
                    + " slots");
        });
        String excMsg = t.getMessage();
        assert excMsg != null : "Exception message should not be null";
        System.out.println("\"" + excMsg + "\"");
    }

}