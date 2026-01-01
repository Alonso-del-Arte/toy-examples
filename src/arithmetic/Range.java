package arithmetic;

import java.util.Iterator;

public class Range implements Iterable<Integer> {

    private final int beginning, finish, interval;

    // TODO: Refactor this flag out once all functions are tested
    private final boolean auxConstrFlag;

    // TODO: Write tests for this
    public int getStart() {
        if (this.auxConstrFlag) {
            return Integer.MAX_VALUE;
        } else {
            return this.beginning;
        }
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
        if (this.auxConstrFlag) {
            return this.beginning + " to " + this.finish;
        }
        return this.beginning + " to " + this.finish + " by " + this.interval;
    }

    public Range(int start, int end) {
        this.beginning = start;
        this.finish = end;
        this.interval = 0;
        this.auxConstrFlag = true;
    }

    public Range(int start, int end, int step) {
        this.beginning = start;
        this.finish = end;
        this.interval = step;
        this.auxConstrFlag = false;
    }

}
