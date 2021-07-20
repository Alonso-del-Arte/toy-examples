package collections;

import java.time.LocalDateTime;
import java.util.Random;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ArrayBackedSetTest {

    private static final Random RANDOM = new Random();

    @Test
    void testAdd() {
        ArrayBackedSet<LocalDateTime> set = new ArrayBackedSet<>();
        LocalDateTime dateTime = LocalDateTime.now();
        String msg ="Should be able to add " + dateTime.toString()
                + " to set of times";
        boolean opResult = set.add(dateTime);
        assert opResult : msg;
    }

    @Test
    void testContains() {
        System.out.println("contains");
        ArrayBackedSet<LocalDateTime> set = new ArrayBackedSet<>();
        int size = RANDOM.nextInt(13) + 2;
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
        int size = RANDOM.nextInt(13) + 2;
        LocalDateTime dateTime = LocalDateTime.now();
        for (int i = 0; i < size; i++) {
            dateTime = dateTime.plusDays(i);
            set.add(dateTime);
        }
        String msg = "Set of upcoming times should not contain "
                + LocalDateTime.MIN.toString();
        assert !set.contains(LocalDateTime.MIN) : msg;
    }

    @Test
    void testSize() {
        System.out.println("size");
        int bound = 24;
        ArrayBackedSet<LocalDateTime> set = new ArrayBackedSet<>(bound);
        int expected = RANDOM.nextInt(bound);
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
        int size = RANDOM.nextInt(20) + 25;
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
            System.out.println("\"" + aioobe + "\"");
            fail(msg);
        }
    }

}
