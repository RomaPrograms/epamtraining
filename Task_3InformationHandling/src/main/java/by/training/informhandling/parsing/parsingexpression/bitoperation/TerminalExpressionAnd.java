package by.training.informhandling.parsing.parsingexpression.bitoperation;

import by.training.informhandling.parsing.parsingexpression
        .interpretationtorpn.Context;
import java.util.function.IntBinaryOperator;

/**
 * class for calculating operation with "AND".
 */
public class TerminalExpressionAnd extends AbstractMathExpression {
    /**
     * functional interface for operation AND.
     */
    private static IntBinaryOperator operationAnd = (x, y) -> x & y;
    /**
     * method calculates operation "AND".
     * @param numbers - stack with numbers
     */
    @Override
    public void interpret(final Context numbers) {
        int number = numbers.popValue();
        numbers.pushValue(operationAnd.applyAsInt(number, numbers.popValue()));
    }
}
