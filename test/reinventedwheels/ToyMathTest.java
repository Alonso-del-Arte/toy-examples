package reinventedwheels;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ToyMathTest {

    @Test
    void testRandomIsInUnitInterval() {
        double runningTotal = 0.0;
        double additionalIncrement = 0.1;
        double adjusted = additionalIncrement;
        while (adjusted < 100.0) {
            double x = ToyMath.random();
            String msg = "Pseudorandom number " + x
                    + " should be at least 0.00 and less than 1.0";
            assert x >= 0.0 : msg;
            assert x < 1.0 : msg;
            runningTotal += x;
            adjusted += runningTotal + additionalIncrement;
        }
    }

}
