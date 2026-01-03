package arithmetic;

import java.util.Iterator;

public class Range implements Iterable<Integer> {

    private final int beginning, finish, interval;

    // TODO: Refactor this flag out once all functions are tested
    private final boolean auxConstrFlag;

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
        if (this.interval == 1) {
            return this.beginning + " to " + this.finish;
        }
        return this.beginning + " to " + this.finish + " by " + this.interval;
    }

    public Range(int start, int end) {
        this.beginning = start;
        this.finish = end;
        this.interval = 1;
        this.auxConstrFlag = true;
    }

    public Range(int start, int end, int step) {
        this.beginning = start;
        this.finish = end;
        this.interval = step;
        this.auxConstrFlag = false;
    }

}
