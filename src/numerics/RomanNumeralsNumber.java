/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package numerics;

import arithmetic.Arithmeticable;
import arithmetic.NotDivisibleException;

/**
 * Holds an integer in Roman numeral representation.
 * @author Alonso del Arte
 */
public class RomanNumeralsNumber implements Arithmeticable<RomanNumeralsNumber>,
        Comparable<RomanNumeralsNumber> {

    // TODO: Write getter or add @Getter annotation
    private final short value;

    private static String process(int digit) {
        switch (digit) {
            case 0:
                return "";
            case 1:
                return "I";
            case 2:
                return "II";
            case 3:
                return "III";
            case 4:
                return "IV";
            case 5:
                return "V";
            case 9:
                return "IX";
            case 10:
                return "X";
            case 20:
                return "XX";
            case 30:
                return "XXX";
            case 40:
                return "XL";
            case 50:
                return "L";
            case 90:
                return "XC";
            case 100:
                return "C";
            case 200:
                return "CC";
            case 300:
                return "CCC";
            case 400:
                return "CD";
            case 500:
                return "D";
            case 900:
                return "CM";
            case 1000:
                return "M";
            case 2000:
                return "MM";
            case 3000:
                return "MMM";
            default:
                return "???";
        }
    }

    @Override
    public String toString() {
        String unprocessed = Short.toString(this.value);
        int currLen = unprocessed.length();
        String numeral;
        StringBuilder processed = new StringBuilder();
        char digit;
        short multiplier = 1;
        int digitMult, fiveMult;
        while (currLen > 0) {
            numeral = "";
            digit = unprocessed.charAt(--currLen);
            digitMult = (digit - 48) * multiplier;
            if (digit > '4' && digit < '9') {
                fiveMult = 5 * multiplier;
                numeral = process(fiveMult);
                digitMult -= fiveMult;
            }
            numeral = numeral + process(digitMult);
            processed.insert(0, numeral);
            unprocessed = unprocessed.substring(0, currLen);
            multiplier *= 10;
        }
        return processed.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!obj.getClass().equals(this.getClass())) {
            return false;
        }
        return this.value == ((RomanNumeralsNumber) obj).value;
    }

    @Override
    public int hashCode() {
        return this.value;
    }

    @Override
    public RomanNumeralsNumber plus(RomanNumeralsNumber addend) {
        if (this.value + addend.value > 3999) {
            throw new ArithmeticException("The number "
                    + (this.value + addend.value) + " exceeds 3999");
        }
        return new RomanNumeralsNumber(this.value + addend.value);
    }

    @Override
    public RomanNumeralsNumber plus(int addend) {
        if (this.value + addend > 3999) {
            throw new ArithmeticException("The number "
                    + (this.value + addend) + " exceeds 3999");
        }
        return new RomanNumeralsNumber(this.value + addend);
    }

    @Override
    public RomanNumeralsNumber negate() {
        String excMsg = "This class can't represent negative numbers";
        throw new UnsupportedOperationException(excMsg);
    }

    @Override
    public RomanNumeralsNumber minus(RomanNumeralsNumber subtrahend) {
        if (this.value - subtrahend.value < 1) {
            String excMsg = "The number " + (this.value - subtrahend.value)
                    + " is outside the range of this class";
            throw new ArithmeticException(excMsg);
        }
        return new RomanNumeralsNumber(this.value - subtrahend.value);
    }

    @Override
    public RomanNumeralsNumber minus(int subtrahend) {
        if (this.value - subtrahend < 1) {
            String excMsg = "The number " + (this.value - subtrahend)
                    + " is outside the range of this class";
            throw new ArithmeticException(excMsg);
        }
        return new RomanNumeralsNumber(this.value - subtrahend);
    }

    @Override
    public RomanNumeralsNumber times(RomanNumeralsNumber multiplicand) {
        if (this.value * multiplicand.value > 3999) {
            throw new ArithmeticException("The number "
                    + (this.value * multiplicand.value) + " exceeds 3999");
        }
        return new RomanNumeralsNumber(this.value * multiplicand.value);
    }

    @Override
    public RomanNumeralsNumber times(int multiplicand) {
        if (this.value * multiplicand > 3999) {
            throw new ArithmeticException("The number "
                    + (this.value * multiplicand) + " exceeds 3999");
        }
        return new RomanNumeralsNumber(this.value * multiplicand);
    }

    @Override
    public RomanNumeralsNumber divides(RomanNumeralsNumber divisor)
            throws NotDivisibleException {
        int remainder = this.value % divisor.value;
        if (remainder == 0) {
            return new RomanNumeralsNumber(this.value / divisor.value);
        } else {
            String excMsg = this.toString() + " divided by "
                    + divisor.toString() + " leaves a remainder of "
                    + remainder;
            throw new NotDivisibleException(excMsg, this, divisor);
        }
    }

    // STUB TO FAIL THE FIRST TEST
    @Override
    public RomanNumeralsNumber divides(int divisor)
            throws NotDivisibleException {
        return new RomanNumeralsNumber(this.value / divisor);
    }

    @Override
    public int compareTo(RomanNumeralsNumber other) {
        return Short.compare(this.value, other.value);
    }

    // STUB TO FAIL THE FIRST TEST
    public static RomanNumeralsNumber parse(String s) {
        return new RomanNumeralsNumber(3999);
    }

    public RomanNumeralsNumber(int n) {
        // TODO: Add validation: no 0, no negative numbers
        // TODO: Determine maximum n and add validation
        this.value = (short) n;
    }

}
