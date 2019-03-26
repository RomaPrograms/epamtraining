package by.training.informhandling.parsing.parsingexpression.bitoperation;

import by.training.informhandling.parsing.parsingexpression
        .interpretationtorpn.Context;

import java.util.function.IntBinaryOperator;

/**
 * class for calculating operation with "LEFT SHIFTING".
 */
public class TerminalExpressionLeft implements AbstractMathExpression {
    /**
     * functional interface for operation "LEFT SHIFTING".
     */
    private static IntBinaryOperator operationLeft = (x, y) -> x << y;
    /**
     * method calculate operation "LEFT SHIFTING".
     * @param numbers - stack with numbers
     */
    @Override
    public void interpret(final Context numbers) {
        int secondNumb = numbers.popValue();
        int firstNumb = numbers.popValue();
        numbers.pushValue(operationLeft.applyAsInt(firstNumb, secondNumb));
    }
}
