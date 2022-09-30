package arithmetic;

/**
 * Numbers of a type <code>T</code> that can be added to other numbers of type
 * <code>T</code>. For example, fractions can be added to other fractions.
 * @param <T> The type of the elements to be summed. For example,
 *           <code>Fraction</code>.
 * @author Alonso del Arte
 */
public interface RingSummable<T extends RingSummable<T>> {

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

    /**
     * Subtracts a number of type <code>T</code> from this one. Implementations
     * may include overflow checking, but are not required to.
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
    T minus(T subtrahend);

}
