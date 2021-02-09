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

import arithmetic.NotDivisibleException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RomanNumeralsNumberTest {

    private static final Random RANDOM = new Random();

    @Test
    void testToString() {
        System.out.println("toString");
        RomanNumeralsNumber one = new RomanNumeralsNumber(1);
        RomanNumeralsNumber two = new RomanNumeralsNumber(2);
        RomanNumeralsNumber three = new RomanNumeralsNumber(3);
        assertEquals("I", one.toString());
        assertEquals("II", two.toString());
        assertEquals("III", three.toString());
    }

    @Test
    void testToStringTens() {
        RomanNumeralsNumber ten = new RomanNumeralsNumber(10);
        RomanNumeralsNumber twenty = new RomanNumeralsNumber(20);
        RomanNumeralsNumber thirty = new RomanNumeralsNumber(30);
        assertEquals("X", ten.toString());
        assertEquals("XX", twenty.toString());
        assertEquals("XXX", thirty.toString());
    }

    @Test
    void testToStringHundreds() {
        RomanNumeralsNumber hundred = new RomanNumeralsNumber(100);
        RomanNumeralsNumber twoHundred = new RomanNumeralsNumber(200);
        RomanNumeralsNumber threeHundred = new RomanNumeralsNumber(300);
        assertEquals("C", hundred.toString());
        assertEquals("CC", twoHundred.toString());
        assertEquals("CCC", threeHundred.toString());
    }

    @Test
    void testToStringThousands() {
        RomanNumeralsNumber thousand = new RomanNumeralsNumber(1000);
        RomanNumeralsNumber twoThousand = new RomanNumeralsNumber(2000);
        RomanNumeralsNumber threeThousand = new RomanNumeralsNumber(3000);
        assertEquals("M", thousand.toString());
        assertEquals("MM", twoThousand.toString());
        assertEquals("MMM", threeThousand.toString());
    }

    @Test
    void testToStringFive() {
        RomanNumeralsNumber five = new RomanNumeralsNumber(5);
        assertEquals("V", five.toString());
    }

    @Test
    void testToStringFifty() {
        RomanNumeralsNumber fifty = new RomanNumeralsNumber(50);
        assertEquals("L", fifty.toString());
    }

    @Test
    void testToStringFiveHundred() {
        RomanNumeralsNumber fiveHundred = new RomanNumeralsNumber(500);
        assertEquals("D", fiveHundred.toString());
    }

    @Test
    void testToStringFour() {
        RomanNumeralsNumber four = new RomanNumeralsNumber(4);
        assertEquals("IV", four.toString());
    }

    @Test
    void testToStringForty() {
        RomanNumeralsNumber forty = new RomanNumeralsNumber(40);
        assertEquals("XL", forty.toString());
    }

    @Test
    void testToStringFourHundred() {
        RomanNumeralsNumber fourHundred = new RomanNumeralsNumber(400);
        assertEquals("CD", fourHundred.toString());
    }

    @Test
    void testToStringNine() {
        RomanNumeralsNumber nine = new RomanNumeralsNumber(9);
        assertEquals("IX", nine.toString());
    }

    @Test
    void testToStringNinety() {
        RomanNumeralsNumber ninety = new RomanNumeralsNumber(90);
        assertEquals("XC", ninety.toString());
    }

    @Test
    void testToStringNineHundred() {
        RomanNumeralsNumber nineHundred = new RomanNumeralsNumber(900);
        assertEquals("CM", nineHundred.toString());
    }

    @Test
    void testToStringSixSevenEight() {
        RomanNumeralsNumber six = new RomanNumeralsNumber(6);
        RomanNumeralsNumber seven = new RomanNumeralsNumber(7);
        RomanNumeralsNumber eight = new RomanNumeralsNumber(8);
        assertEquals("VI", six.toString());
        assertEquals("VII", seven.toString());
        assertEquals("VIII", eight.toString());
    }

    @Test
    void testToStringSixtySeventyEighty() {
        RomanNumeralsNumber sixty = new RomanNumeralsNumber(60);
        RomanNumeralsNumber seventy = new RomanNumeralsNumber(70);
        RomanNumeralsNumber eighty = new RomanNumeralsNumber(80);
        assertEquals("LX", sixty.toString());
        assertEquals("LXX", seventy.toString());
        assertEquals("LXXX", eighty.toString());
    }

    @Test
    void testToStringSixHundredSevenHundredEightHundred() {
        RomanNumeralsNumber sixHundred = new RomanNumeralsNumber(600);
        RomanNumeralsNumber sevenHundred = new RomanNumeralsNumber(700);
        RomanNumeralsNumber eightHundred = new RomanNumeralsNumber(800);
        assertEquals("DC", sixHundred.toString());
        assertEquals("DCC", sevenHundred.toString());
        assertEquals("DCCC", eightHundred.toString());
    }

    @Test
    void testFortyPlusOneTwoThree() {
        RomanNumeralsNumber fortyOne = new RomanNumeralsNumber(41);
        RomanNumeralsNumber fortyTwo = new RomanNumeralsNumber(42);
        RomanNumeralsNumber fortyThree = new RomanNumeralsNumber(43);
        assertEquals("XLI", fortyOne.toString());
        assertEquals("XLII", fortyTwo.toString());
        assertEquals("XLIII", fortyThree.toString());
    }

    @Test
    void testFortyPlusSixSevenEight() {
        RomanNumeralsNumber fortySix = new RomanNumeralsNumber(46);
        RomanNumeralsNumber fortySeven = new RomanNumeralsNumber(47);
        RomanNumeralsNumber fortyEight = new RomanNumeralsNumber(48);
        assertEquals("XLVI", fortySix.toString());
        assertEquals("XLVII", fortySeven.toString());
        assertEquals("XLVIII", fortyEight.toString());
    }

    @Test
    void testNinetyPlusOneTwoThree() {
        RomanNumeralsNumber ninetyOne = new RomanNumeralsNumber(91);
        RomanNumeralsNumber ninetyTwo = new RomanNumeralsNumber(92);
        RomanNumeralsNumber ninetyThree = new RomanNumeralsNumber(93);
        assertEquals("XCI", ninetyOne.toString());
        assertEquals("XCII", ninetyTwo.toString());
        assertEquals("XCIII", ninetyThree.toString());
    }

    @Test
    void testNinetyPlusSixSevenEight() {
        RomanNumeralsNumber ninetySix = new RomanNumeralsNumber(96);
        RomanNumeralsNumber ninetySeven = new RomanNumeralsNumber(97);
        RomanNumeralsNumber ninetyEight = new RomanNumeralsNumber(98);
        assertEquals("XCVI", ninetySix.toString());
        assertEquals("XCVII", ninetySeven.toString());
        assertEquals("XCVIII", ninetyEight.toString());
    }

    private String repeatLastChar(String s) {
        char ch = s.charAt(s.length() - 1);
        return s + ch;
    }

    @Test
    void testToStringTensPlusOneTwoThree() {
        RomanNumeralsNumber number;
        String expected, actual;
        for (String tens = "X";
             !tens.equals("XXXX");
             tens = repeatLastChar(tens)) {
            for (String ones = "I";
                 !ones.equals("IIII");
                 ones = repeatLastChar(ones)) {
                number = new RomanNumeralsNumber(tens.length() * 10
                        + ones.length());
                expected = tens + ones;
                actual = number.toString();
                assertEquals(expected, actual);
            }
        }
    }

    @Test
    void testToStringFiftyPlusTensPlusOneTwoThree() {
        RomanNumeralsNumber number;
        String expected, actual;
        for (String fiftyPlusTens = "LX";
             !fiftyPlusTens.equals("LXXXX");
             fiftyPlusTens = repeatLastChar(fiftyPlusTens)) {
            for (String ones = "I";
                 !ones.equals("IIII");
                 ones = repeatLastChar(ones)) {
                number = new RomanNumeralsNumber(40 + fiftyPlusTens.length()
                        * 10 + ones.length());
                expected = fiftyPlusTens + ones;
                actual = number.toString();
                assertEquals(expected, actual);
            }
        }
    }

    @Test
    void testToStringTensPlusSixSevenEight() {
        RomanNumeralsNumber number;
        String expected, actual;
        for (String tens = "X";
             !tens.equals("XXXX");
             tens = repeatLastChar(tens)) {
            for (String onesWithFive = "VI";
                 !onesWithFive.equals("VIIII");
                 onesWithFive = repeatLastChar(onesWithFive)) {
                number = new RomanNumeralsNumber(tens.length() * 10
                        + onesWithFive.length() + 4);
                expected = tens + onesWithFive;
                actual = number.toString();
                assertEquals(expected, actual);
            }
        }
    }

    @Test
    void testToStringFiftyPlusTensPlusSixSevenEight() {
        RomanNumeralsNumber number;
        String expected, actual;
        for (String fiftyPlusTens = "LX";
             !fiftyPlusTens.equals("LXXXX");
             fiftyPlusTens = repeatLastChar(fiftyPlusTens)) {
            for (String onesWithFive = "VI";
                 !onesWithFive.equals("VIIII");
                 onesWithFive = repeatLastChar(onesWithFive)) {
                number = new RomanNumeralsNumber(40 + fiftyPlusTens.length()
                        * 10 + onesWithFive.length() + 4);
                expected = fiftyPlusTens + onesWithFive;
                actual = number.toString();
                assertEquals(expected, actual);
            }
        }
    }

    @Test
    void testToStringTensPlusFour() {
        RomanNumeralsNumber fourteen = new RomanNumeralsNumber(14);
        RomanNumeralsNumber twentyFour = new RomanNumeralsNumber(24);
        RomanNumeralsNumber thirtyFour = new RomanNumeralsNumber(34);
        RomanNumeralsNumber fortyFour = new RomanNumeralsNumber(44);
        RomanNumeralsNumber fiftyFour = new RomanNumeralsNumber(54);
        RomanNumeralsNumber sixtyFour = new RomanNumeralsNumber(64);
        RomanNumeralsNumber seventyFour = new RomanNumeralsNumber(74);
        RomanNumeralsNumber eightyFour = new RomanNumeralsNumber(84);
        RomanNumeralsNumber ninetyFour = new RomanNumeralsNumber(94);
        assertEquals("XIV", fourteen.toString());
        assertEquals("XXIV", twentyFour.toString());
        assertEquals("XXXIV", thirtyFour.toString());
        assertEquals("XLIV", fortyFour.toString());
        assertEquals("LIV", fiftyFour.toString());
        assertEquals("LXIV", sixtyFour.toString());
        assertEquals("LXXIV", seventyFour.toString());
        assertEquals("LXXXIV", eightyFour.toString());
        assertEquals("XCIV", ninetyFour.toString());
    }

    @Test
    void testToStringTensPlusNine() {
        RomanNumeralsNumber nineteen = new RomanNumeralsNumber(19);
        RomanNumeralsNumber twentyNine = new RomanNumeralsNumber(29);
        RomanNumeralsNumber thirtyNine = new RomanNumeralsNumber(39);
        RomanNumeralsNumber fortyNine = new RomanNumeralsNumber(49);
        RomanNumeralsNumber fiftyNine = new RomanNumeralsNumber(59);
        RomanNumeralsNumber sixtyNine = new RomanNumeralsNumber(69);
        RomanNumeralsNumber seventyNine = new RomanNumeralsNumber(79);
        RomanNumeralsNumber eightyNine = new RomanNumeralsNumber(89);
        RomanNumeralsNumber ninetyNine = new RomanNumeralsNumber(99);
        assertEquals("XIX", nineteen.toString());
        assertEquals("XXIX", twentyNine.toString());
        assertEquals("XXXIX", thirtyNine.toString());
        assertEquals("XLIX", fortyNine.toString());
        assertEquals("LIX", fiftyNine.toString());
        assertEquals("LXIX", sixtyNine.toString());
        assertEquals("LXXIX", seventyNine.toString());
        assertEquals("LXXXIX", eightyNine.toString());
        assertEquals("XCIX", ninetyNine.toString());
    }

    @Test
    void testToString101To399Skip200And300() {
        String expected, actual;
        RomanNumeralsNumber number, underHundred;
        int hundredPart;
        for (String hundreds = "C";
             !hundreds.equals("CCCC");
             hundreds = repeatLastChar(hundreds)) {
            hundredPart = hundreds.length() * 100;
            for (int i = 1; i < 100; i++) {
                underHundred = new RomanNumeralsNumber(i);
                number = new RomanNumeralsNumber(hundredPart + i);
                expected = hundreds + underHundred.toString();
                actual = number.toString();
                assertEquals(expected, actual);
            }
        }
    }

    @Test
    void testToString401To499() {
        String expected, actual;
        RomanNumeralsNumber number, underHundred;
        for (int i = 1; i < 100; i++) {
            underHundred = new RomanNumeralsNumber(i);
            number = new RomanNumeralsNumber(400 + i);
            expected = "CD" + underHundred.toString();
            actual = number.toString();
            assertEquals(expected, actual);
        }
    }

    @Test
    void testToString501To599() {
        String expected, actual;
        RomanNumeralsNumber number, underHundred;
        for (int i = 1; i < 100; i++) {
            underHundred = new RomanNumeralsNumber(i);
            number = new RomanNumeralsNumber(500 + i);
            expected = "D" + underHundred.toString();
            actual = number.toString();
            assertEquals(expected, actual);
        }
    }

    @Test
    void testToString601To899Skip600And700() {
        String expected, actual;
        RomanNumeralsNumber number, underHundred;
        int hundredPart;
        for (String hundreds = "DC";
             !hundreds.equals("DCCCC");
             hundreds = repeatLastChar(hundreds)) {
            hundredPart = hundreds.length() * 100 + 400;
            for (int i = 1; i < 100; i++) {
                underHundred = new RomanNumeralsNumber(i);
                number = new RomanNumeralsNumber(hundredPart + i);
                expected = hundreds + underHundred.toString();
                actual = number.toString();
                assertEquals(expected, actual);
            }
        }
    }

    @Test
    void testToString901To999() {
        String expected, actual;
        RomanNumeralsNumber number, underHundred;
        for (int i = 1; i < 100; i++) {
            underHundred = new RomanNumeralsNumber(i);
            number = new RomanNumeralsNumber(900 + i);
            expected = "CM" + underHundred.toString();
            actual = number.toString();
            assertEquals(expected, actual);
        }
    }

    @Test
    void testToString1001To3999Skip2000And3000() {
        String expected, actual;
        RomanNumeralsNumber number, underThousand;
        int thousandPart;
        for (String thousands = "M";
             !thousands.equals("MMMM");
             thousands = repeatLastChar(thousands)) {
            thousandPart = thousands.length() * 1000;
            for (int i = 1; i < 1000; i++) {
                underThousand = new RomanNumeralsNumber(i);
                number = new RomanNumeralsNumber(thousandPart + i);
                expected = thousands + underThousand.toString();
                actual = number.toString();
                assertEquals(expected, actual);
            }
        }
    }

    @Test
    void testReferentialEquality() {
        int n = RANDOM.nextInt(3998) + 1;
        RomanNumeralsNumber someNumber = new RomanNumeralsNumber(n);
        assertEquals(someNumber, someNumber);
    }

    @Test
    void testNotEqualsNull() {
        int n = RANDOM.nextInt(3998) + 1;
        RomanNumeralsNumber someNumber = new RomanNumeralsNumber(n);
        assertNotEquals(someNumber, null);
    }

    @Test
    void testNotEqualsDiffClass() {
        int n = RANDOM.nextInt(3998) + 1;
        RomanNumeralsNumber someNumber = new RomanNumeralsNumber(n);
        ComplexNumber someNumberPlusZeroImaginary = new ComplexNumber(n, 0);
        assertNotEquals(someNumber, someNumberPlusZeroImaginary);
    }

    @Test
    void testNotEqualsDiffNumber() {
        int n = RANDOM.nextInt(1997) + 1;
        int nPlusRandom = n + RANDOM.nextInt(n) + 1;
        RomanNumeralsNumber someNumber = new RomanNumeralsNumber(n);
        RomanNumeralsNumber diffNumber = new RomanNumeralsNumber(nPlusRandom);
        assertNotEquals(someNumber, diffNumber);
    }

    @Test
    void testEquals() {
        System.out.println("equals");
        int n = RANDOM.nextInt(3998) + 1;
        RomanNumeralsNumber someNumber = new RomanNumeralsNumber(n);
        RomanNumeralsNumber sameNumber = new RomanNumeralsNumber(n);
        assertEquals(someNumber, sameNumber);
    }

    @Test
    void testCompareTo() {
        System.out.println("compareTo");
        int a = RANDOM.nextInt(795) + 1;
        int b = a + RANDOM.nextInt(a) + 1;
        int c = b + RANDOM.nextInt(b) + 1;
        int d = c + RANDOM.nextInt(c) + 1;
        int e = d + RANDOM.nextInt(d) + 1;
        RomanNumeralsNumber numberA = new RomanNumeralsNumber(a);
        RomanNumeralsNumber numberB = new RomanNumeralsNumber(b);
        RomanNumeralsNumber numberC = new RomanNumeralsNumber(c);
        RomanNumeralsNumber numberD = new RomanNumeralsNumber(d);
        RomanNumeralsNumber numberE = new RomanNumeralsNumber(e);
        ArrayList<RomanNumeralsNumber> expected = new ArrayList<>();
        expected.add(numberA);
        expected.add(numberB);
        expected.add(numberC);
        expected.add(numberD);
        expected.add(numberE);
        ArrayList<RomanNumeralsNumber> actual = new ArrayList<>();
        actual.add(numberE);
        actual.add(numberB);
        actual.add(numberC);
        actual.add(numberA);
        actual.add(numberD);
        Collections.sort(actual);
        assertEquals(expected, actual);
    }

    @Test
    void testPlus() {
        System.out.println("plus");
        int a = RANDOM.nextInt(1998) + 1;
        int b = RANDOM.nextInt(1998) + 1;
        RomanNumeralsNumber addendA = new RomanNumeralsNumber(a);
        RomanNumeralsNumber addendB = new RomanNumeralsNumber(b);
        RomanNumeralsNumber expected = new RomanNumeralsNumber(a + b);
        RomanNumeralsNumber actual = addendA.plus(addendB);
        assertEquals(expected, actual);
    }

    @Test
    void testPlusInt() {
        int a = RANDOM.nextInt(1998) + 1;
        int b = RANDOM.nextInt(1998) + 1;
        RomanNumeralsNumber addendA = new RomanNumeralsNumber(a);
        RomanNumeralsNumber expected = new RomanNumeralsNumber(a + b);
        RomanNumeralsNumber actual = addendA.plus(b);
        assertEquals(expected, actual);
    }

    @Test
    void testPlusOverflow() {
        int a = RANDOM.nextInt(2000) + 2000;
        int b = a + RANDOM.nextInt(4000 - a);
        RomanNumeralsNumber addendA = new RomanNumeralsNumber(a);
        RomanNumeralsNumber addendB = new RomanNumeralsNumber(b);
        Throwable throwable = assertThrows(ArithmeticException.class, () -> {
            RomanNumeralsNumber result = addendA.plus(addendB);
            System.out.println(addendA.toString() + " + " + addendB.toString()
                    + " is said to be " + result.toString());
        });
        String excMsg = throwable.getMessage();
        assert excMsg != null : "Message should not be null";
        System.out.println("Trying to add " + addendA.toString() + " to "
                + addendB.toString()
                + " correctly caused ArithmeticException");
        System.out.println("\"" + excMsg + "\"");
    }

    @Test
    void testPlusIntOverflow() {
        int a = RANDOM.nextInt(2000) + 2000;
        int b = a + RANDOM.nextInt(4000 - a);
        RomanNumeralsNumber addendA = new RomanNumeralsNumber(a);
        Throwable throwable = assertThrows(ArithmeticException.class, () -> {
            RomanNumeralsNumber result = addendA.plus(b);
            System.out.println(addendA.toString() + " + " + b
                    + " is said to be " + result.toString());
        });
        String excMsg = throwable.getMessage();
        assert excMsg != null : "Message should not be null";
        System.out.println("Trying to add " + addendA.toString() + " to "
                + b + " correctly caused ArithmeticException");
        System.out.println("\"" + excMsg + "\"");
    }

    @Test
    void testNegate() {
        int n = RANDOM.nextInt(3999) + 1;
        RomanNumeralsNumber number = new RomanNumeralsNumber(n);
        Throwable throwable = assertThrows(RuntimeException.class, () -> {
            RomanNumeralsNumber result = number.negate();
            System.out.println(number.toString() + " is said to be "
                    + result.toString());
        });
        assert (throwable instanceof ArithmeticException
                || throwable instanceof UnsupportedOperationException);
        String excMsg = throwable.getMessage();
        assert excMsg != null : "Message should not be null";
        System.out.println("Trying to negate " + number.toString()
                + " correctly caused " + throwable.getClass().getName());
        System.out.println("\"" + excMsg + "\"");
    }

    @Test
    void testMinus() {
        System.out.println("minus");
        int a = RANDOM.nextInt(3988) + 10;
        int b = RANDOM.nextInt(a - 3) + 1;
        RomanNumeralsNumber minuend = new RomanNumeralsNumber(a);
        RomanNumeralsNumber subtrahend = new RomanNumeralsNumber(b);
        RomanNumeralsNumber expected = new RomanNumeralsNumber(b - a);
        RomanNumeralsNumber actual = minuend.minus(subtrahend);
        assertEquals(expected, actual);
    }

    @Test
    void testMinusInt() {
        int a = RANDOM.nextInt(1998) + 1;
        int b = a + RANDOM.nextInt(a) + 1;
        RomanNumeralsNumber minuend = new RomanNumeralsNumber(b);
        RomanNumeralsNumber expected = new RomanNumeralsNumber(b - a);
        RomanNumeralsNumber actual = minuend.minus(a);
        assertEquals(expected, actual);
    }

    @Test
    void testMinusOverflow() {
        int a = RANDOM.nextInt(2000) + 1;
        int b = a + RANDOM.nextInt(2000);
        RomanNumeralsNumber minuend = new RomanNumeralsNumber(a);
        RomanNumeralsNumber subtrahend = new RomanNumeralsNumber(b);
        Throwable throwable = assertThrows(ArithmeticException.class, () -> {
            RomanNumeralsNumber result = minuend.minus(subtrahend);
            System.out.println(minuend.toString() + " minus "
                    + subtrahend.toString() + " is said to be "
                    + result.toString());
        });
        String excMsg = throwable.getMessage();
        assert excMsg != null : "Message should not be null";
        System.out.println("Trying to subtract " + subtrahend.toString()
                + " from " + minuend.toString()
                + " correctly caused ArithmeticException");
        System.out.println("\"" + excMsg + "\"");
    }

    @Test
    void testMinusIntOverflow() {
        int a = RANDOM.nextInt(2000) + 1;
        int b = a + RANDOM.nextInt(2000);
        RomanNumeralsNumber minuend = new RomanNumeralsNumber(a);
        Throwable throwable = assertThrows(ArithmeticException.class, () -> {
            RomanNumeralsNumber result = minuend.minus(b);
            System.out.println(minuend.toString() + " minus " + b
                    + " is said to be " + result.toString());
        });
        String excMsg = throwable.getMessage();
        assert excMsg != null : "Message should not be null";
        System.out.println("Trying to subtract " + b + " from "
                + minuend.toString()
                + " correctly caused ArithmeticException");
        System.out.println("\"" + excMsg + "\"");
    }

    @Test
    void testTimes() {
        System.out.println("times");
        int a = RANDOM.nextInt(30) + 1;
        int b = a + RANDOM.nextInt(a) + 1;
        RomanNumeralsNumber multiplicandA = new RomanNumeralsNumber(a);
        RomanNumeralsNumber multiplicandB = new RomanNumeralsNumber(b);
        RomanNumeralsNumber expected = new RomanNumeralsNumber(a * b);
        RomanNumeralsNumber actual = multiplicandA.times(multiplicandB);
        assertEquals(expected, actual);
    }

    @Test
    void testTimesInt() {
        int a = RANDOM.nextInt(30) + 1;
        int b = a + RANDOM.nextInt(a) + 1;
        RomanNumeralsNumber multiplicandA = new RomanNumeralsNumber(a);
        RomanNumeralsNumber expected = new RomanNumeralsNumber(a * b);
        RomanNumeralsNumber actual = multiplicandA.times(b);
        assertEquals(expected, actual);
    }

    @Test
    void testTimesOverflow() {
        fail("Haven't written test yet");
    }

    @Test
    void testDivides() {
        System.out.println("divides");
        int a = RANDOM.nextInt(30) + 1;
        int b = a + RANDOM.nextInt(a) + 1;
        int c = a * b;
        RomanNumeralsNumber dividend = new RomanNumeralsNumber(c);
        RomanNumeralsNumber divisor = new RomanNumeralsNumber(b);
        RomanNumeralsNumber expected = new RomanNumeralsNumber(a);
        try {
            RomanNumeralsNumber actual = dividend.divides(divisor);
            assertEquals(expected, actual);
        } catch (NotDivisibleException nde) {
            String msg = "Trying to divide " + dividend.toString() + " by "
                    + divisor.toString()
                    + " should not have caused NotDivisibleException: "
                    + nde.getMessage();
            fail(msg);
        } catch (Exception e) {
            String msg = e.getClass().getName()
                    + " should not have occurred trying to divide "
                    + dividend.toString() + " by " + divisor.toString();
            fail(msg);
        }
    }

    @Test
    void testDividesInt() {
        int a = RANDOM.nextInt(30) + 1;
        int b = a + RANDOM.nextInt(a) + 1;
        int c = a * b;
        RomanNumeralsNumber dividend = new RomanNumeralsNumber(c);
        RomanNumeralsNumber expected = new RomanNumeralsNumber(a);
        try {
            RomanNumeralsNumber actual = dividend.divides(b);
            assertEquals(expected, actual);
        } catch (NotDivisibleException nde) {
            String msg = "Trying to divide " + dividend.toString() + " by " + b
                    + " should not have caused NotDivisibleException: "
                    + nde.getMessage();
            fail(msg);
        } catch (Exception e) {
            String msg = e.getClass().getName()
                    + " should not have occurred trying to divide "
                    + dividend.toString() + " by " + b;
            fail(msg);
        }
    }

    @Test
    void testDivideByNotDivisible() {
        int a = RANDOM.nextInt(60) + 1;
        int b = a + RANDOM.nextInt(a) + 1;
        int c = a * b;
        RomanNumeralsNumber badDividend = new RomanNumeralsNumber(c);
        RomanNumeralsNumber badDivisor = new RomanNumeralsNumber(b + 1);
        fail("Haven't finished writing test yet");
    }

    @Test
    void testDivideByNotDivisibleInt() {
        int a = RANDOM.nextInt(60) + 1;
        int b = a + RANDOM.nextInt(a) + 1;
        int c = a * b;
        RomanNumeralsNumber badDividend = new RomanNumeralsNumber(c);
        int badDivisor = b + 1;
        fail("Haven't finished writing test yet");
    }

    @Test
    void testHashCode() {
        System.out.println("hashCode");
        RomanNumeralsNumber number;
        int actual;
        for (int expected = 1; expected < 4000; expected++) {
            number = new RomanNumeralsNumber(expected);
            actual = number.hashCode();
            assertEquals(expected, actual);
        }
    }

    // TODO: Test constructor rejects negative parameter

    // TODO: Test constructor rejects 0

    // TODO: Test constructor rejects parameter greater than 3999
    
}