package assignments.no7arraymethods;

import java.util.Arrays;
import java.util.Objects;

public class MyArrayList {
    static Integer[] array = new Integer[10];

    /**
     * Double the length of the array.
     */
    private static void allocArray () {
        Integer[] temp = new Integer[array.length * 2];
        for (int i = 0; i < array.length; i++)
            temp[i] = array[i];

        array = temp.clone();
    }

    /**
     * Add a value to the end of the array.
     * @param value The value to be added.
     */
    public static void add (int value) {
        // Check available space
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) {
                array[i] = value;
                return;
            }
        }

        // Copy previous array
        allocArray();
        array[array.length / 2] = value;
    }

    /**
     * Delete the last element from the array.
     */
    public static void pop () {
        int countNull = 0;

        for (int i = array.length - 1; i >= 0; i--) {
            if (array[i] != null) {
                array[i] = null;

                // Check for space occupation
                if (++countNull >= array.length / 2) {
                    Integer[] temp = new Integer[array.length / 2];
                    for (int j = 0; j < temp.length; j++)
                        temp[j] = array[j];

                    array = temp.clone();
                }

                return;
            }

            countNull++;
        }
    }

    /**
     * Insert a value to the inputted index.
     * @param index The index where the element will be.
     * @param value The value being inserted.
     */
    public static void insert (int index, int value) {
        // Check available space
        boolean isFull = true;
        for (Integer x : array) {
            if (x == null) {
                isFull = false;
                break;
            }
        }

        if (isFull) allocArray();
        // Shift array
        for (int i = array.length - 2; i >= index; i--)
            array[i + 1] = array[i];

        array[index] = value;
    }

    /**
     * Print the array as a string.
     * For test purpose.
     */
    public static void printStr (String msg) {
        System.out.println(msg);
        Arrays.stream(array).filter(Objects::nonNull).forEach(x -> System.out.printf("%d ", x));
        System.out.println('\n');
    }

    public static void main (String[] args) {
        // TEST: add 15 elements
        for (int i = 0; i < 15; i++)
            add((int) Math.floor(Math.random() * 1000));
        printStr("Added 15 elem:");

        // TEST: pop 6 elements
        for (int i = 0; i < 6; i++) pop();
        printStr("Popped 6 elem:");

        // TEST: insert 2 elements
        insert(2, 2147483647);
        insert(7, 2147483647);
        printStr("Insert 2 elem @ind2&7:");
    }
}
