package by.training.informhandling.parsing.parsingexpression.bitoperation;

import by.training.informhandling.parsing.parsingexpression
        .interpretationtorpn.Context;
import java.util.function.IntBinaryOperator;

/**
 * class for calculating operation with "EXCLUDING OR".
 */
public class TerminalExpressionExcludingOr extends AbstractMathExpression {
    /**
     * functional interface for operation "EXCLUDING OR".
     */
    private static IntBinaryOperator operationOr = (x, y) -> x ^ y;
    /**
     * method calculate operation "EXCLUDING OR".
     * @param numbers - stack with numbers
     */
    @Override
    public void interpret(final Context numbers) {
        int number = numbers.popValue();
        numbers.pushValue(operationOr.applyAsInt(number, numbers.popValue()));
    }
}
