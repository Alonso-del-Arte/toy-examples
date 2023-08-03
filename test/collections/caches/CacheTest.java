package collections.caches;

import java.util.regex.Pattern;

class CacheTest {

    private static class CacheImpl extends LRUCache<String, Pattern> {

        @Override
        protected Pattern create(String name) {
            return Pattern.compile(name);
        }

        CacheImpl(int size) {
            super(size);
        }

    }

}