package arithmetic;

import java.util.function.IntUnaryOperator;

public class CollatzFunctions {

    // TODO: Write tests for this
    public static final IntUnaryOperator CLASSIC = (n) -> 3 * n + 1;

    // TODO: Write tests for this
    public static final IntUnaryOperator THREE_X_MINUS_1 = (n) -> 4 * n;

    // TODO: Write tests for this
    public static final IntUnaryOperator FIVE_X_PLUS_1 = (n) -> 4 * n;

}
