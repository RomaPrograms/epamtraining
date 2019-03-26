package by.training.informhandling.parsing.parsingexpression.bitoperation;

import by.training.informhandling.parsing.parsingexpression
        .interpretationtorpn.Context;
import java.util.function.IntBinaryOperator;

/**
 * class for calculating operation with "DOUBLE RIGHT SHIFTING".
 */
public class TerminalExpressionDoubleRight implements AbstractMathExpression {
    /**
     * functional interface for operation "DOUBLE RIGHT SHIFTING".
     */
    private static IntBinaryOperator operationRight = (x, y) -> x >>> y;
    /**
     * method calculates operation "DOUBLE RIGHT SHIFTING".
     * @param numbers - stack with numbers
     */
    @Override
    public void interpret(final Context numbers) {
        int secondNumb = numbers.popValue();
        int firstNumb = numbers.popValue();
        numbers.pushValue(operationRight.applyAsInt(firstNumb, secondNumb));
    }
}
