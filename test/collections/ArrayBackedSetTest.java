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
        assert set.add(dateTime) : msg;
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
        LocalDateTime[] dateTimes = new LocalDateTime[size];
        for (int i = 0; i < size; i++) {
            dateTime = dateTime.plusDays(i);
            dateTimes[i] = dateTime;
            set.add(dateTime);
        }
        String msg = "Set of upcoming times should not contain "
                + LocalDateTime.MIN.toString();
        assert !set.contains(LocalDateTime.MIN) : msg;
    }

}