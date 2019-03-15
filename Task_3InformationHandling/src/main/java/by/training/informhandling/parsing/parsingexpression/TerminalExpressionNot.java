package by.training.informhandling.parsing.parsingexpression;

public class TerminalExpressionNot extends AbstractMathExpression {
    @Override
    public void interpret(Context c) {
        c.pushValue(~c.popValue());
    }

}
