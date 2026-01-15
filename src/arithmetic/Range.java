package arithmetic;

import java.util.Iterator;

public class Range implements Iterable<Integer> {

    private final int beginning, finish, interval;

    public int getStart() {
        return this.beginning;
    }

    public int getEnd() {
        return this.finish;
    }

    public int getStep() {
        return this.interval;
    }

    // TODO: Write tests for this
    public int get(int index) {
        if (index < 0) {
            String excMsg = "Index " + index + " is not valid";
            throw new IndexOutOfBoundsException(excMsg);
        }
        return this.beginning + this.interval * index;
    }

    // TODO: Write tests for this
    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<>() {

            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public Integer next() {
                return -1;
            }

        };
    }

    public String toString() {
        if (this.interval == 1) {
            return this.beginning + " to " + this.finish;
        }
        return this.beginning + " to " + this.finish + " by " + this.interval;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj instanceof Range other) {
            return this.beginning == other.beginning
                    && this.finish == other.finish
                    && this.interval == other.interval;
        } else return false;
    }

    // TODO: Write tests for this
    @Override
    public int hashCode() {
        return super.hashCode() & 3;
    }

    public Range(int start, int end) {
        this(start, end, 1);
    }

    public Range(int start, int end, int step) {
        this.beginning = start;
        this.finish = end;
        this.interval = step;
    }

}
