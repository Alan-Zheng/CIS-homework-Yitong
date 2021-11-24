package assignments.loops;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * A basic rock-paper-scissors game.
 *
 * @author Yitong Zheng
 */
public class RockPaperScissors {
    /**
     * Generate a random choice among rock(1), paper(2), and scissors(3).
     *
     * @return The choice that has been generated.
     */
    public static int randomChoice () {
        double choice = Math.random() * 3; // Generate a random number between 0 and 3.

        return (int) Math.floor(choice) + 1;
    }


    /**
     * The main method that responsible for taking user input and calculate the result.
     *
     * @param args Required.
     */
    public static void main (String[] args) {
        // Variables setup
        Scanner scn = new Scanner(System.in);
        String[] match = {"rock", "paper", "scissor"};
        String startMsg = "Welcome to the rock-paper-scissors game!\n  1. rock  2. paper  3. scissor\n",
                quitMsg = "Thank you for playing.";

        while (true) { // Game loop
            // Start message
            System.out.println(startMsg);
            System.out.print("Ready? Rock paper scissors! ");

            int response;
            // Prevent non-numeric user input; anything other than a valid number means QUIT.
            try {
                response = scn.nextInt(); // Scan the user's choice
            } catch (InputMismatchException e) {
                System.err.println("\nPlease enter a valid number.");
                return;
            }

            // Validate user input
            if (response <= 0 || response > 3) {
                System.err.println("\nInvalid choice.");
                continue;
            }

            int computer = randomChoice(); // Get random choice

            if (response == computer) System.out.println("It's a tie!");
            // User win cases: rock vs. scissors; paper vs. rock; scissor vs. paper.
            else if ((response == 1 && computer == 3) || (response == 2 && computer == 1)
                    || (response == 3 && computer == 2))
                System.out.println("You win!");
            else System.out.println("You lose!");

            // Print the choices
            System.out.printf("\n----------\nYou: %s\n", match[response - 1]);
            System.out.printf("Computer: %s\n----------\n", match[computer - 1]);

            System.out.print("\nPlay again? ");
            String confirm = scn.next();

            // Quit message if the user not going to play anymore.
            if (!(confirm.equalsIgnoreCase("y") || confirm.equalsIgnoreCase("yes"))) {
                System.out.println(quitMsg);
                return;
            }

            System.out.println(); // Blank line
        }
    }
}
