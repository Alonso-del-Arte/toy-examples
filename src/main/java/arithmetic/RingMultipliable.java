package arithmetic;

/**
 * Numbers of a type <code>T</code> that can be multiplied by other numbers of
 * type <code>T</code>. For example, fractions can be multiplied by other
 * fractions.
 * @param <T> The type of the elements to be multiplied. For example,
 *           <code>Fraction</code>.
 * @author Alonso del Arte
 */
public interface RingMultipliable<T extends RingMultipliable<T>> {

    // TODO: Write Javadoc
    T times(T multiplicand);

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

}
