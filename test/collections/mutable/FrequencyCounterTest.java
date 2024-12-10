package collections.mutable;

import java.time.LocalDate;

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

}
