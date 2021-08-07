/*
 * Copyright (C) 2021 Alonso del Arte
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package numerics;

import java.util.Random;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ComplexNumberTest {

    private static final double TEST_DELTA = 0.00000001;

    private static final Random RANDOM = new Random();

    @Test
    void testToString() {
        System.out.println("toString");
        double re = Math.random();
        double im = Math.random();
        ComplexNumber z = new ComplexNumber(re, im);
        String expected = re + "+" + im + "i";
        String actual = z.toString().replace(" ", "");
        assertEquals(expected, actual);
    }

    @Test
    void testToASCIIString() {
        System.out.println("toASCIIString");
        double re = 1.0 + Math.random();
        double im = -1.0 + Math.random();
        ComplexNumber z = new ComplexNumber(re, im);
        String expected = re + "" + im + "i";
        String actual = z.toASCIIString().replace(" ", "");
        assertEquals(expected, actual);
    }

    @Test
    void testToStringNegativeRealPart() {
        double re = -1.0 + Math.random();
        double im = 1.0 + Math.random();
        ComplexNumber z = new ComplexNumber(re, im);
        String expected = "\u2212" + Math.abs(re) + "+" + im + "i";
        String actual = z.toString().replace(" ", "");
        assertEquals(expected, actual);
    }

    @Test
    void testToStringNegativeImaginaryPart() {
        double re = 1.0 + Math.random();
        double im = -1.0 + Math.random();
        ComplexNumber z = new ComplexNumber(re, im);
        String expected = re + "\u2212" + Math.abs(im) + "i";
        String actual = z.toString().replace(" ", "");
        assertEquals(expected, actual);
    }

    @Test
    void testGetRealPart() {
        System.out.println("getRealPart");
        double expected = RANDOM.nextDouble();
        double imag = RANDOM.nextDouble();
        ComplexNumber number = new ComplexNumber(expected, imag);
        double actual = number.getRealPart();
        assertEquals(expected, actual, TEST_DELTA);
    }

    @Test
    void testGetImagPart() {
        System.out.println("getImagPart");
        double real = RANDOM.nextDouble();
        double expected = RANDOM.nextDouble();
        ComplexNumber number = new ComplexNumber(real, expected);
        double actual = number.getImagPart();
        assertEquals(expected, actual, TEST_DELTA);
    }

    @Test
    void testReferentialEquality() {
        double real = RANDOM.nextDouble();
        double imag = RANDOM.nextDouble();
        ComplexNumber number = new ComplexNumber(real, imag);
        assertEquals(number, number);
    }

    @Test
    void testNotEqualsNull() {
        double real = RANDOM.nextDouble();
        double imag = RANDOM.nextDouble();
        ComplexNumber number = new ComplexNumber(real, imag);
        assertNotEquals(number, null);
    }

    @Test
    void testNotEqualsOtherClass() {
        ComplexNumber complexNumber = new ComplexNumber(1.0, 0.0);
        RomanNumeralsNumber romanNumeralsNumber = new RomanNumeralsNumber(1);
        assertNotEquals(complexNumber, romanNumeralsNumber);
    }

    @Test
    void testNotEqualsDiffRe() {
        double real = RANDOM.nextDouble();
        double imag = RANDOM.nextDouble();
        ComplexNumber number = new ComplexNumber(real, imag);
        ComplexNumber numberTimesI = new ComplexNumber(-imag, real);
        assertNotEquals(number, numberTimesI);
    }

    @Test
    void testNotEqualsDiffIm() {
        double real = RANDOM.nextDouble();
        double imag = RANDOM.nextDouble();
        ComplexNumber number = new ComplexNumber(real, imag);
        ComplexNumber conjugate = new ComplexNumber(real, -imag);
        assertNotEquals(number, conjugate);
    }

    @Test
    void testEquals() {
        System.out.println("equals");
        double real = RANDOM.nextDouble();
        double imag = RANDOM.nextDouble();
        ComplexNumber someNumber = new ComplexNumber(real, imag);
        ComplexNumber sameNumber = new ComplexNumber(real, imag);
        assertEquals(someNumber, sameNumber);
    }

    /**
     * Test of plus method, of class ComplexNumber.
     */
    @Test
    void testPlus() {
        System.out.println("plus");
        double addendARe = RANDOM.nextDouble();
        double addendAIm = RANDOM.nextDouble();
        ComplexNumber addendA = new ComplexNumber(addendARe, addendAIm);
        double addendBRe = RANDOM.nextDouble();
        double addendBIm = RANDOM.nextDouble();
        ComplexNumber addendB = new ComplexNumber(addendBRe, addendBIm);
        ComplexNumber expected = new ComplexNumber(addendARe + addendBRe,
                addendAIm + addendBIm);
        ComplexNumber actual = addendA.plus(addendB);
        assertEquals(expected, actual);
    }

    /**
     * Test of plus method, of class ComplexNumber.
     */
    @Test
    void testPlus_int() {
        int addend = 0;
        ComplexNumber instance = null;
        ComplexNumber expResult = null;
//        ComplexNumber result = instance.plus(addend);
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of the negate function, of the ComplexNumber class.
     */
    @Test
    void testNegate() {
        System.out.println("negate");
        double re = RANDOM.nextDouble();
        double im = RANDOM.nextDouble();
        ComplexNumber number = new ComplexNumber(re, im);
        ComplexNumber expected = new ComplexNumber(-re, -im);
        ComplexNumber actual = number.negate();
        assertEquals(expected, actual);
    }

    /**
     * Test of the minus function, of the ComplexNumber class.
     */
    @Test
    void testMinus() {
        System.out.println("minus");
        double minuendRe = RANDOM.nextDouble();
        double minuendIm = RANDOM.nextDouble();
        ComplexNumber minuend = new ComplexNumber(minuendRe, minuendIm);
        double subtrahendRe = RANDOM.nextDouble();
        double subtrahendIm = RANDOM.nextDouble();
        ComplexNumber subtrahend = new ComplexNumber(subtrahendRe,
                subtrahendIm);
        ComplexNumber expected = new ComplexNumber(minuendRe - subtrahendRe,
                minuendIm - subtrahendIm);
        ComplexNumber actual = minuend.minus(subtrahend);
        assertEquals(expected, actual);
    }

    /**
     * Test of times method, of class ComplexNumber. For this test I chose to go
     * with integers rather than floating point numbers to sidestep issues
     * pertaining to the loss of machine precision.
     */
    @Test
    void testTimes() {
        System.out.println("times");
        int multiplicandARe = RANDOM.nextInt(128) - 64;
        int multiplicandAIm = RANDOM.nextInt(128) - 64;
        ComplexNumber multiplicandA = new ComplexNumber(multiplicandARe,
                multiplicandAIm);
        int multiplicandBRe = RANDOM.nextInt(128) - 64;
        int multiplicandBIm = RANDOM.nextInt(128) - 64;
        ComplexNumber multiplicandB = new ComplexNumber(multiplicandBRe,
                multiplicandBIm);
        int expRe = multiplicandARe * multiplicandBRe
                - multiplicandAIm * multiplicandBIm;
        int expIm = multiplicandARe * multiplicandBIm
                + multiplicandAIm * multiplicandBRe;
        ComplexNumber expected = new ComplexNumber(expRe, expIm);
        ComplexNumber actual = multiplicandA.times(multiplicandB);
        assertEquals(expected, actual);
    }

    /**
     * Test of times method, of class ComplexNumber.
     */
    @Test
    void testTimes_int() {
        int multiplicand = 0;
        ComplexNumber instance = null;
        ComplexNumber expResult = null;
//        ComplexNumber result = instance.times(multiplicand);
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of divides method, of class ComplexNumber.
     */
    @Test
    void testDivides_ComplexNumber() {
        System.out.println("divides");
        ComplexNumber divisor = null;
        ComplexNumber instance = null;
        ComplexNumber expResult = null;
//        ComplexNumber result = instance.divides(divisor);
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of divides method, of class ComplexNumber.
     */
    @Test
    void testDivides_int() {
        System.out.println("divides");
        int divisor = 0;
        ComplexNumber instance = null;
        ComplexNumber expResult = null;
//        ComplexNumber result = instance.divides(divisor);
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    void testAbs() {
        System.out.println("abs");
        double real = RANDOM.nextDouble() - RANDOM.nextInt(16);
        double imag = RANDOM.nextDouble() - RANDOM.nextInt(16);
        ComplexNumber number = new ComplexNumber(real, imag);
        double expected = Math.sqrt(real * real + imag * imag);
        double actual = number.abs();
        assertEquals(expected, actual, TEST_DELTA);
    }

}