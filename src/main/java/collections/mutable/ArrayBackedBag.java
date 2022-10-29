package collections.mutable;

/**
 * A bag is a collection that can contains duplicates and keeps track of how
 * many times an item is in the collection. This is sort of like a bag for
 * shopping. For example, shopping at a supermarket, a bag might three loaves of
 * bread, one bottle of ketchup, etc.
 * @param <E> The type of the elements.
 * @author Alonso del Arte
 */
public class ArrayBackedBag<E> extends ArrayBackedCollection<E> {

    public int getCount(E element) {
        return this.contains(element) ? 1 : 0;
    }

    // TODO: Write tests for this
    public ArrayBackedSet<E> uniqueSet() {
        return new ArrayBackedSet<>();
    }

    public ArrayBackedBag() {
        super(ArrayBackedCollection.DEFAULT_INITIAL_CAPACITY);
    }

    public ArrayBackedBag(ArrayBackedCollection<? extends E> collection) {
        super(collection);
    }

}
