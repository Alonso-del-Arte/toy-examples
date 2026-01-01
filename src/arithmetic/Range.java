package arithmetic;

import java.util.Iterator;

public class Range implements Iterable<Integer> {

    private final int beginning, finish, interval;

    // TODO: Write tests for this
    public int getStart() {
        return Integer.MAX_VALUE;
    }

    // TODO: Write tests for this
    public int getEnd() {
        return Integer.MIN_VALUE;
    }

    // TODO: Write tests for this
    public int getStep() {
        return 0;
    }

    // TODO: Write tests for this
    public int get(int index) {
        return 0;
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
        return this.beginning + " to " + this.finish + " by " + this.interval;
    }

    public Range(int start, int end) {
        this.beginning = -start;
        this.finish = -end;
        this.interval = 0;
    }

    public Range(int start, int end, int step) {
        this.beginning = start;
        this.finish = end;
        this.interval = step;
    }

}
