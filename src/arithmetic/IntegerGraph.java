package arithmetic;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.function.IntUnaryOperator;

public class IntegerGraph {

    public static final int GENERAL_ITERATION_MAXIMUM = 64;

    private final IntUnaryOperator function;

    private final HashMap<Integer, IntegerNode> map = new HashMap<>();

    public IntegerGraph(IntUnaryOperator fn) {
        this.function = fn;
    }

    private class IntegerNode {

        private final int number;

        private Set<IntegerNode> previous = new HashSet<>();

        private IntegerNode next;

        private boolean hasNextAttached = false;

        void attachPrevious(IntegerNode node) {
            this.previous.add(node);
        }

        Set<IntegerNode> getPrevious() {
            return new HashSet<>(this.previous);
        }

        public IntegerNode(int n) {
            this.number = n;
        }

    }

}
