package collections.comparators;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashCodeComparatorTest {

    @Test
    void testCompare() {
        System.out.println("compare");
        HashCodeComparator comparator = new HashCodeComparator();
        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(1);
        expected.add(2);
        expected.add(3);
        ArrayList<Integer> actual = new ArrayList<>();
        actual.add(2);
        actual.add(1);
        actual.add(3);
        actual.sort(comparator);
        assertEquals(expected, actual);
    }

}
