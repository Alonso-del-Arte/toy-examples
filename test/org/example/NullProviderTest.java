package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NullProviderTest {

    @Test
    void testProvideNull() {
        System.out.println("provideNull");
        Object actual = NullProvider.provideNull();
        assertNull(actual);
    }

}
