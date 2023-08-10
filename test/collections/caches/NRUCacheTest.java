package collections.caches;

import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import static randomness.ExtendedRandom.nextInt;

class NRUCacheTest {

    @Test
    void testConstructorRejectsNegativeSize() {
        int badSize = CacheTest.vetBadSize(nextInt(-512) - 1, -1);
        Throwable t = assertThrows(IllegalArgumentException.class, () -> {
            NRUCacheImpl badCache = new NRUCacheImpl(badSize);
            System.out.println("Should not have been able to create "
                    + badCache + " of size " + badSize);
        });
        String excMsg = t.getMessage();
        assert excMsg != null : "Message should not be null";
        assert !excMsg.isEmpty() : "Message should not be empty";
        assert excMsg.contains(CacheTest.MIN_CAP_STR)
                : CacheTest.CAPACITY_ASSERTION_MESSAGE;
        assert excMsg.contains(CacheTest.MAX_CAP_STR)
                : CacheTest.CAPACITY_ASSERTION_MESSAGE;
        System.out.println("\"" + excMsg + "\"");
    }

    private static class NRUCacheImpl extends NRUCache<String, Pattern> {

        @Override
        protected Pattern create(String name) {
            return Pattern.compile(name);
        }

        NRUCacheImpl(int size) {
            super(size);
        }

    }

}