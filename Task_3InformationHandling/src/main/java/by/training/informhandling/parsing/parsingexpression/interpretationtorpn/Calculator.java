package by.training.informhandling.parsing
        .parsingexpression.interpretationtorpn;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * class translates our bit expression to RPN.
 */
public final class Calculator {

    /**
     * priority for operation "not".
     */
    private static final int PRIORITY_FOR_OPERATION_NOT = 3;

    /**
     * private constructor without parameters.
     */
    private Calculator() {
    }

    /**
     * method translate our expression to RPN.
     *
     * @param expr - normal view of expression
     * @return expression in RPN
     */
    public static String expressionToRPN(final String expr) {
        StringBuilder finalExpressionInRPN = new StringBuilder(" ");
        Deque<String> deque = new ArrayDeque<>();
        int priority;
        int i = 0;

        while (i != expr.length()) {
            StringBuilder currentExpression = new StringBuilder();
            if (expr.charAt(i) == '<' || expr.charAt(i) == '>') {
                currentExpression.append(expr.charAt(i));
                if (expr.charAt(i + 2) == '<' || expr.charAt(i + 2) == '>') {
                    currentExpression.append(expr.charAt(i + 1));
                    currentExpression.append(expr.charAt(i + 2));
                    i += 2;
                } else {
                    currentExpression.append(expr.charAt(i + 1));
                    i++;
                }
                priority = getPriority(currentExpression.toString());
            } else {
                currentExpression.append(expr.charAt(i));
                priority = getPriority(String.valueOf(expr.charAt(i)));
            }

            switch (priority) {
                case 0:
                    finalExpressionInRPN.append(currentExpression.toString());
                    break;
                case 1:
                    deque.push(currentExpression.toString());
                    break;
                case 2:
                    finalExpressionInRPN.append(" ");

                    while (!deque.isEmpty()) {
                        if (getPriority(deque.peek()) >= priority) {
                            finalExpressionInRPN.append(deque.pop());
                            finalExpressionInRPN.append(" ");
                        } else {
                            break;
                        }
                    }
                    deque.push(currentExpression.toString());

                    break;
                case -1:
                    finalExpressionInRPN.append(" ");
                    while (getPriority(deque.peek()) != 1) {
                        finalExpressionInRPN.append(deque.pop());
                        finalExpressionInRPN.append(" ");
                    }

                    deque.pop();
                    break;
                default:
                    finalExpressionInRPN.append(String
                            .valueOf(expr.charAt(i + 1)));
                    finalExpressionInRPN.append(" ");
                    finalExpressionInRPN.append(currentExpression.toString());
                    i++;
            }
            i++;
        }

        while (!deque.isEmpty()) {
            finalExpressionInRPN.append(" ");
            finalExpressionInRPN.append(deque.pop());
        }
        return finalExpressionInRPN.toString();
    }
    /**
     * method which gives some priority to every element of expression.
     *
     * @param token - some element of expression
     * @return priority of element
     */
    private static int getPriority(final String token) {
        if (token.equals("(")) {
            return 1;
        } else {
            if (token.equals(")")) {
                return -1;
            } else {
                if (token.equals("<<") || token.equals(">>")
                        || token.equals(">>>") || token.equals("^")
                        || token.equals("|") || token.equals("&")
                        || token.equals("-") || token.equals("+")) {
                    return 2;
                } else {
                    if (token.equals("~")) {
                        return PRIORITY_FOR_OPERATION_NOT;
                    } else {
                        return 0;
                    }
                }
            }
        }

    }
}
