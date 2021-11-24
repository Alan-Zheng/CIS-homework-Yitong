package assignments.ifstatement;

import java.util.Scanner;

public class Quiz {
    static Scanner scn;
    static int score = 0;

    public static void question (String prompt, int correct, String wrongMsg) {
        System.out.println(prompt);

        System.out.print("> ");
        int response = scn.nextInt();

        if (response == correct) {
            score++;
            System.out.println("\nCorrect!");
        } else System.out.println('\n' + wrongMsg);
    }

    public static void main (String[] args) {
        scn = new Scanner(System.in);

        System.out.print("Are you ready for a quiz? ");
        String confirm = scn.next();
        if (confirm.equalsIgnoreCase("y") || confirm.equalsIgnoreCase("yes"))
            System.out.println("Okay!");
        else return;

        String prompt = """
                
                Q1) What is the super class of all classes in Java?
                    1) Lang
                    2) Object
                    3) Java
                """;
        question(prompt, 2, "Object is the base class for all classes.");

        prompt = """
                
                Q2) How many bytes does a "int" in Java have?
                    1) 4
                    2) 1
                    3) 8
                """;
        question(prompt, 1, "int is 4 bytes (32 bits).");

        prompt = """
                
                Q3) What is the result of doing "int x = 2147483648;"?
                    1) InputMismatchException
                    2) NumberFormatException
                    3) Compile error
                """;
        question(prompt, 3, "The compiler will give an error.");

        System.out.printf("\n\nYou got %d out of 3.\nThank you for playing.\n", score);
    }
}
