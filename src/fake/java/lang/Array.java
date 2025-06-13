package fake.java.lang;

public class Array<E> {

    public final int length;

    private final Object[] elements;

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("[");
        for (int i = 0; i < this.length; i++) {
            s.append(this.elements[i].toString());
            s.append(", ");
        }
        s.append(']');
        return s.toString().replace(", ]", "]");
    }

    // TODO: Write tests for this
    @Override
    public boolean equals(Object obj) {
        return obj instanceof Array;
    }

    // TODO: Write tests for this
    @Override
    public int hashCode() {
        return 0;
    }

    @SafeVarargs
    public Array(E... elems) {
        this.length = elems.length;
        this.elements = elems;
    }

}
