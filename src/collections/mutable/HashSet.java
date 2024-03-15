package collections.mutable;

public class HashSet<E> {

    private boolean empty = true;

    private int count = 0;

    public boolean isEmpty() {
        return this.empty;
    }

    public int size() {
        return this.count;
    }

    public boolean add(E element) {
        this.empty = false;
        this.count++;
        return false;
    }

    public HashSet() {
        //
    }

}
