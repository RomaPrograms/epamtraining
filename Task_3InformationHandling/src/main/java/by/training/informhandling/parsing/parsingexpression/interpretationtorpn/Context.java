package by.training.informhandling.parsing
        .parsingexpression.interpretationtorpn;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * class which contain result after every bit operation.
 */
public class Context {
    /**
     * deque with results of every bit operation.
     */
    private Deque<Integer> contextValues = new ArrayDeque<>();

    /**
     * methods takes result of operation from deque.
     * @return result of some bit operation
     */
    public Integer popValue() {
        return contextValues.pop();
    }

    /**
     * methods pushes result of operation to deque.
     * @param value - result of operation
     */
    public void pushValue(final Integer value) {
        this.contextValues.push(value);
    }
}
