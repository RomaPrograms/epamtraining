package by.training.informhandling.parsing.parsingexpression.bitoperation;

import by.training.informhandling.parsing.parsingexpression.interpretationtorpn.Context;

public class TerminalExpressionAnd extends AbstractMathExpression {
    @Override
    public void interpret(Context c) {
        c.pushValue(c.popValue()&c.popValue());
    }
}
