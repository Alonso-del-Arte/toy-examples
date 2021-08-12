package collections.comparators;

import java.util.Comparator;

public class HashCodeComparator implements Comparator<Object> {

    @Override
    public int compare(Object objectA, Object objectB) {
        return Integer.compare(objectA.hashCode(), objectB.hashCode());
    }

}
