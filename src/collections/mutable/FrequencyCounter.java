package collections.mutable;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * WORK IN PROGRESS it's going to be like a histogram
 * @param <K> The type of the instances the frequencies of which is to be
 *           counted. Preferably a type with {@code equals()} defined which
 *           implements {@code Comparable}. For example, to determine which word
 *           occurs the most frequently in Shakespeare's &oelig;uvre, we might
 *           use {@code String}, or we might use a special type that allows us
 *           to count different conjugations of a verb or the singular and
 *           plural of a word as the same.
 */
public class FrequencyCounter<K> {

    private boolean empty = true;

    // TODO: Write tests for this
    public boolean isEmpty() {
        return this.empty;
    }

    // TODO: Write tests for this
    public int size() {
        return 0;
    }

    // TODO: Write tests for this
    public void increment(K key, int incr) {
        this.empty = false;
    }

    // TODO: Write tests for this
    public void increment(K key) {
        this.empty = false;
    }

    // TODO: Write tests for this
    public void reset(K key) {
        //
    }

    // TODO: Write tests for this
    boolean has(K key) {
        return true;
    }

    // TODO: Write tests for this
    public int getCountOf(K key) {
        return Integer.MAX_VALUE;
    }

    // TODO: Write tests for this
    public void remove(K key) {
        //
    }

    // TODO: Write tests for this
    public FrequencyCounter() {
        //
    }

}
