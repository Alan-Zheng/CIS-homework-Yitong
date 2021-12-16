package no4ifelseif;

import java.util.Scanner;

public class Assignment2 {
    public static void main (String[] args) {
        Scanner scn = new Scanner(System.in);

        System.out.print("Hey, what's your name? ");
        String name = scn.nextLine();

        System.out.printf("OK %s, how old are you? ", name);
        int age = scn.nextInt();

        String message;
        if (age < 0 || age > 130) message = "It's not your true age";
        else if (age < 16) message = "You can't drive";
        else if (age <= 17) message = "You can drive but not vote";
        else if (age <= 24) message = "You can vote but not rent a car";
        else message = "You can do pretty much anything";

        System.out.printf("\n%s, %s.\n", message, name);
    }
}
