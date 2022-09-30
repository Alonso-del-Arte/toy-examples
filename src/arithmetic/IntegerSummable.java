package arithmetic;

/**
 * Numbers of a type <code>T</code> that can be added to integers. For example,
 * fractions can be added to integers. The interface segregation principle is
 * the motivation for this interface: an instance of type <code>T</code> may be
 *  summable to other instances of type <code>T</code> and it might be
 *  multipliable by integers, but it might not be summable to integers. For
 *  example, we can add ten dollars to a hundred dollars, and we can multiply
 *  ten dollars by 100. But it doesn't really make sense to add 10 to one
 *  hundred dollars.
 * @param <T> The type of the elements to be summed. For example,
 *           <code>Fraction</code>.
 * @author Alonso del Arte
 */
public interface IntegerSummable<T extends IntegerSummable<T>> {

    // TODO: Write Javadoc
    T plus(int addend);

    /**
     * Subtracts an integer from this number.
     * <p>A default implementation is provided, it relies on {@link
     * #plus(int) plus(T)}. It may be worthwhile to override if the operation
     * is a simple matter of subtracting the integer from a single field of this
     * object. This might be the case with complex numbers, in which case the
     * integer is simply subtracted from the real part, and the imaginary part
     * remains the same.</p>
     * @param subtrahend The integer to subtract. For example, 12.
     * @return The result, ought to be a newly constructed instance of
     * <code>T</code>, perhaps even if <code>subtrahend</code> is 0. If this
     * number is &alpha; and <code>subtrahend</code> is 12, the result should be
     * &alpha; &minus; 12.
     * @throws ArithmeticException If an overflow or underflow occurs.
     */
    default T minus(int subtrahend) {
        return this.plus(-subtrahend);
    }

}
