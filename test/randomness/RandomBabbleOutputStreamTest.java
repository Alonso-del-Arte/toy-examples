package randomness;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import static randomness.ExtendedRandom.nextInt;

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

    @Test
    void testSkipNotSupported() {
        InputStream instance = new RandomBabbleOutputStream();
        int bound = 1024;
        int n = nextInt(bound);
        String message = "Trying to skip " + n
                + " characters should've caused exception";
        Throwable t = assertThrows(IOException.class, () -> {
            long skipCount = instance.skip(n);
            System.out.println("Said to have skipped " + skipCount
                    + " characters");
        }, message);
        String excMsg = t.getMessage();
        assert excMsg != null : "Exception message should not be null";
        assert !excMsg.isEmpty() : "Exception message should not be empty";
        String numStr = Integer.toString(n);
        String containsMsg = "Exception message should contain \"" + numStr
                + "\"";
        assert excMsg.contains(numStr) : containsMsg;
        System.out.println("\"" + excMsg + "\"");
    }

}
