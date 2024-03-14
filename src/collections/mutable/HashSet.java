package collections.mutable;

public class HashSet<E> {

    private boolean empty = true;

    public boolean isEmpty() {
        return this.empty;
    }

    public int size() {
        return 0;
    }

    public boolean add(E element) {
        this.empty = false;
        return false;
    }

    public HashSet() {
        //
    }

}
