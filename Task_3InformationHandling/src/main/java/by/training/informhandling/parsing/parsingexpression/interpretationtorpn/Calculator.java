package by.training.informhandling.parsing
        .parsingexpression.interpretationtorpn;

import java.util.Stack;

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
    private Calculator() { }

    /**
     * method translate our expression to RPN.
     * @param expr - normal view of expression
     * @return expression in RPN
     */
    public static String expressionToRPN(final String expr) {
        StringBuilder finalExpressionInRPN = new StringBuilder(" ");
        Stack<String> stack = new Stack<>();
        int priority;

        for (int i = 0; i < expr.length(); i++) {
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

            if (priority == 0) {
                finalExpressionInRPN.append(currentExpression.toString());
            }
            if (priority == 1) {
                stack.push(currentExpression.toString());
            }
            if (priority == 2) {
                finalExpressionInRPN.append(" ");

                while (!stack.empty()) {
                    if (getPriority(stack.peek()) >= priority) {
                        finalExpressionInRPN.append(stack.pop());
                        finalExpressionInRPN.append(" ");
                    } else {
                        break;
                    }
                }
                stack.push(currentExpression.toString());
            }
            if (priority == -1) {
                finalExpressionInRPN.append(" ");
                while (getPriority(stack.peek()) != 1) {
                    finalExpressionInRPN.append(stack.pop());
                    finalExpressionInRPN.append(" ");
                }

                stack.pop();
            }
            if (priority == PRIORITY_FOR_OPERATION_NOT) {
                finalExpressionInRPN.append(String.valueOf(expr.charAt(i + 1)));
                finalExpressionInRPN.append(" ");
                finalExpressionInRPN.append(currentExpression.toString());
                i++;
            }
        }

        while (!stack.empty()) {
            finalExpressionInRPN.append(" ");
            finalExpressionInRPN.append(stack.pop());
        }
        return finalExpressionInRPN.toString();
    }

    /**
     * method which gives some priority to every element of expression.
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
