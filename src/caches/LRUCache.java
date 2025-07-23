package caches;

/**
 * A fixed-capacity least recently used cache. When the cache is full and a new
 * name-value pair is added, the least recently used name-value pair will be
 * removed to make room.
 * @param <N> The type for the names. Preferably a type for which instances are
 *           in some sense inexpensive to compute, like
 *           <code>java.lang.String</code>.
 * @param <V> The type for the values. It should generally be a value that is in
 *           some sense expensive to compute and thus easier to retrieve from a
 *           cache. For example, <code>java.util.regex.Pattern</code>.
 */
public abstract class LRUCache<N, V> extends Cache<N, V> {

    private final Object[] names;

    private final Object[] values;

    private int nextUp = 0;

    private static int indexOf(Object obj, Object[] array, int endBound) {
        boolean found = false;
        int curr = 0;
        while (!found && curr < endBound) {
            found = obj.equals(array[curr]);
            curr++;
        }
        if (found) {
            return curr - 1;
        } else {
            return -1;
        }
    }

    boolean has(V value) {
        return indexOf(value, this.values, this.capacity) > -1;
    }

    /**
     * Moves an object in an array to index 0. The other objects are shifted
     * accordingly, e.g., the object at index 0 is moved to index 1, the object
     * at index 1 is moved to index 2, etc.
     * @param objects The array of objects.
     * @param position A nonnegative integer, preferably positive.
     * @throws ArrayIndexOutOfBoundsException If <code>position</code> is
     * negative, equal to or greater than <code>objects.length</code>.
     */
    private static void moveToFront(Object[] objects, int position) {
        Object mostRecent = objects[position];
        System.arraycopy(objects, 0, objects, 1, position);
        objects[0] = mostRecent;
    }

    private V add(N name) {
        V value = this.create(name);
        this.names[this.nextUp] = name;
        this.values[this.nextUp] = value;
        this.nextUp++;
        if (this.nextUp == this.capacity) {
            this.nextUp--;
        }
        return value;
    }

    @SuppressWarnings(value = "unchecked")
    public V retrieve(N name) {
        V value;
        int index = indexOf(name, this.names, this.capacity);
        if (index > -1) {
            value = (V) this.values[index];
        } else {
            index = this.nextUp;
            value = this.add(name);
        }
        if (index > 0) {
            moveToFront(this.names, index);
            moveToFront(this.values, index);
        }
        return value;
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
    public LRUCache(int size) {
        super(size);
        this.names = new Object[this.capacity];
        this.values = new Object[this.capacity];
    }

}
