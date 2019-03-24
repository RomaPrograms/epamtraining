package by.training.informhandling.parsing.parsingexpression.bitoperation;

import by.training.informhandling.parsing.parsingexpression
        .interpretationtorpn.Context;

/**
 * class for calculating operation with "DOUBLE RIGHT SHIFTING".
 */
public class TerminalExpressionDoubleRight extends AbstractMathExpression {
    /**
     * method calculates operation "AND".
     * @param numbers - stack with numbers
     */
    @Override
    public void interpret(final Context numbers) {
        int secondNumb = numbers.popValue();
        int firstNumb = numbers.popValue();
        numbers.pushValue(firstNumb >>> secondNumb);
    }
}
