package collections.mutable;

import java.sql.ResultSet;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HashSetTest {

    @Test
    void testIsEmpty() {
        System.out.println("isEmpty");
        HashSet<ResultSet> set = new HashSet<>();
        assert set.isEmpty() : "Newly created set should be empty";
    }

    @Test
    void testIsNotEmpty() {
        HashSet<LocalDateTime> set = new HashSet<>();
        LocalDateTime dateTime = LocalDateTime.now();
        set.add(dateTime);String msg = "After adding " + dateTime
                + " to set of times, set should not be empty";
        assert !set.isEmpty() : msg;
    }

}
