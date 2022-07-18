package collections.immutable;

import java.util.Iterator;

/**
 * Represents a range of integers. The idea is that this is going to be like
 * Scala's <code>Range</code> as much as I care to manage in Java.
 * @author Alonso del Arte
 */
public class Range implements Iterable<Integer> {

    private final int begin, end, interval, compare;

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {

            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public Integer next() {
                return null;
            }

        };
    }

    public int getStart() {
        return this.begin;
    }

    public int getFinish() {
        return this.end;
    }

    public int getStep() {
        return this.interval;
    }

    public Range(int start, int finish) {
        this(start, finish, Integer.signum(finish - start));
    }

    public Range(int start, int finish, int step) {
        this(start, finish, step, step);
    }

    private Range(int start, int finish, int step, int comparison) {
        this.begin = start;
        this.end = finish;
        this.interval = step;
        this.compare = comparison;
    }

}
