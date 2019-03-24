package by.training.informhandling.parsing.parsingexpression.bitoperation;

import by.training.informhandling.parsing.parsingexpression
        .interpretationtorpn.Context;
/**
 * class for pushing number to stack.
 */
public class NonTerminalExpressionNumber extends AbstractMathExpression {
    /**
     * number.
     */
    private int number;

    /**
     * constructor with one parameter.
     * @param curNumber - number for pushing to stack
     */
    public NonTerminalExpressionNumber(final int curNumber) {
        this.number = curNumber;
    }

    /**
     * method pushes number to stack.
     * @param numbers - stack with numbers
     */
    @Override
    public void interpret(final Context numbers) {
        numbers.pushValue(number);
    }
}
