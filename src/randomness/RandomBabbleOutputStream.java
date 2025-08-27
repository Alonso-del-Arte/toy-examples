package randomness;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;

public class RandomBabbleOutputStream extends InputStream {

    public static final Random RANDOM = new Random(-System.currentTimeMillis());

    // TODO: Write tests for this
    @Override
    public int read() throws IOException {
        return RANDOM.nextInt(95) + 32;
    }

}
