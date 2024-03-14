package collections.mutable;

public class HashSet<E> {

    private boolean empty = true;

    public boolean isEmpty() {
        return this.empty;
    }

    public int size() {
        return Integer.MIN_VALUE;
    }

    public boolean add(E element) {
        this.empty = false;
        return false;
    }

    public HashSet() {
        //
    }

}
