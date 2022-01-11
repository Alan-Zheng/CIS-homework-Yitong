package org.example.gui;

import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.EmptyStackException;

/**
 * The controller that controls the behaviour of the calculator.
 */
public class Controller {
    static String expression = "", result = "";

    public TextField field;

    /**
     * Called when a button is clicked.
     * The {@code Event} being passed in will be converted into a {@code Button},
     * and the method will respond based on the type of buttons:
     * <ul>
     *     <li>clear</li>
     *     <li>backspace</li>
     *     <li>equals</li>
     *     <li>other buttons -> add to {@code expression}</li>
     * </ul>
     *
     * @param event The event (button) being passed in.
     */
    public void onButtonClick (Event event) {
        Button button = (Button) event.getTarget();
        String text = button.getText();

        switch (text) {
            // Equals button
            case "=" -> {
                try {
                    result = Evaluator.evaluate();
                } catch (EmptyStackException e) {
                    result = "SYNTAX ERROR";
                }

                field.setText(result);
                expression = "";
                return;
            }
            // Clear button
            case Evaluator.CLEAR -> expression = "";
            // Backspace button
            case Evaluator.BACKSPACE -> {
                if (expression.length() > 0) // Check length to prevent exceptions.
                    expression = expression.substring(0, expression.length() - 1);
            }
            default -> expression += button.getText();
        }

        field.setText(expression);
    }
}