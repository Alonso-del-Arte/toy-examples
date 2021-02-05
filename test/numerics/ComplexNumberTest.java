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

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ComplexNumberTest {

    @Test
    void testToString() {
        System.out.println("toString");
        double re = Math.random();
        double im = Math.random();
        ComplexNumber z = new ComplexNumber(re, im);
        String expected = Double.toString(re) + "+" + Double.toString(im)
                + "i";
        String actual = z.toString().replace(" ", "");
        assertEquals(expected, actual);
    }

    /**
     * Test of plus method, of class ComplexNumber.
     */
    @Test
    void testPlus_ComplexNumber() {
        System.out.println("plus");
        ComplexNumber addend = null;
        ComplexNumber instance = null;
        ComplexNumber expResult = null;
//        ComplexNumber result = instance.plus(addend);
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of plus method, of class ComplexNumber.
     */
    @Test
    void testPlus_int() {
        System.out.println("plus");
        int addend = 0;
        ComplexNumber instance = null;
        ComplexNumber expResult = null;
//        ComplexNumber result = instance.plus(addend);
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of negate method, of class ComplexNumber.
     */
    @Test
    void testNegate() {
        System.out.println("negate");
        ComplexNumber instance = null;
        ComplexNumber expResult = null;
//        ComplexNumber result = instance.negate();
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of times method, of class ComplexNumber.
     */
    @Test
    void testTimes_ComplexNumber() {
        System.out.println("times");
        ComplexNumber multiplicand = null;
        ComplexNumber instance = null;
        ComplexNumber expResult = null;
//        ComplexNumber result = instance.times(multiplicand);
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of times method, of class ComplexNumber.
     */
    @Test
    void testTimes_int() {
        System.out.println("times");
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

}