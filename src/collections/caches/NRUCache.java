package collections.caches;

public abstract class NRUCache<N, V> {

    /**
     * The minimum capacity for a cache. Obviously this should not be a negative
     * number, and zero doesn't make sense either. A value of 1 would be
     * pointless, since the cache would be constantly pushing items out. So
     * perhaps 2 is the smallest value that makes sense. But I think 4 is the
     * smallest value likely to be used with any frequency. I don't think the
     * cache should be too large, however, though I'm not providing a maximum
     * capacity constant.
     */
    public static final int MINIMUM_CAPACITY = 4;

    public static final int MAXIMUM_CAPACITY = 128;

    /**
     * Creates a value for a given name. Ideally this function should only be
     * called by {@link #retrieve(java.lang.Object) retrieve()}.
     * @param name The name to create a value for. Once the value is in the
     * cache, this name can be used to retrieve it.
     * @return A new value. Preferably not null.
     */
    protected abstract V create(N name);

    // TODO: Write tests for this
    boolean has(V value) {
        return true;
    }

    // TODO: Write tests for this
    public V retrieve(N name) {
        return null;
    }

    // TODO: Write tests for this
    public NRUCache(int size) {
        if (size < 0) {
            String excMsg = "Size " + size
                    + " is not valid, should be at least "
                    + Cache.MINIMUM_CAPACITY + ", at most "
                    + Cache.MAXIMUM_CAPACITY;
            throw new IllegalArgumentException(excMsg);
        }
    }

}
