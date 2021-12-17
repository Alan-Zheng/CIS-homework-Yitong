package org.example.gui;

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
    // PEDMAS order
    private static final int HIGH = 3, MID = 2, LOW = 1, NONE = 0;

    private static LinkedList<String> expression = new LinkedList<>();
    private static Stack<String> operators = new Stack<>();

    /**
     * Determine whether the given parameter is a mathematical operator.
     *
     * @param token The token.
     * @return {@code true} if token is a valid operator, {@code false} otherwise.
     */
    private static boolean isOperator (String token) {
        return ops.contains(token);
    }

    /**
     * Determine the
     *
     * @param token The current token; must be an operator.
     * @param onStack The last operator that was pushed into {@code operators}.
     * @return {@code true} if
     */
    private static boolean pedmas (String token, String onStack) {
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

    public static int evaluate () {
        StringTokenizer st = new StringTokenizer(Controller.expression);

        while (st.hasMoreTokens()) {
            String token = st.nextToken();
        }

        return 0;
    }
}
