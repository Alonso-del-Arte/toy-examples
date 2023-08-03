package collections.caches;

public abstract class Cache<N, V> {

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
