package assignments.no4ifelseif;

import java.util.Scanner;

public class Assignment3 {
    public static void main (String[] args) {
        double[] gravity = {0.78, 0.39, 2.65, 1.17, 1.05, 1.23};

        Scanner scn = new Scanner(System.in);

        System.out.print("Please enter your current earth weight: ");
        int weight = scn.nextInt();

        System.out.println("""
                                
                I have information for the following planets:
                    1. Venus   2. Mars    3. Jupiter
                    4. Saturn  5. Uranus  6. Neptune
                """);

        System.out.print("Which planet are you visiting? ");
        int choice = scn.nextInt();

        System.out.printf("Your weight would be %.2f pounds on that planet.\n", weight * gravity[choice - 1]);
    }
}
