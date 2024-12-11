package collections.mutable;

import java.time.LocalDate;

import randomness.ExtendedRandom;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class FrequencyCounterTest {

    @Test
    void testIsEmptyAtFirst() {
        FrequencyCounter<String> instance = new FrequencyCounter<>();
        assert instance.isEmpty() : "Collection should be empty at first";
    }

    @Test
    void testSizeZeroAtFirst() {
        FrequencyCounter<LocalDate> instance = new FrequencyCounter<>();
        assertEquals(0, instance.size(),
                "Collection should have size 0 at first");
    }

    @Test
    void testIsNotEmptyAfterSpecifiedIncrementAdd() {
        FrequencyCounter<LocalDate> instance = new FrequencyCounter<>();
        LocalDate today = LocalDate.now();
        int incr = ExtendedRandom.nextInt(28) + 2;
        instance.increment(today, incr);
        String msg = "Instance should not be empty after incrementing " + today;
        assert !instance.isEmpty() : msg;
    }

    @Test
    void testIsNotEmptyAfterDefaultIncrementAdd() {
        FrequencyCounter<LocalDate> instance = new FrequencyCounter<>();
        LocalDate today = LocalDate.now();
        instance.increment(today);
        String msg = "Instance should not be empty after incrementing " + today;
        assert !instance.isEmpty() : msg;
    }

}
