package by.training.informhandling.parsing.parsingexpression.bitoperation;

import by.training.informhandling.parsing.parsingexpression
        .interpretationtorpn.Context;
import java.util.function.IntUnaryOperator;

/**
 * class for calculating operation with "NOT".
 */
public class TerminalExpressionNot extends AbstractMathExpression {
    /**
     * functional interface for operation "NOT".
     */
    private static IntUnaryOperator operationNot = x -> ~x;
    /**
     * method calculate operation "NOT".
     * @param numbers - stack with numbers
     */
    @Override
    public void interpret(final Context numbers) {
        numbers.pushValue(operationNot.applyAsInt(numbers.popValue()));
    }

}
