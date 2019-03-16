package by.training.informhandling.parsing.parsingexpression.bitoperation;

import by.training.informhandling.parsing.parsingexpression.interpretationtorpn.Context;

public class TerminalExpressionLeft extends AbstractMathExpression {
    @Override
    public void interpret(Context c) {
        int secondNumb = c.popValue();
        int firstNumb = c.popValue();
        c.pushValue(firstNumb<<secondNumb);
    }
}
