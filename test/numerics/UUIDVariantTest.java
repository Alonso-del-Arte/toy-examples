package numerics;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class UUIDVariantTest {

    private static final long DCE_VARIANT_MASK = -4611686018427387905L;

    private static final Random RANDOM = new Random(DCE_VARIANT_MASK);

}
