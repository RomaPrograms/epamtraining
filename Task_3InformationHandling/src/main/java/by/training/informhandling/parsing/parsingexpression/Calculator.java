package by.training.informhandling.parsing.parsingexpression;

import java.util.Stack;

public class Calculator {
    public static String ExpressionToRPN(String expr) {
        String current = "";
        Stack<String> stack = new Stack<>();
        int priority;
        int incrementation = 0;

        for (int i = 0; i < expr.length(); i++) {
            StringBuilder currentExpression = new StringBuilder();
            if (expr.charAt(i) == '<' || expr.charAt(i) == '>') {
                currentExpression.append(expr.charAt(i));
                if (expr.charAt(i + 2) == '<' || expr.charAt(i + 2) == '>') {
                   currentExpression.append(expr.charAt(i+1));
                    currentExpression.append(expr.charAt(i+2));
                   i += 2;
                } else {
                    currentExpression.append(expr.charAt(i+1));
                    i++;
                }
                priority = getP(currentExpression.toString());
            } else {
                currentExpression.append(expr.charAt(i));
                priority = getP(String.valueOf(expr.charAt(i)));
            }

            if (priority == 0) {
                current += currentExpression.toString();
            }
            if (priority == 1) {
                stack.push(currentExpression.toString());
            }
            if (priority == 2) {
                current += " ";

                while(!stack.empty()) {
                    if(getP(stack.peek()) >= priority) {
                        current += stack.pop();
                        current += " ";
                    } else {
                        break;
                    }
                }
                stack.push(currentExpression.toString());
            }
            if (priority == -1) {
                current += " ";
                while(getP(stack.peek()) != 1) {
                    current += stack.pop();
                    current += " ";
                }

                stack.pop();
            }
            if(priority == 3) {
                current += String.valueOf(expr.charAt(i + 1));
                current += " ";
                current += currentExpression.toString();
                i++;
            }
        }

        while (!stack.empty()) {
            current += " ";
            current += stack.pop();
        }
        return current;
    }

    private static int getP(String token) {
        if(token.equals("(")) {
            return 1;
        } else {
            if(token.equals(")")) {
                return -1;
            } else {
                if (token.equals("<<")||token.equals(">>")||token.equals(">>>")
                        ||token.equals("^")||token.equals("^")
                        ||token.equals("|")||token.equals("&")
                        ||token.equals("-")||token.equals("+")) {
                   return 2;
                } else {
                    if (token.equals("~")) {
                        return 3;
                    } else {
                        return 0;
                    }
                }
            }
        }

    }
}
