package collections.mutable;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class FrequencyCounterTest {

    @Test
    void testIsEmptyAtFirst() {
        FrequencyCounter<String> instance = new FrequencyCounter<>();
        assert instance.isEmpty() : "Collection should be empty at first";
    }

}
