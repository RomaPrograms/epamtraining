package by.training.informhandling.parsing.parsingexpression
        .interpretationtorpn;

import by.training.informhandling.parsing.parsingexpression.bitoperation.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {
    private ArrayList<AbstractMathExpression> listExpression;
    public Client(String expression) {
        listExpression = new ArrayList<>();
        parse(expression);
    }
    private void parse(String expression) { // синтаксический анализ
        for (String lexeme : expression.split("\\p{Blank}+")) {
            if (lexeme.isEmpty()) {
                continue;
            }
            char temp = lexeme.charAt(0);
            switch (temp) {
                case '&':
                    listExpression.add(new TerminalExpressionAnd());
                    break;
                case '|':
                    listExpression.add(new TerminalExpressionOr());
                    break;
                case '^':
                    listExpression.add(new TerminalExpressionExcludeOr());
                    break;
                case '>':
                    if (lexeme.length() == 3) {
                        listExpression.add(new TerminalExpressionDoubleRight());
                    } else {
                        listExpression.add(new TerminalExpressionRight());
                    }
                    break;
                case '<':
                    listExpression.add(new TerminalExpressionLeft());
                    break;
                case '~':
                    listExpression.add(new TerminalExpressionNot());
                    break;
                default:
                    Scanner scan = new Scanner(lexeme);
                    if (scan.hasNextInt()) {
                        listExpression.add(new
                                NonTerminalExpressionNumber(scan.nextInt()));
                    }
            }
        }
    }

    public Number calculate() {
        Context context = new Context();
        // выполнение простых задач и сборка результата
        for (AbstractMathExpression terminal : listExpression) {
            terminal.interpret(context);
        }
        return context.popValue();
    }
}
