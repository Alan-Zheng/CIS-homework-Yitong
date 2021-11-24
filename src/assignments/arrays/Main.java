package assignments.arrays;

import java.util.Arrays;

public class Main {
    /**
     * Test the array by printing it out. For test purpose only.
     * @param array The array being printed out.
     */
    public static void testArray (int[] array) {
        Arrays.stream(array).forEach(x -> System.out.printf("%d ", x));
        System.out.println();
    }

    /**
     * The main method.
     * "Cheat ver." in Q1 & Q2 is for verifying the answer.
     * @param args Required.
     */
    public static void main (String[] args) {
        // First array
        int[] firstArray = new int[20];

        // Randomly assign value
        for (int i = 0; i < firstArray.length; i++)
            firstArray[i] = (int) Math.floor(Math.random() * 6) + 1;

        /* ANSWER FOR Q1 */
        // Standard ver.
        int count = 0;
        for (int x : firstArray)
            if (x == 1) count++;
        System.out.printf("There are %d 1s. Verify: ", count);

        // Cheat ver.
        System.out.println(Arrays.stream(firstArray).filter(x -> x == 1).count());
        /* ENDS */

        // Second array
        int[] secondArray = new int[10];

        // Randomly assign value
        for (int i = 0; i < secondArray.length; i++)
            secondArray[i] = (int) Math.floor(Math.random() * 100) + 1;

        /* ANSWER FOR Q2 */
        // Standard ver.
        int sum = 0;
        for (int x: secondArray)
            sum += x;
        System.out.printf("The average is %.1f. Verify: ", (double) sum / secondArray.length);

        // Cheat ver.
        System.out.println(Arrays.stream(secondArray).average().orElseThrow());
        /* ENDS */

        System.out.println("Second array:");
        testArray(secondArray);

        /* ANSWER FOR Q3 */
        int temp = secondArray[0];
        secondArray[0] = secondArray[secondArray.length - 1];
        secondArray[secondArray.length - 1] = temp;
        /* ENDS */

        System.out.println("After swap (first and last):");
        testArray(secondArray);

        /* ANSWER FOR Q4 */
        for (int i = 0; i < secondArray.length / 2; i++) {
            int tmp = secondArray[i];
            secondArray[i] = secondArray[secondArray.length - i - 1];
            secondArray[secondArray.length - i - 1] = tmp;
        }
        /* ENDS */

        System.out.println("After reversed:");
        testArray(secondArray);
    }
}
