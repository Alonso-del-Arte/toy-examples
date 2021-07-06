package collections;

import java.util.Random;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LRUCacheTest {

    private static final int DEFAULT_SIZE = 7;

    private static final Random RANDOM = new Random();

    @Test
    void testAddToCache() {
        LRUCacheImpl cache = new LRUCacheImpl(DEFAULT_SIZE);
        String expected = "^[a-zA-Z0–9+_.-]+@[a-zA-Z0–9.-]+$";
        String actual = cache.retrieve(expected).pattern();
        assertEquals(expected, actual);
    }

    @Test
    void testRetrieve() {
        System.out.println("retrieve");
        LRUCacheImpl cache = new LRUCacheImpl(DEFAULT_SIZE);
        String romanName = "^(?=[MDCLXVI])M*(C[MD]|D?C{0,3})(X[CL]|L?X{0,3})"
                + "(I[XV]|V?I{0,3})$";
        Pattern expected = cache.retrieve(romanName);
        Pattern actual = cache.retrieve(romanName);
        assertEquals(expected, actual);
    }

    @Test
    void testCacheRetainsValueWhileCapacityAvailable() {
        LRUCacheImpl cache = new LRUCacheImpl(LRUCache.MINIMUM_CAPACITY);
        String timeName = "^(?:\\d|[01]\\d|2[0-3]):[0-5]\\d$";
        Pattern expected = cache.retrieve(timeName);
        for (int i = 1; i < LRUCache.MINIMUM_CAPACITY; i++) {
            String fillerName = "^" + i + "*$";
            cache.retrieve(fillerName);
        }
        Pattern actual = cache.retrieve(timeName);
        assertEquals(expected, actual);
    }

    private static class LRUCacheImpl extends LRUCache<String, Pattern> {

        int createCallCount = 0;

        @Override
        protected Pattern create(String name) {
            createCallCount++;
            return Pattern.compile(name);
        }

        LRUCacheImpl(int size) {
            super(size);
        }

    }

}
