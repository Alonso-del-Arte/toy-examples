package caches;

import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import static randomness.ExtendedRandom.nextInt;

class CacheTest {

    static final String MIN_CAP_STR = Integer.toString(Cache.MINIMUM_CAPACITY);

    static final String MAX_CAP_STR = Integer.toString(Cache.MAXIMUM_CAPACITY);

    static final String CAPACITY_ASSERTION_MESSAGE
            = "Exception message should include \"" + MIN_CAP_STR + "\" and \""
            + MAX_CAP_STR + "\"";

    @Test
    void testConstants() {
        assertEquals(4, Cache.MINIMUM_CAPACITY);
        assertEquals(128, Cache.MAXIMUM_CAPACITY);
    }

    static int vetBadSize(int firstSelection, int step) {
        int badSize = firstSelection - step;
        String numStr;
        do {
            badSize += step;
            numStr = Integer.toString(badSize);
        } while (numStr.contains(MIN_CAP_STR) || numStr.contains(MAX_CAP_STR));
        return badSize;
    }

    @Test
    void testConstructorRejectsNegativeSize() {
        int badSize = vetBadSize(nextInt(-1024) - 1, -1);
        Throwable t = assertThrows(IllegalArgumentException.class, () -> {
            CacheImpl badCache = new CacheImpl(badSize);
            System.out.println("Should not have been able to create "
                    + badCache + " of size " + badSize);
        });
        String excMsg = t.getMessage();
        assert excMsg != null : "Message should not be null";
        assert !excMsg.isEmpty() : "Message should not be empty";
        assert excMsg.contains(MIN_CAP_STR) : CAPACITY_ASSERTION_MESSAGE;
        assert excMsg.contains(MAX_CAP_STR) : CAPACITY_ASSERTION_MESSAGE;
        System.out.println("\"" + excMsg + "\"");
    }

    @Test
    void testConstructorRejectsSizeBelowMinimum() {
        for (int i = 0; i < Cache.MINIMUM_CAPACITY; i++) {
            final int badSize = i;
            Throwable t = assertThrows(IllegalArgumentException.class, () -> {
                CacheImpl badCache = new CacheImpl(badSize);
                System.out.println("Should not have been able to create "
                        + badCache + " of size " + badSize
                        + ", which is less than minimum capacity "
                        + Cache.MINIMUM_CAPACITY);
            });
            String excMsg = t.getMessage();
            assert excMsg != null : "Message should not be null";
            assert excMsg.contains(MIN_CAP_STR) : CAPACITY_ASSERTION_MESSAGE;
            assert excMsg.contains(MAX_CAP_STR) : CAPACITY_ASSERTION_MESSAGE;
            System.out.println("\"" + excMsg + "\"");
        }
    }

    @Test
    void testConstructorRejectsSizeAboveMaximum() {
        int badSize = vetBadSize(Cache.MAXIMUM_CAPACITY + nextInt(768) + 1, 1);
        Throwable t = assertThrows(IllegalArgumentException.class, () -> {
            CacheImpl badCache = new CacheImpl(badSize);
            System.out.println("Should not have been able to create "
                    + badCache + " of size " + badSize
                    + ", which is more than maximum capacity "
                    + Cache.MAXIMUM_CAPACITY);
        });
        String excMsg = t.getMessage();
        assert excMsg != null : "Message should not be null";
        assert excMsg.contains(MIN_CAP_STR) : CAPACITY_ASSERTION_MESSAGE;
        assert excMsg.contains(MAX_CAP_STR) : CAPACITY_ASSERTION_MESSAGE;
        System.out.println("\"" + excMsg + "\"");
    }

    /**
     * A cache with the Least Recently Added eviction policy.
     */
    private static class CacheImpl extends Cache<String, Pattern> {

        private final String[] names;

        private final Pattern[] values;

        private final int capacity;

        private int nextUp = 0;

        @Override
        protected Pattern create(String name) {
            return Pattern.compile(name);
        }

        private static int indexOf(Object[] array, Object obj) {
            int i = 0;
            while (i < array.length && !obj.equals(array[i])) {
                i++;
            }
            return i;
        }

        @Override
        boolean has(Pattern value) {
            return indexOf(this.values, value) < this.capacity;
        }

        public Pattern retrieve(String name) {
            int index = indexOf(this.names, name);
            if (index < this.capacity) {
                return this.values[index];
            } else {
                this.names[this.nextUp] = name;
                Pattern value = this.create(name);
                this.values[this.nextUp] = value;
                this.nextUp++;
                if (this.nextUp == this.capacity) {
                    this.nextUp = 0;
                }
                return value;
            }
        }

        CacheImpl(int size) {
            super(size);
            this.capacity = size;
            this.names = new String[this.capacity];
            this.values = new Pattern[this.capacity];
        }

    }

}
