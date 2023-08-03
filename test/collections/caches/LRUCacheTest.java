package collections.caches;

import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LRUCacheTest {

    private static final int DEFAULT_SIZE = 7;

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

    @Test
    void testHas() {
        System.out.println("has");
        LRUCacheImpl cache = new LRUCacheImpl(DEFAULT_SIZE);
        String ssnName = "\\d{3}-\\d{2}-\\d{4}";
        Pattern pattern = cache.retrieve(ssnName);
        String msg = "After adding pattern " + pattern.toString()
                + " to cache, cache should acknowledge having that pattern";
        assert cache.has(pattern) : msg;
    }

    @Test
    void testDoesNotHave() {
        LRUCacheImpl cache = new LRUCacheImpl(DEFAULT_SIZE);
        String numberName = "([-+]?\\d*)";
        Pattern pattern = Pattern.compile(numberName);
        String msg = "Nothing has been added to cache, so it should not have "
                + pattern.toString();
        assert !cache.has(pattern) : msg;
    }

    @Test
    void testValueEventuallyForgotten() {
        LRUCacheImpl cache = new LRUCacheImpl(DEFAULT_SIZE);
        String romanName = "^(?=[MDCLXVI])M*(C[MD]|D?C{0,3})(X[CL]|L?X{0,3})"
                + "(I[XV]|V?I{0,3})$";
        Pattern romanValue = cache.retrieve(romanName);
        for (int i = 0; i < DEFAULT_SIZE; i++) {
            assert cache.has(romanValue);
            String fillerName = "^" + i + "*$";
            cache.retrieve(fillerName);
        }
        String msg = "After filling in " + DEFAULT_SIZE
                + " other values, Roman numerals pattern should be off cache";
        assert !cache.has(romanValue) : msg;
    }

    @Test
    void testFrequentlyUsedStaysCached() {
        LRUCacheImpl cache = new LRUCacheImpl(DEFAULT_SIZE);
        String dollarAmountName = "\\$\\d*\\.\\d{2}";
        Pattern expected = cache.retrieve(dollarAmountName);
        for (int i = 0; i < DEFAULT_SIZE; i++) {
            Pattern actual = cache.retrieve(dollarAmountName);
            assertEquals(expected, actual);
            String fillerName = "^" + i + "*$";
            cache.retrieve(fillerName);
        }
        String msg = "Cache should still have " + expected.toString()
                + " because it was retrieved repeatedly";
        assert cache.has(expected) : msg;
    }

    @Test
    void testConstructorRejectsSizeBelowMinimum() {
        int badSize = LRUCache.MINIMUM_CAPACITY - 1;
        Throwable t = assertThrows(IllegalArgumentException.class, () -> {
            LRUCacheImpl badCache = new LRUCacheImpl(badSize);
            System.out.println("Should not have been able to create "
                    + badCache.toString() + " of size " + badSize
                    + ", one less than minimum capacity");
        });
        String excMsg = t.getMessage();
        assert excMsg != null : "Message should not be null";
        System.out.println("\"" + excMsg + "\"");
    }

    @Test
    void testConstructorRejectsSizeAboveMaximum() {
        int badSize = LRUCache.MAXIMUM_CAPACITY + 1;
        Throwable t = assertThrows(IllegalArgumentException.class, () -> {
            LRUCacheImpl badCache = new LRUCacheImpl(badSize);
            System.out.println("Should not have been able to create "
                    + badCache.toString() + " of size " + badSize
                    + ", one more than maximum capacity");
        });
        String excMsg = t.getMessage();
        assert excMsg != null : "Message should not be null";
        System.out.println("\"" + excMsg + "\"");
    }

    private static class LRUCacheImpl extends LRUCache<String, Pattern> {

        @Override
        protected Pattern create(String name) {
            return Pattern.compile(name);
        }

        LRUCacheImpl(int size) {
            super(size);
        }

    }

}
