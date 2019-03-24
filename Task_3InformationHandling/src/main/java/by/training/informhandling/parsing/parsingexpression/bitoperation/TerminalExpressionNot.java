package by.training.informhandling.parsing.parsingexpression.bitoperation;

import by.training.informhandling.parsing.parsingexpression
        .interpretationtorpn.Context;

/**
 * class for calculating operation with "NOT".
 */
public class TerminalExpressionNot extends AbstractMathExpression {
    /**
     * method calculate operation "NOT".
     * @param numbers - stack with numbers
     */
    @Override
    public void interpret(final Context numbers) {
        numbers.pushValue(~numbers.popValue());
    }

}
