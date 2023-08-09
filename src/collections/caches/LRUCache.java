package collections.caches;

public abstract class LRUCache<N, V> {

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

    private final Object[] names;

    private final Object[] values;

    private final int capacity;

    private int nextUp = 0;

    /**
     * Creates a value for a given name. Ideally this function should only be
     * called by {@link #retrieve(java.lang.Object) retrieve()}.
     * @param name The name to create a value for. Once the value is in the
     * cache, this name can be used to retrieve it.
     * @return A new value. Preferably not null.
     */
    protected abstract V create(N name);

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

    public LRUCache(int size) {
        if (size < Cache.MINIMUM_CAPACITY) {
            String excMsg = "Size " + size + " not valid";
            throw new IllegalArgumentException(excMsg);
        }
//        if (size < Cache.MINIMUM_CAPACITY) {
//            String excMsg = "Size " + size + " is less than minimum "
//                    + Cache.MINIMUM_CAPACITY;
//            throw new IllegalArgumentException(excMsg);
//        }
//        if (size > Cache.MAXIMUM_CAPACITY) {
//            String excMsg = "Size " + size + " is more than maximum "
//                    + Cache.MAXIMUM_CAPACITY;
//            throw new IllegalArgumentException(excMsg);
//        }
        this.capacity = Cache.MINIMUM_CAPACITY;
        this.names = new Object[this.capacity];
        this.values = new Object[this.capacity];
    }

}
