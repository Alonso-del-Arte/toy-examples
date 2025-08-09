package randomness;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RandomBabbleOutputStreamTest {

    @Test
    void testRead() throws IOException {
        System.out.println("read");
        InputStream instance = new RandomBabbleOutputStream();
        int maxReadCount = 1024;
        int minimum = maxReadCount / 16;
        Set<Integer> reads = new HashSet<>(minimum);
        for (int i = 0; i < maxReadCount; i++) {
            int b = instance.read();
            reads.add(b);
        }
        int actual = reads.size();
        String msg = "After " + maxReadCount + " reads, expecting at least "
                + minimum + " distinct, got " + actual + " distinct";
        assert actual >= minimum : msg;
    }

}
