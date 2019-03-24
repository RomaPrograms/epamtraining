package by.training.informhandling.parsing.parsingexpression.bitoperation;

import by.training.informhandling.parsing.parsingexpression
        .interpretationtorpn.Context;
/**
 * class for calculating operation with "LEFT SHIFTING".
 */
public class TerminalExpressionLeft extends AbstractMathExpression {
    /**
     * method calculate operation "LEFT SHIFTING".
     * @param numbers - stack with numbers
     */
    @Override
    public void interpret(final Context numbers) {
        int secondNumb = numbers.popValue();
        int firstNumb = numbers.popValue();
        numbers.pushValue(firstNumb << secondNumb);
    }
}
