package collections.mutable;

import java.sql.ResultSet;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HashSetTest {

    @Test
    void testIsEmpty() {
        HashSet<ResultSet> set = new HashSet<>();
        assert set.isEmpty() : "Newly created set should be empty";
    }
}
