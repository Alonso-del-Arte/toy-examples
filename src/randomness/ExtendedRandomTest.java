package randomness;
import org.junit.jupiter.api.Test;
import randomness.ExtendedRandom;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class ExtendedRandomTest {

    @Test
    public void testNextObjectWithArray() {
        String[] weekdays = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        String randomWeekday = ExtendedRandom.nextObject(weekdays);
        assertTrue(Arrays.asList(weekdays).contains(randomWeekday));
    }

    @Test
    public void testNextObjectWithList() {
        List<String> fruits = Arrays.asList("Apple", "Banana", "Orange", "Grapes");
        String randomFruit = ExtendedRandom.nextObject(fruits);
        assertTrue(fruits.contains(randomFruit));
    }

    @Test
    public void testNextObjectWithSet() {
        Set<Integer> numbers = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));
        int randomNumber = ExtendedRandom.nextObject(numbers);
        assertTrue(numbers.contains(randomNumber));
    }

    @Test
    public void testNextObjectWithEmptyCollection() {
        List<String> emptyList = Collections.emptyList();
        Set<Integer> emptySet = Collections.emptySet();

        assertThrows(NoSuchElementException.class, () -> ExtendedRandom.nextObject(emptyList));
        assertThrows(NoSuchElementException.class, () -> ExtendedRandom.nextObject(emptySet));
    }
}
