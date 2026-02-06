package arithmetic;

import java.util.Iterator;

public class Range implements Iterable<Integer> {

    private final int beginning, finish, interval, numberOfElements;

    public int getStart() {
        return this.beginning;
    }

    public int getEnd() {
        return this.finish;
    }

    public int getStep() {
        return this.interval;
    }

    public int size() {
        return this.numberOfElements;
    }

    public int get(int index) {
        if (index < 0 || index > this.numberOfElements) {
            String excMsg = "Index " + index + " is not valid";
            throw new IndexOutOfBoundsException(excMsg);
        }
        return this.beginning + this.interval * index;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<>() {

            private int counter = 0;

            @Override
            public boolean hasNext() {
                return this.counter < Range.this.numberOfElements;
            }

            @Override
            public Integer next() {
                return Range.this.beginning + Range.this.interval
                        * this.counter++;
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

    @Override
    public int hashCode() {
        int hash = this.beginning << 22;
        hash += (this.finish << 12);
        return hash + this.interval;
    }

    public Range(int start, int end) {
        this(start, end, 1);
    }

    public Range(int start, int end, int step) {
        this.beginning = start;
        this.finish = end;
        this.interval = step;
        this.numberOfElements = (this.finish - this.beginning) / this.interval
                + 1;
    }

}
