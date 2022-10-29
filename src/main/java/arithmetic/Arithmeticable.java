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
package arithmetic;

/**
 * This interface indicates that the implementing concrete class provides the
 * basic arithmetic operations for numbers represented by that class. The basic
 * arithmetic operations are addition, subtraction, multiplication and division.
 * Also includes negation.
 * <p>"Arithmeticable" is of course not a valid word of the English language,
 * and it ought to be flagged as a misspelling every time it does not occur as a
 * type identifier.</p>
 * <p>This interface is understood to operate in the usual commutative algebra,
 * in which <i>a</i> + <i>b</i> = <i>b</i> + <i>a</i> and <i>ab</i> =
 * <i>ba</i>.</p>
 * <p>This interface contains the basic operations for both operands being of
 * type <code>T</code> and the result also of that type and for one operand
 * being of type <code>T</code> and the other of type <code>int</code>, with the
 * result being of type <code>T</code> even if it can be represented by
 * <code>int</code>.</p>
 * <p>This interface contains default implementations. These are indicated in
 * the pertinent documentation.</p>
 * @param <T> A type that is some kind of algebraic integer or number. It's nice
 *           if <code>T</code> is <code>Comparable</code>, or a
 *           <code>Comparator</code>, but this is not required.
 * @author Alonso del Arte
 */
public interface Arithmeticable<T extends Arithmeticable<T>> {

    /**
     * Adds a number of type <code>T</code> to this one.
     * @param addend The number to add. For example, &alpha; or &beta;.
     * @return The result, ought to be a newly constructed instance of
     * <code>T</code>. For the examples, this would be one of &alpha; + &beta,
     * 2&alpha; or 2&beta;. Also, <code>alpha.plus(beta)</code> should give the
     * same result as <code>beta.plus(alpha)</code>.
     * @throws ArithmeticException If an overflow or underflow occurs.
     */
    T plus(T addend);

    // TODO: Write Javadoc
    T plus(int addend);

    /**
     * Multiplies this number by &minus;1. A default implementation is provided,
     * which relies on {@link #times(int)}. In some cases, it may be more
     * efficient to override this default implementation so as to negate the
     * pertinent primitive number fields one by one. There should be no
     * overflows, except in a few extreme cases.
     * @return The result, ought to be a newly constructed instance of
     * <code>T</code>. For example, if this number is &alpha;, the result should
     * be &minus;&alpha;.
     * @throws ArithmeticException If an overflow or underflow occurs. That is
     * extremely unlikely for this function.
     */
    default T negate() {
        return this.times(-1);
    }

    /**
     * Subtracts a number of type <code>T</code> from this one. Implementations
     * may include overflow checking, but are not required to.
     * <p>A default implementation is provided, it relies on {@link
     * #plus(Arithmeticable) plus(T)} and {@link #negate()}. It may be
     * worthwhile to override if the operation is a simple matter of lining up
     * the fields of this number to the fields of the subtrahend and subtracting
     * one by one. It is not worth overriding if that requires repeating many
     * lines from <code>plus()</code> and/or the creation of several
     * intermediate result objects. For example, it may be worthwhile to
     * override for complex numbers, it might not be worthwhile to override for
     * purely real fractions.</p>
     * <p>For the examples in the following explanations, suppose that this
     * object is either &alpha; or &beta;, which are represented by
     * <code>alpha</code> and <code>beta</code>, respectively, both instances of
     * <code>T</code>.</p>
     * @param subtrahend The number to subtract. For example, either &alpha; or
     *                   &beta;.
     * @return The result, ought to be a newly constructed instance of
     * <code>T</code>. For the examples, this would be one of 0, &alpha; &minus;
     * &beta; or &beta; &minus; &alpha;.
     * @throws ArithmeticException If an overflow or underflow occurs.
     */
    default T minus(T subtrahend) {
        return this.plus(subtrahend.negate());
    }

    /**
     * Subtracts an integer from this number.
     * <p>A default implementation is provided, it relies on {@link
     * #plus(Arithmeticable) plus(T)}. It may be worthwhile to override if the
     * operation is a simple matter of subtracting the integer from a single
     * field of this object. This might be the case with complex numbers, in
     * which case the integer is simply subtracted from the real part, and the
     * imaginary part remains the same.</p>
     * @param subtrahend The integer to subtract. For example, 12.
     * @return The result, ought to be a newly constructed instance of
     * <code>T</code>, perhaps even if <code>subtrahend</code> is 0. If this
     * algebraic integer is &alpha; and <code>subtrahend</code> is 12,
     * the result should be &alpha; &minus; 12.
     * @throws ArithmeticException If an overflow or underflow occurs.
     */
    default T minus(int subtrahend) {
        return this.plus(-subtrahend);
    }

    // TODO: Write Javadoc
    T times(T multiplicand);

    /**
     * Multiplies this number by an integer.
     * @param multiplicand The integer to multiply by. For example, 12.
     * @return The product. For example, if this number is &alpha;, this
     * function returns 12&alpha;.
     * @throws ArithmeticException If an overflow or underflow occurs.
     */
    T times(int multiplicand);

    /**
     * Divides this number by another number of type <code>T</code>.
     * @param divisor The number to divide by.
     * @return The result of the division.
     * @throws ArithmeticException If an overflow or underflow occurs, or if
     * <code>divisor</code> is 0.
     * @throws IllegalArgumentException If <code>divisor</code> is 0.
     * @throws NotDivisibleException If the result of dividing this number by
     * <code>divisor</code> isn't a number of type <code>T</code> (but don't use
     * this exception for division by zero). The occurrence of this exception
     * suggests that the division can be rounded to a number that <em>is</em> of
     * type <code>T</code>. Although this is a checked exception, implementing
     * classes may leave it out if it's not applicable (e.g., complex numbers,
     * fractions).
     */
    T divides(T divisor) throws NotDivisibleException;

    /**
     * Divides this number by an integer.
     * @param divisor The integer to divide by. For example, 12.
     * @return The result of the division. For example, if this number is
     * &alpha; and <code>divisor</code> is 12, this would return
     * <sup>&alpha;</sup>&frasl;<sub>12</sub>.
     * @throws ArithmeticException If an overflow or underflow occurs, or if
     * <code>divisor</code> is 0.
     * @throws IllegalArgumentException If <code>divisor</code> is 0.
     * @throws NotDivisibleException If the result of dividing this number by
     * <code>divisor</code> isn't a number of type <code>T</code> (but don't use
     * this exception for division by zero). The occurrence of this exception
     * suggests that the division can be rounded to a number that <em>is</em> of
     * type <code>T</code>. For example, suppose <code>T</code> represents
     * Gaussian integers. We see that 9 + <i>i</i> is a Gaussian integer, but
     * <sup>(9 + <i>i</i>)</sup>&frasl;<sub>12</sub> =
     * <sup>3</sup>&frasl;<sub>4</sub> +
     * <sup><i>i</i></sup>&frasl;<sub>12</sub>, which is not a Gaussian integer.
     * Although this is a checked exception, implementing classes may leave it
     * out if it's not applicable (e.g., complex numbers that are not
     * necessarily Gaussian integers, purely real fractions).
     */
    T divides(int divisor) throws NotDivisibleException;

}
