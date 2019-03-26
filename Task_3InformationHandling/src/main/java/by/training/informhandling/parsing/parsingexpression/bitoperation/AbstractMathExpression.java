package by.training.informhandling.parsing.parsingexpression.bitoperation;

import by.training.informhandling.parsing.parsingexpression
        .interpretationtorpn.Context;
/**
 * interface with common methods and variables for all operations.
 */
public interface AbstractMathExpression {
    /**
     * method which does some bit operation.
     * @param numbers - stack with numbers
     */
    void interpret(Context numbers);
}
