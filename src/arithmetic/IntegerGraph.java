package arithmetic;

//import collections.immutable.Range;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.IntUnaryOperator;

public class IntegerGraph {

    public static final int GENERAL_ITERATION_MAXIMUM = 64;

    private final IntUnaryOperator function;

    private final HashMap<Integer, IntegerNode> map = new HashMap<>();

    private IntegerNode retrieveNode(int n) {
        if (this.map.containsKey(n)) {
            return this.map.get(n);
        } else {
            IntegerNode node = new IntegerNode(n);
            this.map.put(n, node);
            return node;
        }
    }

    // TODO: Write tests for this
    public Set<Integer> precursors(int n) {
        return new HashSet<>();
    }

    // TODO: Write tests for this
    public int successor(int n) {
        return Integer.MIN_VALUE;
    }

//    public void scan(Range range) {
//        // TODO: Write tests for this
//    }

    // TODO: Write tests for this
    public Optional<List<Integer>> path(int from) {
        return this.path(from, 1);
    }

    // TODO: Write tests for this
    public Optional<List<Integer>> path(int from, int to) {
        List<Integer> list = new ArrayList<>();
        return Optional.of(list);
    }

    // TODO: Write tests for this
    // TODO: Write tests for this


    public IntegerGraph(IntUnaryOperator fn) {
        this.function = fn;
    }

    private class IntegerNode {

        private final int number;

        private final Set<IntegerNode> previous = new HashSet<>();

        private IntegerNode next;

        private boolean hasNextAttached = false;

        void attachPrevious(IntegerNode node) {
            this.previous.add(node);
        }

        Set<IntegerNode> getPrevious() {
            return new HashSet<>(this.previous);
        }

        void attachNext(IntegerNode node) {
            if (this.hasNextAttached) {
                String excMsg = "This node already has a next node attached";
                throw new IllegalStateException(excMsg);
            }
            this.next = node;
            this.hasNextAttached = true;
            this.next.attachPrevious(this);
        }

        IntegerNode getNext() {
            if (this.hasNextAttached) {
                return this.next;
            } else {
                int successor
                        = IntegerGraph.this.function.applyAsInt(this.number);
                IntegerNode successorNode
                        = IntegerGraph.this.retrieveNode(successor);
                this.attachNext(successorNode);
                return successorNode;
            }
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || this.getClass() != obj.getClass()) return false;
            IntegerNode other = (IntegerNode) obj;
            return this.number == other.number;
        }

        @Override
        public int hashCode() {
            return this.number;
        }

        private IntegerNode(int n) {
            this.number = n;
        }

    }

}
