package collections.caches;

// TODO: Implement not recently used cache.
// It should be weighted so that creates award two points in the recency score
// and retrievals without create aware only one point. Or is it the other way
// around? Then when the cache is full, the name-value pair with the lowest
// recency score is removed to make room for a new name-value pair.
public abstract class NRUCache<N, V> extends Cache<N, V> {

    // TODO: Write tests for this
    boolean has(V value) {
        return true;
    }

    // TODO: Write tests for this
    public V retrieve(N name) {
        return null;
    }

    /**
     * Sole constructor.
     * @param size How many slots the cache should have. This value can't be
     *             changed after construction. It should be at least {@link
     *             Cache#MINIMUM_CAPACITY}, at most {@link
     *             Cache#MAXIMUM_CAPACITY}.
     * @throws IllegalArgumentException If <code>size</code> is less than
     * <code>MINIMUM_CAPACITY</code> or more than <code>MAXIMUM_CAPACITY</code>.
     */
    public NRUCache(int size) {
        super(size);
    }

}
