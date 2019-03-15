package by.training.informhandling.parsing.parsingexpression;

public class TerminalExpressionExcludeOr extends AbstractMathExpression {
    @Override
    public void interpret(Context c) {
        c.pushValue(c.popValue()^c.popValue());
    }
}
