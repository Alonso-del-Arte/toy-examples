package collections.caches;

import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NRUCacheTest {

//    @Test
//    void testConstructorRejectsSizeBelowMinimum() {
//        int badSize = NRUCache.MINIMUM_CAPACITY - 1;
//        Throwable t = assertThrows(IllegalArgumentException.class, () -> {
//            NRUCacheImpl badCache = new NRUCacheImpl(badSize);
//            System.out.println("Should not have been able to create "
//                    + badCache + " of size " + badSize
//                    + ", one less than minimum capacity");
//        });
//        String excMsg = t.getMessage();
//        assert excMsg != null : "Message should not be null";
//        System.out.println("\"" + excMsg + "\"");
//    }

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