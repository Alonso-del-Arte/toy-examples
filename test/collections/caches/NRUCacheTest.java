package collections.caches;

import java.util.regex.Pattern;

class NRUCacheTest {

    private static class NRUCacheImpl extends LRUCache<String, Pattern> {

        @Override
        protected Pattern create(String name) {
            return Pattern.compile(name);
        }

        NRUCacheImpl(int size) {
            super(size);
        }

    }

}