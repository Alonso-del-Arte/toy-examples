package arithmetic;

import java.util.function.IntUnaryOperator;

public class CollatzFunctions {

    public static final IntUnaryOperator CLASSIC
            = (n) -> (n % 2 == 0) ? n / 2 : Math.multiplyExact(3, n) + 1;

    public static final IntUnaryOperator THREE_X_MINUS_1
            = (n) -> (n % 2 == 0) ? n / 2 : Math.multiplyExact(3, n) - 1;

    // TODO: Write tests for this
    public static final IntUnaryOperator FIVE_X_PLUS_1 = (n) -> 4 * n;

}
