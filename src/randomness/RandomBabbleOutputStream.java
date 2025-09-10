package randomness;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class RandomBabbleOutputStream extends InputStream {

    public static final Random RANDOM = new Random(-System.currentTimeMillis());

    // TODO: Write tests for this
    @Override
    public int read() {
        return RANDOM.nextInt(95) + 32;
    }

    @Override
    public long skip(long n) throws IOException {
        String excMsg = "Skipping " + n + " characters not supported";
        throw new IOException(excMsg);
    }

    @Override
    public boolean markSupported() {
        return true;
    }

}
