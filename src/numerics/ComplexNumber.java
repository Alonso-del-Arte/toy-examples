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

import arithmetic.Arithmeticable;

/**
 * Represents a complex number. A complex number has a real part and an
 * imaginary part. In this class, both parts are represented as floating point
 * numbers.
 * @author Alonso del Arte
 */
public class ComplexNumber implements Arithmeticable<ComplexNumber>  {

    private final double realPart, imagPart;

    @Override
    public String toString() {
        return this.realPart + " + " + this.imagPart + "i";
    }

    // TODO: Write test for this
    public double getRealPart() {
        return Double.NaN;
    }

    // TODO: Write test for this
    public double getImagPart() {
        return Double.NaN;
    }

    @Override
    public ComplexNumber plus(ComplexNumber addend) {
        double sumRe = this.realPart + addend.realPart;
        double sumIm = this.imagPart + addend.imagPart;
        if (Double.isInfinite(sumRe) || Double.isInfinite(sumIm)) {
            String excMsg = "Overflow occurred";
            throw new ArithmeticException(excMsg);
        }
        return new ComplexNumber(sumRe, sumIm);
    }

    // STUB TO FAIL THE FIRST TEST
    @Override
    public ComplexNumber plus(int addend) {
        return new ComplexNumber(0.0, 0.0);
    }

    // STUB TO FAIL THE FIRST TEST
    @Override
    public ComplexNumber negate() {
        return new ComplexNumber(0.0, 0.0);
    }

    // STUB TO FAIL THE FIRST TEST
    @Override
    public ComplexNumber times(ComplexNumber multiplicand) {
        return new ComplexNumber(0.0, 0.0);
    }

    // STUB TO FAIL THE FIRST TEST
    @Override
    public ComplexNumber times(int multiplicand) {
        return new ComplexNumber(0.0, 0.0);
    }

    // STUB TO FAIL THE FIRST TEST
    @Override
    public ComplexNumber divides(ComplexNumber divisor) {
        return new ComplexNumber(0.0, 0.0);
    }

    // STUB TO FAIL THE FIRST TEST
    @Override
    public ComplexNumber divides(int divisor) {
        return new ComplexNumber(0.0, 0.0);
    }

    public ComplexNumber(double re, double im) {
        this.realPart = re;
        this.imagPart = im;
    }

}
