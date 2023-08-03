package collections.caches;

public abstract class Cache<N, V> {

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

    abstract boolean has(V value);

    public abstract V retrieve(N name);

    public Cache(int size) {
        //
    }

}
