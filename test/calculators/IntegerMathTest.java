package calculators;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class IntegerMathTest {

    @Test
    void testNeitherPrimeNorComposite() {
        assert !IntegerMath.isPrime(-1) : "-1 is not prime";
        assert !IntegerMath.isPrime(0) : "0 is not prime";
        assert !IntegerMath.isPrime(1) : "1 is not prime";
    }

}
