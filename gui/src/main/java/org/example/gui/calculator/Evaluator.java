package org.example.gui.calculator;

import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * The evaluator that helps to evaluate the result of {@code Controller.expression}.
 */
public class Evaluator {
    public static final String BACKSPACE = "⌫", CLEAR = "C",
            MULTIPLY = "×", DIVIDE = "÷", PI = "π";
    private static final String ops = "^×÷+-";
    // PEMDAS order
    private static final int HIGH = 3, MID = 2, LOW = 1, NONE = 0;

    private static final LinkedList<String> expression = new LinkedList<>();
    // Responsible for storing operators in the first round and numbers in the second round.
    private static final Stack<String> stack = new Stack<>();

    /**
     * Try to convert the token to a {@code double}.
     *
     * @param token The token being tested.
     * @return {@code true} if the token can be parsed to a number, {@code false} otherwise.
     */
    private static boolean isNumeric (String token) {
        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Determine whether the given parameter is a mathematical operator.
     *
     * @param token The token.
     * @return {@code true} if token is a valid operator, {@code false} otherwise.
     */
    private static boolean isOperator (String token) {
        return ops.contains(token);
    }

    private static boolean isInteger (double num) {
        return num == (int) num;
    }

    /**
     * Determine the PEMDAS order of the expression.
     *
     * @param token The current token; must be an operator.
     * @param onStack The last operator that was pushed into {@code operators}.
     * @return {@code true} if the order of the current operator is smaller than the operator on stack, {@code false}
     * otherwise.
     */
    private static boolean pemdas (String token, String onStack) {
        int current = switch (token) {
            case "^" -> HIGH;
            case MULTIPLY, DIVIDE -> MID;
            case "+", "-" -> LOW;
            default -> NONE;
        },
        stacking = switch (onStack) {
            case "^" -> HIGH;
            case MULTIPLY, DIVIDE -> MID;
            case "+", "-" -> LOW;
            default -> NONE;
        };

        return current - stacking <= 0;
    }

    /**
     * Evaluate {@code Controller.expression}.
     * The method first changes the expression into a "postfix" form, which the order of operation can be performed.
     * It will then go through mathematical evaluation.
     *
     * @return The final result of the expression.
     */
    public static String evaluate () throws EmptyStackException {
        StringTokenizer st = new StringTokenizer(Controller.expression);

        while (st.hasMoreTokens()) {
            String token = st.nextToken();

            if (isOperator(token)) {
                while (!stack.empty() && !stack.peek().equals("(") && pemdas(token, stack.peek()))
                    expression.add(stack.pop());

                stack.push(token);
            } else if (isNumeric(token)) expression.add(token);
            else if (token.equals(PI)) expression.add(String.valueOf(Math.PI));
            else if (token.equals("e")) expression.add(String.valueOf(Math.E));
            else if (token.equals("(")) stack.push("(");
            else if (token.equals(")")) {
                do {
                    if (isOperator(stack.peek()))
                        expression.add(stack.pop());
                } while (!stack.peek().equals("("));

                if (stack.peek().equals("(")) stack.pop();
            }
        }

        while (!stack.empty()) expression.add(stack.pop());

        while (!expression.isEmpty()) {
            if (isNumeric(expression.peek())) stack.push(expression.pop());
            else {
                String op = expression.pop();
                double b = Double.parseDouble(stack.pop()),
                        a = Double.parseDouble(stack.pop());

                double tmpAns = switch (op) {
                    case "+" -> a + b;
                    case "-" -> a - b;
                    case MULTIPLY -> a * b;
                    case DIVIDE -> a / b;
                    case "^" -> Math.pow(a, b);
                    default -> 0;
                };

                if (isInteger(tmpAns))
                    stack.push(String.valueOf((int) tmpAns));
                else
                    stack.push(String.valueOf(tmpAns));
            }
        }

        return stack.pop();
    }
}
