package by.training.informhandling.parsing.parsingexpression.bitoperation;

import by.training.informhandling.parsing.parsingexpression
        .interpretationtorpn.Context;

/**
 * class for calculating operation "OR".
 */
public class TerminalExpressionOr extends AbstractMathExpression {
    /**
     * method calculate operation "OR".
     * @param numbers - stack with numbers
     */
    @Override
    public void interpret(final Context numbers) {
        int number = numbers.popValue();
        numbers.pushValue(number | numbers.popValue());
    }
}
