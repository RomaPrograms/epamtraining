package by.training.informhandling.parsing.parsingexpression;

public class TerminalExpressionDoubleRight extends AbstractMathExpression {
    @Override
    public void interpret(Context c) {
        int secondNumb = c.popValue();
        int firstNumb = c.popValue();
        c.pushValue(firstNumb>>>secondNumb);
    }
}
