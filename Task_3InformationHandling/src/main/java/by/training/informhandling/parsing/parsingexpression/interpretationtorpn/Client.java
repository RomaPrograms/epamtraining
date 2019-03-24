package by.training.informhandling.parsing.parsingexpression
        .interpretationtorpn;

import by.training.informhandling.actions.TextAction;
import by.training.informhandling.parsing.parsingexpression.bitoperation
        .AbstractMathExpression;
import by.training.informhandling.parsing.parsingexpression.bitoperation
        .TerminalExpressionAnd;
import by.training.informhandling.parsing.parsingexpression.bitoperation
        .TerminalExpressionOr;
import by.training.informhandling.parsing.parsingexpression.bitoperation
        .TerminalExpressionExcludeOr;
import by.training.informhandling.parsing.parsingexpression.bitoperation
        .TerminalExpressionDoubleRight;
import by.training.informhandling.parsing.parsingexpression.bitoperation
        .TerminalExpressionLeft;
import by.training.informhandling.parsing.parsingexpression.bitoperation
        .TerminalExpressionRight;
import by.training.informhandling.parsing.parsingexpression.bitoperation
        .NonTerminalExpressionNumber;
import by.training.informhandling.parsing.parsingexpression.bitoperation
        .TerminalExpressionNot;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * class for calculating expression in RPN.
 */
public class Client {
    /**
     * Logger for creation notes to some appender.
     */
    private static final Logger LOGGER = LogManager.getLogger(Client.class);

    /**
     * length of operation >>> in text view.
     */
    private static final int
            LENGTH_TOKEN_OPERATION_DOUBLE_RIGHT_SHIFTING = 3;
    /**
     * list with expressions.
     */
    private ArrayList<AbstractMathExpression> listExpression;

    /**
     * constructor with single parameter.
     * @param expression - expression in RPN
     */
    public Client(final String expression) {
        listExpression = new ArrayList<>();
        parse(expression);
    }

    /**
     * method for calculating expression in RPN which makes
     * arrayList of operations.
     * @param expression - expression in RPN
     */
    private void parse(final String expression) {
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
                    if (lexeme.length()
                            == LENGTH_TOKEN_OPERATION_DOUBLE_RIGHT_SHIFTING) {
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
                    scan.close();
            }
        }
    }

    /**
     * method for calculating expression.
     * @return number calculated from RPN
     */
    public int calculate() {
        Context context = new Context();

        for (AbstractMathExpression terminal : listExpression) {
            terminal.interpret(context);
        }

        LOGGER.info("Bit expression were calculated successfully.");
        return context.popValue();
    }
}
