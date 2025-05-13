package fake.java.lang;

public class Array<E> {

    public final int length;

    @SafeVarargs
    public Array(E... elems) {
        this.length = elems.length;
    }

}
