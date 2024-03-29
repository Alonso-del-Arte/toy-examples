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

    @Override
    public String toString() {
        return this.begin + " to " + this.end + ((this.interval == 1) ? ""
                : " by " + this.interval);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!this.getClass().equals(obj.getClass())) {
            return false;
        }
        final Range other = (Range) obj;
        if (this.begin != other.begin || this.end != other.end) {
            return false;
        }
        return this.interval == other.interval;
    }

    @Override
    public int hashCode() {
        int hash = this.begin << 22;
        hash += this.end << 11;
        return hash + this.interval;
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
