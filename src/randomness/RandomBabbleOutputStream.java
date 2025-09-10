package randomness;

import java.io.InputStream;
import java.util.Random;

public class RandomBabbleOutputStream extends InputStream {

    public static final Random RANDOM = new Random(-System.currentTimeMillis());

    // TODO: Write tests for this
    @Override
    public int read() {
        return RANDOM.nextInt(95) + 32;
    }

}
