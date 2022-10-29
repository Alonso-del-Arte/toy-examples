package arithmetic;

/**
 * Numbers of a type <code>T</code> that can be added to integers. For example,
 * fractions can be added to integers. The interface segregation principle is
 * the motivation for this interface: an instance of type <code>T</code> may be
 * summable to other instances of type <code>T</code> and it might be
 * multipliable by integers, but it might not be multipliable by other instances
 * of type <code>T</code>. For example, we can add ten dollars to a hundred
 * dollars, and we can multiply ten dollars by 100. But it doesn't really make
 * sense to multiply ten dollars by one hundred dollars.
 * @param <T> The type of the elements to be multiplied by each other. For
 *           example, <code>Fraction</code>.
 * @author Alonso del Arte
 */
public interface IntegerMultipliable<T extends IntegerMultipliable<T>> {

    /**
     * Multiplies this number by an integer.
     * @param multiplicand The integer to multiply by. For example, 12.
     * @return The product. For example, if this number is &alpha;, this
     * function returns 12&alpha;.
     * @throws ArithmeticException If an overflow or underflow occurs.
     */
    T times(int multiplicand);

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
