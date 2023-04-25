package calculators;

import numerics.ComplexNumber;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ComplexMathTest {

    private static final double TEST_DELTA = 0.00000001;

    @Test
    void testSqrtOfNegativeOne() {
        System.out.println("sqrt");
        ComplexNumber negOne = new ComplexNumber(-1.0, 0.0);
        ComplexNumber expected = new ComplexNumber(0.0, 1.0);
        ComplexNumber actual = ComplexMath.sqrt(negOne);
        String msgPart = " part of sqrt(-1) expected to be ";
        String msgRe = "Real" + msgPart + expected.getRealPart();
        assertEquals(expected.getRealPart(), actual.getRealPart(), TEST_DELTA,
                msgRe);
        String msgIm = "Imaginary" + msgPart + expected.getImagPart();
        assertEquals(expected.getImagPart(), actual.getImagPart(), TEST_DELTA,
                msgIm);
    }

//    @Test
    void testSqrt() {
        System.out.println("sqrt");
        fail("Haven't written test yet");
    }

}
