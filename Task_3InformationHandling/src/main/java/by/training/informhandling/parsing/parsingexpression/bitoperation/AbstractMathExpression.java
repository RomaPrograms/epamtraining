package by.training.informhandling.parsing.parsingexpression.bitoperation;

import by.training.informhandling.parsing.parsingexpression
        .interpretationtorpn.Context;
/**
 * class with common methods and variables for all operations.
 */
public abstract class AbstractMathExpression {
    /**
     * method which does some bit operation.
     * @param numbers - stack with numbers
     */
    public abstract void interpret(Context numbers);
}
